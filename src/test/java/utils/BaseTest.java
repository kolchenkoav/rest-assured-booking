package utils;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;

public class BaseTest {

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
