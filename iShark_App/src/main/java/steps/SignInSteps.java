package steps;

import com.beust.ah.A;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import pages.SignInPage;

public class SignInSteps extends BaseSteps {
    private AndroidDriver driver;
    private SignInPage signInPage;

    public SignInSteps(AndroidDriver driver) {
        this.driver = driver;
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
