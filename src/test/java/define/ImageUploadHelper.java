package define;

import locators.XPathLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ImageUploadHelper {

    WebDriver driver;

    // Constructor to initialize WebDriver
    public ImageUploadHelper(WebDriver driver) {
        this.driver = driver;
    }

    // Method to upload a file based on the document name and file path
    public void uploadFile(String documentName, String filePath) {
        // Get dynamic locators for the Browse button, File input, and Upload button
        String browseXpath = XPathLocators.getBrowseButtonXpath(documentName);
        String fileInputXpath = XPathLocators.getFileInputXpath(documentName);
        String uploadXpath = XPathLocators.getUploadButtonXpath(documentName);

        // Click the Browse button
        WebElement browseButton = driver.findElement(By.xpath(browseXpath));
        browseButton.click();

        // Wait for the file input to appear, and upload the file
        WebElement fileInput = driver.findElement(By.xpath(fileInputXpath));
        fileInput.sendKeys(filePath);

        // Click the Upload button once the file is uploaded
        WebElement uploadButton = driver.findElement(By.xpath(uploadXpath));
        uploadButton.click();
    }
}
