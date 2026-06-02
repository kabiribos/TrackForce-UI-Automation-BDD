package hooks;

import factory.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import utilities.ConfigReader;

import java.util.Properties;

public class ApplicationHooks {
    private DriverFactory driverFactory;
    private WebDriver driver;
    private ConfigReader configReader;
    Properties prop;

    @Before(order = 0)
    public void getProperty(){
        configReader=new ConfigReader();
        prop=configReader.initProperties();
    }
    @Before(order = 1)
    public void launchBrowser(){
        String browserName=prop.getProperty("browser");
        driverFactory=new DriverFactory();
        driver=driverFactory.initDriver(browserName);
    }

    @After(order = 0)
    public void quitBrowser(){

        if (driver!=null){
            driver.quit();
        }
    }

    @After(order = 1)
    public void tearDown(Scenario scenario){
        if(driver!=null){
            if (scenario.isFailed()) {
                String screenshotName=scenario.getName().replaceAll(" ","-");
                byte[] sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(sourcePath, "image/png", screenshotName);
            }
        }

    }

}
