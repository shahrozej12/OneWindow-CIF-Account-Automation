package define;

import locators.XPathLocators;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Account_actions {
    private WebDriver driver;
    private WebDriverWait wait;
    private DatePickerHandler date;

    public Account_actions(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // Increased timeout to 30 seconds
        this.date = new DatePickerHandler(driver); // Initialize here

    }

    public void waitUntilSpinnerIsInvisible() {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("spinner-overlay")));
        } catch (Exception e) {
            System.out.println("Spinner did not disappear: " + e.getMessage());
        }
    }

    public void clickElement(WebElement element) {
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

    

        public void selectAccOpType (String dropdownXPath, String optionName){
            waitUntilSpinnerIsInvisible();
            WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dropdownXPath)));
            clickElement(dropdown);
            String optionXPath = String.format("//ng-select[@name='acctOperType']//span[normalize-space()='%s']", optionName);
            WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(optionXPath)));
            clickElement(option);
        }

        public void selectAccType (String dropdownXPath, String optionName){
            waitUntilSpinnerIsInvisible();
            WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dropdownXPath)));
            clickElement(dropdown);
            String optionXPath = String.format("//ng-select[@name='accountCategoryCode']//span[normalize-space()='%s']", optionName);
            WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(optionXPath)));
            clickElement(option);
        }

        public void selectCurrency (String dropdownXPath, String optionName){
            waitUntilSpinnerIsInvisible();
            WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dropdownXPath)));
            clickElement(dropdown);
            String optionXPath = String.format("//ng-select[@name='currency']//span[normalize-space()='%s']", optionName);
            WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(optionXPath)));
            clickElement(option);
        }


        public void selectDebitRequest (String dropdownXPath, String optionName){
            waitUntilSpinnerIsInvisible();
            WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dropdownXPath)));
            clickElement(dropdown);
            String optionXPath = String.format("//ng-select[@name='DEBIT_CARD_REQUIRED']//span[normalize-space()='%s']", optionName);
            WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(optionXPath)));
            clickElement(option);
        }

        public void selectDebitCardType (String dropdownXPath, String optionName){
            waitUntilSpinnerIsInvisible();
            WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dropdownXPath)));
            clickElement(dropdown);
            String optionXPath = String.format("//ng-select[@name='DEBIT_CARD_PRODUCTS']//span[normalize-space()='%s']", optionName);
            WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(optionXPath)));
            clickElement(option);
        }

        public void selectDebitCardPickupBranch (String dropdownXPath, String optionName){
            waitUntilSpinnerIsInvisible();
            WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dropdownXPath)));
            clickElement(dropdown);
            String optionXPath = String.format("//ng-select[@name='DEBIT_CARD_PICKUP_BRANCH']//span[normalize-space()='%s']", optionName);
            WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(optionXPath)));
            clickElement(option);
        }

        public void selectChequeBook (String dropdownXPath, String optionName){
            waitUntilSpinnerIsInvisible();
            WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dropdownXPath)));
            clickElement(dropdown);
            String optionXPath = String.format("//ng-select[@name='CHEQUE_BOOK_REQUIRED']//span[normalize-space()='%s']", optionName);
            WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(optionXPath)));
            clickElement(option);
        }

        public void selectChequeBookLeaves (String dropdownXPath, String optionName){
            waitUntilSpinnerIsInvisible();
            WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dropdownXPath)));
            clickElement(dropdown);
            String optionXPath = String.format("//ng-select[@name='CHEQUE_BOOK_LEAVES']//span[normalize-space()='%s']", optionName);
            WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(optionXPath)));
            clickElement(option);
        }

        public void selectChequeBookPickupBranch (String dropdownXPath, String optionName){
            waitUntilSpinnerIsInvisible();
            WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dropdownXPath)));
            clickElement(dropdown);
            String optionXPath = String.format("//ng-select[@name='CHEQUE_BOOK_PICKUP_BRANCH']//span[normalize-space()='%s']", optionName);
            WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(optionXPath)));
            clickElement(option);
        }

        public void selectGeoBusSpread (String dropdownXPath, String optionName){
            waitUntilSpinnerIsInvisible();
            WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dropdownXPath)));
            clickElement(dropdown);
            String optionXPath = String.format("//ng-select[@name='geographicalBusinessSpread']//span[normalize-space()='%s']", optionName);
            WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(optionXPath)));
            clickElement(option);
        }

        public void selectTypeTrans (String dropdownXPath, String optionName){
            waitUntilSpinnerIsInvisible();
            WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dropdownXPath)));
            clickElement(dropdown);
            String optionXPath = String.format("//ng-select[@name='typeOfTransactions']//span[normalize-space()='%s']", optionName);
            WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(optionXPath)));
            clickElement(option);
        }

        public void selectExpModesTrans (String dropdownXPath, String optionName){
            waitUntilSpinnerIsInvisible();
            WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dropdownXPath)));
            clickElement(dropdown);
            String optionXPath = String.format("//ng-select[@name='expectedModesOfTransaction']//span[normalize-space()='%s']", optionName);
            WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(optionXPath)));
            clickElement(option);
        }

        public void selectExpCountParties (String dropdownXPath, String optionName){
            waitUntilSpinnerIsInvisible();
            WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dropdownXPath)));
            clickElement(dropdown);
            String optionXPath = String.format("//ng-select[@name='EXPECTED_COUNTER_PARTIES']//span[normalize-space()='%s']", optionName);
            WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(optionXPath)));
            clickElement(option);
        }

        public void selectExpMonDebTrans (String dropdownXPath, String optionName){
            waitUntilSpinnerIsInvisible();
            WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dropdownXPath)));
            clickElement(dropdown);
            String optionXPath = String.format("//ng-select[@name='expectedmonthlydebittransaction']//span[normalize-space()='%s']", optionName);
            WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(optionXPath)));
            clickElement(option);
        }

        public void selectExpMonCredTrans (String dropdownXPath, String optionName){
            waitUntilSpinnerIsInvisible();
            WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dropdownXPath)));
            clickElement(dropdown);
            String optionXPath = String.format("//ng-select[@name='expectedmonthlycredittransaction']//span[normalize-space()='%s']", optionName);
            WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(optionXPath)));
            clickElement(option);
        }

        public void selectExpMonDebTurn (String dropdownXPath, String optionName){
            waitUntilSpinnerIsInvisible();
            WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dropdownXPath)));
            clickElement(dropdown);
            String optionXPath = String.format("//ng-select[@name='expectedmonthlydebitturnover']//span[normalize-space()='%s']", optionName);
            WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(optionXPath)));
            clickElement(option);
        }

        public void selectExpMonCredTurn (String dropdownXPath, String optionName){
            waitUntilSpinnerIsInvisible();
            WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dropdownXPath)));
            clickElement(dropdown);
            String optionXPath = String.format("//ng-select[@name='expectedmonthlycreditturnover']//span[normalize-space()='%s']", optionName);
            WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(optionXPath)));
            clickElement(option);
        }

        public void selectProvince (String dropdownXPath, String optionName){
            waitUntilSpinnerIsInvisible();
            WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dropdownXPath)));
            clickElement(dropdown);
            String optionXPath = String.format("//ng-select[@name='province']//span[normalize-space()='%s']", optionName);
            WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(optionXPath)));
            clickElement(option);
        }

        public void selectPhotoAcc (String dropdownXPath, String optionName){
            waitUntilSpinnerIsInvisible();
            WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dropdownXPath)));
            clickElement(dropdown);
            String optionXPath = String.format("//ng-select[@name='photo-account']//span[normalize-space()='%s']", optionName);
            WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(optionXPath)));
            clickElement(option);
        }

        public void selectJSAccHolder (String dropdownXPath, String optionName){
            waitUntilSpinnerIsInvisible();
            WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dropdownXPath)));
            clickElement(dropdown);
            String optionXPath = String.format("//ng-select[@name='isJsAcctHolder']//span[normalize-space()='%s']", optionName);
            WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(optionXPath)));
            clickElement(option);
        }
    public void selectCountParty (String dropdownXPath, String optionName){
        waitUntilSpinnerIsInvisible();
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dropdownXPath)));
        clickElement(dropdown);
        String optionXPath = String.format("//ng-select[@name='COUNTER_PARTY_INDUSTRY']//span[normalize-space()='%s']", optionName);
        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(optionXPath)));
        clickElement(option);
    }
        public void selectEcoActivity (String dropdownXPath, String optionName){
            waitUntilSpinnerIsInvisible();
            WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dropdownXPath)));
            clickElement(dropdown);
            String optionXPath = String.format("//ng-select[@name='economyActivity']//span[normalize-space()='%s']", optionName);
            WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(optionXPath)));
            clickElement(option);
        }
    public void selectClassOfIS (String dropdownXPath, String optionName){
        waitUntilSpinnerIsInvisible();
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dropdownXPath)));
        clickElement(dropdown);
        String optionXPath = String.format("//ng-select[@name='classificationOfIS']//span[normalize-space()='%s']", optionName);
        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(optionXPath)));
        clickElement(option);
    }
    public void selectEStatement (String dropdownXPath, String optionName){
        waitUntilSpinnerIsInvisible();
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dropdownXPath)));
        clickElement(dropdown);
        String optionXPath = String.format("//ng-select[@name='eStatement']//span[normalize-space()='%s']", optionName);
        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(optionXPath)));
        clickElement(option);
    }
    public void selectEStatementFreq (String dropdownXPath, String optionName){
        waitUntilSpinnerIsInvisible();
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dropdownXPath)));
        clickElement(dropdown);
        String optionXPath = String.format("//ng-select[@name='E_STATEMENT_FRQ']//span[normalize-space()='%s']", optionName);
        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(optionXPath)));
        clickElement(option);
    }
    public void selectSMSAlert (String dropdownXPath, String optionName){
        waitUntilSpinnerIsInvisible();
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dropdownXPath)));
        clickElement(dropdown);
        String optionXPath = String.format("//ng-select[@name='CUST_ACCOUNT_SMS_ALERT']//span[normalize-space()='%s']", optionName);
        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(optionXPath)));
        clickElement(option);
    }
    public void selectMobileTelecom (String dropdownXPath, String optionName){
        waitUntilSpinnerIsInvisible();
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dropdownXPath)));
        clickElement(dropdown);
        String optionXPath = String.format("//ng-select[@name='TELECOM']//span[normalize-space()='%s']", optionName);
        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(optionXPath)));
        clickElement(option);
    }
    public void selectInternetBanking (String dropdownXPath, String optionName){
        waitUntilSpinnerIsInvisible();
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dropdownXPath)));
        clickElement(dropdown);
        String optionXPath = String.format("//ng-select[@name='INTERNET_BANK']//span[normalize-space()='%s']", optionName);
        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(optionXPath)));
        clickElement(option);
    }
    public void selectMobileBanking (String dropdownXPath, String optionName){
        waitUntilSpinnerIsInvisible();
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dropdownXPath)));
        clickElement(dropdown);
        String optionXPath = String.format("//ng-select[@name='MOBILE_BANKING']//span[normalize-space()='%s']", optionName);
        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(optionXPath)));
        clickElement(option);
    }
    public void selectAccOfficer (String dropdownXPath, String optionName){
        waitUntilSpinnerIsInvisible();
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dropdownXPath)));
        clickElement(dropdown);
        String optionXPath = String.format("//ng-select[@name='accountofficer']//span[normalize-space()='%s']", optionName);
        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(optionXPath)));
        clickElement(option);
    }
    public void selectGenderAcc (String dropdownXPath, String optionName){
        waitUntilSpinnerIsInvisible();
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dropdownXPath)));
        clickElement(dropdown);
        String optionXPath = String.format("//ng-select[@bindlabel='GENDER_DESC']//span[normalize-space()='%s']", optionName);
        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(optionXPath)));
        clickElement(option);
    }
    public void selectAccCountry (String dropdownXPath, String optionName){
        waitUntilSpinnerIsInvisible();
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dropdownXPath)));
        clickElement(dropdown);
        String optionXPath = String.format("//ng-select[@name='accountcountry']//span[normalize-space()='%s']", optionName);
        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(optionXPath)));
        clickElement(option);
    }


    }

