package myProject.StepsDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import config.WebDriverConfig;
import lombok.extern.java.Log;
import org.openqa.selenium.WebDriver;

@Log
public class Hooks {

    public static WebDriver driver;

    @Before
    public void initWebDriver(Scenario scenario) throws Exception {
        driver = WebDriverConfig.initWebConfig();
        if (driver == null) {
            log.info("WebDriver was not set properly");
        } else {
            log.info("WebDriver started successfully");
        }
    }

    @After
    public void tearDown(Scenario scenario) throws Exception {
        if (driver != null) {
            driver.quit();
        } else {
            log.info("WebDriver is null, cannot tear down");
        }
    }
}
