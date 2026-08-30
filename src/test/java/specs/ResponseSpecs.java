package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ResponseSpecs {

    /**
     * 200 + JSON + схема токена: POST /auth ({"token": "..."}).
     */
    public static final ResponseSpecification OK_TOKEN_JSON = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectContentType(ContentType.JSON)
            .expectBody(matchesJsonSchemaInClasspath("schemas/token.schema.json"))
            .build();

    /**
     * 200 + JSON + схема плоской брони: GET / PUT / PATCH /booking/{id}.
     * additionalneeds в схеме не обязателен (по документации API).
     */
    public static final ResponseSpecification OK_BOOKING_JSON = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectContentType(ContentType.JSON)
            .expectBody(matchesJsonSchemaInClasspath("schemas/booking.schema.json"))
            .build();

    /**
     * 200 + JSON + схема обёртки: POST /booking ({"bookingid": ..., "booking": {...}}).
     */
    public static final ResponseSpecification OK_CREATED_BOOKING_JSON = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectContentType(ContentType.JSON)
            .expectBody(matchesJsonSchemaInClasspath("schemas/created-booking.schema.json"))
            .build();

    /**
     * 201: DELETE /booking/{id}. Тело ответа — строка "Created", Content-Type не JSON,
     * поэтому проверка контента здесь не задаётся.
     */
    public static final ResponseSpecification CREATED = new ResponseSpecBuilder()
            .expectStatusCode(201)
            .build();

    /**
     * 404: GET /booking/{id} несуществующей (в т.ч. удалённой) брони.
     * В doc/Restful-booker-API.md код не документирован — зафиксирован живым поведением API
     * (тело "Not Found", Content-Type не JSON — контент не проверяем).
     */
    public static final ResponseSpecification NOT_FOUND = new ResponseSpecBuilder()
            .expectStatusCode(404)
            .build();
}
