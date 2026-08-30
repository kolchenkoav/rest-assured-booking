package specs;

import config.BookingConfig;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RequestSpecs {

    /**
     * Базовая спецификация запроса: baseUri + JSON (Content-Type, Accept).
     * Применяется ко всем эндпоинтам, включая POST /auth.
     *
     * Accept задаётся строкой "application/json", а НЕ ContentType.JSON:
     * ContentType.JSON разворачивается в "application/json, application/javascript,
     * text/javascript, text/json", и анти-бот restful-booker отвечает 418 I'm a Teapot.
     */
    public static final RequestSpecification BASE_SPEC = new RequestSpecBuilder()
            .setBaseUri(BookingConfig.BASE_URI)
            .setContentType(ContentType.JSON)
            .setAccept("application/json")
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
