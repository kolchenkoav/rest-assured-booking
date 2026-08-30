package clients;

import config.BookingConfig;
import specs.RequestSpecs;
import specs.ResponseSpecs;

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
                .spec(RequestSpecs.BASE_SPEC)
                .body(Map.of(
                        "username", BookingConfig.USERNAME,
                        "password", BookingConfig.PASSWORD
                ))
                .when()
                .post("/auth")
                .then()
                .spec(ResponseSpecs.OK_JSON)
                .extract()
                .jsonPath()
                .getString("token");
    }
}
