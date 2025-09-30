package utilities;

import org.openqa.selenium.WebDriver;

public class WebDriverProvider {
    private static final ThreadLocal<WebDriver> threadLocal = new ThreadLocal<>();

    public static void set(WebDriver driver) {
        threadLocal.set(driver);
    }

    public static WebDriver get() {
        return threadLocal.get();
    }
}
