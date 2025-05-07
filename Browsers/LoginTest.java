package browsers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginTest {

    WebDriver driver;

    @Parameters({"browser"})
    @BeforeClass
    void setup(String browser) {
        driver = openBrowser(browser);
        driver.get("https://the-internet.herokuapp.com/login");
    }


    /**
     * @param name : chrome, firefox
     * @return WebDriver
     */
    public static WebDriver openBrowser(String name){
        if(name.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver();
        } else if (name.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            return new FirefoxDriver();

        } else if (name.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            return new EdgeDriver();

        } else if (name.equalsIgnoreCase("safari")) {
            return new SafariDriver();

        } else if (name.equalsIgnoreCase("ie")) {
            WebDriverManager.iedriver().setup();
            return new InternetExplorerDriver();

        } else throw new IllegalArgumentException("The browser "+name+ " does not support!");
    }

    @BeforeTest
    void beforeTest(){

    }

    @Test(priority = 1)
    void successfullyWithValidCredentials() {
        submitCredentials("tomsmith", "SuperSecretPassword!");

        Assert.assertEquals(driver.getCurrentUrl(), "https://the-internet.herokuapp.com/secure");
        String message = driver.findElement(By.className("success")).getText();
        Assert.assertTrue(message.contains("You logged into a secure area!"));
    }

    @DataProvider
    Object[][] invalidCredentialsData(){
        return new Object[][]{
                {"tomsmith","SuperSecretPassword","Your password is invalid!"},
                {"tom","SuperSecretPassword","Your username is invalid!"},
                {"","","Your username is invalid!"}
        };
    }

    @Test(dataProvider = "invalidCredentialsData",priority = 0)
    void errorWithInvalidCredentials(String username,String password,String errorMessage) {
        submitCredentials(username, password);

        Assert.assertEquals(driver.getCurrentUrl(), "https://the-internet.herokuapp.com/login");
        String message = driver.findElement(By.className("error")).getText();
        Assert.assertTrue(message.contains(errorMessage));
    }

    private void submitCredentials(String username, String password) {
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.cssSelector("button[type=submit]")).click();
    }

    @AfterClass
    void tearDown() {
        driver.quit();
    }
}