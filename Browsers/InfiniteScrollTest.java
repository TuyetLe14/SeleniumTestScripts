package browsers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class InfiniteScrollTest {
    WebDriver driver;

    @BeforeClass
    void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/infinite_scroll");
    }

    @Test
    void scrollDown() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        for (int i = 1; i < 10; i++) {
            js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        }

    }
}
