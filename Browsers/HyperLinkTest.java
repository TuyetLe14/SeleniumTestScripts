package browsers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HyperLinkTest {
    WebDriver driver;

    @BeforeClass
    void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/status_codes");
    }

    /**
     * <a href="status_codes/200">200</a> --> open link in current tab
     */

    // <a href="status_codes/200" target="_blank">200</a> --> open link in new tab
    @Test
    void verifyNavigationSuccessfully(){

        driver.findElement(By.linkText("200")).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("status_codes/200"));

        driver.navigate().back();


        driver.findElement(By.linkText("301")).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("status_codes/301"));
        driver.navigate().back();

        driver.findElement(By.linkText("404")).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("status_codes/404"));
        driver.navigate().back();

        driver.findElement(By.linkText("500")).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("status_codes/500"));
        driver.navigate().back();

    }


    /**
     * Step : open new tab
     * Step 2: switch to new tab
     * Step 3: actions in new tab
     * Step 3: back to previous tab
     *
     */

    @AfterClass
    void tearDown(){
        driver.quit();
    }
}
