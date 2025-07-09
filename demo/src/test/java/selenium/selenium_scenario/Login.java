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
    public void setup() throws InterruptedException{
         // Setup WebDriver
         System.setProperty("webdriver.chrome.drive", "C:\\ChromeDriver\\chromedriver-win64");

        driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/client");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));
    }

    @Test(priority = 1)
    public void validCredentials() throws InterruptedException {
        // This is a placeholder for the actual test implementation
        // You can add your test logic here
        System.out.println("Valid credentials test is running.");

        //Insert credential
        driver.findElement(By.id("userEmail")).sendKeys("simanjuntakalbert57@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("XBf@rWNvByn!#K8");

        driver.findElement(By.id("login")).click();

        String homepage = driver.findElement(By.xpath("//div[@class = 'left mt-1']/p")).getText();

        Assert.assertEquals(homepage, "Automation Practice","Home page text does not match!");
    }

    @Test(priority = 1, dataProvider = "invalidCredentialsData")
    public void invalidCredentials(String email, String password, String emailError, String passwordError) throws InterruptedException {
        // This is a placeholder for the actual test implementation
        // You can add your test logic here
        System.out.println("Invalid credentials test is running.");

        /*
         * 1. Valid Email , Invalid Password
         * 2. Invalid Email , Valid Password
         * 3. Invalid Email , Invalid Password
         * 4. Empty Email , Invalid Password
         */

        //Insert credential
        driver.findElement(By.id("userEmail")).sendKeys(email);
        driver.findElement(By.id("userPassword")).sendKeys(password);
        driver.findElement(By.id("login")).click();


        // Validate error messages
        // Validate email error
        if(isElementPresent(By.xpath("//input[@id='userEmail']/following-sibling::div//div[@class='ng-star-inserted']"))){
            String emailErrorMessage = driver.findElement(By.xpath("//input[@id='userEmail']/following-sibling::div//div[@class='ng-star-inserted']")).getText();
            Assert.assertEquals(emailErrorMessage, emailError, "Email error message does not match!");
        }

        // Validate password error
        if (isElementPresent(By.xpath("//input[@id='userPassword']/following-sibling::div//div[@class='ng-star-inserted']"))) {
            String passwordErrorMessage = driver.findElement(By.xpath("//input[@id='userPassword']/following-sibling::div//div[@class='ng-star-inserted']")).getText();           
            Assert.assertEquals(passwordErrorMessage, passwordError, "Password error message does not match!");
        }
        
    }


    @DataProvider(name = "invalidCredentialsData")
    public Object[][] invalidCredentialsData() {
        return new Object[][] {
            {"simanjuntakalbert57@gmail.com","","","*Password is required"},
            {"simanjuntakalbert57","XBf@rWNvByn!#K8","*Enter Valid Email",""},
            {"simanjuntakalbert57","","*Enter Valid Email","*Password is required"},
            {"","vByn!#K8","*Email is required",""},
            {"","", "*Email is required", "*Password is required"}
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
