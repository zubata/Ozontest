package ozon.core;


import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hooks {

    @Before
    public void startup() {
        Initial.startBrowser();
        Initial.getDriver().get(Initial.getUrl());
    }

    @After
    public void finish(Scenario scenario) {
        if(scenario.isFailed()) takeScreenshot();
        Initial.getDriver().quit();
    }

    @Attachment(type = "image/png", value = "Screenshot")
    public byte[] takeScreenshot() {
        return ((TakesScreenshot) Initial.getDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
