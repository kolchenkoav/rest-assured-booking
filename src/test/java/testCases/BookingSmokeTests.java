package testCases;

import clients.BookingClient;
import dto.BookDTO;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.BaseTest;
import utils.TestDataGeneration;

public class BookingSmokeTests extends BaseTest {

    private final BookingClient bookingClient = new BookingClient();

    @Test
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

        // --- Далее: GET / PUT / DELETE по bookingId ---
    }
}
