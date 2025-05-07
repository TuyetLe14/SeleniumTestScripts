package browsers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class TableTest2 {
    WebDriver driver;
    List<Person> persons;

    @BeforeClass
    void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/tables");
        List<WebElement> rows = driver.findElements(By.xpath("//table[@id='table1']/tbody/tr"));

        persons = rows.stream()
                .map(this::toPerson).collect(Collectors.toList());

    }
    @Test
    void largestDuePerson() {
        Person largestDuePerson = persons
                .stream()
                .max(Comparator.comparing(Person::getDue))
                .orElseThrow(NoSuchElementException::new);

        Assert.assertEquals(largestDuePerson.getFullName(), "Jason Doe");
    }

    /**
     * element is a row in table
     * @param element
     * @return
     */
    private Person toPerson(WebElement element) {
        String lastName = element.findElements(By.tagName("td")).get(0).getText();
        String firstname = element.findElements(By.tagName("td")).get(1).getText();
        float due = Float.parseFloat(element.findElements(By.tagName("td")).get(3).getText().trim().replace("$", ""));
        return new Person(String.format("%s %s", firstname, lastName), due);
    }

}
