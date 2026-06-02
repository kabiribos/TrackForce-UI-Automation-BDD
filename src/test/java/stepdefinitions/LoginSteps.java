package stepdefinitions;

import factory.DriverFactory;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;

import java.time.Duration;

public class LoginSteps {

    private static String title;
    private LoginPage loginPage;
    private WebDriver driver;
    private WebDriverWait wait;

    @Given("User is on login page")
    public void userIsOnLoginPage() {
        driver = DriverFactory.getDriver();
        loginPage = new LoginPage(driver);
        driver.get("http://app.trackforce.io");

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @When("User gets the title of the page")
    public void userGetsTheTitleOfThePage() {
        title = loginPage.getPageTitle();
        System.out.println("The title of the page is: "+title);
    }

    @Then("Page title should be {string}")
    public void pageTitleShouldBe(String expectedTitle) {
        Assert.assertTrue(title.contains(expectedTitle));
    }

    @Then("Forgot password link should be displayed")
    public void forgotPasswordLinkShouldBeDisplayed() {
        Assert.assertTrue(loginPage.isForgotPwdLinkExist());
    }

    @When("User enters username {string}")
    public void userEntersUsername(String username) {
        loginPage.enterUsername(username);

    }

    @And("User enters password {string}")
    public void userEntersPassword(String password) {
        loginPage.enterPassword(password);
    }

    @And("User clicks on login button")
    public void userClicksOnLoginButton() {
        loginPage.clickLoginButton();
    }

    @And("The page title should be {string}")
    public void thePageTitleShouldBe(String expectedTitle) {
        Assert.assertTrue(title.contains(expectedTitle));
    }
}
