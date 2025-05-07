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

import java.util.List;

public class TableTest {
    WebDriver driver;

    @BeforeClass
    void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/tables");
    }

    //C1
    @Test
    void verifyTableHasChecked() {

//        List<WebElement> bodyCells = driver.findElements(By.xpath("//table[@id='table1']/tbody/tr/td"));
//
////        bodyCells.forEach(cell -> {
////            System.out.println(cell.getText());
////        });
//
//        List<WebElement> dueColumn = driver.findElements(By.xpath("//table[@id='table1']/tbody/tr/td[4]"));
        List<WebElement> AllRows = driver.findElements(By.xpath("//table[@id='table1']/tbody/tr"));

        WebElement firstRow = AllRows.get(0);
        String maxDuePerson = firstRow.findElement(By.xpath("//td[1]")).getText() + " " +
                firstRow.findElement(By.xpath("//td[2]")).getText();
        String maxDueString = firstRow.findElement(By.xpath("//td[4]")).getText().replace("$", "");

        float maxDue = Float.parseFloat(maxDueString);

        for (int i = 1; i < AllRows.size(); i++) {
            WebElement Geti = AllRows.get(i);
            String currentDueParse = Geti.findElement(By.xpath("//*/table[1]/tbody/tr["+i+"]/td[4]")).getText().replace("$", "");
            String currentDuePerson = Geti.findElement(By.xpath("//*/table[1]/tbody/tr["+i+"]/td[1]")).getText() + " " + Geti.findElement(By.xpath("//*/table[1]/tbody/tr["+i+"]/td[2]")).getText();
            float currentDue = Float.parseFloat(currentDueParse);
                if (currentDue >= maxDue) {
                    maxDue = currentDue;
                    maxDuePerson = currentDuePerson;
                }
        }
        System.out.println("max: " + maxDue);
        System.out.println("người: "+ maxDuePerson);
        Assert.assertTrue(maxDuePerson.contains("Doe Jason"));
    }

    @AfterClass
    void tearDown(){
        driver.quit();
    }
}
