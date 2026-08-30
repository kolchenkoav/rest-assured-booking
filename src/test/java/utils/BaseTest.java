package utils;

import clients.AuthClient;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;

public class BaseTest {

    /**
     * Auth-токен (POST /auth), создаётся один раз на suite и переиспользуется
     * в тестах PUT/DELETE /booking как Cookie: token=<value>.
     */
    protected static String authToken;

    @BeforeSuite
    public void suiteSetUp() {
        authToken = AuthClient.createToken();
        if (authToken == null || authToken.isBlank()) {
            throw new IllegalStateException("/auth не вернул token — проверьте учётные данные в BookingConfig");
        }
        System.out.println("Auth token created for the suite");
    }

    @BeforeMethod
    public void methodSetUp(Method method) {
        System.out.println("Starting test: " + method.getName());
        System.out.println("Method Set Up");
    }

    @AfterMethod
    public void methodTearDown(Method method) {
        System.out.println("Method Tear Down");
        System.out.println("Completing test: " + method.getName());

    }
}
