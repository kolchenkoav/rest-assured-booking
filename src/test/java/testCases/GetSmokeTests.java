package testCases;

import clients.BookingClient;
import listeners.RunTestAgain;
import org.testng.annotations.Test;
import utils.BaseTest;

public class GetSmokeTests extends BaseTest {

    private final BookingClient bookingClient = new BookingClient();

    @Test(description = "Negative GET: non-existent return 404 Not Found",
            retryAnalyzer = RunTestAgain.class, priority = 0)
    public void smokeTestGetNEGATIVEBookingNonExistent() {

        bookingClient.getNEGATIVEBooking(0);
        System.out.println("Get negative: non existed booking_id");

    }

    @Test(description = "Negative GET: invalid booking ids return 404 Not Found",
            retryAnalyzer = RunTestAgain.class, priority = 1)
    public void smokeTestGetNEGATIVEBookingInvalid() {

        bookingClient.getNEGATIVEBooking(-1);
        System.out.println("Get negative: value < 0");
    }
}
