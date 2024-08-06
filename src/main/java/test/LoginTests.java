package test;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import steps.HomeSteps;
import steps.SignInSteps;

import java.io.IOException;
import java.io.InputStream;

public class LoginTests {
    InputStream datais;
    JSONObject loginUsers;

    @BeforeClass
    public void beforeClass() throws IOException {
        try {
            String dataFile = "data/loginUsers.json";
            datais = getClass().getClassLoader().getResourceAsStream(dataFile);
            JSONTokener tokener = new JSONTokener(datais);
            loginUsers = new JSONObject(tokener);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (datais != null) {
                datais.close();
            }
        }
    }

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
