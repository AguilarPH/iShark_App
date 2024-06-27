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

    //    @AndroidFindBy(className = "android.widget.EditText")
    @AndroidFindBy(xpath = "//input[@id='i0116']")
    private WebElement setNsuEmailText;
    public WebElement nsuEmailText() {return setNsuEmailText;}

    @AndroidFindBy(xpath = "//android.widget.Button[@text='Next']")
//    @AndroidFindBy(xpath = "//input[@id='idSIButton9']")
    private WebElement setNsuNextButton;
    public WebElement nsuNextButton() {return setNsuNextButton;}

    /*@AndroidFindBy(xpath = "//android.widget.TextView[@text='Enter password']/parent::android.view.View" +
            "/following-sibling::*[1]/descendant::android.widget.EditText")*/
    @AndroidFindBy(xpath = "//input[@id='i0118']")
    private WebElement setNsuPasswordText;
    public WebElement nsuPasswordText() {return setNsuPasswordText;}

    @AndroidFindBy(xpath = "//android.widget.Button[@text='Sign in']")
    private WebElement setNsuSignInButton;
    public WebElement nsuSignInButton() {return setNsuSignInButton;}
}
