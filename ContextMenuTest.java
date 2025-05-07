package browsers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.datatransfer.*;
import java.io.IOException;

public class ContextMenuTest {
    WebDriver driver;

    @BeforeClass
    void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/context_menu");
    }

    @Test
    void rightClick(){
        Actions mouse = new Actions(driver);
        WebElement hotPot = driver.findElement(By.id("hot-spot"));
        mouse.contextClick(hotPot).perform();

        driver.switchTo().alert().accept();

    }

    @Test
    void clipBoard() throws IOException, UnsupportedFlavorException {
        driver.get("https://the-internet.herokuapp.com/login");
        Clipboard c = Toolkit.getDefaultToolkit().getSystemClipboard();
        StringSelection testData;

        //  Add some test data
        testData = new StringSelection( "hello" );
        c.setContents(testData, testData);

        //  Get clipboard contents, as a String

        Transferable t = c.getContents( null );

        if ( t.isDataFlavorSupported(DataFlavor.stringFlavor) )
        {
            Object o = t.getTransferData( DataFlavor.stringFlavor );
            String data = (String)t.getTransferData( DataFlavor.stringFlavor );
            System.out.println( "Clipboard contents: " + data );
        }
        Actions keyboard = new Actions(driver);
        WebElement el= driver.findElement(By.id("username"));
        keyboard.keyDown(el, Keys.CONTROL).perform();
        keyboard.sendKeys(el, "v").perform();
        keyboard.keyUp(el, Keys.CONTROL).perform();

    }
}
