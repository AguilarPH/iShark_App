package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class SignInPage extends BasePage{

    public SignInPage(AppiumDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(xpath ="//android.widget.Image[@text='NSU Student, Faculty, Staff sign in']")
    private WebElement setSignInButton;
    public WebElement signInButton() {return setSignInButton;}

    @AndroidFindBy(xpath = "//android.widget.Image[@text='NSU - Guest']")
    private WebElement setGuestIngress;
    public WebElement guestIngress() {return setGuestIngress;}

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Sign in']")
    private WebElement setNsuSignInText;
    public WebElement nsuSignInText() {return setNsuSignInText;}

    @AndroidFindBy(className = "android.widget.EditText")
    private WebElement setNsuEmailText;
    public WebElement nsuEmailText() {return setNsuEmailText;}

    @AndroidFindBy(xpath = "//android.widget.Button[@text='Next']")
    private WebElement setNsuNextButton;
    public WebElement nsuNextButton() {return setNsuNextButton;}

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"We couldn't find an account with that username.\"]")
    private WebElement setNsuInvalidEmailText;
    public WebElement nsuInvalidEmailText() {return setNsuInvalidEmailText;}

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Enter password']")
    private WebElement setNsuPasswordText;
    public WebElement nsuPasswordText() {return setNsuPasswordText;}

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Enter password']/parent::android.view.View" +
            "/following-sibling::*[1]/descendant::android.widget.EditText")
    private WebElement setNsuPasswordField;
    public WebElement nsuPasswordField() {return setNsuPasswordField;}

    @AndroidFindBy(xpath = "//android.widget.Button[@text='Sign in']")
    private WebElement setNsuSignInButton;
    public WebElement nsuSignInButton() {return setNsuSignInButton;}

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Your account or password is incorrect. If you can't remember your password\"]")
    private WebElement setNsuInvalidPasswordText;
    public WebElement nsuInvalidPasswordText() {return setNsuInvalidPasswordText;}
}
