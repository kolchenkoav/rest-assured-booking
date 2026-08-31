package clients;

import dto.BookDTO;
import dto.PatchBookingDTO;
import io.restassured.response.ValidatableResponse;
import specs.RequestSpecs;
import specs.ResponseSpecs;

import static io.restassured.RestAssured.given;

public class BookingClient {

    /**
     * Создаёт бронирование: POST /booking.
     * Общие проверки статуса/контента/схемы (200 + JSON + created-booking.schema.json)
     * выполняются здесь через ResponseSpecs.OK_CREATED_BOOKING_JSON,
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
                .spec(ResponseSpecs.OK_CREATED_BOOKING_JSON)
                .log().body();
    }

    /**
     * Возвращает бронирование по id: GET /booking/{id}.
     * Тело ответа — объект брони без обёртки bookingid/booking,
     * поэтому в тесте он читается целиком: .extract().as(BookDTO.class).
     * Общие проверки (200 + JSON + booking.schema.json) — здесь через ResponseSpecs.OK_BOOKING_JSON.
     */
    public ValidatableResponse getBooking(int bookingId) {
        System.out.println("\nPOSITIVE GET\n");
        return given()
                .spec(RequestSpecs.BASE_SPEC)
                .log().uri()
                .when()
                .get("/booking/{id}", bookingId)
                .then()
                .spec(ResponseSpecs.OK_BOOKING_JSON)
                .log().body();
    }

    /**
     * Обновляет бронирование целиком: PUT /booking/{id}.
     * Требует ПОЛНЫЙ payload (все поля — частичное обновление это PATCH)
     * и авторизацию: токен передаётся параметром и уходит как Cookie token=<value>
     * через RequestSpecs.authSpec. Тело ответа — обновлённая бронь без обёртки,
     * читается в тесте целиком: .extract().as(BookDTO.class).
     */
    public ValidatableResponse updateBooking(int bookingId, BookDTO bookDTO, String token) {
        System.out.println("\nPOSITIVE PUT\n");
        return given()
                .spec(RequestSpecs.authSpec(token))
                .log().uri()
                .body(bookDTO)
                .when()
                .put("/booking/{id}", bookingId)
                .then()
                .spec(ResponseSpecs.OK_BOOKING_JSON)
                .log().body();
    }

    /**
     * Частично обновляет бронирование: PATCH /booking/{id}.
     * Payload — PatchBookingDTO (по контракту доки — firstname и lastname),
     * авторизация — токен параметром, уходит как Cookie token=<value> через authSpec.
     * Ответ: 200 + JSON, полная бронь после мержа патча — .extract().as(BookDTO.class).
     */
    public ValidatableResponse partialUpdateBooking(int bookingId, PatchBookingDTO patchDTO, String token) {
        System.out.println("\nPOSITIVE PATCH\n");
        return given()
                .spec(RequestSpecs.authSpec(token))
                .log().uri()
                .body(patchDTO)
                .when()
                .patch("/booking/{id}", bookingId)
                .then()
                .spec(ResponseSpecs.OK_BOOKING_JSON)
                .log().body();
    }

    /**
     * Удаляет бронирование: DELETE /booking/{id}.
     * Авторизация — токен параметром, уходит как Cookie token=<value> через authSpec.
     * Успех — 201 (не 200!) с текстовым телом "Created": поэтому спека CREATED
     * не проверяет Content-Type.
     */
    public ValidatableResponse deleteBooking(int bookingId, String token) {
        System.out.println("\nPOSITIVE DELETE\n");
        return given()
                .spec(RequestSpecs.authSpec(token))
                .log().uri()
                .when()
                .delete("/booking/{id}", bookingId)
                .then()
                .spec(ResponseSpecs.CREATED)
                .log().body();
    }

    /**
     * Контрольный GET удалённой брони: GET /booking/{id} после DELETE — ожидаем 404.
     * Отдельный метод вместо getBooking(): тот применяет OK_JSON (200 + JSON)
     * и падает на статусе 404 раньше, чем тест что-либо проверит.
     */
    public ValidatableResponse getDeletedBooking(int bookingId) {
        System.out.println("\nNEGATIVE GET after DELETE\n");
        return given()
                .spec(RequestSpecs.BASE_SPEC)
                .log().uri()
                .when()
                .get("/booking/{id}", bookingId)
                .then()
                .spec(ResponseSpecs.NOT_FOUND)
                .log().body();
    }

    /**
     * Негативный GET: несуществующий id (0, -1, ...) — ожидаем 404 "Not Found".
     * Спека NOT_FOUND проверяет только статус: тело ответа не JSON
     * ("Not Found"), поэтому Content-Type и схему не проверяем.
     * Ожидание «успеха» здесь было бы ошибкой: OK_BOOKING_JSON требует 200 + JSON
     * и падает JsonParseException на тексте "Not Found".
     */
    public ValidatableResponse getNEGATIVEBooking(int bookingId) {
        System.out.println("\nNEGATIVE GET\n");
        return given()
                .spec(RequestSpecs.BASE_SPEC)
                .log().uri()
                .when()
                .get("/booking/{id}", bookingId)
                .then()
                .spec(ResponseSpecs.NOT_FOUND)
                .log().body();
    }
}
