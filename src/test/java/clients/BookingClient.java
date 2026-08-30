package clients;

import dto.BookDTO;
import io.restassured.response.ValidatableResponse;
import specs.RequestSpecs;
import specs.ResponseSpecs;

import static io.restassured.RestAssured.given;

public class BookingClient {

    /**
     * Создаёт бронирование: POST /booking.
     * Общие проверки статуса/контента (200 + JSON) выполняются здесь через ResponseSpecs.OK_JSON,
     * ответ возвращается тесту для точечных проверок и извлечения данных:
     * bookingid — .extract().jsonPath().getInt("bookingid"),
     * созданная бронь целиком — .extract().jsonPath().getObject("booking", BookDTO.class).
     */
    public ValidatableResponse addBooking(BookDTO bookDTO) {
        System.out.println("\nPOSITIVE POST\n");
        return given()
                .spec(RequestSpecs.BASE_SPEC)
                .log().uri()
                .body(bookDTO)
                .when()
                .post("/booking")
                .then()
                .spec(ResponseSpecs.OK_JSON)
                .log().body();
    }

    /**
     * Возвращает бронирование по id: GET /booking/{id}.
     * Тело ответа — объект брони без обёртки bookingid/booking,
     * поэтому в тесте он читается целиком: .extract().as(BookDTO.class).
     * Общие проверки (200 + JSON) — здесь через ResponseSpecs.OK_JSON.
     */
    public ValidatableResponse getBooking(int bookingId) {
        System.out.println("\nPOSITIVE GET\n");
        return given()
                .spec(RequestSpecs.BASE_SPEC)
                .log().uri()
                .when()
                .get("/booking/{id}", bookingId)
                .then()
                .spec(ResponseSpecs.OK_JSON)
                .log().body();
    }
}
