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

    /**
     * 404: GET /booking/{id} несуществующей (в т.ч. удалённой) брони.
     * В doc/Restful-booker-API.md код не документирован — зафиксирован живым поведением API
     * (тело "Not Found", Content-Type не JSON — контент не проверяем).
     */
    public static final ResponseSpecification NOT_FOUND = new ResponseSpecBuilder()
            .expectStatusCode(404)
            .build();
}
