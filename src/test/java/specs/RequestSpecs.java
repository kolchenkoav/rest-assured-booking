package specs;

import config.BookingConfig;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RequestSpecs {

    /**
     * Базовая спецификация запроса: baseUri + JSON (Content-Type, Accept).
     * Применяется ко всем эндпоинтам, включая POST /auth.
     */
    public static final RequestSpecification BASE_SPEC = new RequestSpecBuilder()
            .setBaseUri(BookingConfig.BASE_URI)
            .setContentType(ContentType.JSON)
            .setAccept(ContentType.JSON)
            .build();

    /**
     * Спецификация с авторизацией Cookie: token=<value> — для PUT/PATCH/DELETE /booking.
     * Строится от BASE_SPEC, поэтому baseUri и JSON-заголовки наследуются.
     *
     * @param token значение токена из POST /auth (см. BaseTest.authToken)
     */
    public static RequestSpecification authSpec(String token) {
        return new RequestSpecBuilder()
                .addRequestSpecification(BASE_SPEC)
                .addCookie("token", token)
                .build();
    }
}
