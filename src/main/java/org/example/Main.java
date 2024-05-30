package org.example;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import steps.BaseSteps;
import pages.SignInPage;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        BaseSteps baseSteps = new BaseSteps();
        AppiumDriver driver = baseSteps.getDriver();

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

        List<WebElement> navTabsChildren = driver.findElements(By.xpath("//android.widget.FrameLayout" +
                "[@resource-id=\"com.blackboard.android.central.nova:id/bottom_navigation\"]" +
                "/android.widget.FrameLayout/android.view.ViewGroup/*"));

        List<String> expectedTabs = new ArrayList<>();
        expectedTabs.add("Home");
        expectedTabs.add("Messages");
        expectedTabs.add("Favorites");
        expectedTabs.add("Menu");

        for (int i=0; i < navTabsChildren.size(); i++) {
            String tabText = navTabsChildren.get(i).findElement(AppiumBy.className("Android.widget.TextView")).getText();
            //System.out.println(tabText);
            Assert.assertEquals(tabText, expectedTabs.get(i));
        }

        driver.quit();
    }
}