package browsers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LinkTest {
    WebDriver driver;

    @BeforeClass
    void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/status_codes");
    }

    @Test
    void verifyHyperLinkHasChecked(){
        driver.findElement(By.xpath("//a[@href='status_codes/200']")).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("status_codes/200"));
        driver.findElement(By.partialLinkText("here")).click();
        driver.navigate().back();
//        driver.quit();
    }
}
