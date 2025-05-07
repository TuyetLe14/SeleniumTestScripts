
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;


public class data {

    public static WebDriver driver;

    @Test
    public void open(){
        WebDriverManager.chromedriver().driverVersion("100").setup();
        //C2: System.setProperty("webdriver.chrome.driver","drivers/chromedriver1.exe");
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\Program Files\\CocCoc\\Browser\\Application\\browser.exe");

        driver = new ChromeDriver(options);

        //Applied wait time
//        driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);

        //maximize window
        driver.manage().window().maximize();

        //open browser with desried URL
        driver.get("https://www.selenium.dev/");

        //driver.close();
    }

}
