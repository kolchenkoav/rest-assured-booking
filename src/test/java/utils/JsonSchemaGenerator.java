package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Одноразовая утилита: генерирует JSON Schema (Draft-07) из JSON-образцов
 * src/test/resources/schemas/*-sample.json, рядом пишет *.schema.json.
 *
 * Запуск (pom не меняется):
 * mvn test-compile
 * mvn org.codehaus.mojo:exec-maven-plugin:3.1.0:java -Dexec.mainClass=utils.JsonSchemaGenerator -Dexec.classpathScope=test
 *
 * Правила инференса:
 * - required = ключи, присутствующие во всех одноимённых объектах массива
 *   (у одиночного объекта — все его ключи);
 * - строки вида YYYY-MM-DD получают "format": "date";
 * - additionalProperties = true — API может расширять ответы, схемы не должны ломаться.
 *
 * ВНИМАНИЕ: из одного образца опциональность полей не выводится — после генерации
 * сверяйте required с документацией API и правьте вручную (например, additionalneeds
 * в booking.schema.json / created-booking.schema.json не обязателен).
 */
public class JsonSchemaGenerator {

    private static final Pattern DATE_PATTERN = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final Path SCHEMAS_DIR = Path.of("src", "test", "resources", "schemas");

    public static void main(String[] args) throws Exception {
        List<Path> samples;
        try (var files = Files.list(SCHEMAS_DIR)) {
            samples = files
                    .filter(p -> p.getFileName().toString().endsWith("-sample.json"))
                    .sorted()
                    .toList();
        }
        for (Path sample : samples) {
            String fileName = sample.getFileName().toString();
            JsonNode sampleJson = MAPPER.readTree(sample.toFile());

            ObjectNode root = MAPPER.createObjectNode();
            root.put("$schema", "http://json-schema.org/draft-07/schema#");
            root.put("title", titleFrom(fileName));
            root.setAll(schemaFor(sampleJson));

            Path out = SCHEMAS_DIR.resolve(fileName.replace("-sample.json", ".schema.json"));
            MAPPER.writerWithDefaultPrettyPrinter().writeValue(out.toFile(), root);
            System.out.println("Generated " + out);
        }
    }

    private static String titleFrom(String fileName) {
        String stem = fileName.replace("-sample.json", "");
        String[] words = stem.split("-");
        StringBuilder title = new StringBuilder();
        for (String word : words) {
            if (!word.isBlank()) {
                title.append(Character.toUpperCase(word.charAt(0)))
                        .append(word.substring(1))
                        .append(' ');
            }
        }
        return title.toString().trim();
    }

    /** Строит подсхему для узла JSON-образца. */
    private static ObjectNode schemaFor(JsonNode node) {
        ObjectNode schema = MAPPER.createObjectNode();
        switch (node.getNodeType()) {
            case OBJECT -> {
                schema.put("type", "object");
                ObjectNode properties = schema.putObject("properties");
                ArrayNode required = schema.putArray("required");
                Iterator<Map.Entry<String, JsonNode>> fields = node.fields();
                while (fields.hasNext()) {
                    Map.Entry<String, JsonNode> field = fields.next();
                    properties.set(field.getKey(), schemaFor(field.getValue()));
                    required.add(field.getKey());
                }
                schema.put("additionalProperties", true);
            }
            case ARRAY -> {
                schema.put("type", "array");
                ObjectNode itemsSchema = null;
                for (JsonNode element : node) {
                    ObjectNode elementSchema = schemaFor(element);
                    itemsSchema = itemsSchema == null ? elementSchema : mergeSchemas(itemsSchema, elementSchema);
                }
                schema.set("items", itemsSchema == null ? MAPPER.createObjectNode() : itemsSchema);
            }
            case STRING -> {
                schema.put("type", "string");
                if (DATE_PATTERN.matcher(node.asText()).matches()) {
                    schema.put("format", "date");
                }
            }
            case NUMBER -> schema.put("type", node.isIntegralNumber() ? "integer" : "number");
            case BOOLEAN -> schema.put("type", "boolean");
            default -> {
                // null и прочее — без ограничений
            }
        }
        return schema;
    }

    /**
     * Мержит схемы элементов массива: properties — объединение,
     * required — пересечение (в порядке первой схемы), additionalProperties — true.
     */
    private static ObjectNode mergeSchemas(ObjectNode a, ObjectNode b) {
        boolean bothObjects = "object".equals(a.path("type").asText())
                && "object".equals(b.path("type").asText());
        if (!bothObjects) {
            return a.path("type").asText().equals(b.path("type").asText())
                    ? a
                    : MAPPER.createObjectNode();
        }

        ObjectNode merged = MAPPER.createObjectNode();
        merged.put("type", "object");

        ObjectNode properties = merged.putObject("properties");
        a.path("properties").fields()
                .forEachRemaining(field -> properties.set(field.getKey(), field.getValue()));
        b.path("properties").fields().forEachRemaining(field -> {
            JsonNode existing = properties.get(field.getKey());
            if (existing == null) {
                properties.set(field.getKey(), field.getValue());
            } else if (existing.isObject() && field.getValue().isObject()) {
                properties.set(field.getKey(),
                        mergeSchemas((ObjectNode) existing, (ObjectNode) field.getValue()));
            }
        });

        Set<String> bRequired = new HashSet<>();
        b.path("required").forEach(node -> bRequired.add(node.asText()));
        ArrayNode required = merged.putArray("required");
        List<String> kept = new ArrayList<>();
        a.path("required").forEach(node -> {
            if (bRequired.contains(node.asText())) {
                kept.add(node.asText());
            }
        });
        kept.forEach(required::add);

        merged.put("additionalProperties", true);
        return merged;
    }
}
