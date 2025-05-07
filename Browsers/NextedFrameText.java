package browsers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class NextedFrameText {
    WebDriver driver;

    @BeforeClass
    void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/nested_frames");
    }

    /**
     * <frame src="/frame_middle" scrolling="no" name="frame-middle">
     */
    @Test
    void getPageContent(){
        // origin
        driver.switchTo().frame("frame-top"); // frame-top
        driver.switchTo().frame("frame-middle"); // frame-middle
        String middleContent = driver.findElement(By.id("content")).getText();
        Assert.assertEquals(middleContent,"MIDDLE");


        driver.switchTo().defaultContent(); //origin
        driver.switchTo().frame("frame-top");
        driver.switchTo().frame("frame-left"); //frame-left
        String leftContent = driver.findElement(By.xpath("//html/body")).getText();
        Assert.assertEquals(leftContent,"LEFT");

        driver.switchTo().defaultContent(); //origin
        driver.switchTo().frame("frame-top"); //frame-right
        driver.switchTo().frame("frame-right"); //frame-right
        String rightContent = driver.findElement(By.xpath("//html/body")).getText();
        Assert.assertEquals(rightContent,"RIGHT");

        driver.switchTo().defaultContent(); //origin
        driver.switchTo().frame("frame-bottom"); //frame-bottom
        String bottomContent = driver.findElement(By.xpath("//html/body")).getText();
        Assert.assertEquals(bottomContent,"BOTTOM");

    }

    @AfterClass
    void tearDown(){
        driver.quit();
    }
}
