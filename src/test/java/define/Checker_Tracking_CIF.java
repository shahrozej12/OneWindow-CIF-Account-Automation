        package define;// ExamplePage.java
        import locators.XPathLocators;
        import org.openqa.selenium.By;
        import org.openqa.selenium.WebDriver;
        import org.openqa.selenium.WebElement;
        import org.openqa.selenium.support.ui.ExpectedConditions;
        import org.openqa.selenium.support.ui.WebDriverWait;

        import java.time.Duration;
        import java.util.regex.Matcher;
        import java.util.regex.Pattern;

        public class Checker_Tracking_CIF {
            private WebDriverWait wait;
            WebDriver driver;

            // Separate variables to store Tracking ID and CIF No
            private String trackingId;
            private String cifNo;

            // Constructor
            public Checker_Tracking_CIF(WebDriver driver) {
                this.driver = driver;
                this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));  // 30-second timeout

            }

            // Method to extract and store Tracking ID and CIF No independently
            public void extractTrackingInfo()  {
                // Use the locators defined as String, convert them to By using By.xpath()
                WebElement trackingInfo = driver.findElement(By.xpath(XPathLocators.TRACKING_INFO));
                String trackingText = trackingInfo.getText();  // Get the dynamic text from the locator

                // Regular expression to capture Tracking ID and CIF No independently
                String regex = "Tracking Id: ([^,]+), Cif No# ([^,]+)";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(trackingText);

                // Extract and store Tracking ID and CIF No in separate variables
                if (matcher.find()) {
                    this.trackingId = matcher.group(1);  // Capture and store Tracking ID
                    this.cifNo = matcher.group(2);        // Capture and store CIF No

                    // Log the values
                    System.out.println("Tracking ID: " + trackingId);  // Log the Tracking ID
                    System.out.println("CIF No: " + cifNo);            // Log the CIF No
                } else {
                    System.out.println("Error: Tracking ID or CIF No not found in the tracking information.");
                }
            }


            public String getCifNo() {
                return this.cifNo;
            }

            // Method to search and click the Tracking ID across pages
            public void findAndClickTrackingID() {
                if (trackingId == null || trackingId.isEmpty()) {
                    System.out.println("Tracking ID is not available.");
                    return;
                }

                boolean found = false;
                while (!found) {
                    try {
                        String trackingXPath = String.format(XPathLocators.Find_Tracking_ID, trackingId);
                        System.out.println("Looking for Tracking ID with XPath: " + trackingXPath);

                        WebElement trackingElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(trackingXPath)));
                        trackingElement.click();
                        found = true;
                        System.out.println("Tracking ID found and clicked: " + trackingId);
                    } catch (Exception e) {
                        System.out.println("Tracking ID not found on this page, going to next page.");
                        goToNextPage();
                    }
                }
            }
            // Method to go to the next page
            private void goToNextPage() {
                try {
                    System.out.println("Looking for 'Next' button with XPath: " + XPathLocators.Find_Tracking_ID_Next);
                    WebElement nextPageButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPathLocators.Find_Tracking_ID_Next)));
                    nextPageButton.click();
                    System.out.println("Navigated to next page.");
                    waitUntilSpinnerIsInvisible();
                } catch (Exception e) {
                    System.out.println("Next button not found or cannot navigate to next page: " + e.getMessage());
                }
            }
            private void waitUntilSpinnerIsInvisible() {
                try {
                    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("spinner-overlay")));
                } catch (Exception e) {
                    System.out.println("Spinner did not disappear: " + e.getMessage());
                }
            }
            // New function to extract only digits from a given XPath
            public String extractDigitsFromElementByXpath(String xpath) {
                try {
                    // Locate the element using the XPath
                    WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));

                    // Get the full text of the element
                    String fullText = element.getText();

                    // Extract digits using regex (remove everything that's not a digit)
                    String digitsOnly = fullText.replaceAll("[^0-9]", "");

                    return digitsOnly; // Return the extracted digits
                } catch (Exception e) {
                    System.out.println("Error extracting digits from the element: " + e.getMessage());
                    return "";  // Return empty string in case of an error
                }
            }

            // Example usage of the method in the class
            public void extractAndUseDigitsFromTrackingInfo() {
                // XPath for an element that contains tracking information
                String xpath = "//div[normalize-space()='ID DOC']/..//div[@class='col-8 value']"; // Example XPath

                // Call the function to extract digits from the element
                String digitsOnly = extractDigitsFromElementByXpath(xpath);

                // Print the extracted digits or use it elsewhere
                System.out.println("Extracted digits: " + digitsOnly);

                // You can use this extracted digits value wherever needed
                // For example, filling another form field
                WebElement inputField = driver.findElement(By.xpath("//input[@id='someField']"));
                inputField.sendKeys(digitsOnly);  // Send the extracted digits to the input field
            }
        }