package browsers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DropDownListTest {
    WebDriver driver;

    @BeforeClass
    void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/dropdown");
    }

    @Test
    void verifyOptionHasChecked(){
        Select value= new Select(driver.findElement(By.id("dropdown")));
        value.selectByVisibleText("Option 1");
        value.deselectByVisibleText("Option 1");
        Assert.assertTrue(driver.findElement(By.xpath("//option[@selected='selected']")).getText().equalsIgnoreCase("Option 1"));
    }

    @Test
    void VerifyDropdownchoosen(){
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/dropdown");
        driver.findElement(By.xpath("//select[@id='dropdown']//option[@value='1']")).click();
        Assert.assertTrue(driver.findElement(By.id("dropdown")).findElement(By.xpath("//option[@value='1']")).isSelected());
    }

}
