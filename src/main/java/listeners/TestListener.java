package listeners;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import steps.BaseSteps;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class TestListener implements  ITestListener {
    BaseSteps baseSteps = new BaseSteps();

    public void onTestFailure(ITestResult result) {
        if (result.getThrowable() != null) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            result.getThrowable().printStackTrace(pw);
            System.out.println(sw.toString());
        }

        File file = baseSteps.getDriver().getScreenshotAs(OutputType.FILE);

        Map<String, String> params = new HashMap<String, String>();
        params = result.getTestContext().getCurrentXmlTest().getAllParameters();

        String imgPath = "Screenshots" + File.separator + params.get("platformName") + "_" +params.get("platformVersion")
                + "_" + params.get("deviceName") + File.separator + baseSteps.getDateTime() + File.separator +
                result.getTestClass().getRealClass().getSimpleName() + File.separator + result.getName() + ".png";
        String completeImagePath = System.getProperty("user.dir") + File.separator + imgPath;
        try {
            FileUtils.copyFile(file, new File("FailScr.png"));
            Reporter.log("This is the Screenshot of the failed test");
            Reporter.log("<a href='"+ completeImagePath + "'> < img scr='" + completeImagePath +
                    "'height='100' width='100'/> </a>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
