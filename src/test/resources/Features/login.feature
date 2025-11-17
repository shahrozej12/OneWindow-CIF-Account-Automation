Feature: Customer Information Form Submission

  Background: Login Page
    Given I am on the login page
    And I fill in the login information:
      | Field      | Value                 |
      | username   | shahroze.janjua         |
      | password   | Nuzhat.321              |
    Then I log in

  @Self_Employed
  Scenario: Submit customer information where Sector Code is 1019 - Self Employed

    And I navigate to customer information
    And I navigate to CIF
      And I fill in the customer information:
        | Field                     | Value                           |
        | Customer Category          | INDIVIDUAL - CIF AND ACCOUNT    |
        | ID Document Type           | ID-N - CNIC                     |
        | ID Document Number         | 4210122390312                 |
        | CNIC Issuance Date         | 2024-11-05 |
        | CNIC Expiry Date           | 2025-08-05 |
        | VISA Expiry Date           | 2026-11-22 |
        | Date Of Birth              | 2004-10-12 |
      | Sector Code                | 1018 - Salaried Person      |
      | Nationality                | Pakistan             |
      | Assan Account               | NO                              |
      | Marital Status             | SINGLE                          |
      | Customer Title             | Mr.                             |
      | Gender                     | MALE                            |
      | First Name                 | Javed                             |
      | Middle Name                | Asad                          |
      | Last Name                  | das                            |
      | Father's Name              | Paul                       |
      | Mother's Name              | ALina                           |
      | Occupation                 | 133 - PRIVATE SERVICE             |
      | Industry Code              | 5101 - Arms Manufacturer/Arms Dealer                        |
      | Tax Reason (CRS)           | REASON A                        |
      | Tax Residence (CRS)        | NO                             |


    Then Click on CUST_INFO_Next

    And I fill in the PEP-Customer Demographic:
      | Field                     | Value                          |
      | PEP                        | NO                             |
      | PEP Status                 | Inspector-General of Police    |
      | Address Type               | Registered                     |
      | Province                   | S                               |
      | Employee Status            | EMPLOYED                       |
      | Employer Name              | BRAD                            |
      | Designation                | COO                             |
      | RM Code                    | 176 - AYUB KHAN                |
      | DSR Code                   | ABC12                           |
      | Education                  | Graduate                        |
      | Place of Birth             | Pakistan                        |
      | Business Name              | BRAD Ent                        |

    Then Click on PEP_Next

    And I fill in the Contact Detail:
      | Field                         | Value                     |
        | Cell Country Code             | Pakistan                  |
      | Cell Mobile Number            | 03232858781              |
      | Residence Country Code       | Pakistan                  |
      | Residence Phone Number       | 02207895623                   |
      | Residential Address Line 1    | District East             |
      | Residential Address Line 2    | District West             |
      | Email Address                 | john@gmail.com        |
      | Office / Business Address Line 1 | Sheet 81                 |
      | Office / Business Address Line 2 | Sheet 20                 |
      | Preferred Mailing Address     | OFFICE                    |
      | Mobile Telecom               | Warid                    |


    Then Click on CONT_DET_Next

    And I fill in the Next Of Kin Detail:
      | Field                         | Value                     |
      | Next of Kin Name              | Jason                       |
      | Next of Kin Relationship      | Parent                  |
      | Next of Kin Mobile Number     | 03217894256                  |
      | Next of Kin Address Line 1    | Los Sheet 91                  |


    Then Click on KIN_Next

    And I fill in the Account Detail:
      | Field                         | Value                     |
      | Account Operational Type          | SINGLY                       |
      | Account Type                       | 1201 - Personal Loan Running Finance                   |
      | Currency                           | PKR - Pakistani Rupee                          |
      | Debit Card Request                 | NO                          |
      | Debit Card Type                    | 0610 - MasterCard Pay Pak Debit Card                     |
      | Debit Card Pickup Branch           | 9036 - Sarghoda Branch                     |
      | Cheque Book Request                | NO                          |
      | Cheque Book Leaves                 | 25 - CHEQUE BOOK 25 LEAVES (PKRA)                           |
      | Cheque Book Pickup Branch          | 9036 - Sarghoda Branch                     |
      | Geo Business Spread                | Within Pakistan                       |
      | Type of Transactions               | Internal Transfers                        |
      | Expected Modes of Transaction      | Internet Bankng                       |
      | Expected Counter Parties           | Individual                           |
      | Expected Monthly Debit Transactions| 100                        |
      | Expected Monthly Credit Transactions| 100                       |
      | Expected Monthly Debit Turnover    | 1000000                       |
      | Expected Monthly Credit Turnover   | 25000000                       |
      | Provinces                           | Sindh                      |
      | Photo Account                      | NO                          |
      | JS Account Holder                  | NO                          |
      | JS Account Number                      | 0002487705                        |
      | Counter Party Industry            | 5007 - Mining of metal ores                          |
      | Name of Counter Party                      | Jason Pvt.Ltd                       |
      | Economy Activity                   | INDUSTRY                     |
      | Classification of IS               | CLASS_A                      |
      | E-Statement                        | YES                          |
      | E-Statement Frequency              | 602 - Weekly / Bi Annually                      |
      | SMS Alert                          | NO                          |
      | Mobile Telecom                     | WARID                       |
      | Internet Banking                   | YES                          |
      | Mobile Banking                     | YES                          |
      | Account Country                    | PK - Pakistan                     |
      | Account Officer                    | 19585280 - New Challi Khi Branch Head ope                     |
      | Gender (Account)                   | MALE                       |
      | Account KYC Remarks                | KYC passed                   |

    Then Submit the form

    And Uploading Files:
      | Document Name      | File Path                           |
  |   IF ACCOUNT RISK LEVEL HIGH AND BUSINESS | D:/CIF_Image_Auto/AOF.PNG|
  | Attested copy of CNIC   | D:/CIF_Image_Auto/AOF.PNG|
      | Proof of Income        | D:/CIF_Image_Auto/Id Document.PNG    |
      | Crop and Upload Signature Image Only        | C:/IRS.PNG    |
      | Nadra Verysis   | D:/CIF_Image_Auto/CRS.PNG|
      | Specimen Signature Card    | D:/CIF_Image_Auto/SSC.jpg|



    Then Send To Supervisor
    And Go Back To Market Place
    And Select "CHECKER"
    And I navigate to customer information UAT
    And I search and click the Tracking Id
    And Enter Comments "Approved" and Press "Request Approve" and Press "YES"
    Then Go Back
#    And Open T24 environment
#    And I fill enter Login Credentials for T24:
#      | Field      | Value                 |
#      | username   | NADEEM01         |
#      | password   |   NADEEM01        |
#    And I login in T24
#    And Enter "CUSTOMER"  and Press Tick Button
#    And Enter CIF No and View it

#  @Salaried_person
#  Scenario: Submit customer information where Sector Code is 1018 - Salaried Person
#    And I navigate to customer information
#    And I navigate to CIF
#    And I fill in the customer information:
#      | Field                     | Value                           |
#      | Customer Category          | INDIVIDUAL                      |
#      | ID Document Type           | ID-N - CNIC                     |
#      | ID Document Number         | 4210112769201                |
#      | CNIC Issuance Date         | 2023-10-05                       |
#      | CNIC Expiry Date           | 2026-05-03                       |
#      | VISA Expiry Date           | 2025-11-24                       |
#      | Date Of Birth              | 2005-09-24                       |
#      | Sector Code                | 1018 - Salaried Person      |
#      | Nationality                | Barbados            |
#      | Assan Account               | YES                              |
#      | Marital Status             | SINGLE                          |
#      | Customer Title             | Mr.                             |
#      | Gender                     | MALE                            |
#      | First Name                 | MICHAEL                             |
#      | Middle Name                | Stan                          |
#      | Last Name                  | CLARKE                            |
#      | Father's Name              | VIN                       |
#      | Mother's Name              | ADELE                           |
#      | Occupation                 | 133 - PRIVATE SERVICE             |
#      | Industry Code              | 5101 - Arms Manufacturer/Arms Dealer                        |
#      | Tax Reason (CRS)           | REASON A                        |
#      | Tax Residence (CRS)        | NO                             |
#
#
#    Then Click on CUST_INFO_Next
#
#    And I fill in the PEP-Customer Demographic:
#      | Field                     | Value                          |
#      | PEP                        | YES                             |
#      | PEP Status                 | Inspector-General of Police    |
#      | Address Type               | Registered                     |
#      | Province                   | S                               |
#      | Employee Status            | EMPLOYED                       |
#      | Employer Name              | BRAD                            |
#      | Designation                | COO                             |
#      | RM Code                    | 176 - AYUB KHAN                |
#      | DSR Code                   | ABC12                           |
#      | Education                  | Graduate                        |
#      | Place of Birth             | Pakistan                        |
#      | Business Name              | BRAD Ent                        |
#
#    Then Click on PEP_Next
#
#    And I fill in the Contact Detail:
#      | Field                         | Value                     |
#      | Cell Country Code             | Pakistan                  |
#      | Cell Mobile Number            | 03218795325               |
#      | Residence Country Code       | Pakistan                  |
#      | Residence Phone Number       | 03007895623               |
#      | Residential Address Line 1    | District East             |
#      | Residential Address Line 2    | District West             |
#      | Email Address                 | john@gmail.com        |
#      | Office / Business Address Line 1 | Sheet 81                 |
#      | Office / Business Address Line 2 | Sheet 20                 |
#      | Preferred Mailing Address     | OFFICE                    |
#
#    Then Click on CONT_DET_Next
#
#    And I fill in the Next Of Kin Detail:
#      | Field                         | Value                     |
#      | Next of Kin Name              | Asad                       |
#      | Next of Kin Relationship      | Parent                  |
#      | Next of Kin Mobile Number     | 03217894256                  |
#      | Next of Kin Address Line 1    | Street 90 NY                  |
#      | Next of Kin Address Line 2    | Street 121 East California                  |
#      | KYC Remarks             | Checking if automation is working fully or not           |
#
#
#    Then Click on KIN_Next
#
#    And I fill in the Account Detail:
#      | Field                         | Value                     |
#      | Account Operational Type          | SINGLY                       |
#      | Account Type                       | 1082 - Asaan Account                     |
#      | Currency                           | PKR - Pakistani Rupee                          |
#      | Debit Card Request                 | YES                          |
#      | Debit Card Type                    | 610 - MasterCard Pay Pak Debit Card                     |
#      | Debit Card Pickup Branch           | 9001 - JS Bank Shaheen Complex                     |
#      | Cheque Book Request                | NO                          |
#      | Cheque Book Leaves                 | 25 - CHEQUE BOOK 25 LEAVES (PKRA)                           |
#      | Cheque Book Pickup Branch          | 9006 - DHA Karachi                     |
#      | Geo Business Spread                | Within Pakistan                       |
#      | Type of Transactions               | Internal Transfers                        |
#      | Expected Modes of Transaction      | Internet Bankng                       |
#      | Expected Counter Parties           | Individual                           |
#      | Expected Monthly Debit Transactions| 070                        |
#      | Expected Monthly Credit Transactions| 100                       |
#      | Expected Monthly Debit Turnover    | 1000000                       |
#      | Expected Monthly Credit Turnover   | 25000000                       |
#      | Province                           | Sindh                      |
#      | Photo Account                      | NO                          |
#      | JS Account Holder                  | NO                          |
#      | JS Account Number                      | 0002487705                        |
#      | Counter Party Industry            | 5007 - Mining of metal ores                          |
#      | Name of Counter Party                      | Jason Pvt.Ltd                       |
#      | Economy Activity                   | INDUSTRY                     |
#      | Classification of IS               | CLASS_A                      |
#      | E-Statement                        | YES                          |
#      | E-Statement Frequency              | 602 - Weekly / Bi Annually                      |
#      | SMS Alert                          | NO                          |
#      | Mobile Telecom                     | WARID                       |
#      | Internet Banking                   | YES                          |
#      | Mobile Banking                     | YES                          |
#      | Account Officer                    | 19203015 - SOLIDER BAZAR KARACH                     |
#      | Account Operational Type (Signature) | SINGLE                       |
#      | Account KYC Remarks                | KYC passed                   |
#
#    Then Submit the form
#
#    And Uploading Files:
#      | Field      | Value                           |
#      | ID Document        | D:/CIF_Image_Auto/Id Document.PNG    |
#      | Proof of Address   | D:/CIF_Image_Auto/POF.PNG|
#      | Proof of Income   | D:/CIF_Image_Auto/POI.PNG|
#      | CRS Form   | D:/CIF_Image_Auto/CRS.PNG|
#      | IRS Form   | D:/CIF_Image_Auto/IRS.PNG|
#      | Signature Speciman Card   | D:/CIF_Image_Auto/SSC.jpg|
#      | ACCOUNT OPENING FORM   | D:/CIF_Image_Auto/AOF.PNG|
#      | Key Fact Sheet   | D:/CIF_Image_Auto/Key_Fact.PNG |
#      | Term & Conditions   | D:/CIF_Image_Auto/Terms.PNG|
#
#    Then Send To Supervisor
#    And Go Back To Market Place
#    And Select "CHECKER"
#    And I navigate to customer information UAT
#    And I search and click the Tracking Id
#    And Enter Comments "Approved" and Press "Request Approve" and Press "YES"
#    Then Go Back
#    And Open T24 environment
#    And I fill enter Login Credentials for T24:
#      | Field      | Value                 |
#      | username   | NADEEM01         |
#      | password   |   NADEEM01        |
#    And I login in T24
#    And Enter "CUSTOMER"  and Press Tick Button
#    And Enter CIF No and View it
#
#  @Gmail_failed
#  Scenario: Submit customer information where Gmail failed
#    And I navigate to customer information
#    And I navigate to CIF
#    And I fill in the customer information:
#      | Field                     | Value                           |
#      | Customer Category          | PRIMARY                      |
#      | ID Document Type           | ID-N - CNIC                     |
#      | ID Document Number         | 4210170111212                 |
#      | CIF         | Housewife                 |
#      | CNIC Issuance Date         | 2022-01-14                       |
#      | CNIC Expiry Date           | 2026-12-05                       |
#      | VISA Expiry Date           | 2026-12-24                       |
#      | Date Of Birth              | 1999-09-24                       |
#      | Sector Code                | 1018 - Salaried Person      |
#      | Nationality                | Qatar            |
#      | Assan Account               | YES                              |
#      | Marital Status             | SINGLE                          |
#      | Customer Title             | Mr.                             |
#      | Gender                     | MALE                            |
#      | First Name                 | Wasim                             |
#      | Middle Name                | Amir                          |
#      | Last Name                  | Sohail                            |
#      | Father's Name              | Kamran                       |
#      | Mother's Name              | Faiza                          |
#      | Occupation                 | 133 - PRIVATE SERVICE             |
#      | Industry Code              | 5101 - Arms Manufacturer/Arms Dealer                        |
#      | Tax Reason (CRS)           | REASON A                        |
#      | Tax Residence (CRS)        | NO                             |
#
#
#    Then Click on CUST_INFO_Next
#
#    And I fill in the PEP-Customer Demographic:
#      | Field                     | Value                          |
#      | PEP                        | YES                             |
#      | PEP Status                 | Chairman FBR    |
#      | Address Type               | Registered                     |
#      | Province                   | S                               |
#      | Employee Status            | EMPLOYED                       |
#      | Employer Name              | BRAD                            |
#      | Designation                | COO                             |
#      | RM Code                    | 176 - AYUB KHAN                |
#      | DSR Code                   | ABC12                           |
#      | Education                  | Graduate                        |
#      | Place of Birth             | Pakistan                        |
#      | Business Name              | BRAD Ent                        |
#
#    Then Click on PEP_Next
#
#    And I fill in the Contact Detail:
#      | Field                         | Value                     |
#      | Cell Country Code             | Pakistan                  |
#      | Cell Mobile Number            | 03218795325               |
#      | Residence Country Code       | Pakistan                  |
#      | Residence Phone Number       | 03007895623               |
#      | Residential Address Line 1    | District West         |
#      | Residential Address Line 2    | District South             |
#      | Email Address                 | john@       |
#      | Office / Business Address Line 1 | Sheet 81                 |
#      | Office / Business Address Line 2 | Sheet 20                 |
#      | Preferred Mailing Address     | OFFICE                    |
#
#
#    Then Click on CONT_DET_Next
#
#    And I fill in the Next Of Kin Detail:
#      | Field                         | Value                     |
#      | Next of Kin Name              | Asad                       |
#      | Next of Kin Relationship      | Parent                  |
#      | Next of Kin Mobile Number     | 03217894256                  |
#      | Next of Kin Address Line 1    | Street 90 NY                  |
#      | Next of Kin Address Line 2    | Street 121 East California                  |
#      | KYC Remarks             | Checking if automation is working fully or not           |
#
#
#    Then Click on KIN_Next
#
#    And I fill in the Account Detail:
#      | Field                         | Value                     |
#      | Account Operational Type          | SINGLY                       |
#      | Account Type                       | 1082 - Asaan Account                     |
#      | Currency                           | PKR - Pakistani Rupee                          |
#      | Debit Card Request                 | YES                          |
#      | Debit Card Type                    | 610 - MasterCard Pay Pak Debit Card                     |
#      | Debit Card Pickup Branch           | 9001 - JS Bank Shaheen Complex                     |
#      | Cheque Book Request                | NO                          |
#      | Cheque Book Leaves                 | 25 - CHEQUE BOOK 25 LEAVES (PKRA)                           |
#      | Cheque Book Pickup Branch          | 9006 - DHA Karachi                     |
#      | Geo Business Spread                | Within Pakistan                       |
#      | Type of Transactions               | Internal Transfers                        |
#      | Expected Modes of Transaction      | Internet Bankng                       |
#      | Expected Counter Parties           | Individual                           |
#      | Expected Monthly Debit Transactions| 070                        |
#      | Expected Monthly Credit Transactions| 100                       |
#      | Expected Monthly Debit Turnover    | 1000000                       |
#      | Expected Monthly Credit Turnover   | 25000000                       |
#      | Province                           | Sindh                      |
#      | Photo Account                      | NO                          |
#      | JS Account Holder                  | NO                          |
#      | JS Account Number                      | 0002487705                        |
#      | Counter Party Industry            | 5007 - Mining of metal ores                          |
#      | Name of Counter Party                      | Jason Pvt.Ltd                       |
#      | Economy Activity                   | INDUSTRY                     |
#      | Classification of IS               | CLASS_A                      |
#      | E-Statement                        | YES                          |
#      | E-Statement Frequency              | 602 - Weekly / Bi Annually                      |
#      | SMS Alert                          | NO                          |
#      | Mobile Telecom                     | WARID                       |
#      | Internet Banking                   | YES                          |
#      | Mobile Banking                     | YES                          |
#      | Account Officer                    | 19203015 - SOLIDER BAZAR KARACH                     |
#      | Account Operational Type (Signature) | SINGLE                       |
#      | Account KYC Remarks                | KYC passed                   |
#
#    Then Submit the form
#
#    And Uploading Files:
#      | Document Name      | File Path                           |
#      | ID Document        | D:/CIF_Image_Auto/Id Document.PNG    |
#      | Proof of Address   | D:/CIF_Image_Auto/POF.PNG|
#      | Proof of Income   | D:/CIF_Image_Auto/POI.PNG|
#      | CRS Form   | D:/CIF_Image_Auto/CRS.PNG|
#      | IRS Form   | D:/IRS.PNG|
#      | Signature Speciman Card   | D:/CIF_Image_Auto/SSC.jpg|
#      | ACCOUNT OPENING FORM   | D:/CIF_Image_Auto/AOF.PNG|
#      | Key Fact Sheet   | D:/CIF_Image_Auto/Key_Fact.PNG |
#      | Term & Conditions   | D:/CIF_Image_Auto/Terms.PNG|
#
#    Then Send To Supervisor
#
#  @SMS_alert_No
#  Scenario: Submit customer information where Nationality is Pakistan
#    And I navigate to customer information
#    And I navigate to CIF
#    And I fill in the customer information:
#      | Field                     | Value                           |
#      | Customer Category          | INDIVIDUAL                      |
#      | ID Document Type           | ID-N - CNIC                     |
#      | ID Document Number         | 4345135147845                |
#      | CNIC Issuance Date         | 2023-10-05                       |
#      | CNIC Expiry Date           | 2025-12-05                       |
#      | VISA Expiry Date           | 2025-11-24                       |
#      | Date of Birth              | 2006-10-12                          |
#      | Sector Code                | 1018 - Salaried Person      |
#      | Nationality                | Pakistan            |
#      | Assan Account               | YES                              |
#      | Marital Status             | SINGLE                          |
#      | Customer Title             | Mr.                             |
#      | Gender                     | MALE                            |
#      | First Name                 | John                             |
#      | Middle Name                | Cena                          |
#      | Last Name                  |   See                          |
#      | Father's Name              | Rock                   |
#      | Mother's Name              | Angelina                           |
#      | Occupation                 | 133 - PRIVATE SERVICE             |
#      | Industry Code              | 5101 - Arms Manufacturer/Arms Dealer                        |
#      | Tax Reason (CRS)           | REASON A                        |
#      | Tax Residence (CRS)        | NO                             |
#
#
#    Then Click on CUST_INFO_Next
#
#    And I fill in the PEP-Customer Demographic:
#      | Field                     | Value                          |
#      | PEP                        | YES                             |
#      | PEP Status                 | Inspector-General of Police    |
#      | Address Type               | Registered                     |
#      | Province                   | S                               |
#      | Employee Status            | EMPLOYED                       |
#      | Employer Name              | Ariana                            |
#      | Designation                | Manager                             |
#      | RM Code                    | 176 - AYUB KHAN                |
#      | DSR Code                   | ABC12                           |
#      | Education                  | Graduate                        |
#      | Place of Birth             | Pakistan                        |
#      | Business Name              | Johana Ent                        |
#
#    Then Click on PEP_Next
#
#    And I fill in the Contact Detail:
#      | Field                         | Value                     |
#      | Cell Country Code             | Pakistan                  |
#      | Cell Mobile Number            | 03218795325               |
#      | Residence Country Code       | Pakistan                  |
#      | Residence Phone Number       | 03007895623               |
#      | Residential Address Line 1    | California District         |
#      | Residential Address Line 2    | District North             |
#      | Email Address                 | asad@gmail.com    |
#      | Office / Business Address Line 1 | Sheet 81                 |
#      | Office / Business Address Line 2 | Sheet 20                 |
#      | Preferred Mailing Address     | OFFICE                    |
#
#
#
#    Then Click on CONT_DET_Next
#
#    And I fill in the Next Of Kin Detail:
#      | Field                         | Value                     |
#      | Next of Kin Name              | Peter                       |
#      | Next of Kin Relationship      | Parent                  |
#      | Next of Kin Mobile Number     | 03217894256                  |
#      | Next of Kin Address Line 1    | Street 90 NY                  |
#      | Next of Kin Address Line 2    | Street 121 East California                  |
#      | KYC Remarks             | Checking if automation is working fully or not           |
#
#
#    Then Click on KIN_Next
#
#    And I fill in the Account Detail:
#      | Field                         | Value                     |
#      | Account Operational Type          | SINGLY                       |
#      | Account Type                       | 1082 - Asaan Account                     |
#      | Currency                           | PKR - Pakistani Rupee                          |
#      | Debit Card Request                 | YES                          |
#      | Debit Card Type                    | 610 - MasterCard Pay Pak Debit Card                     |
#      | Debit Card Pickup Branch           | 9001 - JS Bank Shaheen Complex                     |
#      | Cheque Book Request                | NO                          |
#      | Cheque Book Leaves                 | 25 - CHEQUE BOOK 25 LEAVES (PKRA)                           |
#      | Cheque Book Pickup Branch          | 9006 - DHA Karachi                     |
#      | Geo Business Spread                | Within Pakistan                       |
#      | Type of Transactions               | Internal Transfers                        |
#      | Expected Modes of Transaction      | Internet Bankng                       |
#      | Expected Counter Parties           | Individual                           |
#      | Expected Monthly Debit Transactions| 070                        |
#      | Expected Monthly Credit Transactions| 100                       |
#      | Expected Monthly Debit Turnover    | 1000000                       |
#      | Expected Monthly Credit Turnover   | 25000000                       |
#      | Province                           | Sindh                      |
#      | Photo Account                      | NO                          |
#      | JS Account Holder                  | NO                          |
#      | JS Account Number                      | 0002487705                        |
#      | Counter Party Industry            | 5007 - Mining of metal ores                          |
#      | Name of Counter Party                      | Jason Pvt.Ltd                       |
#      | Economy Activity                   | INDUSTRY                     |
#      | Classification of IS               | CLASS_A                      |
#      | E-Statement                        | YES                          |
#      | E-Statement Frequency              | 602 - Weekly / Bi Annually                      |
#      | SMS Alert                          | NO                          |
#      | Mobile Telecom                     | WARID                       |
#      | Internet Banking                   | YES                          |
#      | Mobile Banking                     | YES                          |
#      | Account Officer                    | 19203015 - SOLIDER BAZAR KARACH                     |
#      | Account Operational Type (Signature) | SINGLE                       |
#      | Account KYC Remarks                | KYC is working fine                |
#
#    Then Submit the form
#
#
#
#    And Uploading Files:
#      | Document Name      | File Path                           |
#      | ID Document        | C:/Images_Auto/Id Document.PNG    |
#      | Proof of Address   | C:/Images_Auto/POF.PNG|
#      | Proof of Income  | C:/Images_Auto/POI.PNG|
#      | CRS Form   | C:/Images_Auto/CRS.PNG|
#      | IRS Form   | C:/Images_Auto/IRS.PNG|
#      | Signature Speciman Card   | C:/Images_Auto/SSC.jpg |
#      | ACCOUNT OPENING FORM   | C:/Images_Auto/AOF.PNG  |
#      | Key Fact Sheet   | C:/Images_Auto/Key_Fact.PNG |
#      | Term & Conditions   | C:/Images_Auto/Terms.PNG|
#
#    Then Send To Supervisor
#
#    And Go Back To Market Place
#    And Select "CHECKER"
#    And I navigate to customer information UAT
#    And I search and click the Tracking Id
#    And Enter Comments "Approved" and Press "Request Approve" and Press "YES"
#    Then Go Back
#    And Open T24 environment
#    And I fill enter Login Credentials for T24:
#      | Field      | Value                 |
#      | username   | NADEEM01         |
#      | password   |   NADEEM01        |
#    And I login in T24
#    And Enter "CUSTOMER"  and Press Tick Button
#    And Enter CIF No and View it
#
#  @JOINTLY
#  Scenario: Submit customer information where Nationality is Pakistan
#    And I navigate to customer information
#    And I navigate to CIF
#    And I fill in the customer information:
#      | Field                     | Value                           |
#      | Customer Category          | PRIMARY                      |
#      | ID Document Type           | ID-N - CNIC                     |
#      | ID Document Number         | 4210157321061                |
#      | CNIC Issuance Date         | 2023-10-05                       |
#      | CNIC Expiry Date           | 2025-12-05                       |
#      | VISA Expiry Date           | 2025-11-24                       |
#      | Date Of Birth              | 2005-09-24                       |
#      | Sector Code                | 1018 - Salaried Person      |
#      | Nationality                | Pakistan            |
#      | Assan Account               | YES                              |
#      | Marital Status             | SINGLE                          |
#      | Customer Title             | Mr.                             |
#      | Gender                     | MALE                            |
#      | First Name                 | Seth                             |
#      | Middle Name                | Rollins                          |
#      | Last Name                  | Peter                            |
#      | Father's Name              | Rock                   |
#      | Mother's Name              | Angelina                           |
#      | Occupation                 | 133 - PRIVATE SERVICE             |
#      | Industry Code              | 5101 - Arms Manufacturer/Arms Dealer                        |
#      | Tax Reason (CRS)           | REASON A                        |
#      | Tax Residence (CRS)        | NO                             |
#
#
#    Then Click on CUST_INFO_Next
#
#    And I fill in the PEP-Customer Demographic:
#      | Field                     | Value                          |
#      | PEP                        | YES                             |
#      | PEP Status                 | Inspector-General of Police    |
#      | Address Type               | Registered                     |
#      | Province                   | S                               |
#      | Employee Status            | EMPLOYED                       |
#      | Employer Name              | Ariana                            |
#      | Designation                | Manager                             |
#      | RM Code                    | 176 - AYUB KHAN                |
#      | DSR Code                   | ABC12                           |
#      | Education                  | Graduate                        |
#      | Place of Birth             | Pakistan                        |
#      | Business Name              | Johana Ent                        |
#
#    Then Click on PEP_Next
#
#    And I fill in the Contact Detail:
#      | Field                         | Value                     |
#      | Cell Country Code             | Pakistan                  |
#      | Cell Mobile Number            | 03218795325               |
#      | Residence Country Code       | Pakistan                  |
#      | Residence Phone Number       | 03007895623               |
#      | Residential Address Line 1    | California District         |
#      | Residential Address Line 2    | District North             |
#      | Email Address                 | asad@gmail.com    |
#      | Office / Business Address Line 1 | Sheet 81                 |
#      | Office / Business Address Line 2 | Sheet 20                 |
#      | Preferred Mailing Address     | OFFICE                    |
#
#
#
#    Then Click on CONT_DET_Next
#
#    And I fill in the Next Of Kin Detail:
#      | Field                         | Value                     |
#      | Next of Kin Name              | Peter                       |
#      | Next of Kin Relationship      | Parent                  |
#      | Next of Kin Mobile Number     | 03217894256                  |
#      | Next of Kin Address Line 1    | Street 90 NY                  |
#      | Next of Kin Address Line 2    | Street 121 East California                  |
#      | KYC Remarks             | Checking if automation is working fully or not           |
#
#
#    Then Click on KIN_Next
#
#    And I fill in the Account Detail:
#      | Field                         | Value                     |
#      | Account Operational Type          | JOINTLY                       |
#      | Account Type                       | 1082 - Asaan Account                     |
#      | Currency                           | PKR - Pakistani Rupee                          |
#      | Debit Card Request                 | YES                          |
#      | Debit Card Type                    | 610 - MasterCard Pay Pak Debit Card                     |
#      | Debit Card Pickup Branch           | 9001 - JS Bank Shaheen Complex                     |
#      | Cheque Book Request                | NO                          |
#      | Cheque Book Leaves                 | 25 - CHEQUE BOOK 25 LEAVES (PKRA)                           |
#      | Cheque Book Pickup Branch          | 9006 - DHA Karachi                     |
#      | Geo Business Spread                | Within Pakistan                       |
#      | Type of Transactions               | Internal Transfers                        |
#      | Expected Modes of Transaction      | Internet Bankng                       |
#      | Expected Counter Parties           | Individual                           |
#      | Expected Monthly Debit Transactions| 070                        |
#      | Expected Monthly Credit Transactions| 100                       |
#      | Expected Monthly Debit Turnover    | 1000000                       |
#      | Expected Monthly Credit Turnover   | 25000000                       |
#      | Province                           | Sindh                      |
#      | Photo Account                      | NO                          |
#      | JS Account Holder                  | NO                          |
#      | JS Account Number                      | 0002487705                        |
#      | Counter Party Industry            | 5007 - Mining of metal ores                          |
#      | Name of Counter Party                      | Jason Pvt.Ltd                       |
#      | Economy Activity                   | INDUSTRY                     |
#      | Classification of IS               | CLASS_A                      |
#      | E-Statement                        | YES                          |
#      | E-Statement Frequency              | 602 - Weekly / Bi Annually                      |
#      | SMS Alert                          | NO                          |
#      | Mobile Telecom                     | WARID                       |
#      | Internet Banking                   | YES                          |
#      | Mobile Banking                     | YES                          |
#      | Account Officer                    | 19203015 - SOLIDER BAZAR KARACH                     |
#      | Account Operational Type (Signature) | SINGLE                       |
#      | Account KYC Remarks                | KYC is working fine                |
#      | Joint Relation                |      Parent          |
#
#    Then Submit the form
#
#
#
#    And Uploading Files:
#      | Document Name      | File Path                           |
#      | ID Document        | C:/Images_Auto/Id Document.PNG    |
#      | Proof of Address   | C:/Images_Auto/POF.PNG|
#      | Proof of Income  | C:/Images_Auto/POI.PNG|
#      | CRS Form   | C:/Images_Auto/CRS.PNG|
#      | IRS Form   | C:/Images_Auto/IRS.PNG|
#      | Signature Speciman Card   | C:/Images_Auto/SSC.jpg |
#      | ACCOUNT OPENING FORM   | C:/Images_Auto/AOF.PNG  |
#      | Key Fact Sheet   | C:/Images_Auto/Key_Fact.PNG   |
#
#    Then Send To Supervisor
#
#    And Go Back To Market Place
#    And Select "CHECKER"
#    And I navigate to customer information UAT
#    And I search and click the Tracking Id
#    And Enter Comments "Approved" and Press "Request Approve" and Press "YES"
#    Then Go Back
#    And Open T24 environment
#    And I fill enter Login Credentials for T24:
#      | Field      | Value                 |
#      | username   | NADEEM01         |
#      | password   |   NADEEM01        |
#    And I login in T24
#    And Enter "CUSTOMER"  and Press Tick Button
#    And Enter CIF No and View it




