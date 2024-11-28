package myProject.StepsDefinitions;

import config.web.WebDriverHelper;
import config.web.WebDriverProperties;
import io.cucumber.java.en.*;
import lombok.extern.java.Log;
import myProject.web.Pages.EmergenciasPage;
import myProject.web.Pages.OrangeHRMPage;
import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.SkipException;

import java.util.Arrays;
import java.util.List;

@Log
public class StepDefinitions {

    WebDriver driver = null;

    OrangeHRMPage orangeHRMPage = new OrangeHRMPage();
    WebDriverProperties wdp = new WebDriverProperties();
    EmergenciasPage emergenciasPage = new EmergenciasPage();

    public StepDefinitions() {
        driver = Hooks.driver;
    }

    @Given("^an example scenario$")
    public void anExampleScenario() {
        System.out.println("Example scenario");
        driver.getCurrentUrl();
        driver.getTitle();
    }

    @When("^all step definitions are implemented$")
    public void allStepDefinitionsAreImplemented() {
    }

    @Then("^the scenario passes$")
    public void theScenarioPasses() {
    }

    @Given("^User enters username$")
    public void userEntersUsername() {
        orangeHRMPage.fillUserName(driver,"Admin");
        JSONObject testData = orangeHRMPage.getRawTestData();
    }

    @And("^User enters password$")
    public void userEntersPassword() {
        orangeHRMPage.fillPassword(driver, "admin123");
    }

    @And("^The user clicks on Login button$")
    public void theUserClicksOnLoginButton() {
        orangeHRMPage.clickLoginButton(driver);
    }

    @When("^the user is logged in$")
    public void theUserIsLoggedIn() {
        JSONObject testData = orangeHRMPage.getRawTestData();
        String adminUser = orangeHRMPage.getTestData("admin");

        //From test properties file
        orangeHRMPage.login(driver, wdp.getMAIN_USERNAME(), wdp.getMAIN_PASSWORD());
    }

    @Then("^Verify the user is logged in$")
    public void verifyTheUserIsLoggedIn() {
        //orangeHRMPage.login(driver, "Admin", "admin123");
        WebElement profilePictureElem = orangeHRMPage.getUserProfile(driver);
        Assert.assertNotNull(profilePictureElem, "User is not logged in");

    }

    @And("^the user clicks on Admin$")
    public void theUserClicksOnAdmin() {
        orangeHRMPage.clickOnAdminMenu(driver);
    }


    @Then("^Users are displayed$")
    public void usersAreDisplayed() {
        List<WebElement> usersListFromTableElem = orangeHRMPage.getUsersListFromTable(driver);

//        for (WebElement user : usersListFromTableElem) {
//            System.out.println(user);
//        }
        Assert.assertFalse(usersListFromTableElem.isEmpty(), "Users are not displayed");
    }


    @Then("^User (.*?) exists on System Admin list$")
    public void userExistsOnSystemAdminList(String userName) {
        List<WebElement> usersListFromTableElem = orangeHRMPage.getUsersListFromTable(driver);

        //Good practice
//        if (usersListFromTableElem.isEmpty()) {
//            throw new SkipException("Table has not users");
//        }
//
//        boolean anyMatch = usersListFromTableElem.stream()
//                .anyMatch(row -> {
//                    String element = Arrays.stream(row.getText().split("\n")).findFirst().orElseThrow(() ->
//                            new SkipException("User not found")
//                    );
//                    return element.equals(userName);
//                });
//
//        Assert.assertTrue(anyMatch, "User is not displayed");


        boolean anyMatch;
        if(!usersListFromTableElem.isEmpty()){
            anyMatch = usersListFromTableElem.stream()
                    .anyMatch(row -> {
                        String element = Arrays.stream(row.getText().split("\n")).findFirst().orElseThrow(() ->
                                new SkipException("User not found")
                        );
                        return element.equals(userName);
                    });

        } else {
            throw new SkipException("Table has not users");
        }

        Assert.assertTrue(anyMatch, "User is not displayed");

//Simple solution
//        for (WebElement user : usersListFromTableElem) {
//            if (Arrays.stream(user.getText().split("\n")).findFirst().equals(userName)) {
//                Assert.assertTrue(user.isDisplayed(), "User is not displayed");
//                break;
//            }
//        }


    }

    @When("^the Admin user is Logged in$")
    public void theAdminUserIsLoggedIn() {
        orangeHRMPage.loginAdminUser(driver);
    }

    @Given("^I am waiting for the first page is loaded$")
    public void iAmWaitingForTheFirstPageIsLoaded() {
        emergenciasPage.waitForElementsAreLoaded(driver);
    }

    @Then("^I am filling the following text boxes$")
    public void iAmFillingTheFollowingTextBoxes(List<List<String>> table) {
        emergenciasPage.setFirstStepTextBoxes(driver, table);
    }
}
