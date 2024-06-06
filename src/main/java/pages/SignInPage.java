package pages;

import io.appium.java_client.AppiumDriver;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class SignInPage extends BasePage{

    public SignInPage() {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
    public SignInPage(AppiumDriver driver) {PageFactory.initElements(new AppiumFieldDecorator(driver), this);}

    @AndroidFindBy(xpath = "//android.widget.Image[@text='CURRENT SIGN IN']")
    private WebElement setSignInButton;
    public WebElement signInButton() {return setSignInButton;}

    @AndroidFindBy(xpath = "//android.widget.Image[@text='NSU - Guest']")
    private WebElement setGuestIngress;
    public WebElement guestIngress() {return setGuestIngress;}

    @AndroidFindBy(className = "android.widget.EditText")
    private WebElement setNsuEmailText;
    public WebElement nsuEmailText() {return setNsuEmailText;}

    @AndroidFindBy(xpath = "//android.widget.Button[@text='Next']")
    private WebElement setNsuNextButton;
    public WebElement nsuNextButton() {return setNsuNextButton;}

    @AndroidFindBy(className = "android.widget.EditText")
    private WebElement setNsuPasswordText;
    public WebElement nsuPasswordText() {return setNsuPasswordText;}

    @AndroidFindBy(id = "idSIButton9")
    private WebElement setNsuSignInButton;
    public WebElement nsuSignInButton() {return setNsuSignInButton;}
}
