package org.example.Core;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserUtils {
    /**
     * Opens a new Chrome browser instance, maximizes the window, and returns the WebDriver instance.
     *
     * @return WebDriver instance representing the opened browser.
     */
    public static WebDriver openBrowser() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        return driver;
    }
    /**
     * Closes the browser if the WebDriver instance is not null.
     *
     * @param driver The WebDriver instance to be closed.
     */
    public static void closeBrowser(WebDriver driver) {
        if (driver != null) {
            driver.quit();
        }
}
}
