package testCases;

import clients.BookingClient;
import listeners.RunTestAgain;
import org.testng.annotations.Test;
import utils.BaseTest;

public class GetSmokeTests extends BaseTest {

    private final BookingClient bookingClient = new BookingClient();

    @Test(description = "Negative GET: non-existent and invalid booking ids return 404 Not Found",
            retryAnalyzer = RunTestAgain.class)
    public void smokeTestGetRequest() {

        bookingClient.getNEGATIVEBooking(0);
        System.out.println("Get negative: non existed bookingid");


        bookingClient.getNEGATIVEBooking(-1);
        System.out.println("Get negative: value < 0");
    }
}
