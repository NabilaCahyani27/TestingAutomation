package selenium.selenium_scenario;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CheckoutTest {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setup() throws InterruptedException {
        // Setup WebDriver
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
    }

    @Test
    public void Login() {
        // This is a placeholder for the actual test implementation
        // You can add your test logic here
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        // Cek apakah kita di halaman inventory
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("inventory"), "Login failed or not redirected to inventory!");
    }

    @Test(dependsOnMethods = { "Login" })
    public void CheckoutScenarioTest() throws InterruptedException {
        String productName = "Sauce Labs Backpack"; // produk yang tersedia di SauceDemo

        // Tunggu produk muncul
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("inventory_item")));

        // Pilih produk berdasarkan nama
        List<WebElement> products = driver.findElements(By.className("inventory_item"));
        WebElement selectedProduct = products.stream()
                .filter(p -> p.findElement(By.className("inventory_item_name")).getText().equals(productName))
                .findFirst()
                .orElse(null);

        Assert.assertNotNull(selectedProduct, "Product not found: " + productName);
        selectedProduct.findElement(By.tagName("button")).click(); // Klik "Add to cart"

        driver.findElement(By.className("shopping_cart_link")).click(); // Klik cart icon

        // Verifikasi produk muncul di keranjang
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("cart_item")));
        String cartItemName = driver.findElement(By.className("inventory_item_name")).getText();
        Assert.assertEquals(cartItemName, productName, "Product not in cart!");

        // Klik Checkout
        driver.findElement(By.id("checkout")).click();

        // Isi form checkout
        driver.findElement(By.id("first-name")).sendKeys("Nabila");
        driver.findElement(By.id("last-name")).sendKeys("Cahyani");
        driver.findElement(By.id("postal-code")).sendKeys("12345");
        driver.findElement(By.id("continue")).click();

        // Verifikasi halaman review order
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("finish")));
        driver.findElement(By.id("finish")).click(); // Submit order

        // Verifikasi pesan sukses
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("complete-header")));
        String confirmationText = driver.findElement(By.className("complete-header")).getText();
        Assert.assertEquals(confirmationText, "Thank you for your order!", "Order confirmation not found!");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
