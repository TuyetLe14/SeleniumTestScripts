package browsers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class HoverTest {
    WebDriver driver;

    @BeforeClass
    void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/hovers");
    }

    @Test
    void hoverUser1() {
        Actions mouse = new Actions(driver);
        WebElement user1 = driver.findElement(By.xpath("//div[@class='figure'][2]"));
        mouse.moveToElement(user1).perform();
        WebElement caption = driver
                .findElement(By.className("figcaption"))
                .findElement(By.tagName("h5"));
        Assert.assertEquals(caption.getText(), "name: user1");
    }

    @AfterMethod
    void tearDown(ITestResult result){
        if(!result.isSuccess()){
            TakesScreenshot scrShot =((TakesScreenshot)driver);
            File DestFile = new File("./target/"+result.getName()+".png");
            try {
                FileUtils.copyFile(scrShot.getScreenshotAs(OutputType.FILE), DestFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
