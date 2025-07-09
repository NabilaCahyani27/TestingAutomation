package selenium.locator;


import java.util.Iterator;
import java.util.Set;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

public class HandleWindow {
       WebDriver driver, mozilaDriver;
    
    @Test
    public void handleWindowTest() throws InterruptedException {
         // Setup WebDriver
        System.setProperty("webdriver.chrome.drive", "/Users/bytedance/CourseQAAutomation/APIAdvance/bootcamp_batch3/materi/automation-after-office-batch3/chromedriver");
        
        //Admin
        driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/loginpagePractise/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));
        Thread.sleep(5000);

        driver.findElement(By.className("blinkingText")).click();

        Set<String> windowHandles = driver.getWindowHandles();

        /*
         * Bakal ada dua value di dalam set ini [parent window, child window]
         */

         System.out.println("Total window handles: " + windowHandles.size());
         System.out.println("Window handles: " + windowHandles);

        Iterator<String> iterator = windowHandles.iterator();
        String parentWindow = iterator.next();
        String childWindow = iterator.next();

        //Window handles: [DD55B80CB3D76C0159CE0F88D1B03E11, 8287DCE50C81A91A6976DC6C689E3170]

        Thread.sleep(4000);
        driver.switchTo().window(childWindow);

       
        String getText = driver.findElement(By.cssSelector("h1")).getText();  
        Assert.assertEquals("DOCUMENTS REQUEST", getText, "Text does not match!");


        Thread.sleep(4000);
        driver.switchTo().window(parentWindow);

        Thread.sleep(4000);
        driver.switchTo().window(childWindow);

        Thread.sleep(4000);
        driver.switchTo().window(parentWindow);

        driver.quit();



        //Buyer
        mozilaDriver = new FirefoxDriver();
          System.setProperty("webdriver.chrome.drive", "C:\\ChromeDriver\\chromedriver-win64");
        mozilaDriver.get("https://rahulshettyacademy.com/loginpagePractise/");


        // directory folder "/Users/bytedance/Downloads/3753631/3753631.zip"
    }
}
