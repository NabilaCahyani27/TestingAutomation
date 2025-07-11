package selenium.definitions;

import org.openqa.selenium.By;
import org.testng.Assert;

import demo.base.BaseTest;
import demo.pages.LoginPage;
import demo.pages.InventoryPage;
import demo.pages.CartPage;
import demo.pages.CheckoutPage;
import demo.pages.ConfirmationPage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitions extends BaseTest {
    /*
     * Given User landing to logged ecommerce
     * When User input email "" and password ""
     * Then User redirect to homepage
     */
    LoginPage loginPage;
    InventoryPage inventoryPage;
    CartPage cartPage;
    CheckoutPage checkoutPage;
    ConfirmationPage confirmationPage;

    @Given("User is on the SauceDemo login page")
    public void user_is_on_login_page() {
        setUp();
        loginPage = new LoginPage(driver);
        driver.get("https://www.saucedemo.com/");
    }

    @Then("User should see {string}")
    public void user_should_see(String expectedText) {
        String actualText = "";

        // Coba cari pesan error login
        try {
            actualText = driver.findElement(By.cssSelector("[data-test='error']")).getText(); // pesan error
        } catch (Exception e) {
            // Jika tidak ada error, cek apakah redirect ke halaman inventory
            if (driver.getCurrentUrl().contains("inventory")) {
                actualText = "inventory page";
            } else {
                actualText = "unknown page";
            }
        }

        Assert.assertTrue(
                actualText.contains(expectedText),
                "Expected: \"" + expectedText + "\" but got: \"" + actualText + "\"");
    }

    @When("User logs in with username {string} and password {string}")
    public void user_logs_in(String username, String password) {
        loginPage.login(username, password);

        // Inisialisasi hanya jika login berhasil (tidak ada pesan error)
        if (driver.getCurrentUrl().contains("inventory")) {
            inventoryPage = new InventoryPage(driver);
            cartPage = new CartPage(driver);
            checkoutPage = new CheckoutPage(driver);
            confirmationPage = new ConfirmationPage(driver);
        }
    }

    @Then("User should be redirected to the inventory page")
    public void user_should_be_on_inventory_page() {
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"), "User is not on the inventory page.");
    }

    @When("User adds {string} to the cart")
    public void user_adds_product_to_cart(String productName) {
        inventoryPage.addProductToCart(productName);
        inventoryPage.goToCart();
        cartPage = new CartPage(driver);
    }

    @And("User proceeds to checkout")
    public void user_proceeds_to_checkout() {
        cartPage.clickCheckout();
        checkoutPage = new CheckoutPage(driver);
    }

    @And("User fills in checkout information with first name {string}, last name {string}, and postal code {string}")
    public void user_fills_checkout_info(String firstName, String lastName, String postalCode) {
        checkoutPage.fillCheckoutInfo(firstName, lastName, postalCode);
        checkoutPage.finishCheckout();
        confirmationPage = new ConfirmationPage(driver);
    }

    @Then("User should see the order confirmation message {string}")
    public void user_should_see_order_confirmation(String expectedMessage) {
        String actualMessage = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(actualMessage.contains(expectedMessage), "Order confirmation message does not match!");
    }
}
