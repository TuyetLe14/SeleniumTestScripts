package browsers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v100.emulation.Emulation;
import org.openqa.selenium.devtools.v100.performance.Performance;
import org.openqa.selenium.devtools.v100.performance.model.Metric;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class OpenChromeBrowserTest {

    @Test
    void openNormalMode(){
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
    }

    @Test
    void openHeadlessMode(){
        WebDriverManager.chromedriver().setup();

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setHeadless(true);

        WebDriver driver = new ChromeDriver(chromeOptions);
    }

    @Test
    void openMobileMode(){
        WebDriverManager.chromedriver().setup();

        Map<String, Object> deviceMetrics = new HashMap<>();
        deviceMetrics.put("width", 390);
        deviceMetrics.put("height", 844);
        deviceMetrics.put("pixelRatio", 1.0);
        Map<String, Object> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceMetrics", deviceMetrics);
        mobileEmulation.put("userAgent", "Mozilla/5.0 (iPhone; CPU iPhone OS 10_3_1 like Mac OS X) AppleWebKit/603.1.30 (KHTML, like Gecko) Version/10.0 Mobile/14E304 Safari/602.1");

        ChromeOptions chromeOptions = new ChromeOptions();

        chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);

        // launch chrome app
        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://google.com.vn");
    }

    @Test
    void devTool(){
        WebDriverManager.chromedriver().setup();

        ChromeDriver driver = new ChromeDriver();
        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        devTools.send(Emulation.setGeolocationOverride(Optional.of(52.5043),
                Optional.of(13.4501),
                Optional.of(1)));

        driver.get("https://my-location.org/");
        driver.quit();
    }

    @Test
    void collectBrowserMetric(){
        WebDriverManager.chromedriver().setup();

        ChromeDriver driver = new ChromeDriver();
        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        devTools.send(Performance.enable(Optional.empty()));
        List<Metric> metricList = devTools.send(Performance.getMetrics());

        driver.get("https://google.com");
        driver.quit();

        for(Metric m : metricList) {
            System.out.println(m.getName() + " = " + m.getValue());
        }
    }
}