package browsers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.text.DecimalFormat;

public class CheckBmiCalculatorPage {
    WebDriver driver;
    FillAge fillAge;

    @BeforeClass
    void setAccount() {fillAge = new FillAge();}

    @BeforeClass
    void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.calculator.net/bmi-calculator.html");
        driver.manage().window().maximize();
    }

    //Select Jtabbed Pane
    @Test
    void CheckSelectMetricUnitTab(){
        WebElement sel = driver.findElement(By.xpath("//div[@class='topmenucenter']//ul//li//a[contains(@onclick,'metric')]"));
        if (sel.isSelected()==false) {
            sel.click();
        }else
        Assert.assertEquals(sel.isSelected(), true);
    }

    @Test
    void CheckButtonClear(){
        driver.findElement(By.className("clearbtn")).click();
        String text = driver.findElement(By.xpath("//input[@type='text']")).getText();
        Assert.assertTrue(text.length()==0);
    }
    @DataProvider
    Object[][] FillData(){
        return new Object[][]{
                {"25","160","70","27.3"},
                {"33","160","70","27.3"},
                {"25","170","58","20.1"},
                {"36","160","66","25.8"}
        };
    }

    @Test (dataProvider = "FillData")
    void CheckResult(String age, String height, String weight, String expectedResult){
        Assert.assertEquals(CalculateBmi(age, height, weight),expectedResult);
    }

    @DataProvider
    Object[][] DataAgeTest(){
        return new Object[][]{
                {"120",true},
                {"0",false},
                {"1", false},
                {"25",true},
                {"36",true}
        };
    }
    @Test(dataProvider = "DataAgeTest")
    void checkAgeFill(String age,boolean expectedResult) {
        Assert.assertEquals(fillAge.TestAge(age),expectedResult);
    }

    private void submitCalculate(String age, String height, String weight ) {
        driver.findElement(By.xpath("//*[@id='cage']")).sendKeys(age);
        driver.findElement(By.xpath("//*[@id='cheightmeter']")).sendKeys(height);
        driver.findElement(By.xpath("//*[@id='ckg']")).sendKeys(weight);
        driver.findElement(By.xpath("//input[@value='Calculate']")).click();
    }

    String CalculateBmi(String age, String height, String weight)  {
        driver.findElement(By.className("clearbtn")).click();
        submitCalculate(age,height,weight);

        float changeHeight = Float.parseFloat(height);
        float changeUnitHeight= changeHeight/100;
        float changeWeight = Float.parseFloat(weight);
        float resultCal = changeWeight/(changeUnitHeight*changeUnitHeight);
        DecimalFormat df = new DecimalFormat("#.#");
        String ResultFinal = df.format(resultCal);
        return ResultFinal;
    }

//    @AfterClass
//    void tearDown(){
//        driver.quit();
//    }
}
