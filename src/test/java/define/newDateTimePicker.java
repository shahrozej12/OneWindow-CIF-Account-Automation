package define;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class newDateTimePicker {
    WebDriver driver;
    WebDriverWait wait;

    public newDateTimePicker(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Adjust timeout as needed
    }

    public void selectDate(String xpath, String dateToSelect) {
        WebElement dateField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        dateField.click(); // Click to open date picker

        // Wait for the date picker popup
        WebElement datePickerPopup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'datepicker')]")));

        // Extract year, month, and day from the given date (Format: YYYY-MM-DD)
        String[] dateParts = dateToSelect.split("-");
        String year = dateParts[0];
        String month = dateParts[1]; // Ensure correct month format
        String day = dateParts[2];

        // Select the year
        WebElement yearElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@class='year-selector']")));
        yearElement.click();
        WebElement selectYear = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//option[text()='" + year + "']")));
        selectYear.click();

        // Select the month
        WebElement monthElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@class='month-selector']")));
        monthElement.click();
        WebElement selectMonth = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//option[text()='" + month + "']")));
        selectMonth.click();

        // Select the day
        WebElement selectDay = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[text()='" + day + "']")));
        selectDay.click();
    }
}
