package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By emailID = By.xpath("(//input[contains(@class,'ant-input ant-input-lg')])[1]");
    private By password = By.xpath("(//input[contains(@class,'ant-input ant-input-lg')])[2]");
    private By login = By.xpath("(//button[contains(@class,'ant-btn css-1m045la')])[1]");
    //    private By forgotPassword = By.xpath("//a[contains(@class,'text-primaryColor text-sm')]");
    private By forgotPassword = By.linkText("Forgot Password?");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        // Explicit wait for 10 seconds
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public boolean isForgotPwdLinkExist() {
        // Wait until the forgot password link is visible
        WebElement link = wait.until(ExpectedConditions.visibilityOfElementLocated(forgotPassword));
        return link.isDisplayed();
    }

    public void enterUsername(String username) {
        // Wait until the email field is visible
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(emailID));
        emailField.clear();
        emailField.sendKeys(username);
    }

    public void enterPassword(String pwd) {
        // Wait until the password field is visible
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(password));
        passwordField.clear();
        passwordField.sendKeys(pwd);
    }

    public void clickLoginButton() {
        // Wait until the login button is clickable
        WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(login));
        loginBtn.click();
    }

}
