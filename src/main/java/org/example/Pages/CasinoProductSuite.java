package org.example.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CasinoProductSuite {

    /**
     * Retrieves the casino product suite description from the specified web page.
     * @param driver The WebDriver instance used to navigate and interact with the page.
     * @return The extracted text of the casino description or an error message if retrieval fails.
     */
    public static String getCasinoDescription(WebDriver driver) {
        try {
            driver.get("https://www.playtechpeople.com/life-at-playtech/");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".container")));

            try {
                WebElement casinoHeader = driver.findElement(By.xpath("//h3[text()='Casino']"));
                WebElement description = casinoHeader.findElement(By.xpath("following-sibling::p"));
                return description.getText();
            } catch (Exception e1) {
                try {
                    WebElement description = driver.findElement(By.xpath("//div[contains(@class, 'product-card_inner')]/p"));
                    return description.getText();
                } catch (Exception e2) {
                    try {
                        WebElement productCard = driver.findElement(By.cssSelector(".product-card"));
                        WebElement description = productCard.findElement(By.tagName("p"));
                        return description.getText();
                    } catch (Exception e3) {
                        WebElement description = driver.findElement(By.xpath("//p[contains(text(), 'world') and contains(text(), 'largest') and contains(text(), 'online casino')]"));
                        return description.getText();
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error finding Casino product suite description: " + e.getMessage());
            e.printStackTrace();
            return "Error finding Casino product suite description: " + e.getMessage();
        }
    }

}