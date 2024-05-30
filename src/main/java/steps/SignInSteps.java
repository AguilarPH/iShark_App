package steps;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import pages.SignInPage;

public class SignInSteps extends BaseSteps {
    private AppiumDriver driver;
    private SignInPage signInPage;

    public SignInSteps() {
        this.driver = getDriver();
        this.signInPage = new SignInPage();
        PageFactory.initElements(new AppiumFieldDecorator(driver), signInPage);
    }

    public void clickSignIn() {
        signInPage.signInButton().click();
    }

    public void sendNSUEmail(String email) {
        signInPage.nsuEmailText().sendKeys(email);
    }
    public void clickNSUNextButton() {signInPage.nsuNextButton().click();}
    public void sendNSUPassword(String password) {signInPage.nsuPasswordText().sendKeys(password);}
    public void clickNSUSignIn() {signInPage.nsuSignInButton().click();}
}
