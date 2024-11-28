package config.web;

import io.cucumber.datatable.DataTable;
import lombok.extern.java.Log;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;

@Log
public class WebDriverHelper extends WebDriverDataManagementHelper{

    static final Duration EXPLICIT_TIMEOUT = Duration.ofSeconds(15);

    public HashMap<String, String>windowsHandle = new HashMap<>();

    public List<WebElement> getElements(WebDriver driver, By loc){
        return isWebElementDisplayed(driver, loc)?driver.findElements(loc) : null;

    }

    public static WebElement getElement(WebDriver driver, By loc){
        return isWebElementDisplayed(driver, loc)?driver.findElement(loc) : null;
    }

    public static boolean isWebElementDisplayed(WebDriver driver, By element){

        boolean isDisplayed;
        try{
            WebDriverWait wait = new WebDriverWait(driver, EXPLICIT_TIMEOUT);
            isDisplayed = wait.until(ExpectedConditions.presenceOfElementLocated(element)).isDisplayed()
                    && wait.until(ExpectedConditions.visibilityOfElementLocated(element)).isDisplayed();
        }catch(NoSuchElementException | TimeoutException e){
            isDisplayed = false;
            log.info(e.getMessage());
            log.info(String.valueOf(e));
        }
        return isDisplayed;

    }

    public Alert isAlertPresent(WebDriver driver){
        Alert simpleAlert = null;
        try {
            WebDriverWait wait = new WebDriverWait(driver, EXPLICIT_TIMEOUT);
            wait.until(ExpectedConditions.alertIsPresent());
            simpleAlert = driver.switchTo().alert();
        } catch (Exception e) {
            log.info("Alert not found");
        }
        return simpleAlert;
    }

    public void getWindowHandle(WebDriver driver, String windowName){
        boolean alreadyExists;
        sleep(10);
        if(windowsHandle.containsKey(windowName)){
            driver.switchTo().window(windowsHandle.get(windowName));
        } else {
            for (String winHandle : driver.getWindowHandles()) {
                for (String entry : windowsHandle.keySet()) {
                    String value = windowsHandle.get(entry.trim());
                    alreadyExists = StringUtils.equalsIgnoreCase(value, windowName);
                    if(alreadyExists){
                        windowsHandle.put(windowName, winHandle);
                    }
                }
            }
        }
    }

    public void sleep(int seconds){
        try {
            Thread.sleep(1000 * seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void scrollToElement(WebDriver driver, By loc){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement element = driver.findElement(loc) != null ? driver.findElement(loc) : null;
        if (element != null) {
            js.executeScript("arguments[0].scrollIntoView();", element);
        } else {
            throw new SkipException("Element not found");
        }
    }

    public void jsClick(WebDriver driver, By loc){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement element = driver.findElement(loc) != null ? driver.findElement(loc) : null;
        if (element != null) {
            js.executeScript("arguments[0].click();", element);
        } else {
            throw new SkipException("Element not found");
        }
    }

    public void setAttribute(WebDriver driver, By loc, String key, String value){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement element = driver.findElement(loc) != null ? driver.findElement(loc) : null;
        if (element != null) {
            js.executeScript(String.format("arguments[0].setAttribute('%s', '%s')", key, value), element);
        } else {
            throw new SkipException("Element not found");
        }
    }

    public void waitPageCompletelyLoaded(WebDriver driver){
        String getActual = driver.getCurrentUrl();
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(EXPLICIT_TIMEOUT)
                .pollingEvery(Duration.ofSeconds(3))
                .ignoring(NoSuchElementException.class);
        wait.until(webDriver -> ((JavascriptExecutor) webDriver)
                .executeScript("return document.readyState")
                .equals("complete"));
    }

    /**
     * Create a table with parameters given on feature step
     * @param table is a list with parameters given on step
     * @return
     */
    public DataTable createDataTable(List<List<String>> table) {
        DataTable data;
        data = DataTable.create(table);
        log.info(data.toString());
        return data;
    }

    public void jsSendKeys(WebDriver driver, By locator, String value) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        WebElement elem = driver.findElement(locator) != null ? driver.findElement(locator) : null;
        if (elem != null) {
            jse.executeScript(String.format("arguments[0].value = '%s'", value), elem);
            log.info(locator + "value set by js" + value);
        } else {
            throw new SkipException("Element not found" + locator);
        }
    }

}
