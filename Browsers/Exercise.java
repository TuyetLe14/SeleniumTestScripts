package browsers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.AfterClass;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class Exercise {
    WebDriver driver;

    @BeforeClass
    void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://todomvc.com/examples/vanillajs/");
    }

    @Test(priority = 1)
    void TC6_successfullyWithValidInput() {
        submitvalid("excercisehi");

        Assert.assertEquals(driver.getCurrentUrl(), "https://todomvc.com/examples/vanillajs/");
        String message = driver.findElement(By.className("toggle")).getText();
    }

    @Test(priority = 2)
    public void TC2_CheckboxIsDisplayed() throws Exception {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://todomvc.com/examples/vanillajs/");
        driver.manage().window().maximize();

        WebElement element = driver.findElement(By.xpath("//*[@class='new-todo']"));

        Assert.assertEquals(element.isDisplayed(),true);

        Thread.sleep(2);
    }

    @Test(priority = 3)
    public void TC2_LearnIsDisplayed() throws Exception {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://todomvc.com/examples/vanillajs/");
        driver.manage().window().maximize();

        WebElement element = driver.findElement(By.xpath("//*[@class='learn']")) ;

        Assert.assertEquals(element.isDisplayed(),true);
        Thread.sleep(2);
    }

    @Test(priority = 4)
    public void TC3_ListIsDisplayed() throws Exception {
      WebElement element = driver.findElement(By.xpath("//*[@id='toggle-all']"));

      Assert.assertEquals(element.isDisplayed(),true);
    }

    @Test(priority = 5)
    public void TC4_FilterIsDisplayed() throws Exception {
        WebElement element = driver.findElement(By.xpath("//*[@class='filters']"));

        Assert.assertEquals(element.isDisplayed(),true);
    }

    @Test(priority = 6)
    public void TC5_FooterIsDisplayed() throws Exception {
        WebElement element = driver.findElement(By.xpath("//*[@class='info']"));

        Assert.assertEquals(element.isDisplayed(),true);
    }

//    @Test(priority = 6)
//    public void TC6_ButtonIsDisplayed() throws Exception {
//        String element = "//*[@class='destroy']";
//        WebElement ele = driver.findElement(By.className("destroy"));
//        Actions action = new Actions(driver);
//        action.moveToElement(ele);
//        if(isElementDisplayed(driver, element)){
//            System.out.println("Button is displayed");
//        } else{
//            System.out.println("Button isn't displayed");
//        }
//    }




    @Test(priority = 7)
    void TC7_successfullyWithValidCheck() throws InterruptedException {
        submitvalid("excercisehi1");
        List<WebElement> ListCheck = driver.findElements(By.className("toggle"));
        boolean isSelected = ListCheck.get(0).isSelected();
        if (isSelected == true) {
            ListCheck.get(1).click();
        } else {ListCheck.get(0).click();}

        Thread.sleep(2);
    }

//    @Test(priority = 8)
//    void successfullyWithDestroyValid() throws InterruptedException {
//        submitvalid("excercise1");
//
//
//    }

    public boolean isElementDisplayed(WebDriver driver, String yourLocator) {
        try {
            WebElement locator;
            locator = driver.findElement(By.xpath(yourLocator));
            return locator.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    private void submitvalid(String todo)
    {
        driver.findElement(By.tagName("input")).sendKeys(todo);
        WebElement value = driver.findElement(By.tagName("input"));
        value.sendKeys(Keys.ENTER);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}



