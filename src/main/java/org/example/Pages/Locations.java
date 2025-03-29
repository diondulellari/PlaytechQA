package org.example.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class Locations {

    /**
     * Retrieves a list of location names from the Playtech locations page.
     * @param driver The WebDriver instance used to interact with the webpage.
     * @return A list of location names or an empty list if no locations are found.
     */
    public static List<String> getLocations(WebDriver driver) {
        driver.get("https://www.playtechpeople.com/locations-countries/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[contains(@class," +
                        " 'location-item-wrap')]//div[contains(@class," +
                        " 'location-description')]//h4")
        ));

        List<WebElement> locationElements = driver.findElements(
                By.xpath("//div[contains(@class," +
                        " 'location-item-wrap')]//div[contains(@class," +
                        " 'location-description')]//h4")
        );

        return locationElements.stream()
                .map(WebElement::getText)
                .filter(text -> text != null && !text.trim().isEmpty())
                .collect(Collectors.toList());
    }
}