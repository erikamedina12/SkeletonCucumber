package myProject.web.Pages;

import config.web.WebDriverHelper;
import myProject.web.PageObjects.OrangeHRMLoginPageObjects;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;

import java.util.List;

public class OrangeHRMPage extends WebDriverHelper {

    static OrangeHRMLoginPageObjects orangeHRMLoginPageObjects = new OrangeHRMLoginPageObjects();

    public void login(WebDriver driver, String username, String password) {

        fillUserName(driver, username);
        fillPassword(driver, password);
        clickLoginButton(driver);
    }

    public void loginAdminUser(WebDriver driver) {

        JSONObject adminUser = adminUserCredentials();
        String username = adminUser.get("userName").toString();
        String password = adminUser.get("password").toString();
        fillUserName(driver, username);
        fillPassword(driver, password);
        clickLoginButton(driver);
    }

    public void clickLogout(WebDriver driver, String username, String password) {

        WebElement logoutButtonElem = getElement(driver, orangeHRMLoginPageObjects.logoutButtonLoc);

        if(logoutButtonElem!=null) {
            logoutButtonElem.click();
        } else {
            throw new SkipException("Logout option is not present");
        }

    }

    public void fillUserName(WebDriver driver, String userName) {
        WebElement userNameElem = getElement(driver, orangeHRMLoginPageObjects.userNameLoc);

        if(StringUtils.isEmpty(userName)) {
            throw new SkipException("User name is empty");
        }

        if(userNameElem!=null) {
            userNameElem.sendKeys(userName);
        } else {
            throw new SkipException("userName is not present");
        }
    }

    public void fillPassword(WebDriver driver, String password) {

        WebElement passwordElem = getElement(driver, orangeHRMLoginPageObjects.passwordLoc);

        if(StringUtils.isEmpty(password)) {
            throw new SkipException("Password is empty");
        }

        if(passwordElem!=null) {
            passwordElem.sendKeys(password);
        } else {
            throw new SkipException("Password is not present");
        }
    }

    public void clickLoginButton(WebDriver driver) {

        WebElement loginBtnElem = getElement(driver, orangeHRMLoginPageObjects.loginButtonLoc);

        if(loginBtnElem !=null) {
            loginBtnElem.click();
        } else {
            throw new SkipException("Login button is not present");
        }
    }

    public WebElement getUserProfile(WebDriver driver) {
        return getElement(driver, orangeHRMLoginPageObjects.loginProfilePictureLoc);
    }

    public void clickOnAdminMenu(WebDriver driver) {
        WebElement adminMenuOptionElem = getElement(driver, orangeHRMLoginPageObjects.adminMenuOptionLoc);
        if(adminMenuOptionElem!=null) {
            adminMenuOptionElem.click();
        }
    }

    public List<WebElement> getUsersListFromTable(WebDriver driver) {

        List<WebElement> usersListFromTableElem = getElements(driver, orangeHRMLoginPageObjects.usersListFromTableLoc);

        if(!usersListFromTableElem.isEmpty()) {
            return usersListFromTableElem;
        } else {
            throw new SkipException("Users list is not present");
        }
    }
}
