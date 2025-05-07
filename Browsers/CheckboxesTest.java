package browsers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CheckboxesTest {

    WebDriver driver;

    @BeforeClass
    void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/checkboxes");
    }

    @Test
    void verifyCheckboxes1HasChecked(){
        WebElement checkbox1 = driver.findElements(By.cssSelector("input[type='checkbox']")).get(0);
        if(checkbox1.isSelected() == false){
            checkbox1.click();
            Assert.assertTrue(checkbox1.isSelected());
        }
    }

    @AfterClass
    void tearDown(){
        driver.quit();
    }
}
