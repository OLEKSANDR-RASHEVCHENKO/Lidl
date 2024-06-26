package e2e;

import config.Config;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.ByteArrayInputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class ApplicationManager {
    private final Config config = new Config();
    public WebDriver driver;

    public void init() {
        if (config.getSelenoidState()) {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setBrowserName("chrome");
            capabilities.setVersion("120.0");
            Map<String, Object> selenoidOptions = new HashMap<>();
            selenoidOptions.put("enableVNC", false);

            capabilities.setCapability("selenoid:options", selenoidOptions);
            try {
                driver = new RemoteWebDriver(
                        URI.create(config.getSelenoidUrl()).toURL(),
                        capabilities
                );
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }


        } else {
            WebDriverManager.chromedriver().clearResolutionCache().setup();
            ChromeOptions options = new ChromeOptions();
            if (config.getHeadless()) {
                options.addArguments("--headless");
            }
            options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
            options.addArguments("--force-device-scale-factor=1");
            options.addArguments("--high-dpi-support=1");
            driver = new ChromeDriver(options);
        }
        driver.get(config.getProjectUrl());
        driver.manage().window().setSize(new Dimension(config.getWindowWeight(), config.getWindowHeight()));
    }


    public void stop(boolean testPassed) {
        if (!testPassed) {
            byte[] screenshotData = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment("Screenshot on failure", new ByteArrayInputStream(screenshotData));
        }
        driver.quit();
    }
}
