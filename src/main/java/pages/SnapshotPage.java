package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SnapshotPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By monitoringMenu =
            By.xpath("(//span[contains(@class,'text-sm truncate')])[2]");

    private By snapshotSubMenu =
            By.xpath("(//span[contains(@class,'text-xs font-medium')])[1]");

    private By refreshButton =
            By.xpath("//span[contains(@class,'anticon anticon-reload')]");

    //First card fields (latest snapshot)
    private By employeeName =
            By.xpath("(//h1[contains(@class,'text-sm font-bold')])[1]");

    private By snapshotDate =
            By.xpath("(//span[@class='truncate'])[1]");

    private By snapshotTime =
            By.xpath("(//span[@class='truncate'])[2]");

    public SnapshotPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void goToSnapshotPage() {
        wait.until(ExpectedConditions.elementToBeClickable(monitoringMenu)).click();
        wait.until(ExpectedConditions.elementToBeClickable(snapshotSubMenu)).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(employeeName));
    }

    public void clickRefresh() {
        wait.until(ExpectedConditions.elementToBeClickable(refreshButton)).click();
    }

    //unique snapshot identifier
    public String getLatestSnapshotSignature() {

        String name = wait.until(ExpectedConditions.visibilityOfElementLocated(employeeName)).getText();
        String date = driver.findElement(snapshotDate).getText();
        String time = driver.findElement(snapshotTime).getText();

        return name + "_" + date + "_" + time;
    }
}
