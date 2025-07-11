package selenium.selenium_scenario;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Login {
    WebDriver driver;

    @BeforeMethod
    public void setup() throws InterruptedException {
        // Setup WebDriver
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver.exe");

        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));
    }

    @Test(priority = 1)
    public void validCredentials() throws InterruptedException {
        // This is a placeholder for the actual test implementation
        // You can add your test logic here
        System.out.println("Valid credentials test is running.");

        // Insert credential
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");

        driver.findElement(By.id("login-button")).click();

        boolean isOnInventoryPage = driver.getCurrentUrl().contains("inventory.html");
        Assert.assertTrue(isOnInventoryPage, "Login failed with valid credentials!");
    }

    @Test(priority = 2, dataProvider = "invalidCredentialsData")
    public void invalidCredentials(String email, String password, String expectedErrorMessage)
            throws InterruptedException {
        // This is a placeholder for the actual test implementation
        // You can add your test logic here
        System.out.println("Invalid credentials test is running.");

        /*
         * 1. Valid Email , Invalid Password
         * 2. Invalid Email , Valid Password
         * 3. Invalid Email , Invalid Password
         * 4. Empty Email , Invalid Password
         */

        // Insert credential
        driver.findElement(By.id("user-name")).sendKeys(email);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();

        // Validate error messages
        // Validate email error
        // if (isElementPresent(
        // By.xpath("//input[@id='user-name']/following-sibling::div//div[@class='ng-star-inserted']")))
        // {
        // String emailErrorMessage = driver
        // .findElement(
        // By.xpath("//input[@id='user-name']/following-sibling::div//div[@class='ng-star-inserted']"))
        // .getText();
        // Assert.assertEquals(emailErrorMessage, emailError, "Email error message does
        // not match!");
        // }

        // // Validate password error
        // if (isElementPresent(
        // By.xpath("//input[@id='password']/following-sibling::div//div[@class='ng-star-inserted']")))
        // {
        // String passwordErrorMessage = driver
        // .findElement(
        // By.xpath("//input[@id='password']/following-sibling::div//div[@class='ng-star-inserted']"))
        // .getText();
        // Assert.assertEquals(passwordErrorMessage, passwordError, "Password error
        // message does not match!");
        // }

        By errorLocator = By.cssSelector("[data-test='error']");
        Assert.assertTrue(isElementPresent(errorLocator), "Expected error message not displayed!");

        String actualErrorMessage = driver.findElement(errorLocator).getText();
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Error message does not match!");

    }

    @DataProvider(name = "invalidCredentialsData")
    public Object[][] invalidCredentialsData() {
        return new Object[][] {
                { "", "", "Epic sadface: Username is required" },
                { "standard_user", "", "Epic sadface: Password is required" },
                { "invalid_user", "secret_sauce",
                        "Epic sadface: Username and password do not match any user in this service" },
                { "standard_user", "wrong_password",
                        "Epic sadface: Username and password do not match any user in this service" }
        };
    }

    public Boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @AfterMethod
    public void tearDown() {
        // Close the browser after the test
        if (driver != null) {
            driver.quit();
        }
    }

}
