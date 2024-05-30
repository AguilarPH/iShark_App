package pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage{

    public HomePage(AndroidDriver driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver), driver);
    }
    @AndroidFindBy(xpath = "//android.widget.FrameLayout[@content-desc='Home']")
    private WebElement homeTab;

    @AndroidFindBy(xpath = "//android.widget.FrameLayout[@content-desc='Messages']")
    private WebElement messagesTab;

    @AndroidFindBy(xpath = "//android.widget.FrameLayout[@content-desc='Favorites']")
    private WebElement favoritesTab;

    @AndroidFindBy(xpath = "//android.widget.FrameLayout[@content-desc='Menu']")
    private WebElement menuTab;

}
