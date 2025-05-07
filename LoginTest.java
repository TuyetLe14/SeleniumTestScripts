package Account;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest {
    WebDriver driver;

    @BeforeClass
    void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/login");
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
        //C1: driver.findElement(By.name("username")).sendKeys("tomsmith");
        //C2: driver.findElement(By.tagName("username")).sendKeys("tomsmith");
        //C3 css: driver.findElement(By.cssSelector("[type=text]")).sendKeys("tomsmith"); tìm tất cả các đối tượng với bất cứ tagname nào có attribute thay bằng tag và gửi chữ vào
        //tương đương dòng trên : driver.findElement(By.xpath("//*[@type='text']")).sendKeys("tomsmith");
        //C4 xpath: driver.findElement(By.xpath("//input[@type='text")).sendKeys("tomsmith"); thu hẹp tìm đối tượng ở html hiện tại với tagname là input có attribute thay bằng tag
        //driver.findElement(By.cssSelector("[name=username]")).sendKeys("tomsmith");
        //driver.findElement(By.xpath("//input[@name='username']")).sendKeys("tomsmith");
        //driver.findElement(By.cssSelector("#username")).sendKeys("tomsmith");
        //driver.findElement(By.xpath("//input[@id='username']")).sendKeys("tomsmith");
        //driver.findElement(By.xpath("//*[contains(@type,'text')]")).sendKeys("tomsmith");
        //driver.findElement(By.xpath("//input[contains(@type='text')]")).sendKeys("tomsmith"); cho lấy đối tượng đủ hơn không bị ảnh hưởng những cái đằng sau đằng trước
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.cssSelector("button[type=submit]")).click();
    }

//    @AfterClass
//    void tearDown() {
//        driver.quit();
//    }
}
