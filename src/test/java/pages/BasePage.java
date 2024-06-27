package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
    protected AppiumDriver driver = null;

    public BasePage(AppiumDriver driver) {
        if (this.driver == null) {
            this.driver = driver;
            PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
        }
    }

    @AndroidFindBy(xpath = "//android.widget.FrameLayout[@content-desc='Home']")
    private WebElement setHomeTab;
    public WebElement homeTab() {return setHomeTab;}

    @AndroidFindBy(xpath = "//android.widget.FrameLayout[@content-desc='Messages']")
    private WebElement setMessagesTab;
    public WebElement messagesTab() {return setMessagesTab;}

    @AndroidFindBy(xpath = "//android.widget.FrameLayout[@content-desc='Favorites']")
    private WebElement setFavoritesTab;
    public WebElement favoritesTab() {return setFavoritesTab;}

    @AndroidFindBy(xpath = "//android.widget.FrameLayout[@content-desc='Menu']")
    private WebElement setMenuTab;
    public WebElement menuTab() {return setMenuTab;}

}