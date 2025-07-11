package selenium.locator;

import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AlertTest {

    WebDriver driver;

    @Test
    public void alertTest() throws InterruptedException {
        // Setup WebDriver
       System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver.exe");

        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/inventory.html"); // Sesuaikan URL jika tidak ada alert
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));
        Thread.sleep(3000);

        System.out.println("Test dimulai.");

        driver.findElement(By.id("user-name")).sendKeys("standard_user");

        // Jika ingin test alert, pastikan ada tombol alert dan aktifkan baris ini:
        // driver.findElement(By.id("alertbtn")).click();

        // Handle alert jika memang muncul
        try {
            Alert alert = driver.switchTo().alert();
            System.out.println("Alert text: " + alert.getText());
            alert.accept();
        } catch (Exception e) {
            System.out.println("Tidak ada alert yang muncul: " + e.getMessage());
        }

        Thread.sleep(3000);
        driver.quit();
    }
}
