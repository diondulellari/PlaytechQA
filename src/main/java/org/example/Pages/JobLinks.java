package org.example.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class JobLinks {

    /**
     * Retrieves job listings for Estonia from the Playtech jobs page.
     * @param driver The WebDriver instance used to interact with the webpage.
     * @return A list of job titles and links in Estonia, or an error message if retrieval fails.
     */
    public static List<String> getJobLinks(WebDriver driver) {
        try {
            // Navigate to the Jobs page
            driver.get("https://www.playtechpeople.com/jobs-our/");

            // Wait for job listings to be visible
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.className("jobs-wrap")));

            // Find job links in Estonia
            List<WebElement> jobElements = driver.findElements(By.xpath(
                    "//p[contains(@class, 'location-link') and text()='Estonia']" +
                            "/parent::a[contains(@class, 'job-item')]"
            ));

            // Extract job titles and links
            List<String> estonianJobLinks = jobElements.stream()
                    .map(element -> {
                        String jobTitle = element.findElement(By.tagName("h6")).getText();
                        String jobLink = element.getAttribute("href");
                        return jobTitle + " : " + jobLink;
                    })
                    .collect(Collectors.toList());

            return estonianJobLinks;

        } catch (Exception e) {
            System.err.println("Error finding job links: " + e.getMessage());
            e.printStackTrace();
            return List.of("Error finding job links: " + e.getMessage());
        }
    }
}