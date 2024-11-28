package myProject.web.PageObjects;

import org.openqa.selenium.By;

public class OrangeHRMLoginPageObjects {

    public By userNameLoc = By.name("username");
    public By passwordLoc = By.name("password");
    public By loginButtonLoc = By.xpath("//button[@type='submit']");
    public By loginProfilePictureLoc = By.xpath("//img[@alt=\"profile picture\" and @class=\"oxd-userdropdown-img\"]");
    public By logoutButtonLoc = By.xpath("//a[@href=\"/web/index.php/auth/logout\"]");
    public By usersListFromTableLoc = By.xpath("//div[@class=\"oxd-table-card\"]");
    public By adminMenuOptionLoc = By.xpath("//a[@href=\"/web/index.php/admin/viewAdminModule\"]");


}
