package steps;

import io.appium.java_client.AppiumDriver;
import pages.SignInPage;

public class SignInSteps extends BaseSteps {
    private AppiumDriver driver;
    private SignInPage signInPage;

    public SignInSteps() {
        this.driver = getDriver();
        this.signInPage = new SignInPage();
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
