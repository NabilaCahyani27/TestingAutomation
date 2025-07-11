package demo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class InventoryPage {
    WebDriver driver;

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    public void addProductToCart(String productName) {
        List<WebElement> products = driver.findElements(By.className("inventory_item"));
        for (WebElement product : products) {
            String name = product.findElement(By.className("inventory_item_name")).getText();
            if (name.equals(productName)) {
                product.findElement(By.tagName("button")).click();
                break;
            }
        }
    }

    public void goToCart() {
        driver.findElement(By.className("shopping_cart_link")).click();
    }
}
