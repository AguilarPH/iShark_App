package org.example;

import steps.SignInSteps;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        SignInSteps signInSteps = new SignInSteps();
        signInSteps.wait(6000);

        signInSteps.clickSignIn();
        signInSteps.wait(5000);

        signInSteps.sendNSUEmail("nv486@mynsu.nova.edu");
        signInSteps.clickNSUNextButton();
        signInSteps.wait(5000);

        //WebElement nsuPasswordText = driver.findElement(By.xpath("//android.widget.EditText"));
        //WebElement nsuSignInButton = driver.findElement(By.xpath("//android.widget.Button[@text='Sign in']"));

        signInSteps.sendNSUPassword("NsuFl@ridaQAPWD23");
        signInSteps.clickNSUSignIn();
        signInSteps.wait(5000);

//        WebElement navTabs = driver.findElement(By.xpath("//android.widget.FrameLayout[@resource-id=" +
//                "'com.blackboard.android.central.nova:id/bottom_navigation']" +
//                "/android.widget.FrameLayout/android.view.ViewGroup"));
//
//        List<WebElement> navTabsChildren = driver.findElements(By.xpath("//android.widget.FrameLayout" +
//                "[@resource-id=\"com.blackboard.android.central.nova:id/bottom_navigation\"]" +
//                "/android.widget.FrameLayout/android.view.ViewGroup/*"));

        List<String> expectedTabs = new ArrayList<>();
        expectedTabs.add("Home");
        expectedTabs.add("Messages");
        expectedTabs.add("Favorites");
        expectedTabs.add("Menu");

//        for (int i=0; i < navTabsChildren.size(); i++) {
//            String tabText = navTabsChildren.get(i).findElement(AppiumBy.className("Android.widget.TextView")).getText();
//            //System.out.println(tabText);
//            Assert.assertEquals(tabText, expectedTabs.get(i));
//        }

        signInSteps.tearDown();
    }
}