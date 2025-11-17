package define;

import locators.XPathLocators;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DatePickerHandler {

    private WebDriver driver;
    private WebDriverWait wait;

    public DatePickerHandler(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // Increased timeout to 30 seconds
    }

    // Method to click CNIC Issuance Date Field
    public void clickCnicIssuanceDateField() {
        WebElement dateField = driver.findElement(By.xpath(XPathLocators.CNIC_ISSUANCE_DATE_FIELD));
        dateField.click();
    }

    // Method to click CNIC Expiry Date Field
    public void clickCnicExpiryDateField() {
        WebElement dateField = driver.findElement(By.xpath(XPathLocators.CNIC_EXPIRY_DATE_FIELD));
        dateField.click();
    }

    // Method to click Visa Expiry Date Field
    public void clickVisaExpiryDateField() {
        WebElement dateField = driver.findElement(By.xpath(XPathLocators.VISA_EXPIRY_DATE));
        dateField.click();
    }

    // Method to click Date of Birth Field
    public void clickDateOfBirthField() {
        WebElement dateField = driver.findElement(By.xpath(XPathLocators.DOB));
        dateField.click();
    }

    // Method to open the DatePicker and select a date for any date field (Issuance, Expiry, Visa Expiry, DOB)
    public void openDatePickerAndSelectDate(String datePickerXPath, String targetDate, String maxDate, String dateType) {
        // Validate the targetDate for null or empty value
        if (targetDate == null || targetDate.isEmpty()) {
            System.out.println("Error: targetDate is null or empty. Please provide a valid date.");
            return;  // Exit the method early if the date is invalid
        }
        // Parse the target date in "yyyy-MM-dd" format
        LocalDate targetLocalDate;
        try {
            targetLocalDate = LocalDate.parse(targetDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please provide valid 'yyyy-MM-dd' dates.");
            return;
        }

        // Open the correct date picker based on the dateType (issuance, expiry, visa, or dob)
        if (dateType.equalsIgnoreCase("issuance")) {
            clickCnicIssuanceDateField();
        } else if (dateType.equalsIgnoreCase("expiry")) {
            clickCnicExpiryDateField();
        } else if (dateType.equalsIgnoreCase("visa")) {
            clickVisaExpiryDateField();
        } else if (dateType.equalsIgnoreCase("dob")) {
            clickDateOfBirthField();
        } else {
            System.out.println("Invalid date type specified: " + dateType);
            return;
        }

        // Validate dates based on the field type
        if (!isDateValid(targetLocalDate, dateType)) {
            System.out.println("Invalid date for " + dateType + ". Operation aborted.");
            return;
        }

        // Extract the month, year, and day from the target date
        String targetMonth = targetLocalDate.getMonth().name();
        int targetYear = targetLocalDate.getYear();
        int targetDay = targetLocalDate.getDayOfMonth();

        // Wait for the date picker to be visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(datePickerXPath)));

        // Navigate to the correct month and year in the date picker
        navigateToDatePickerMonthYear(targetMonth, targetYear);

        // Select the correct day from the date picker
        selectDayFromDatePicker(targetDay);
    }

    // Method to check if the date is valid based on the date type
    private boolean isDateValid(LocalDate targetDate, String dateType) {
        LocalDate today = LocalDate.now();  // Get today's date

        switch (dateType.toLowerCase()) {
            case "issuance":
                // No validation needed for issuance date
                return true;

            case "expiry":
                // Expiry date should be greater than today's date
                if (targetDate.isBefore(today) || targetDate.isEqual(today)) {
                    System.out.println("Expiry date should be in the future, not today or in the past.");
                    return false;
                }
                break;

            case "visa":
                // Visa expiry date should be greater than today's date
                if (targetDate.isBefore(today) || targetDate.isEqual(today)) {
                    System.out.println("Visa expiry date should be in the future, not today or in the past.");
                    return false;
                }
                break;
            case "dob":
                // DOB should be at least 18 years ago
                LocalDate minDobDate = today.minusYears(18);
                if (targetDate.isAfter(minDobDate)) {
                    System.out.println("DOB should be at least 18 years ago.");
                    return false;
                }
                break;

            default:
                System.out.println("Invalid date type for validation: " + dateType);
                return false;
        }

        return true;
    }

    // Method to retrieve the issuance date (stub)
    private LocalDate getIssuanceDate() {
        // Here, implement the logic to fetch the CNIC issuance date (this could be from a field or variable)
        // For example, this could be hardcoded or fetched from a web element
        return LocalDate.parse("2020-01-01", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    private void navigateToDatePickerMonthYear(String targetMonth, int targetYear) {
        boolean dateFound = false;

        while (!dateFound) {
            try {
                // Wait for the date picker to load and be visible
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//bs-datepicker-container[@aria-label='calendar']")));

                // Get the current month and year displayed in the date picker
                String displayedMonth = driver.findElement(By.xpath("//bs-datepicker-container[@aria-label='calendar']//button[2]//span")).getText();
                String displayedYear = driver.findElement(By.xpath("//bs-datepicker-container[@aria-label='calendar']//button[3]//span")).getText();


                // Check if the displayed month and year match the target
                if (displayedMonth.equalsIgnoreCase(targetMonth) && displayedYear.equals(String.valueOf(targetYear))) {
                    dateFound = true;
                    System.out.println("Target month and year found!");
                } else {
                    // Navigate forward or backward based on whether the target is before or after the displayed date
                    if (compareMonthsAndYears(displayedMonth, Integer.parseInt(displayedYear), targetMonth, targetYear) < 0) {
                        // Move forward to the next month
                        driver.findElement(By.xpath("//bs-datepicker-container[@aria-label='calendar']//button[4]")).click();
                    } else {
                        // Move backward to the previous month
                        driver.findElement(By.xpath("//bs-datepicker-container[@aria-label='calendar']//button[1]")).click();
                    }
                }
            } catch (NoSuchElementException e) {
                // If an element is not found, print the error and retry
                System.out.println("Month or Year not found, retrying...");

                // Log the HTML content of the date picker for debugging
                WebElement datePicker = driver.findElement(By.xpath("//bs-datepicker-container[@aria-label='calendar']"));
                System.out.println("Date Picker HTML: " + datePicker.getAttribute("outerHTML"));

                // Retry after waiting for a few seconds
                try {
                    Thread.sleep(500);  // 2 second delay before retrying
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            }
        }
    }

    // Helper method to compare months and years
    private int compareMonthsAndYears(String displayedMonth, int displayedYear, String targetMonth, int targetYear) {
        int displayedMonthNumber = getMonthNumber(displayedMonth);
        int targetMonthNumber = getMonthNumber(targetMonth);

        if (displayedYear != targetYear) {
            return Integer.compare(displayedYear, targetYear);
        } else {
            return Integer.compare(displayedMonthNumber, targetMonthNumber);
        }
    }

    // Helper method to convert month name to a number (1 = January, 12 = December)
    private int getMonthNumber(String month) {
        switch (month.toLowerCase()) {
            case "january": return 1;
            case "february": return 2;
            case "march": return 3;
            case "april": return 4;
            case "may": return 5;
            case "june": return 6;
            case "july": return 7;
            case "august": return 8;
            case "september": return 9;
            case "october": return 10;
            case "november": return 11;
            case "december": return 12;
            default: return 0;
        }
    }

    // Method to select the correct day from the date picker
    private void selectDayFromDatePicker(int targetDay) {
        // Find the body of the calendar and create a dynamic XPath for the target day
        WebElement dateBody = driver.findElement(By.xpath("//table[@role='grid']//tbody"));
        String dayXPath = "//span[normalize-space()='" + targetDay + "']";

        try {
            // Try to find and click the target day
            WebElement dayElement = dateBody.findElement(By.xpath(dayXPath));
            dayElement.click();
        } catch (NoSuchElementException e) {
            // Handle case where the date element is not found
            System.out.println("Day " + targetDay + " not found in the calendar.");
        }
    }
}
