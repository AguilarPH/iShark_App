package steps;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.MutableCapabilities;
import pages.BasePage;

import java.io.*;
import java.net.*;
import java.lang.*;
import java.net.URL;

public class BaseSteps {
    protected static AndroidDriver driver;
    protected AppiumDriverLocalService service;

    public void BaseSteps() {
        BaseSteps.driver = setSauceLabsCaps();
    }

    private void configureAppium(URL url) throws MalformedURLException{

        //Appium Server initialization
        File file = new File("C:\\Users\\paguilar\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js");

        AppiumDriverLocalService service = new AppiumServiceBuilder().withAppiumJS(file)
                .withIPAddress("127.0.0.1").usingPort(4723).build();
        service.start();

        //Android driver setup and initialization
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName(""); // Add Device Name
        options.setApp("C:\\Users\\paguilar\\DownloadsiShark_3.3.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1;4723"), options);
    }

    private AndroidDriver setSauceLabsCaps(){

        MutableCapabilities caps = new MutableCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("appium:app", "storage:filename=iShark_3.3.apk");  // The filename of the mobile app
        caps.setCapability("appium:deviceName", "Google Pixel 7 Pro");
        caps.setCapability("appium:platformVersion", "14");
        caps.setCapability("appium:automationName", "UiAutomator2");
        MutableCapabilities sauceOptions = new MutableCapabilities();
        sauceOptions.setCapability("username", "dadmatinova");
        sauceOptions.setCapability("accessKey", "d64f8e10-d97a-4fe4-91f2-17527e7b6949");
        sauceOptions.setCapability("build", "001");
        sauceOptions.setCapability("name", "Appium-SauceLabs Demo");
        caps.setCapability("sauce:options", sauceOptions);

        URL url = null;
        try {
            url = new URL("https://ondemand.us-west-1.saucelabs.com:443/wd/hub");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return new AndroidDriver(url, caps);
    }

    public void tearDown() {
        driver.quit();
        service.stop();
    }
}
