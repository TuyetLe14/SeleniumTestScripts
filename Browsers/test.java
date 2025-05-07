package browsers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class test {
    WebDriver driver;

    @BeforeClass
    void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/tables");
    }
    @Test
    void Test(){
        List<WebElement> AllRows = driver.findElements(By.xpath("//table[@id='table1']/tbody/tr"));
        float max=0;
        String maxperson=null;
        for(int i=1;i<AllRows.size();i++)
        {
            List<WebElement> cols=driver.findElements(By.xpath("//table[@id='table1']/tbody/tr/td"));
            for(int j=1;j<cols.size();j++)
            {
                WebElement GetValuei = AllRows.get(i);
                String currentDueParse = GetValuei.findElement(By.xpath("//*/table[1]/tbody/tr["+i+"]/td[4]")).getText().replace("$", "");
                String currentDuePerson = GetValuei.findElement(By.xpath("//*/table[1]/tbody/tr["+i+"]/td[1]")).getText() + " " + GetValuei.findElement(By.xpath("//*/table[1]/tbody/tr["+i+"]/td[2]")).getText();
                float currentDue = Float.parseFloat(currentDueParse);
                if (max <= currentDue )
                {
                    max = currentDue;
                    maxperson = currentDuePerson;
                }
            }
        }
        System.out.println("max: " + max);
        System.out.println("người: "+ maxperson);
//        Assert.assertTrue(maxperson.contains("Doe Jason"));
    }
}
