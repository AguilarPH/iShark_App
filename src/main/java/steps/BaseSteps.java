package steps;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

import java.io.*;
import java.net.*;
import java.lang.*;
import java.net.URL;
import java.time.Duration;

public class BaseSteps {
    private static AppiumDriver driver = null;
    protected AppiumDriverLocalService service;

    public BaseSteps() {
        if (driver == null) {
//            try {
//                configureAppium();
//            } catch (MalformedURLException e) {
//                throw new RuntimeException(e);
//            }
//            setAndroidOptions();
            setSauceLabsCaps();
        }

    }

    public AppiumDriver getDriver() {return driver;}

    private void configureAppium() throws MalformedURLException{
        // Specify the path to your Node.js executable
        String nodeJsPath = "C:\\Program Files\\nodejs\\node.exe";

        // Specify the path to your Appium.js file
        String appiumJsPath = "C:\\Users\\tanos\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js";

        // Create a service builder
        AppiumServiceBuilder builder = new AppiumServiceBuilder()
                .usingDriverExecutable(new File(nodeJsPath))
                .withAppiumJS(new File(appiumJsPath))
                .withIPAddress("127.0.0.1")
                .usingPort(4723);

        // Build and start the Appium server
        service = AppiumDriverLocalService.buildService(builder);
        service.start();

        if (service == null || !service.isRunning()) {
            throw new RuntimeException("An error occurred while starting the Appium server");
        }

        System.out.println("Appium server started");
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
        System.out.println("emulator session id: " + driver.getSessionId());
    }

    @BeforeSuite
    private void setSauceLabsCaps(){

        MutableCapabilities caps = new MutableCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("appium:app", "storage:filename=iShark_3.3.apk");  // The filename of the mobile app
        caps.setCapability("appium:deviceName", "Google Pixel 7 Pro");
        caps.setCapability("appium:platformVersion", "14");
        caps.setCapability("appium:automationName", "UiAutomator2");
        caps.setCapability("appium:autoGrantPermissions", true);

        MutableCapabilities sauceOptions = new MutableCapabilities();
        sauceOptions.setCapability("appiumVersion", "latest");
        sauceOptions.setCapability("username", "dadmatinova");
        sauceOptions.setCapability("accessKey", "d64f8e10-d97a-4fe4-91f2-17527e7b6949");
        sauceOptions.setCapability("build", "appium-build-001");
        sauceOptions.setCapability("name", "iShark-Android-Test-001");
        caps.setCapability("sauce:options", sauceOptions);

        URL url = null;
        try {
            url = new URL("https://ondemand.us-west-1.saucelabs.com:443/wd/hub");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        this.driver = new AppiumDriver(url, caps);
        System.out.println("SauceLabs session id: " + driver.getSessionId());
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        driver.quit();
        System.out.println("Appium driver closed");

        if (service != null && service.isRunning()) {
            service.stop();
            System.out.println("Appium server stopped");
        }
    }

    protected void waitToClick(WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(webElement)).click();
    }

    protected void waitToSendKeys(WebElement webElement, String text) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(webElement)).sendKeys(text);
    }

    protected void waitToBeVisible(WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

}
