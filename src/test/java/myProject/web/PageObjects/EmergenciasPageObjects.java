package myProject.web.PageObjects;

import org.openqa.selenium.By;

public class EmergenciasPageObjects {


    public String GENERIC_TEXT_LOC = "//*[contains(text(), '%s')]";
    public By nameFieldLoc = By.id("txtNameStep1");
    public By provinciaDropDownLoc = By.id("select2-ProvinceStep1-container");
    public By codAreaLog = By.id("PhoneCodeStep1");
    public By phoneFieldLoc = By.id("PhoneNumberStep1");
    public By cotizaButtonLoc = By.id("btnSubmitHealthStep1");
    public By titleFieldLoc = By.xpath("//div[@class=\"title emergencias-subtitle\"]");
}
