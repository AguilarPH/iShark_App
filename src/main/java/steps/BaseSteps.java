package steps;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.MutableCapabilities;

import java.io.*;
import java.net.*;
import java.lang.*;
import java.net.URL;
import java.time.Duration;

public class BaseSteps {
    private AppiumDriver driver;
    protected AppiumDriverLocalService service;

    public BaseSteps() {
        setSauceLabsCaps();
//        setAndroidOptions();
    }

    public AppiumDriver getDriver() {return driver;}

    private void configureAppium() throws MalformedURLException{

        //Appium Server initialization
        File file = new File("C:\\Users\\paguilar\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js");

        AppiumDriverLocalService service = new AppiumServiceBuilder().withAppiumJS(file)
                .withIPAddress("127.0.0.1").usingPort(4723).build();
        service.start();
    }
    private void setAndroidOptions() {
        String appUrl = System.getProperty("user.dir") + File.separator + "src" +
                File.separator + "main" + File.separator +"resources" + File.separator + "iShark_3.3.apk";

        UiAutomator2Options options = new UiAutomator2Options()
                .setDeviceName("pixel_8")
                .setUdid("emulator-5554")
                .setAvd("Pixel_8_API_VanillaIceCream")
                .setAvdLaunchTimeout(Duration.ofMillis(180000))
                .setAutoGrantPermissions(true)  // This will allow the app to automatically accept the permissions
                .setApp(appUrl);

        URL url = null;
        try {
            url = new URL("http://0.0.0.0:4723");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        driver = new AndroidDriver(url, options);
        System.out.println("session id: " + driver.getSessionId());
    }

    private void setSauceLabsCaps(){

        UiAutomator2Options options = new UiAutomator2Options()
                .setDeviceName("Google Pixel 7 Pro")
                .setPlatformVersion("14")
                .setApp("storage:filename=iShark_3.3.apk");

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
        //caps.setCapability("sauce:options", sauceOptions);

        options.setCapability("sauce:options", sauceOptions);

        URL url = null;
        try {
            url = new URL("https://ondemand.us-west-1.saucelabs.com:443/wd/hub");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        driver = new AndroidDriver(url, caps);
    }

    public void tearDown() {
        driver.quit();
        service.stop();
    }

    public void wait(int time) {driver.manage().timeouts().implicitlyWait(Duration.ofMillis(time));}
}
