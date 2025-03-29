package org.example;

import org.example.Core.BrowserUtils;
import org.example.Core.FileExportUtil;
import org.example.Pages.CasinoProductSuite;
import org.example.Pages.JobLinks;
import org.example.Pages.Locations;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

public class PlaytechAutomation {

    private WebDriver driver;

    /**
     * Constructor to initialize the WebDriver.
     */
    public PlaytechAutomation() {
        this.driver = BrowserUtils.openBrowser();
    }

    /**
     * Runs the automation script to collect and export information from the Playtech website.
     */
    public void startAutomation() {
        List<String> testResults = new ArrayList<>();
        try {
            // 1. Open the browser at the URL
            driver.get("https://www.playtechpeople.com");

            // 2. Find and list locations
            testResults.add("List of locations:");
            List<String> locations = Locations.getLocations(driver);
            testResults.addAll(locations);

            // 3. Print the Casino Product Suite description
            testResults.add("Casino Product Suite description:");
            String casinoDescription = CasinoProductSuite.getCasinoDescription(driver);
            testResults.add(casinoDescription);

            // 4. Print available job links in Estonia
            testResults.add("Job links in Estonia:");
            List<String> jobLinks = JobLinks.getJobLinks(driver);
            testResults.addAll(jobLinks);

        } finally {
            // 5. Close the browser
            BrowserUtils.closeBrowser(driver);
        }
        // 6. Export the results to a .txt file
        String filePath = FileExportUtil.exportToFile("TestResults", testResults);
        if (filePath != null) {
            System.out.println("Test results exported to: " + filePath);
        } else {
            System.err.println("Failed to export test results.");
        }
    }

    /**
     * Main method to start the automation script.
     */
    public static void main(String[] args) {
        PlaytechAutomation automation = new PlaytechAutomation();
        automation.startAutomation();
    }
}