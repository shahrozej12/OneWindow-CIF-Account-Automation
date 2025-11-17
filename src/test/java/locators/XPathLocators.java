package locators;

public class XPathLocators {

    // Login Page
    public static final String USERNAME_FIELD = "//input[@name='username']";
    public static final String PASSWORD_FIELD = "//input[@name='password']";
    public static final String LOGIN_BUTTON = "//button[normalize-space()='Secure Sign-In']";

    // Customer Information
    public static final String SkipIt="//button[normalize-space()='Skip for now']";
    public static final String Customer_UAT = "//button[normalize-space()='Customer Management 2 UAT']";
    public static final String Customer_UAT_Demo="//button[normalize-space()='Customer Management 2 UAT Demo']";
    public static final String CIF_OPENING_BUTTON = "//a[normalize-space()='CIF Opening']";
    public static final String  CUSTOMER_CATEGORY_DROPDOWN = "//ng-select[@name='CUSTOMER_CATEGORY']//span[@class='ng-arrow-wrapper']";
    public static final String ID_DOCUMENT_TYPE_DROPDOWN = "//ng-select[@name='ID_DOCUMENT_TYPE']//span[@class='ng-arrow-wrapper']";
    public static final String ID_DOC_NO_FIELD = "//input[@name='idDocNo']";
    public static final String NEW_CIF_BUTTON = "//span[normalize-space()='ETB/NTB']";
    public static final String CNIC_ISSUANCE_DATE_FIELD = "//input[@id='cnicIssuanceDate']";
    public static final String CNIC_EXPIRY_DATE_FIELD = "//input[@id='cnicExpiryDate']";
    public static final String VISA_EXPIRY_DATE = "//input[@id='visaExpiryDate']";
    public static final String DOB = "//input[@id='dateOfBirth']";

    //input[@id='visaExpiryDate']
    // Sector Code
    public static final String SECTOR_CODE_DROPDOWN = "//ng-select[@name='SECTOR_CODE']//span[@class='ng-arrow-wrapper']";

    // Nationality
    public static final String NATIONALITY_DROPDOWN = "//ng-select[@name='NATIONALIT']//span[@class='ng-arrow-wrapper']";
    // Asan Account
    public static final String ASAN_ACCOUNT_DROPDOWN = "//ng-select[@name='ASSAN_ACCOUNT']//span[@class='ng-arrow-wrapper']";
    // Marital Status
    public static final String MARITAL_STATUS_DROPDOWN = "//ng-select[@name='maritalStatus']//span[@class='ng-arrow-wrapper']";
    // Customer Title
    public static final String CUSTOMER_TITLE_DROPDOWN = "//ng-select[@name='Customer_Title']//span[@class='ng-arrow-wrapper']";
    // Gender
    public static final String GENDER_DROPDOWN = "//ng-select[@name='gender']//span[@class='ng-arrow-wrapper']";
    // Occupation
    public static final String OCCUPATION_DROPDOWN = "//ng-select[@name='GENDER_DESC']//span[@class='ng-arrow-wrapper']";
    // Industry Code
    public static final String INDUSTRY_CODE_DROPDOWN = "//ng-select[@name='indusCode']//span[@class='ng-arrow-wrapper']";
    // Personal Information
    public static final String FIRST_NAME_FIELD = "//input[@id='FIRST_NAME']";
    public static final String MIDDLE_NAME_FIELD = "//input[@id='MIDDLE_NAME']";
    public static final String LAST_NAME_FIELD = "//input[@id='LAST_NAME']";
    public static final String FATHER_NAME_FIELD = "//input[@id='FATHER_NAME']";
    public static final String MOTHER_NAME_FIELD = "//input[@id='MOTHER_NAME']";
    // Tax Residency
    public static final String TAX_RESIDENCY_DROPDOWN = "//ng-select[@name='TAX_RES_CRS']//span[@class='ng-arrow-wrapper']";
    public static final String TAX_REASON_DROPDOWN = "//ng-select[@name='TAX_REASON']//span[@class='ng-arrow-wrapper']";
    public static final String Tax_Reason_Detail = "//input[@id='crsTaxReasonDetail']";
    public static final String SAVE_AS_DRAFT = "//button[normalize-space()='Save As Draft'] ";
    public static final String SAVE_AS_DRAFT_BACK = "//input[@value='Back'] ";
    public static final String CUSTOMER_INFORMATION_NEXT = "//button[normalize-space()='NEXT']";

    //PEP-Customer Demographic
    public static final String PEP_DROPDOWN = "//ng-select[@name='pep']//span[@class='ng-arrow-wrapper']";
    public static final String PEP_STATUS_DROPDOWN = "//ng-select[@name='pepStatus']//span[@class='ng-arrow-wrapper']";
    public static final String ADDRESS_TYPE = "//ng-select[@    bindlabel='ADDR_TYPE_DESC']//span[@class='ng-arrow-wrapper']";
    public static final String PROVINCE = "//ng-select[@name='province']//span[@class='ng-arrow-wrapper']";
    public static final String EMP_STATUS = "//ng-select[@name='empStats']//span[@class='ng-arrow-wrapper']";
    public static final String EMP_NAME = "//input[@id='empName']";
    public static final String DESIGNATION = "//input[@id='DESIGNATION']";
    public static final String RM_CODE = "//ng-select[@name='rmCode']//span[@class='ng-arrow-wrapper']";
    public static final String DSRCODE = "//input[@id='dsrCode']";
    public static final String EDUCATION = "//ng-select[@name='EDU_CODE']//span[@class='ng-arrow-wrapper']";
    public static final String PLACE_BIRTH = "//ng-select[@name='BIRTH_PLACE']//span[@class='ng-arrow-wrapper']";
    public static final String PEP_NEXT = "//button[normalize-space()='NEXT']";
    public static final String BUSINESS_NAME = "//input[@id='BUSINESS_NAME']";


//    Contact Detail Page
    public static final String Cell_Country_Code = "//ng-select[@name='CELLCOUNTRYCODE']//span[@class='ng-arrow-wrapper']";
    public static final String  Cell_No= "//input[@id='mobNumb11Dig']";
    public static final String Res_Country_Code = "//ng-select[@name='RESIDENCE_COUNTRY_CODE']//span[@class='ng-arrow-wrapper']";
    public static final String Res_No = "//input[@id='PHONE_NO']";
    public static final String Res_Add_1 = "//input[@id='ADDRESS_LINE1']";
    public static final String Res_Add_2 = "//input[@id='cnicAddressLine2']";
    public static final String Email = "//input[@id='emailAddress']";
    public static final String Office_Add_1 = "//input[@id='correspondenceOffAddLin1']";
    public static final String Office_Add_2 = "//input[@id='correspondenceOffAddLin2']";
    public static final String Pref_Mail_Add = "//ng-select[@name='PREF_ADDR']//span[@class='ng-arrow-wrapper']";
    public static final String Cont_det_Next = "//button[normalize-space()='NEXT']";
    public static final String Mobile_Telecom="//ng-select[@name='TELECOM']//span[@class='ng-arrow-wrapper']";


//    Next Of Kin

    public static final String Kin_Name = "//input[@id='nextOfKinName']";
    public static final String Kin_relationship = "//ng-select[@name='nextOfKinRelCode']//span[@class='ng-arrow-wrapper']";
    public static final String Kin_Add_1 = "//input[@id='nextOfKinAddLin1']";
    public static final String Kin_Add_2 = "//input[@id='nextOfKinAddLin2']";
    public static final String KYC_Remarks = "//textarea[@id='makerComment']";
    public static final String Kin_No = "//input[@id='nextofkinmobilenumber']";
    public static final String Proc_To_Acc = "//button[normalize-space()='PROCEED TO ACCOUNT']";
    public static final String Generate_CIF_Button = "//button[normalize-space()='Generate CIF']";
    public static final String CIF_SAVE = "//div[normalize-space()='ID DOC']/..//div[@class='col-8 value']";
    public static final String JOINT_ID = "//div[normalize-space()='ID DOC']/..//div[@class='col-8 value']";


//    Account detail

        public static final String Acc_Op_Type = "//ng-select[@name='acctOperType']//span[@class='ng-arrow-wrapper']";
        public static final String Acc_Type = "//ng-select[@name='accountCategoryCode']//span[@class='ng-arrow-wrapper']";
        public static final String Currency = "//ng-select[@name='currency']//span[@class='ng-arrow-wrapper']";
        public static final String SBP_Code = "//input[@id='ACCOUNT_SBP_CODE']";
        public static final String Debit_Request = "//ng-select[@name='DEBIT_CARD_REQUIRED']//span[@class='ng-arrow-wrapper']";
        public static final String Debit_Card_Type = "//ng-select[@name='DEBIT_CARD_PRODUCTS']//span[@class='ng-arrow-wrapper']";
        public static final String Debit_Card_Pickup_branch = "//ng-select[@name='DEBIT_CARD_PICKUP_BRANCH']//span[@class='ng-arrow-wrapper']";
        public static final String Cheque_Book = "//ng-select[@name='CHEQUE_BOOK_REQUIRED']//span[@class='ng-arrow-wrapper']";
        public static final String Cheque_Book_Leaves = "//ng-select[@name='CHEQUE_BOOK_LEAVES']//span[@class='ng-arrow-wrapper']";
        public static final String Cheque_Book_Pickup_Branch = "//ng-select[@name='CHEQUE_BOOK_PICKUP_BRANCH']//span[@class='ng-arrow-wrapper']";
        public static final String Geo_Bus_Spread = "//ng-select[@name='geographicalBusinessSpread']//span[@class='ng-arrow-wrapper']";
        public static final String Type_Trans = "//ng-select[@name='typeOfTransactions']//span[@class='ng-arrow-wrapper']";
        public static final String Exp_modes_Trans = "//ng-select[@name='expectedModesOfTransaction']//span[@class='ng-arrow-wrapper']";
        public static final String Exp_Count_Parties = "//ng-select[@name='EXPECTED_COUNTER_PARTIES']//span[@class='ng-arrow-wrapper']";
        public static final String Exp_Mon_Deb_Trans = "//ng-select[@name='expectedmonthlydebittransaction']//span[@class='ng-arrow-wrapper']";
        public static final String Exp_Mon_Cred_Trans = "//ng-select[@name='expectedmonthlycredittransaction']//span[@class='ng-arrow-wrapper']";
        public static final String Exp_Mon_Deb_Turn = "//ng-select[@name='expectedmonthlydebitturnover']//span[@class='ng-arrow-wrapper']";
        public static final String Exp_Mon_Cred_Turn = "//ng-select[@name='expectedmonthlycreditturnover']//span[@class='ng-arrow-wrapper']";
        public static final String Province = "//ng-select[@name='province']//span[@class='ng-arrow-wrapper']";

        public static final String Photo_Acc = "//ng-select[@name='photo-account']//span[@class='ng-arrow-wrapper']";
        public static final String JS_Acc_Holder = "//ng-select[@name='isJsAcctHolder']//span[@class='ng-arrow-wrapper']";
        public static final String JS_Acc = "//input[@id='jsAcctNumber']";
        public static final String Counter_Party_Industry = "//ng-select[@name='COUNTER_PARTY_INDUSTRY']//span[@class='ng-arrow-wrapper']";
        public static final String Counter_Party_Name = "//input[@id='nameOfCountParty']";
        public static final String Eco_Act = "//ng-select[@name='economyActivity']//span[@class='ng-arrow-wrapper']";
        public static final String Class_IS = "//ng-select[@name='classificationOfIS']//span[@class='ng-arrow-wrapper']";
        public static final String Remarks = "//input[@name='remarks']";
        public static final String E_Statement = "//ng-select[@name='eStatement']//span[@class='ng-arrow-wrapper']";
        public static final String E_Statement_Freq = "//ng-select[@name='E_STATEMENT_FRQ']//span[@class='ng-arrow-wrapper']";
        public static final String Sms_alert = "//ng-select[@name='CUST_ACCOUNT_SMS_ALERT']//span[@class='ng-arrow-wrapper']";
        public static final String Mob_telecom = "//ng-select[@name='TELECOM']//span[@class='ng-arrow-wrapper']";
        public static final String Internet_bank = "//ng-select[@name='INTERNET_BANK']//span[@class='ng-arrow-wrapper']";
        public static final String Mobile_bank = "//ng-select[@name='MOBILE_BANKING']//span[@class='ng-arrow-wrapper']";
        public static final String Acc_officer = "//ng-select[@name='accountofficer']//span[@class='ng-arrow-wrapper']";
        public static final String Gender_ac = "//ng-select[@bindlabel='GENDER_DESC']//span[@class='ng-arrow-wrapper']";
        public static final String ACC_KYC_Remarks = "//textarea[@id='makerComment']";
        public static final String Acc_Submit = "//button[normalize-space()='SUBMIT']";
        public static final String Acc_Country ="//ng-select[@name='accountcountry']//span[@class='ng-arrow-wrapper']";


        public static final String SendToSupervisor = "//button[normalize-space()='Send To Supervisor']";
        public static final String SendToSupervisor_YES = "//button[normalize-space()='YES']";
        public static final String Approve_Back = "//input[@value='Back']";

//        New Images Upload

        public static String getBrowseButtonXpath(String documentName) {
            return "//span[normalize-space(text()) and contains(normalize-space(text()), '" + documentName + "')]/../../..//button[contains(text(),'Browse')]";
            }

            // XPath for the file input element (after clicking the Browse button)
            public static String getFileInputXpath(String documentName) {
                return "//span[normalize-space(text()) and contains(normalize-space(text()), '" + documentName + "')]/../../..//input[@type='file']";
            }

            // XPath for the Upload button (after the Browse button changes to Upload)
            public static String getUploadButtonXpath(String documentName) {
                return "//span[normalize-space(text()) and contains(normalize-space(text()), '"+ documentName +"')]/../../..//button[contains(text(),'Upload')]";
            }
            //Tracking ID and CIF No Xpath
            public static String TRACKING_INFO = "//span[contains(text(),'Tracking Id:')]";
            public static String CIF_NO = "//span[contains(text(),'Cif No#')]";
            public static String Back_To_Market_Place ="//button[normalize-space()='Back to Market Place']";
            public static String Checker_DropDown = "//input[@type='text']//following::span[@class='ng-arrow-wrapper']";
            // XPath for the options inside the dropdown (you can modify this if the structure changes)
            public static String Checker_Select = "//span[normalize-space()='%s']";

            //Checker Page
            public static String Find_Tracking_ID="//th[normalize-space()='TRACKING ID']/../../..//td[normalize-space()='%s']";
            public static String Find_Tracking_ID_Next="//a[@aria-label='NEXT']";
            public static String Enter_Comments="//textarea[@id='approvalComments']";
            public static String Request="//button[normalize-space()='%s']";// For dynamic request buttons (e.g., "Request Approve", "Request Reject")
            public static String Checker_YES_NO="//button[normalize-space()='%s']";// For Yes/No buttons (e.g., "Yes", "No")
            public static String Checker_Back="//input[@value='Back']";
            public static String Quit="//i[@class='mdi mdi-power']";

            //T24 Environment
            public static String T24_Username="//input[@id='signOnName']";
            public static String T24_Password="//input[@id='password']";
            public static String T24_SignIn="//input[@id='sign-in']";
            public static String Frame="//frame[@id='banner016424635401']";
            public static String T24_Text="//input[@id='commandValue']";
            public static String T24_TickButton ="//img[@id='cmdline_img']";
            public static String T24_Customer_Text="//a[normalize-space()='CUSTOMER']/../..//input[@id='transactionId']";
            public static String T24_View_Customer="//img[@title='View a contract']";
            public static String T24_SignOff="a[title='Sign off']";

            //Jointly
            public static String Joint_ID_Document_Text="//input[@name='jointIdDocNo_1']";
            public static String Joint_ID_Document_Button="//button[@class='check-etb-nacta-cls']";
            public static String Joint_Relation_Dropdown="//ng-select[@name='relations']//span[@class='ng-arrow-wrapper']";


}