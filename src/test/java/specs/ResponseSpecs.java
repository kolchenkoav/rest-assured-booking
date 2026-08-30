package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;

public class ResponseSpecs {

    /**
     * 200 + JSON: GET /booking, GET /booking/{id}, POST /booking, PUT/PATCH /booking/{id}, POST /auth.
     */
    public static final ResponseSpecification OK_JSON = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectContentType(ContentType.JSON)
            .build();

    /**
     * 201: DELETE /booking/{id}. Тело ответа — строка "Created", Content-Type не JSON,
     * поэтому проверка контента здесь не задаётся.
     */
    public static final ResponseSpecification CREATED = new ResponseSpecBuilder()
            .expectStatusCode(201)
            .build();
}
