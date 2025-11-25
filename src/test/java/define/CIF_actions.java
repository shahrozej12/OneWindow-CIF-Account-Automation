package define;

import locators.XPathLocators;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CIF_actions {
    private WebDriver driver;
    private WebDriverWait wait;
    private DatePickerHandler date;
    public CIF_actions(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // Increased timeout to 30 seconds
        this.date = new DatePickerHandler(driver); // Initialize here

    }

    private void waitUntilSpinnerIsInvisible() {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("spinner-overlay")));
        } catch (Exception e) {
            System.out.println("Spinner did not disappear: " + e.getMessage());
        }
    }



    public void selectDropdownOption(String dropdownXPath, String optionName) {
        waitUntilSpinnerIsInvisible();

        // Wait for the dropdown to be clickable and then click it
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dropdownXPath)));
        clickElement(dropdown);

        // Construct the option XPath
        String optionXPath = String.format("//ng-select[@name='CUSTOMER_CATEGORY']//span[contains(@class, 'ng-option-label') and normalize-space()='%s']", optionName);

        // Wait for the option to be visible after clicking the dropdown
        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(optionXPath)));

        // Click the option
        clickElement(option);
    }

    public void selectIdDocumentType(String dropdownXPath, String optionName) {
        waitUntilSpinnerIsInvisible();
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dropdownXPath)));
        clickElement(dropdown);
        String optionXPath = String.format("//ng-select[@name='ID_DOCUMENT_TYPE']//span[normalize-space()='%s']", optionName);
        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(optionXPath)));
        clickElement(option);
    }

    public void selectCustomerSegment(String dropdownXPath, String optionName) {
        waitUntilSpinnerIsInvisible();
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dropdownXPath)));
        clickElement(dropdown);
        String optionXPath = String.format("//ng-select[@name='SECTOR_CODE']//span[normalize-space()='%s']", optionName);
        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(optionXPath)));
        clickElement(option);
    }
    public void selectNgSelectOption(String dropdownXPath, String optionName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // 1. Click dropdown to open it
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dropdownXPath)));
        dropdown.click();

        // 2. Find the input inside the expanded dropdown
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@aria-expanded='true']//input[@type='text']")
        ));

        // 3. Type the option letter by letter
        input.clear();
        for (char c : optionName.toCharArray()) {
            input.sendKeys(String.valueOf(c));
            try { Thread.sleep(200); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        }

        // 4. Wait for the option to appear in the dropdown panel
        String optionXPath = String.format(
                "//div[contains(@class,'ng-dropdown-panel')]//div[contains(@class,'ng-option')]//span[text()='%s']",
                optionName.trim()
        );

        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(optionXPath)));

        // 5. Click the option using JavaScript
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", option);

        // Optional: small pause for Angular to process selection
        try { Thread.sleep(200); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }




    public void selectAssanAccount(String dropdownXPath, String optionName) {
        waitUntilSpinnerIsInvisible();
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dropdownXPath)));
        clickElement(dropdown);
        String optionXPath = String.format("//ng-select[@name='ASSAN_ACCOUNT']//span[normalize-space()='%s']", optionName);
        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(optionXPath)));
        clickElement(option);
    }

    public void selectMarital(String dropdownXPath, String optionName) {
        waitUntilSpinnerIsInvisible();
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dropdownXPath)));
        clickElement(dropdown);
        String optionXPath = String.format("//ng-select[@name='maritalStatus']//span[normalize-space()='%s']", optionName);
        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(optionXPath)));
        clickElement(option);
    }

    public void selectCustomerTitle(String dropdownXPath, String optionName) {
        waitUntilSpinnerIsInvisible();
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dropdownXPath)));
        clickElement(dropdown);
        String optionXPath = String.format("//ng-select[@name='Customer_Title']//span[normalize-space()='%s']", optionName);
        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(optionXPath)));
        clickElement(option);
    }

    public void selectGender(String dropdownXPath, String optionName) {
        waitUntilSpinnerIsInvisible();
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dropdownXPath)));
        clickElement(dropdown);
        String optionXPath = String.format("//ng-select[@name='gender']//span[normalize-space()='%s']", optionName);
        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(optionXPath)));
        clickElement(option);
    }

    public void selectOccupation(String dropdownXPath, String optionName) {
        waitUntilSpinnerIsInvisible();
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dropdownXPath)));
        clickElement(dropdown);
        String optionXPath = String.format("//ng-select[@name='GENDER_DESC']//span[normalize-space()='%s']", optionName);
        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(optionXPath)));
        clickElement(option);
    }

    public void selectIndusCode(String dropdownXPath, String optionName) {
        waitUntilSpinnerIsInvisible();
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dropdownXPath)));
        clickElement(dropdown);
        String optionXPath = String.format("//ng-select[@name='indusCode']//span[normalize-space()='%s']", optionName);
        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(optionXPath)));
        clickElement(option);
    }

    public void selectTaxResidence(String dropdownXPath, String optionName) {
        waitUntilSpinnerIsInvisible();
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dropdownXPath)));
        clickElement(dropdown);
        String optionXPath = String.format("//ng-select[@name='TAX_RES_CRS']//span[normalize-space()='%s']", optionName);
        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(optionXPath)));
        clickElement(option);
    }

    public void selectTaxReason(String dropdownXPath, String optionName) {
        waitUntilSpinnerIsInvisible();
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dropdownXPath)));
        clickElement(dropdown);
        String optionXPath = String.format("//ng-select[@name='TAX_REASON']//span[normalize-space()='%s']", optionName);
        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(optionXPath)));
        clickElement(option);
    }
//    PEP/CUSTOMER DEMOGRAPHIC
    public void selectPep(String dropdownXPath, String optionName) {
        waitUntilSpinnerIsInvisible();
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dropdownXPath)));
        clickElement(dropdown);
        String optionXPath = String.format("//ng-select[@name='pep']//span[normalize-space()='%s']", optionName);
        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(optionXPath)));
        clickElement(option);
    }
    public void selectPepStatus(String dropdownXPath, String optionName) {
        waitUntilSpinnerIsInvisible();
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dropdownXPath)));
        clickElement(dropdown);
        String optionXPath = String.format("//ng-select[@name='pepStatus']//span[normalize-space()='%s']", optionName);
        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(optionXPath)));
        clickElement(option);
    }
    public void selectAddressType(String dropdownXPath, String optionName) {
        waitUntilSpinnerIsInvisible();
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dropdownXPath)));
        clickElement(dropdown);
        String optionXPath = String.format("//ng-select[@bindlabel='ADDR_TYPE_DESC']//span[normalize-space()='%s']", optionName);
        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(optionXPath)));
        clickElement(option);
    }
    public void selectProvince(String dropdownXPath, String optionName) {
        waitUntilSpinnerIsInvisible();
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dropdownXPath)));
        clickElement(dropdown);
        String optionXPath = String.format("//ng-select[@name='province']//span[normalize-space()='%s']", optionName);
        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(optionXPath)));
        clickElement(option);
    }
    public void selectEmployee_Status(String dropdownXPath, String optionName) {
        waitUntilSpinnerIsInvisible();
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dropdownXPath)));
        clickElement(dropdown);
        String optionXPath = String.format("//ng-select[@name='empStats']//span[normalize-space()='%s']", optionName);
        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(optionXPath)));
        clickElement(option);
    }
    public void selectRMCode(String dropdownXPath, String optionName) {
        waitUntilSpinnerIsInvisible();
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dropdownXPath)));
        clickElement(dropdown);
        String optionXPath = String.format("//ng-select[@name='rmCode']//span[normalize-space()='%s']", optionName);
        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(optionXPath)));
        clickElement(option);
    }
    public void selectEducation(String dropdownXPath, String optionName) {
        waitUntilSpinnerIsInvisible();
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dropdownXPath)));
        clickElement(dropdown);
        String optionXPath = String.format("//ng-select[@name='EDU_CODE']//span[normalize-space()='%s']", optionName);
        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(optionXPath)));
        clickElement(option);
    }

    public void selectPlaceBirth(String dropdownXPath, String optionName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // 1. Click dropdown to open it
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dropdownXPath)));
        dropdown.click();

        // 2. Find the input inside the expanded dropdown
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@aria-expanded='true']//input[@type='text']")
        ));

        // 3. Type the option letter by letter
        input.clear();
        for (char c : optionName.toCharArray()) {
            input.sendKeys(String.valueOf(c));
            try { Thread.sleep(200); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        }

        // 4. Wait for the option to appear in the dropdown panel
        String optionXPath = String.format(
                "//div[contains(@class,'ng-dropdown-panel')]//div[contains(@class,'ng-option')]//span[text()='%s']",
                optionName.trim()
        );

        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(optionXPath)));

        // 5. Click the option using JavaScript
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", option);

        // Optional: small pause for Angular to process selection
        try { Thread.sleep(200); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }

//    Contact Detail
public void selectCellCountryCode(String dropdownXPath, String optionName) {
    waitUntilSpinnerIsInvisible();
    WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dropdownXPath)));
    clickElement(dropdown);
    String optionXPath = String.format("//ng-select[@name='CELLCOUNTRYCODE']//span[normalize-space()='%s']", optionName);
    WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(optionXPath)));
    clickElement(option);
}
    public void selectResCounryCode(String dropdownXPath, String optionName) {
        waitUntilSpinnerIsInvisible();
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dropdownXPath)));
        clickElement(dropdown);
        String optionXPath = String.format("//ng-select[@name='RESIDENCE_COUNTRY_CODE']//span[normalize-space()='%s']", optionName);
        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(optionXPath)));
        clickElement(option);
    }
    public void selectMailingAdd(String dropdownXPath, String optionName) {
        waitUntilSpinnerIsInvisible();
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dropdownXPath)));
        clickElement(dropdown);
        String optionXPath = String.format("//ng-select[@name='PREF_ADDR']//span[normalize-space()='%s']", optionName);
        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(optionXPath)));
        clickElement(option);
    }
    public void selectMobileTelecom(String dropdownXPath, String optionName) {
        waitUntilSpinnerIsInvisible();
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dropdownXPath)));
        clickElement(dropdown);
        String optionXPath = String.format("//ng-select[@name='TELECOM']//span[normalize-space()='%s']", optionName);
        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(optionXPath)));
        clickElement(option);
    }
//    Next Of Kin
public void selectKinRelationship(String dropdownXPath, String optionName) {
    waitUntilSpinnerIsInvisible();
    WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dropdownXPath)));
    clickElement(dropdown);
    String optionXPath = String.format("//ng-select[@name='nextOfKinRelCode']//span[normalize-space()='%s']", optionName);
    WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(optionXPath)));
    clickElement(option);
}

    //Select Checker
    public void selectChecker(String optionName) {
        waitUntilSpinnerIsInvisible();

        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPathLocators.Checker_DropDown)));
        clickElement(dropdown);  // Click the dropdown to open it
        String optionXPath = String.format(XPathLocators.Checker_Select, optionName);
        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(optionXPath)));
        clickElement(option);  // Click the selected option
    }

    //Jointly Relation
    public void selectRelation(String dropdownXPath, String optionName) {
        waitUntilSpinnerIsInvisible();
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dropdownXPath)));
        clickElement(dropdown);
        String optionXPath = String.format("//ng-select[@name='relations']//span[normalize-space()='%s']", optionName);
        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(optionXPath)));
        clickElement(option);
    }


    // Method to select the day from the calendar
    public void enterCnicAndClickButton(String cnic) {
        if (!isValidCnic(cnic)) {
            int digitCount = (cnic != null) ? cnic.length() : 0; // Count digits
            System.out.println("Invalid CNIC Number");
            throw new IllegalArgumentException("Invalid CNIC: " + cnic + ". CNIC must be exactly 13 digits. You entered " + digitCount + " digits.");
        }

        // Enter the valid CNIC
        WebElement idDocNoField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPathLocators.ID_DOC_NO_FIELD)));
        idDocNoField.clear(); // Clear any existing value
        idDocNoField.sendKeys(cnic); // Enter the valid CNIC

        // Wait for the "New CIF" button to become clickable after entering the CNIC
        WebElement newCifButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPathLocators.NEW_CIF_BUTTON)));
        newCifButton.click(); // Click the button to trigger the message

        // Check for the "New to bank" message
        try {
            WebElement newToBankMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//span[contains(@class, 'badge-success') and normalize-space()='New to bank']")));
            System.out.println("New to bank message displayed: " + newToBankMessage.getText());
        } catch (TimeoutException e) {
            // If the "New to bank" message is not found, check for the "New CIF" button
            try {
                WebElement newCifButtonDisplayed = wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//button[normalize-space()='New CIF']")));
                System.out.println("Pop-up message displayed. 'New CIF' button is visible: " + newCifButtonDisplayed.getText());
            } catch (TimeoutException e2) {
                System.out.println("Neither the 'New to bank' message nor the 'New CIF' button appeared.");
            }
        }
    }
    private boolean isValidCnic(String cnic) {
        return cnic != null && cnic.length() == 13 && cnic.matches("\\d{13}");
    }

    private void clickElement(WebElement element) {
        int attempts = 0;
        while (attempts < 3) {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(element)); // Wait until the element is clickable
                element.click();
                return; // Exit if click is successful
            } catch (ElementClickInterceptedException e) {
                // Wait for any overlays that might be blocking the click
                waitUntilSpinnerIsInvisible(); // Check if the spinner is gone
                attempts++;
            }
        }
    }
}
