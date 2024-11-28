package myProject.web.Pages;

import config.web.WebDriverHelper;
import io.cucumber.datatable.DataTable;
import lombok.extern.java.Log;
import myProject.web.PageObjects.EmergenciasPageObjects;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.SkipException;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Log
public class EmergenciasPage extends WebDriverHelper {

    static EmergenciasPageObjects emergenciasPageObjects = new EmergenciasPageObjects();

    public void waitForElementsAreLoaded(WebDriver driver) {

        By titleLoc = By.xpath(String.format(emergenciasPageObjects.GENERIC_TEXT_LOC, "el precio de tu plan"));
        Assert.assertTrue(isWebElementDisplayed(driver, titleLoc));

        Assert.assertTrue(isWebElementDisplayed(driver, emergenciasPageObjects.nameFieldLoc));
        Assert.assertTrue(isWebElementDisplayed(driver, emergenciasPageObjects.provinciaDropDownLoc));
        Assert.assertTrue(isWebElementDisplayed(driver, emergenciasPageObjects.codAreaLog));
        Assert.assertTrue(isWebElementDisplayed(driver, emergenciasPageObjects.phoneFieldLoc));
        Assert.assertTrue(isWebElementDisplayed(driver, emergenciasPageObjects.cotizaButtonLoc));
    }

    public void setFirstStepTextBoxes(WebDriver driver, List<List<String>> table) {
        DataTable data = createDataTable(table);
        if (data!=null) {
            data.cells().forEach(value -> {
                String KEY = "";
                String VALUE = "";
                try {
                    List<String> rField = Collections.singletonList(value.get(0));
                    List<String> rValue = Collections.singletonList(value.get(1));
                    KEY = rField.get(0);
                    VALUE = rValue.get(0);
                    //Logic here
                    if (StringUtils.containsIgnoreCase(KEY, "Nombre")){
                        jsSendKeys(driver, emergenciasPageObjects.nameFieldLoc, KEY);
                    } else if (StringUtils.containsIgnoreCase(KEY, "Provincia")){
                        jsSendKeys(driver, emergenciasPageObjects.provinciaDropDownLoc, KEY);
                    } else if (StringUtils.containsIgnoreCase(KEY, "Cod. Area")){
                        jsSendKeys(driver, emergenciasPageObjects.codAreaLog, KEY);
                    } else if (StringUtils.containsIgnoreCase(KEY, "Celular")){
                        jsSendKeys(driver, emergenciasPageObjects.phoneFieldLoc, KEY);
                    }
                } catch (NullPointerException e) {
                    log.info(String.format("Path specified on table does not exist: %s", KEY));
                    throw new SkipException(String.format("Path specified on table does not exist: %s", KEY));
                }
            });
        }

    }




}
