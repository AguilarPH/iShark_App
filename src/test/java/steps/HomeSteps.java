package steps;

import io.appium.java_client.AppiumDriver;
import pages.HomePage;

public class HomeSteps extends BaseSteps{
    private AppiumDriver driver;
    private HomePage homePage;

    public HomeSteps() {
        this.driver = getDriver();
        this.homePage = new HomePage(this.driver);
    }

    public void waitForHomePage() {waitToBeVisible(homePage.ProfileName());}
}
