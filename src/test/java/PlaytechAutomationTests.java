
import org.example.Core.BrowserUtils;
import org.example.Pages.CasinoProductSuite;
import org.example.Pages.JobLinks;
import org.example.Pages.Locations;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PlaytechAutomationTests {

    private static WebDriver driver;
    private static final String TEST_RESULTS_DIR = "test_results/";

    @BeforeAll
    public static void setUp() {
        driver = BrowserUtils.openBrowser();
    }

    @AfterAll
    public static void tearDown() {
        BrowserUtils.closeBrowser(driver);
    }

    @Test
    @Order(1)
    @DisplayName("Test retrieving Playtech locations")
    public void testGetLocations() {
        List<String> locations = Locations.getLocations(driver);

        // Assert that locations were found
        assertNotNull(locations, "Locations list should not be null");
        assertFalse(locations.isEmpty(), "Locations list should not be empty");

        // Log the locations found
        System.out.println("Found " + locations.size() + " locations:");
        locations.forEach(System.out::println);
    }

    @Test
    @Order(2)
    @DisplayName("Test retrieving Casino Product Suite description")
    public void testGetCasinoDescription() {
        String casinoDescription = CasinoProductSuite.getCasinoDescription(driver);

        // Assert that description was found
        assertNotNull(casinoDescription, "Casino description should not be null");
        assertFalse(casinoDescription.isEmpty(), "Casino description should not be empty");
        assertFalse(casinoDescription.startsWith("Error"), "Description should not contain an error message");

        // Log the description
        System.out.println("Casino Description: " + casinoDescription);
    }

    @Test
    @Order(3)
    @DisplayName("Test retrieving job links in Estonia")
    public void testGetJobLinks() {
        List<String> jobLinks = JobLinks.getJobLinks(driver);

        // Assert that job links were found
        assertNotNull(jobLinks, "Job links list should not be null");
        assertFalse(jobLinks.isEmpty(), "Job links list should not be empty");

        // Check that no error messages are in the list
        boolean hasError = jobLinks.stream().anyMatch(link -> link.startsWith("Error"));
        assertFalse(hasError, "Job links should not contain error messages");

        // Log the job links found
        System.out.println("Found " + jobLinks.size() + " job links in Estonia:");
        jobLinks.forEach(System.out::println);
    }


    @Test
    @Order(4)
    @DisplayName("Test full automation workflow")
    public void testFullAutomationWorkflow() {
        // Create a temporary directory for this test
        String testDir = TEST_RESULTS_DIR + "full_test/";
        new File(testDir).mkdirs();

        // Run full workflow
        List<String> testResults = new java.util.ArrayList<>();

        // 1. Get and add locations
        testResults.add("List of locations:");
        List<String> locations = Locations.getLocations(driver);
        testResults.addAll(locations);

        // 2. Get and add Casino description
        testResults.add("Casino Product Suite description:");
        String casinoDescription = CasinoProductSuite.getCasinoDescription(driver);
        testResults.add(casinoDescription);

        // 3. Get and add job links
        testResults.add("Job links in Estonia:");
        List<String> jobLinks = JobLinks.getJobLinks(driver);
        testResults.addAll(jobLinks);

        // 4. Export the results
        String filePath = org.example.Core.FileExportUtil.exportToFile(testDir, "FullWorkflowTest", testResults);

        // Assertions
        assertNotNull(filePath, "Export file path should not be null");
        assertTrue(Files.exists(Paths.get(filePath)), "Exported file should exist");

        System.out.println("Full automation workflow completed successfully, results at: " + filePath);
    }
}