package testBase;

import org.openqa.selenium.WebDriver;

public class DriverManager {

    private static ThreadLocal<WebDriver> driver =
            new ThreadLocal<>();

    // Set driver for current thread
    public static void setDriver(WebDriver driverInstance) {
        driver.set(driverInstance);
    }

    // Get driver for current thread
    public static WebDriver getDriver() {
        return driver.get();
    }

    // Remove driver for current thread
    public static void unload() {
        driver.remove();
    }
}