package browsers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.Test;

public class OpenFirefoxBrowerTest {
    @Test
    void open(){
        System.setProperty("webdriver.gecko.driver","drivers/geckodriver.exe");
        WebDriver driver = new FirefoxDriver();

    }

    @Test
    void openHeadlessNode(){
        System.setProperty("webdriver.gecko.driver","drivers/geckodriver.exe");

        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setHeadless(true);

        WebDriver driver = new FirefoxDriver(firefoxOptions);
    }


}
