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

    public void sendNSUEmail(String email) {waitToSendKeys(signInPage.nsuEmailText(), email);}
    public void clickNSUNextButton() {signInPage.nsuNextButton().click();}
    public void sendNSUPassword(String password) {waitToSendKeys(signInPage.nsuPasswordText(), password);}
    public void clickNSUSignIn() {signInPage.nsuSignInButton().click();}
    public void waitForInitialPage() {waitToBeVisible(signInPage.signInButton());}
    public void waitForNSUEmailPage() {waitToBeVisible(signInPage.nsuEmailText());}
    public void waitForNSUPasswordPage() {waitToBeVisible(signInPage.nsuPasswordText());}
}
