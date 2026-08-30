package clients;

import config.BookingConfig;
import io.restassured.http.ContentType;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class AuthClient {

    /**
     * Создаёт auth-токен через POST /auth.
     * Токен даёт доступ к PUT/DELETE /booking и передаётся как Cookie: token=<value>.
     *
     * @return значение токена, например "abc123"
     */
    public static String createToken() {
        return given()
                .baseUri(BookingConfig.BASE_URI)
                .contentType(ContentType.JSON)
                .body(Map.of(
                        "username", BookingConfig.USERNAME,
                        "password", BookingConfig.PASSWORD
                ))
                .when()
                .post("/auth")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getString("token");
    }
}
