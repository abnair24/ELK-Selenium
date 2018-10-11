package common;

import org.openqa.selenium.WebDriver;


public class DriverManager {

    private static InheritableThreadLocal<WebDriver> webDriver = new InheritableThreadLocal<WebDriver>();

    public static WebDriver getDriver() { return webDriver.get(); }

    public static void setWebDriver(WebDriver driver) {
        webDriver.set(driver);
    }

    public static void cleanup() {
        webDriver.remove();
    }
}
