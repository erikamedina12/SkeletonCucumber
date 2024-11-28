package config.web;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.java.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.nio.file.Paths;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Log
public class WebDriverFactory {

    public static WebDriver createNewDriver(String platform, String urlBase) throws Exception {
        WebDriver driver;

        if (!"CHROME_LOCAL".equalsIgnoreCase(platform)) {
            log.info(() -> "Creating");
        }

        if ("FIREFOX".equalsIgnoreCase(platform)) {
            WebDriverManager.firefoxdriver().clearResolutionCache().forceDownload().setup();
            driver = new FirefoxDriver();
        } else if ("CHROME".equalsIgnoreCase(platform)) {
            //WebDriverManager.chromedriver().clearResolutionCache().forceDownload().setup();
            WebDriverManager.chromedriver().setup();
            Map<String, Object> prefs = new HashMap<String, Object>();
            ChromeOptions options = new ChromeOptions();
            prefs.put(
                    "download.default_directory", getCurrentPath() + "\\src\\test\\resources\\downloads");
            prefs.put("download.prompt_for_download", false);
            options.setExperimentalOption("prefs", prefs);
            driver = new ChromeDriver(options);

        } else if ("CHROME_LOCAL".equalsIgnoreCase(platform)) {
            System.setProperty(
                    "webdriver.chrome.driver",
                    getCurrentPath() + "\\src\\test\\resources\\bin\\chromedriver.exe");
            Map<String, Object> prefs = new HashMap<String, Object>();
            ChromeOptions options = new ChromeOptions();
            prefs.put(
                    "download.default_directory", getCurrentPath() + "\\src\\test\\resources\\downloads");
            prefs.put("download.prompt_for_download", false);
            options.setExperimentalOption("prefs", prefs);
            options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
            driver = new ChromeDriver(options);
        } else {
            log.info(() -> "The Driver is not selected properly, invalid name: " + platform);
            return null;
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
        driver.get(urlBase);
        return driver;
    }

    public static String getCurrentPath(){
        return Paths.get("").toAbsolutePath().toString();
    }

}
