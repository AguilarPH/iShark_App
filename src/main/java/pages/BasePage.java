package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import steps.BaseSteps;

public class BasePage extends BaseSteps {
    protected AppiumDriver driver = getDriver();

}