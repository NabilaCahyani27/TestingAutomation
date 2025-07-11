package demo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConfirmationPage { 

    WebDriver driver;

    public ConfirmationPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getConfirmationMessage() {
        return driver.findElement(By.className("complete-header")).getText();
    }
}
