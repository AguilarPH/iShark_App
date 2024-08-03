package steps;

import io.appium.java_client.AppiumDriver;
import pages.SignInPage;

public class SignInSteps extends BaseSteps {
    private AppiumDriver driver;
    private SignInPage signInPage;

    public SignInSteps() {
        this.driver = getDriver();
        this.signInPage = new SignInPage(this.driver);
    }

    public void clickSignIn() {
        click(signInPage.signInButton());
    }
    public void sendNSUEmail(String email) {sendKeys(signInPage.nsuEmailText(), email);}
    public void clickNSUNextButton() {click(signInPage.nsuNextButton());}
    public void sendNSUPassword(String password) {sendKeys(signInPage.nsuPasswordField(), password);}
    public void clickNSUSignIn() {click(signInPage.nsuSignInButton());}
    public void waitForInitialPage() {waitToBeVisible(signInPage.signInButton());}
    public void waitForNSUEmailPage() {waitToBeVisible(signInPage.nsuEmailText());}
    public void waitForNSUPasswordPage() {waitToBeVisible(signInPage.nsuPasswordField());}

    public boolean isNSUEmailValid() {
        return signInPage.nsuPasswordText().isDisplayed();
    }

    public void isNSUEmailInvalid() {
        waitToBeVisible(signInPage.nsuInvalidEmailText());
    }
}
