package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import steps.HomeSteps;
import steps.SignInSteps;

public class LoginTests {

    @Test
    public void testLogin() {
        SignInSteps signInSteps = new SignInSteps();
        HomeSteps homeSteps = new HomeSteps();

        signInSteps.waitForInitialPage();
        signInSteps.clickSignIn();

        signInSteps.sendNSUEmail("nv486@mynsu.nova.edu");
        signInSteps.clickNSUNextButton();
        signInSteps.waitForNSUPasswordPage();

        Assert.assertTrue(signInSteps.isNSUEmailValid());

        signInSteps.sendNSUPassword("NsuFl@ridaQAPWD23");
        signInSteps.clickNSUSignIn();
        homeSteps.waitForHomePage();

    }
}
