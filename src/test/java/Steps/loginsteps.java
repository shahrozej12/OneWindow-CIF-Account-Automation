package Steps;

import define.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import locators.XPathLocators;
import define.Account_actions;
import define.ImageUploadHelper;
import define.AutoITHandler;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.*;
import define.VideoRecorder;
import io.cucumber.java.Scenario;

import java.util.List;
import define.ExcelHelper;

import static define.ExcelHelper.getNextRowData;

public class loginsteps {

    private WebDriver driver;
    private WebDriverWait wait;
    private CIF_actions cifHelpers;
    private Account_actions accHelpers;
    private DatePickerHandler date;
    private AutoITHandler autoITHandler;
    private String sectorCode;
    private String nationality;
    private ImageUploadHelper imageUploadHelper;
    private MyAutomation automation;
    private Checker_Tracking_CIF trackingCif;
    private RequestApprovalSteps requestApprovalSteps;
    private VideoRecorder recorder;


    public loginsteps() {
        automation = new MyAutomation();

    }

    @Before
    public void setup(Scenario scenario) throws Exception {

        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\Users\\Shahroze.Janjua\\Downloads\\chrome-win64_stable\\chrome-win64\\chrome.exe");


            options.addArguments("--headless=new");
//            options.addArguments("--disable-gpu");
//            options.addArguments("--no-sandbox");
//            options.addArguments("--disable-dev-shm-usage");
//            options.addArguments("--window-size=1920,1080");


        // Initialize driver
        driver = new ChromeDriver(options);



        wait = new WebDriverWait(driver, Duration.ofSeconds(45));

        // Initialize recorder
            recorder = new VideoRecorder();

            // Ensure recordings folder exists
            File folder = new File("recordings");
            if (!folder.exists()) folder.mkdirs();

            // Create video file name
            String videoFile = "recordings/" + scenario.getName().replaceAll("[^a-zA-Z0-9]", "_") + ".mp4";

        // Start recording
        recorder.startRecording(videoFile);

        // Add shutdown hook: ensures recording stops even if test is manually stopped
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (recorder != null && recorder.isRecording()) {
                recorder.stopRecording();
                System.out.println("Recording stopped via shutdown hook.");
            }
        }));

    }


    @Given("I am on the login page")
    public void i_am_on_the_login_page() {
        driver.get("https://uatcbao.jsbl.com:9060/login");
        cifHelpers = new CIF_actions(driver);
        accHelpers = new Account_actions(driver);
        trackingCif = new Checker_Tracking_CIF(driver);
        requestApprovalSteps = new RequestApprovalSteps(driver);


    }

    @And("I fill in the login information:")

    public void i_fill_in_the_login_information(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> data = dataTable.asMap(String.class, String.class);
        driver.findElement(By.xpath(XPathLocators.USERNAME_FIELD)).sendKeys(data.get("username"));
        driver.findElement(By.xpath(XPathLocators.PASSWORD_FIELD)).sendKeys(data.get("password"));

    }

    @When("I log in")
    public void i_log_in() {
        runThreadsAfterFormSubmission();
        driver.findElement(By.xpath(XPathLocators.LOGIN_BUTTON)).click();
        runThreadsAfterFormSubmission();
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(7));

            WebElement skipBtn = shortWait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath(XPathLocators.SkipIt))
            );

            skipBtn.click();
            System.out.println("Skip button clicked.");

        } catch (Exception e) {
            // Element not found → continue test
            System.out.println("Skip button not present, moving ahead.");
        }

// Continue normal wait
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XPathLocators.Customer_UAT)));
    }

    @When("I navigate to customer information")
    public void i_navigate_to_customer_information()  {
        WebElement customerInfoButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPathLocators.Customer_UAT)));
        customerInfoButton.click();
        pause(2000);
        waitUntilSpinnerIsInvisible();
        runThreadsAfterFormSubmission();

    }

    @When("I navigate to CIF")
    public void i_navigate_to_cif() {
        WebElement cifButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPathLocators.CIF_OPENING_BUTTON)));
        cifButton.click();


    }
    private void setDateUsingJavaScript(String xpath, String dateValue) {
        WebElement dateElement = driver.findElement(By.xpath(xpath));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript(
                "let el = arguments[0];" +
                        "el.value = arguments[1];" +
                        "el.dispatchEvent(new Event('input', { bubbles: true }));" +
                        "el.dispatchEvent(new Event('change', { bubbles: true }));" +
                        "el.dispatchEvent(new Event('blur', { bubbles: true }));",
                dateElement,
                dateValue
        );
    }

    @When("I fill in the customer information:")
    public void i_fill_in_the_customer_information(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> data = dataTable.asMap(String.class, String.class);

        if (data != null) {
            cifHelpers.selectDropdownOption(XPathLocators.CUSTOMER_CATEGORY_DROPDOWN, data.get("Customer Category"));
            pause(1000);
            cifHelpers.selectIdDocumentType(XPathLocators.ID_DOCUMENT_TYPE_DROPDOWN, data.get("ID Document Type"));
            pause(1000);
            String idDocNo = data.get("ID Document Number");
            cifHelpers.enterCnicAndClickButton(idDocNo); // Pass CNIC here
            // Get date field elements
            String cnicIssuanceDate = data.get("CNIC Issuance Date");
            String cnicExpiryDate = data.get("CNIC Expiry Date");
            String visaExpiryDate = data.get("VISA Expiry Date");
            String dateofBirth = data.get("Date Of Birth");

            // Use JavaScript to set CNIC Issuance Date
            setDateUsingJavaScript(XPathLocators.CNIC_ISSUANCE_DATE_FIELD, cnicIssuanceDate);

            // Use JavaScript to set CNIC Expiry Date
            setDateUsingJavaScript(XPathLocators.CNIC_EXPIRY_DATE_FIELD, cnicExpiryDate);

            // Use JavaScript to set Visa Expiry Date
            setDateUsingJavaScript(XPathLocators.VISA_EXPIRY_DATE, visaExpiryDate);

            // Use JavaScript to set DOB
            setDateUsingJavaScript(XPathLocators.DOB, dateofBirth);

            cifHelpers.selectCustomerSegment(XPathLocators.SECTOR_CODE_DROPDOWN, data.get("Sector Code"));
            pause(2000);
            cifHelpers.selectNationality(XPathLocators.NATIONALITY_DROPDOWN, data.get("Nationality"));
            pause(2000);
            cifHelpers.selectAssanAccount(XPathLocators.ASAN_ACCOUNT_DROPDOWN, data.get("Assan Account"));
            pause(1000);
            cifHelpers.selectMarital(XPathLocators.MARITAL_STATUS_DROPDOWN, data.get("Marital Status"));
            pause(1000);

            // Customer Title Validation
            String customerTitle = data.get("Customer Title");
            if (customerTitle != null && !customerTitle.isEmpty()) {
                // Select the Customer Title dropdown value
                cifHelpers.selectCustomerTitle(XPathLocators.CUSTOMER_TITLE_DROPDOWN, customerTitle);
            } else {
                System.out.println("Customer Title is missing or empty.");
                throw new IllegalArgumentException("Customer Title is missing or empty.");
            }
            pause(1000);

            // Get Gender value
            String gender = data.get("Gender");
            if (gender != null && !gender.isEmpty()) {
                // Validate Gender based on Customer Title
                if ("Mr.".equalsIgnoreCase(customerTitle)) {
                    if (!"Male".equalsIgnoreCase(gender)) {
                        throw new IllegalArgumentException("For 'Mr.' title, gender must be Male. Provided: " + gender);
                    }
                } else if ("Dr.".equalsIgnoreCase(customerTitle)) {
                    // 'Dr.' can be Male or Female
                    if (!"Male".equalsIgnoreCase(gender) && !"Female".equalsIgnoreCase(gender)) {
                        throw new IllegalArgumentException("For 'Dr.' title, gender must be either Male or Female. Provided: " + gender);
                    }
                } else if ("Ms.".equalsIgnoreCase(customerTitle) || "Mrs.".equalsIgnoreCase(customerTitle) || "M/S.".equalsIgnoreCase(customerTitle)) {
                    if (!"Female".equalsIgnoreCase(gender)) {
                        throw new IllegalArgumentException("For 'Ms.', 'Mrs.', or 'M/S.' titles, gender must be Female. Provided: " + gender);
                    }
                } else {
                    // If title is not recognized
                    throw new IllegalArgumentException("Gender validation failed for title: " + customerTitle);
                }

                // If the gender is valid, select the gender from the dropdown
                cifHelpers.selectGender(XPathLocators.GENDER_DROPDOWN, gender);
            } else {
                System.out.println("Gender is missing or empty.");
                throw new IllegalArgumentException("Gender is missing or empty.");
            }


            // Fill in personal information
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XPathLocators.FIRST_NAME_FIELD))).sendKeys(data.get("First Name"));
            pause(1000);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XPathLocators.MIDDLE_NAME_FIELD))).sendKeys(data.get("Middle Name"));
            pause(1000);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XPathLocators.LAST_NAME_FIELD))).sendKeys(data.get("Last Name"));
            pause(1000);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XPathLocators.FATHER_NAME_FIELD))).sendKeys(data.get("Father's Name"));
            pause(1000);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XPathLocators.MOTHER_NAME_FIELD))).sendKeys(data.get("Mother's Name"));
            pause(1000);


            // Check if nationality is NOT Pakistan
            nationality = data.get("Nationality");

            if (!data.get("Nationality").equalsIgnoreCase("Pakistan")) {
                // If nationality is not Pakistan,
                cifHelpers.selectTaxReason(XPathLocators.TAX_REASON_DROPDOWN, data.get("Tax Reason (CRS)"));
                pause(1000);
                cifHelpers.selectTaxResidence(XPathLocators.TAX_RESIDENCY_DROPDOWN, data.get("Tax Residence (CRS)"));
                pause(1000);

            } else {

            }


            // Check if Sector Code is "Sole Proprietorship" or "Self-Employed" to decide whether to fill in Occupation and Industry Code
            sectorCode = data.get("Sector Code");
            if ("1100 - Sole Proprietership".equalsIgnoreCase(sectorCode) || "1019 - Self Employed".equalsIgnoreCase(sectorCode)) {
                cifHelpers.selectOccupation(XPathLocators.OCCUPATION_DROPDOWN, data.get("Occupation"));
                cifHelpers.selectIndusCode(XPathLocators.INDUSTRY_CODE_DROPDOWN, data.get("Industry Code"));
            }
        }
    }

    @Then("Click on CUST_INFO_Next")
    public void clickOnCUST_INFO_Next() {
        WebElement customerInfoNextButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPathLocators.CUSTOMER_INFORMATION_NEXT)));
        customerInfoNextButton.click();
    }


    @When("I fill in the PEP-Customer Demographic:")
    public void iFillInThePEPCustomerDemographic(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> data = dataTable.asMap(String.class, String.class);


        cifHelpers.selectPep(XPathLocators.PEP_DROPDOWN, data.get("PEP"));
        pause(1000);
        // Check if the PEP value is not "No", then fill in the PEP Status dropdown
        if (!data.get("PEP").equalsIgnoreCase("No")) {
            cifHelpers.selectPepStatus(XPathLocators.PEP_STATUS_DROPDOWN, data.get("PEP Status"));
            pause(1000);
        } else {
            // Optionally, you can disable the PEP Status field or log a message
            System.out.println("PEP Status dropdown is disabled because PEP is selected as 'No'");
        }
        cifHelpers.selectAddressType(XPathLocators.ADDRESS_TYPE, data.get("Address Type"));
        pause(1000);
        cifHelpers.selectProvince(XPathLocators.PROVINCE, data.get("Province"));
        pause(1000);
        cifHelpers.selectEmployee_Status(XPathLocators.EMP_STATUS, data.get("Employee Status"));
        pause(1000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XPathLocators.EMP_NAME))).sendKeys(data.get("Employer Name"));
        pause(1000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XPathLocators.DESIGNATION))).sendKeys(data.get("Designation"));
        pause(1000);
        cifHelpers.selectRMCode(XPathLocators.RM_CODE, data.get("RM Code"));
        pause(1000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XPathLocators.DSRCODE))).sendKeys(data.get("DSR Code"));
        pause(1000);
        cifHelpers.selectEducation(XPathLocators.EDUCATION, data.get("Education"));
        pause(3000);
        cifHelpers.selectPlaceBirth(XPathLocators.PLACE_BIRTH, data.get("Place of Birth"));
        pause(3000);

        if ("1100 - Sole Proprietership".equalsIgnoreCase(sectorCode)) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XPathLocators.BUSINESS_NAME)))
                    .sendKeys(data.get("Business Name"));
            pause(1000);

        } else {
            // Do nothing in the else case (you can leave it empty or log an info message)
        }

    }

    @Then("Click on PEP_Next")
    public void clickOnPEP_Next() {
        WebElement customerInfoNextButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPathLocators.PEP_NEXT)));
        customerInfoNextButton.click();
    }

    @When("I fill in the Contact Detail:")
    public void iFillInTheContactDetail(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> data = dataTable.asMap(String.class, String.class);


        // Cell Mobile Number Validation
        String cellCountryCode = data.get("Cell Country Code");
        String cellMobileNumber = data.get("Cell Mobile Number");
        cifHelpers.selectCellCountryCode(XPathLocators.Cell_Country_Code, cellCountryCode);
                pause(1000);
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XPathLocators.Cell_No)))
                        .sendKeys(cellMobileNumber);
                pause(1000);



        // Residence Phone Number Validation
        String resCountryCode = data.get("Residence Country Code");
        String resPhoneNumber = data.get("Residence Phone Number");
        if ("Pakistan".equalsIgnoreCase(resCountryCode)) {
            // Ensure residence phone number starts with "03" and is exactly 11 digits long
            if (resPhoneNumber.startsWith("02") && resPhoneNumber.length() == 11 && resPhoneNumber.matches("\\d{11}")) {
                cifHelpers.selectResCounryCode(XPathLocators.Res_Country_Code, resCountryCode);
                pause(1000);
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XPathLocators.Res_No)))
                        .sendKeys(resPhoneNumber);
                pause(1000);
            } else {
                throw new IllegalArgumentException("Invalid Residence Phone Number for Pakistan: Must start with '03' and be exactly 11 digits.");
            }
        } else {
            // If not Pakistan, just ensure the number is exactly 11 digits
            if (resPhoneNumber.length() == 11 && resPhoneNumber.matches("\\d{11}")) {
                cifHelpers.selectResCounryCode(XPathLocators.Res_Country_Code, resCountryCode);
                pause(1000);
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XPathLocators.Res_No)))
                        .sendKeys(resPhoneNumber);
                pause(1000);
            } else {
                throw new IllegalArgumentException("Invalid Residence Phone Number: Must be exactly 11 digits.");
            }
        }

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XPathLocators.Res_Add_1)))
                .sendKeys(data.get("Residential Address Line 1"));
        pause(1000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XPathLocators.Res_Add_2)))
                .sendKeys(data.get("Residential Address Line 2"));
        pause(1000);

        // Email Address Validation: Must contain "@" and end with ".com"
        String emailAddress = data.get("Email Address");
        if (emailAddress.contains("@") && emailAddress.endsWith(".com")) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XPathLocators.Email)))
                    .sendKeys(emailAddress);
            pause(1000);
        } else {
            System.out.println("Invalid Email Address: Must contain '@' and end with '.com'.");
            throw new IllegalArgumentException("Invalid Email Address: Must contain '@' and end with '.com'.");
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XPathLocators.Office_Add_1)))
                .sendKeys(data.get("Office / Business Address Line 1"));
        pause(1000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XPathLocators.Office_Add_2)))
                .sendKeys(data.get("Office / Business Address Line 2"));
        pause(1000);
        cifHelpers.selectMailingAdd(XPathLocators.Pref_Mail_Add, data.get("Preferred Mailing Address"));
        pause(1000);
             cifHelpers.selectMobileTelecom(XPathLocators.Mobile_Telecom, data.get("Mobile Telecom"));
               pause(1000);
    }

    @When("I fill in the Account Detail:")
    public void     iFillInTheAccountDetail(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> data = dataTable.asMap(String.class, String.class);


        // Fill dropdown fields based on the Gherkin table data
//        accHelpers.selectAccOpType(XPathLocators.Acc_Op_Type, data.get("Account Operational Type"));
//        pause(1000);

        accHelpers.selectAccType(XPathLocators.Acc_Type, data.get("Account Type"));
        pause(1000);

        accHelpers.selectCurrency(XPathLocators.Currency, data.get("Currency"));
        pause(1000);

        accHelpers.selectDebitRequest(XPathLocators.Debit_Request, data.get("Debit Card Request"));
        pause(1000);
        // Handle Debit Card logic
        if (data.get("Debit Card Request").equalsIgnoreCase("YES")) {
            accHelpers.selectDebitCardType(XPathLocators.Debit_Card_Type, data.get("Debit Card Type"));
            pause(1000);

            accHelpers.selectDebitCardPickupBranch(XPathLocators.Debit_Card_Pickup_branch, data.get("Debit Card Pickup Branch"));
            pause(1000);
        } else {
            System.out.println("No need of Debit Card");

        }


        accHelpers.selectChequeBook(XPathLocators.Cheque_Book, data.get("Cheque Book Request"));
        pause(1000);
        // Handle Credit Card logic
        if (data.get("Cheque Book Request").equalsIgnoreCase("YES")) {
            accHelpers.selectChequeBookLeaves(XPathLocators.Cheque_Book_Leaves, data.get("Cheque Book Leaves"));
            pause(1000);

            accHelpers.selectChequeBookPickupBranch(XPathLocators.Cheque_Book_Pickup_Branch, data.get("Cheque Book Pickup Branch"));
            pause(1000);
        } else {
            System.out.println("No need of Debit Card");

        }


        accHelpers.selectGeoBusSpread(XPathLocators.Geo_Bus_Spread, data.get("Geo Business Spread"));
        pause(1000);

        accHelpers.selectTypeTrans(XPathLocators.Type_Trans, data.get("Type of Transactions"));
        pause(1000);

        accHelpers.selectExpModesTrans(XPathLocators.Exp_modes_Trans, data.get("Expected Modes of Transaction"));
        pause(1000);

        accHelpers.selectExpCountParties(XPathLocators.Exp_Count_Parties, data.get("Expected Counter Parties"));
        pause(1000);


        // Retrieve the value from Excel
        String debitValue = data.get("Expected Monthly Debit Transactions");
        String creditValue = data.get("Expected Monthly Credit Transactions");

        // Remove the decimal part if the value is a whole number (ends with .0)
        debitValue = debitValue.matches("\\d+\\.0$") ? debitValue.substring(0, debitValue.indexOf(".0")) : debitValue;
        creditValue = creditValue.matches("\\d+\\.0$") ? creditValue.substring(0, creditValue.indexOf(".0")) : creditValue;

        // Pass the cleaned values to the methods
        accHelpers.selectExpMonDebTrans(XPathLocators.Exp_Mon_Deb_Trans, debitValue);
        pause(1000);

        accHelpers.selectExpMonCredTrans(XPathLocators.Exp_Mon_Cred_Trans, creditValue);
        pause(1000);


//                accHelpers.selectExpMonDebTurn(XPathLocators.Exp_Mon_Deb_Turn, data.get("Expected Monthly Debit Turnover"));
//                pause(1000);

                accHelpers.selectExpMonCredTurn(XPathLocators.Exp_Mon_Cred_Turn, data.get("Expected Monthly Credit Turnover"));
                pause(1000);

        accHelpers.selectProvince(XPathLocators.Province, data.get("Provinces"));
        pause(1000);

        accHelpers.selectPhotoAcc(XPathLocators.Photo_Acc, data.get("Photo Account"));
        pause(1000);
        accHelpers.selectJSAccHolder(XPathLocators.JS_Acc_Holder, data.get("JS Account Holder"));
        pause(1000);
        if (data.get("JS Account Holder").equalsIgnoreCase("YES")) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XPathLocators.JS_Acc)))
                    .sendKeys(data.get("JS Account Number"));
            pause(1000);
        } else {
            // Optionally, you can disable the PEP Status field or log a message
            accHelpers.selectCountParty(XPathLocators.Counter_Party_Industry, data.get("Counter Party Industry"));
            pause(1000);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XPathLocators.Counter_Party_Name)))
                    .sendKeys(data.get("Name of Counter Party"));
            pause(1000);
        }

        pause(1000);
//        // Handle E-Statement logic
//        accHelpers.selectEStatement(XPathLocators.E_Statement, data.get("E-Statement"));
//        pause(1000);

//        if (data.get("E-Statement").equalsIgnoreCase("YES")) {
//            // Enable E-Statement Frequency
//            accHelpers.selectEStatementFreq(XPathLocators.E_Statement_Freq, data.get("E-Statement Frequency"));
//            pause(1000);
//        } else {
//            System.out.println("Working E-statement");
//        }
//
//        // Handle SMS Alert logic
        accHelpers.selectSMSAlert(XPathLocators.Sms_alert, data.get("SMS Alert"));
        pause(1000);


        accHelpers.selectInternetBanking(XPathLocators.Internet_bank, data.get("Internet Banking"));
        pause(1000);

        accHelpers.selectMobileBanking(XPathLocators.Mobile_bank, data.get("Mobile Banking"));
        pause(1000);

        accHelpers.selectAccOfficer(XPathLocators.Acc_officer, data.get("Account Officer"));
        pause(1000);

        accHelpers.selectGenderAcc(XPathLocators.Gender_ac, data.get("Gender (Account)"));
        pause(1000);
        accHelpers.selectAccCountry(XPathLocators.Acc_Country, data.get("Account Country"));
        pause(1000);


        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XPathLocators.ACC_KYC_Remarks)))
                .sendKeys(data.get("Account KYC Remarks"));
        pause(1000);


    }

    @When("I fill in the Next Of Kin Detail:")
    public void iFillInTheNextOfKinDetail(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> data = dataTable.asMap(String.class, String.class);

        pause(1000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XPathLocators.Kin_Name)))
                .sendKeys(data.get("Next of Kin Name"));
        pause(1000);
        cifHelpers.selectKinRelationship(XPathLocators.Kin_relationship, data.get("Next of Kin Relationship"));
        pause(1000);
        String mobileNumber = data.get("Next of Kin Mobile Number");

        if (mobileNumber.length() == 11 && mobileNumber.matches("\\d{11}")) {
            // Proceed with entering the valid mobile number
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XPathLocators.Kin_No)))
                    .sendKeys(mobileNumber);
            pause(1000); // Optional: Pause after entering the value
        } else {
            System.out.println("Invalid Mobile number");
            throw new IllegalArgumentException("Invalid mobile number. The number must be 11 digits long and start with '03'.");
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XPathLocators.Kin_Add_1)))
                .sendKeys(data.get("Next of Kin Address Line 1"));
        pause(1000);
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XPathLocators.Kin_Add_2)))
//                .sendKeys(data.get("Next of Kin Address Line 2"));
//        pause(1000);
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XPathLocators.KYC_Remarks)))
//                .sendKeys(data.get("KYC Remarks"));
//        pause(1000);
    }

    // Method to pause execution for a specified time in milliseconds
    private void pause(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore interrupted status
        }
    }


    @Then("Click on CONT_DET_Next")
    public void clickOnCONT_DET_Next() {
        WebElement contactDetailNextButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPathLocators.Cont_det_Next)));
        contactDetailNextButton.click();
    }


    @Then("Click on KIN_Next")
    public void clickOnKIN_Next() {
        WebElement contactDetailNextButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPathLocators.Proc_To_Acc)));
        contactDetailNextButton.click();
    }



    @Then("Submit the form")
    public void submitTheForm() throws IOException, InterruptedException {
        WebElement contactAccSubmitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPathLocators.Acc_Submit)));
        contactAccSubmitButton.click();
        pause(2000);
        waitUntilSpinnerIsInvisible();
        runThreadsAfterFormSubmission();


    }

    private void waitUntilSpinnerIsInvisible() {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("spinner-overlay")));
        } catch (Exception e) {
            System.out.println("Spinner did not disappear: " + e.getMessage());
        }
    }

    @Then("Send To Supervisor")
    public void sendToSupervisor() {
        WebElement sendSupervisorButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPathLocators.SendToSupervisor)));
        sendSupervisorButton.click();
        pause(3000);
        WebElement sendSupervisorYESButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPathLocators.SendToSupervisor_YES)));
        sendSupervisorYESButton.click();
        waitUntilSpinnerIsInvisible();
        pause(3000);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(XPathLocators.TRACKING_INFO)));
        trackingCif.extractTrackingInfo();  // Call the method to extract Tracking ID and CIF No


        WebElement approveBackSButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPathLocators.Approve_Back)));
        approveBackSButton.click();
        pause(3000);
    }


    @And("Uploading Files:")
    public void uploadingFiles(io.cucumber.datatable.DataTable dataTable) throws IOException {
        // Iterate over each row in the DataTable
        for (Map<String, String> row : dataTable.asMaps(String.class, String.class)) {
            String documentName = row.get("Document Name");  // Get the document name
            String filePath = row.get("File Path");          // Get the file path




            // Upload the document if it’s not skipped
            uploadFile(documentName, filePath);
        }
    }


    public void uploadFile(String documentName, String filePath) throws IOException {
        // Get dynamic locators for the Browse button, File input, and Upload button
        String browseXpath = XPathLocators.getBrowseButtonXpath(documentName);
        String fileInputXpath = XPathLocators.getFileInputXpath(documentName);
        String uploadXpath = XPathLocators.getUploadButtonXpath(documentName);

        // Click the Browse button
        WebElement browseButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(browseXpath)));
        browseButton.click();

        // Wait for the file input to appear, and upload the file
        WebElement fileInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(fileInputXpath)));
        fileInput.sendKeys(filePath);
        AutoITHandler.closeFileExplorer();

        // Click the Upload button once the file is uploaded
        WebElement uploadButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(uploadXpath)));
        uploadButton.click();
        pause(3000);
        waitUntilSpinnerIsInvisible();
    }

    @After
    public void tearDown(Scenario scenario) {
        // Stop video recording first
        if (recorder != null && recorder.isRecording()) {
            recorder.stopRecording();
            System.out.println("Recording stopped for scenario: " + scenario.getName());
        }

        // Then close browser
        if (driver != null) {
            driver.quit();
            pause(2000); // optional, wait to ensure browser closes
            System.out.println("Browser closed for scenario: " + scenario.getName());
        }
    }

    @And("Go Back To Market Place")
    public void goBackToMarketPlace() {
        WebElement sendBackButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPathLocators.Back_To_Market_Place)));
        sendBackButton.click();
        pause(3000);
        waitUntilSpinnerIsInvisible();

    }

    @And("Select {string}")
    public void select(String arg) {
        cifHelpers.selectChecker(arg);
    }

    @And("I navigate to customer information UAT")
    public void iNavigateToCustomerInformationUAT() {
        WebElement customerInfoButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPathLocators.Customer_UAT_Demo)));
        customerInfoButton.click();
        pause(2000);
            runThreadsAfterFormSubmission();

    }

    @And("I search and click the Tracking Id")
    public void iSearchAndClickTheTrackingId() {
        pause(2000);
        trackingCif.findAndClickTrackingID();
        pause(5000);
        System.out.println("DONE");
    }




    @And("Enter Comments {string} and Press {string} and Press {string}")
    public void enterCommentsAndPressAndPress(String comment, String request, String yes_no) {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,700)");

        // Step 1: Enter the comments in the textarea
        requestApprovalSteps.enterComments(comment);
        pause(2000);
        // Step 2: Click the dynamic Request button (Approve or Reject)
        requestApprovalSteps.clickRequestButton(request);
        pause(2000);
        // Step 3: Handle the Yes/No confirmation dialog and click the respective button
        requestApprovalSteps.handleYesNoDialog(yes_no);
        pause(5000);
    }

    @Then("Go Back")
    public void goBack() {
        WebElement checkerBackButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPathLocators.Checker_Back)));
        checkerBackButton.click();
        pause(2000);
        WebElement signoffButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPathLocators.Quit)));
        signoffButton.click();
        pause(2000);



    }
    @And("Open T24 environment")
    public void openT24Environment() {


        driver.navigate().to("http://10.111.201.244:9081/T24UAT/servlet/BrowserServlet");
        driver.manage().window().maximize();

    }

    @And("I fill enter Login Credentials for T24:")
    public void iFillEnterLoginCredentialsForT24(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> data = dataTable.asMap(String.class, String.class);

        driver.findElement(By.xpath(XPathLocators.T24_Username)).sendKeys(data.get("username"));
        pause(1000);
        driver.findElement(By.xpath(XPathLocators.T24_Password)).sendKeys(data.get("password"));
    }

    @And("I login in T24")
    public void iLoginInT24() {
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPathLocators.T24_SignIn)));
        loginButton.click();
        pause(4000);
    }


    @And("Enter {string}  and Press Tick Button")
    public void enterAndPressTickButton(String arg0) {
        driver.switchTo().frame(0);
        driver.findElement(By.xpath(XPathLocators.T24_Text)).sendKeys(arg0);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPathLocators.T24_TickButton))).click();
        // Switch back to the main page
        driver.switchTo().defaultContent();


    }

    @And("Enter CIF No and View it")
    public void enterCIFNoAndViewIt() {
        // Get the current window handle
        String mainWindowHandle = driver.getWindowHandle();

        // Wait for the new window or tab to open
        wait.until(ExpectedConditions.numberOfWindowsToBe(2)); // Wait for 2 windows

        // Get all window handles
        Set<String> allWindowHandles = driver.getWindowHandles();

        // Switch to the new window
        for (String windowHandle : allWindowHandles) {
            if (!windowHandle.equals(mainWindowHandle)) {
                driver.switchTo().window(windowHandle);
                System.out.println("Switched to new window.");

                // Get the CIF No from Checker_Tracking_CIF
                String cifNo = trackingCif.getCifNo(); // Use the getter method to retrieve CIF No

                if (cifNo == null || cifNo.isEmpty()) {
                    System.out.println("CIF No is not available.");
                    return;
                }

                // Enter the CIF No into the customer field
                WebElement customerIdField = driver.findElement(By.xpath(XPathLocators.T24_Customer_Text));
                customerIdField.sendKeys(cifNo);
                WebElement viewButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPathLocators.T24_View_Customer)));
                viewButton.click();
                pause(10000);
                // Close the new window after viewing the customer details
                driver.close();
                System.out.println("New window closed.");

                // Switch back to the main window
                driver.switchTo().window(mainWindowHandle);
                System.out.println("Switched back to the main window.");
                break;
            }
        }
    }
    public void runThreadsAfterFormSubmission() {
        try {
            // Create and start Thread t1 (to simulate page load)
            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("Thread t1 started - Page loaded.");
                }
            });
            t1.start(); // Start thread t1

            // Create and start Thread t2 (to simulate pressing Enter key to dismiss pop-up)
            Thread t2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        // Wait for 5 seconds to simulate the pop-up appearing
                        Thread.sleep(5000);

                        // Use Robot class to simulate pressing Enter key
                        Robot robot = new Robot();
                        robot.keyPress(KeyEvent.VK_ENTER);  // Press the Enter key
                        robot.keyRelease(KeyEvent.VK_ENTER); // Release the Enter key

                        System.out.println("Thread t2 started - Pop-up dismissed.");
                    } catch (InterruptedException | AWTException e) {
                        e.printStackTrace(); // Handle InterruptedException or AWTException
                    }
                }
            });
            t2.start(); // Start thread t2

            // Wait for both threads to complete before continuing
            t1.join(); // Ensure the main thread waits for t1 to finish
            t2.join(); // Ensure the main thread waits for t2 to finish

            System.out.println("Both threads have completed.");

        } catch (InterruptedException e) {
            e.printStackTrace(); // Handle any interruptions
        }
    }
    public void runMultipleTimes() {
        for (int i = 0; i < 5; i++) {
            System.out.println("Running iteration " + (i + 1));
            runThreadsAfterFormSubmission(); // Create and run fresh threads each time
        }
    }




}

