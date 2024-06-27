package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePage extends BasePage{

    public HomePage(AppiumDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@resource-id='com.blackboard.android.central.nova:id/toolbar']" +
            "/descendant::android.widget.TextView")
    private WebElement setStudentStatus;
    public WebElement studentStatus() {return setStudentStatus;}

    @AndroidFindBy(accessibility = "Open user menu")
    private WebElement setUserMenu;
    public WebElement userMenu() {return setUserMenu;}

    @AndroidFindBy(xpath = "//android.widget.Image[@text='Photo']")
    private WebElement setProfilePhoto;
    public WebElement profilePhoto() {return setProfilePhoto;}

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Welcome']/parent::android.view.View" +
            "/following-sibling::*[1]/descendant::android.widget.TextView")
    private WebElement setProfileName;
    public WebElement ProfileName() {return setProfileName;}

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Welcome']/parent::android.view.View" +
            "/following-sibling::*[2]/descendant::android.widget.Button")
    private WebElement setNsuNumber;
    public WebElement NsuNumber() {return setNsuNumber;}

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Student Insights']")
    private WebElement setStudentInsights;
    public WebElement studentInsights() {return setStudentInsights;}

    @AndroidFindBy(xpath = "//android.widget.Button[@text='My Account Balance']")
    private WebElement setAccountBalance;
    public WebElement accountBalance() {return setAccountBalance;}

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='MY DEGREE PROGRESS']/parent::android.view.View" +
            "/following-sibling::*[1]/descendant::android.widget.TabWidget/descendant::*")
    private List<WebElement> setDegreeButtons;
    public List<WebElement> degreeButtons() {return setDegreeButtons;}

}
