package testCases;

import clients.BookingClient;
import dto.BookDTO;
import dto.PatchBookingDTO;
import io.restassured.response.ValidatableResponse;
import listeners.RunTestAgain;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.BaseTest;
import utils.TestDataGeneration;

public class BookingSmokeTests extends BaseTest {

    private final BookingClient bookingClient = new BookingClient();

//    @Test(description = "The test include checking creation, get, update, partial update and delete booking ",
//            retryAnalyzer = RunTestAgain.class)
    @Test(description = "The test include checking creation, get, update, partial update and delete booking ")
    public void smokeTestFullLifeCycle() {
        // --- POST: создаём бронирование ---
        BookDTO expectedBooking = TestDataGeneration.fullBooking();

        ValidatableResponse response = bookingClient.addBooking(expectedBooking);

        int bookingId = response.extract().jsonPath().getInt("bookingid");
        BookDTO actualBooking = response.extract().jsonPath().getObject("booking", BookDTO.class);

        Assert.assertTrue(bookingId > 0,
                "bookingid должен быть положительным числом, получено: " + bookingId);
        Assert.assertEquals(actualBooking, expectedBooking,
                "Тело созданной брони должно совпадать с телом запроса");

        // --- GET: читаем созданное бронирование ---
        ValidatableResponse getResponse = bookingClient.getBooking(bookingId);
        BookDTO fetchedBooking = getResponse.extract().as(BookDTO.class);

        Assert.assertEquals(fetchedBooking, expectedBooking,
                "GET /booking/{id} должен вернуть созданную бронь без изменений");

        // --- PUT: обновляем бронирование ---
        BookDTO updatedBooking = TestDataGeneration.updatedBooking();

        ValidatableResponse putResponse = bookingClient.updateBooking(bookingId, updatedBooking, authToken);
        BookDTO actualUpdated = putResponse.extract().as(BookDTO.class);

        Assert.assertEquals(actualUpdated, updatedBooking,
                "PUT должен вернуть обновлённую бронь");

        // --- контрольный GET: изменения сохранились в API ---
        BookDTO afterUpdate = bookingClient.getBooking(bookingId).extract().as(BookDTO.class);

        Assert.assertEquals(afterUpdate, updatedBooking,
                "GET после PUT должен вернуть обновлённые данные");

        // --- PATCH: частично обновляем бронирование ---
        PatchBookingDTO patch = TestDataGeneration.partialUpdateBooking();

        ValidatableResponse patchResponse = bookingClient.partialUpdateBooking(bookingId, patch, authToken);
        BookDTO afterPatch = patchResponse.extract().as(BookDTO.class);

        BookDTO expectedAfterPatch = BookDTO.builder()
                .firstname(patch.getFirstname())
                .lastname(patch.getLastname())
                .totalprice(updatedBooking.getTotalprice())
                .depositpaid(updatedBooking.isDepositpaid())
                .bookingdates(updatedBooking.getBookingdates())
                .additionalneeds(updatedBooking.getAdditionalneeds())
                .build();

        Assert.assertEquals(afterPatch, expectedAfterPatch,
                "PATCH должен заменить только firstname и lastname, остальное сохранив");

        // --- контрольный GET: изменения сохранились в API ---
        BookDTO persistedAfterPatch = bookingClient.getBooking(bookingId).extract().as(BookDTO.class);

        Assert.assertEquals(persistedAfterPatch, expectedAfterPatch,
                "GET после PATCH должен вернуть частично обновлённые данные");

        // --- DELETE: удаляем бронирование ---
        bookingClient.deleteBooking(bookingId, authToken);

        // --- контрольный GET: брони больше нет ---
        bookingClient.getDeletedBooking(bookingId);

        // --- lifecycle POST → GET → PUT → PATCH → DELETE завершён ---
    }
}
