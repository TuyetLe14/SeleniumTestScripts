package browsers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class JavaScriptAlertTest {
    WebDriver driver;

    @BeforeClass
    void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
    }

    @Test
    void dismissAlert(){
        driver.findElement(By.xpath("//button[.='Click for JS Alert']")).click();
        driver.switchTo().alert().accept();
        String result = driver.findElement(By.id("result")).getText();
        Assert.assertEquals(result,"You successfully clicked an alert");
    }

    @Test
    void acceptConfirm(){
        driver.findElement(By.xpath("//button[.='Click for JS Confirm']")).click();
        driver.switchTo().alert().accept();
        String result = driver.findElement(By.id("result")).getText();
        Assert.assertEquals(result,"You clicked: Ok");
    }

    @Test
    void dismissConfirm(){
        driver.findElement(By.xpath("//button[.='Click for JS Confirm']")).click();
        driver.switchTo().alert().dismiss();
        String result = driver.findElement(By.id("result")).getText();
        Assert.assertEquals(result,"You clicked: Cancel");
    }

    @Test
    void sendKeyToPrompt(){
        driver.findElement(By.xpath("//button[.='Click for JS Prompt']")).click();

        driver.switchTo().alert().sendKeys("hello");
        driver.switchTo().alert().accept();

        String result = driver.findElement(By.id("result")).getText();
        Assert.assertEquals(result,"You entered: hello");
    }
    @AfterClass
    void tearDown(){
        driver.quit();
    }
}
