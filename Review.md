# Playtech Automation Task

This project demonstrates automation using Java and Selenium. The task involves automating a series of actions on the [Playtech People](https://www.playtechpeople.com) website. The primary goal is to perform browser automation to extract data from various sections of the site.

## Requirements

- Java
- Selenium WebDriver
- JUnit for unit testing

## Task Overview

### 1. Open a Web Browser at the URL
- The automation starts by opening a web browser and navigating to [https://www.playtechpeople.com](https://www.playtechpeople.com).

### 2. Extract Locations under the "Locations" Tab
- The script extracts how many locations are listed under the "Locations" tab on the webpage.

### 3. Extract the Casino Product Suite Description
- Under the "Who we are" section on the page, the script navigates to the "Life at Playtech" section.
- It finds and extracts the description of the Casino Product Suite.

### 4. Extract Job Links for Estonia
- The automation extracts job links for available positions in **Estonia**.
- Since Playtech only operates in **Tallinn** and **Tartu**, the script filters job listings specifically for Estonia by using an XPath expression. This eliminates the need to manually filter by city, ensuring efficient job extraction.
- The automation uses the following XPath expression to filter job links based on the "Estonia" location:

  ```java
  List<WebElement> jobElements = driver.findElements(By.xpath(
          "//p[contains(@class, 'location-link') and text()='Estonia']" +
                  "/parent::a[contains(@class, 'job-item')]"
  ));
  
### 5. Close the Browser
- After completing the tasks, the browser is closed automatically.

## Bonus Tasks (Optional)

### 2. Export the Results as a `.txt` File
- The results of the automation, such as the list of locations and job positions, are exported to a `.txt` file for record-keeping.

### 3. Add the Project to JUnit Framework
- The project has been integrated into JUnit for unit testing purposes, allowing the script to run tests rather than just executing as a Java application.

