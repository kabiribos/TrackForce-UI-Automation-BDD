package stepdefinitions;

import factory.DriverFactory;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.SnapshotPage;

public class SnapshotSteps {

    private WebDriver driver = DriverFactory.getDriver();
    private LoginPage loginPage = new LoginPage(driver);
    private SnapshotPage snapshotPage = new SnapshotPage(driver);

    @When("User login with correct credentials")
    public void userLoginWithCorrectCredentials() {
        loginPage.enterUsername("kabir@ibos.io");
        loginPage.enterPassword("Admin24@7");
        loginPage.clickLoginButton();
    }

    @And("User navigate to snapshot page")
    public void userNavigateToSnapshotPage() {
        snapshotPage.goToSnapshotPage();
    }

    @Then("Latest snapshot should updated continuously within {int}")
    public void latestSnapshotShouldUpdatedContinuouslyWithin(int seconds) {
        String oldSignature = snapshotPage.getLatestSnapshotSignature();
        long endTime = System.currentTimeMillis() + seconds * 1000;

        while (System.currentTimeMillis() < endTime) {

            snapshotPage.clickRefresh();

            try {
                Thread.sleep(2000); // allow UI update
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            String newSignature = snapshotPage.getLatestSnapshotSignature();

            if (!newSignature.equals(oldSignature)) {
                System.out.println("✅ New snapshot detected → " + newSignature);
                return;
            }
        }

        throw new AssertionError("❌ Snapshot did not update within " + seconds + " seconds");
    }
}
