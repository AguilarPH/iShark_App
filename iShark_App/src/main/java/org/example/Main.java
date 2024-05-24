package org.example;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.asserts.Assertion;
import pages.SignInPage;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws MalformedURLException {
        MutableCapabilities caps = new MutableCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("appium:app", "storage:filename=iShark_3.3.apk");  // The filename of the mobile app
        caps.setCapability("appium:deviceName", "Google Pixel 6 Pro");
        caps.setCapability("appium:platformVersion", "12");
        caps.setCapability("appium:automationName", "UiAutomator2");

        MutableCapabilities sauceOptions = new MutableCapabilities();
        sauceOptions.setCapability("appiumVersion", "latest");
        sauceOptions.setCapability("username", "dadmatinova");
        sauceOptions.setCapability("accessKey", "d64f8e10-d97a-4fe4-91f2-17527e7b6949");
        sauceOptions.setCapability("build", "001");
        sauceOptions.setCapability("name", "Appium-SauceLabs Demo");

        caps.setCapability("sauce:options", sauceOptions);

        URL url = new URL("https://ondemand.us-west-1.saucelabs.com:443/wd/hub");
        AndroidDriver driver = new AndroidDriver(url, caps);

        SignInPage signInPage = new SignInPage(driver);

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(6000));

        signInPage.signInButton().click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));

        //WebElement nsuEmailText = driver.findElement(AppiumBy.className("android.widget.EditText"));
        //WebElement nsuNextButton = driver.findElement(By.xpath("//android.widget.Button[@text='Next']"));

        signInPage.nsuEmailText().sendKeys("nv486@mynsu.nova.edu");
        signInPage.nsuNextButton().click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));

        //WebElement nsuPasswordText = driver.findElement(By.xpath("//android.widget.EditText"));
        //WebElement nsuSignInButton = driver.findElement(By.xpath("//android.widget.Button[@text='Sign in']"));

        signInPage.nsuPasswordText().sendKeys("NsuFl@ridaQAPWD23");
        signInPage.nsuSignInButton().click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));

        WebElement navTabs = driver.findElement(By.xpath("//android.widget.FrameLayout[@resource-id=" +
                "'com.blackboard.android.central.nova:id/bottom_navigation']" +
                "/android.widget.FrameLayout/android.view.ViewGroup"));

        List<WebElement> navTabsChilds = driver.findElements(By.xpath("//android.widget.FrameLayout" +
                "[@resource-id=\"com.blackboard.android.central.nova:id/bottom_navigation\"]" +
                "/android.widget.FrameLayout/android.view.ViewGroup/*"));

        List<String> expectedTabs = new ArrayList<>();
        expectedTabs.add("Home");
        expectedTabs.add("Messages");
        expectedTabs.add("Favorites");
        expectedTabs.add("Menu");

        for (int i=0; i < navTabsChilds.size(); i++) {
            String tabText = navTabsChilds.get(i).findElement(AppiumBy.className("Android.widget.TextView")).getText();
            //System.out.println(tabText);
            Assert.assertEquals(tabText, expectedTabs.get(i));
        }

        driver.quit();
    }
}