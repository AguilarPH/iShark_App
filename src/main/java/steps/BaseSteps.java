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
import org.testng.annotations.Parameters;

import java.io.*;
import java.net.*;
import java.lang.*;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

public class BaseSteps {
    private static AppiumDriver driver = null;
    protected AppiumDriverLocalService service;
    protected Properties props;
    InputStream inputStream;

//    public BaseSteps() {
//        if (driver == null) {
////            try {
////                configureAppium();
////            } catch (MalformedURLException e) {
////                throw new RuntimeException(e);
////            }
////            setAndroidOptions();
//            setSauceLabsCaps("Android", "14.0", "Google Pixel 7 Pro");
//        }
//
//    }

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

    @Parameters({"platformName", "platformVersion", "deviceName"})
    @BeforeSuite
    private void setSauceLabsCaps(String platformName, String platformVersion, String deviceName) {
        props = new Properties();
        String propFileName = "config.properties";

        try {
            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
            props.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        MutableCapabilities caps = new MutableCapabilities();
        caps.setCapability("platformName", platformName);
        caps.setCapability("appium:app", props.getProperty("androidAppSauceLabsPath"));  // The filename of the mobile app
        caps.setCapability("appium:platformVersion", platformVersion);
        caps.setCapability("appium:deviceName", deviceName);
        caps.setCapability("appium:automationName", props.getProperty("androidAutomationName"));
        caps.setCapability("appium:autoGrantPermissions", true);

        MutableCapabilities sauceOptions = new MutableCapabilities();
        sauceOptions.setCapability("appiumVersion", props.getProperty("appiumVersion"));
        sauceOptions.setCapability("username", props.getProperty("saucelabsUsername"));
        sauceOptions.setCapability("accessKey", props.getProperty("saucelabsAccessKey"));
        sauceOptions.setCapability("build", props.getProperty("buildName"));
        sauceOptions.setCapability("name", props.getProperty("testName"));
        caps.setCapability("sauce:options", sauceOptions);

        URL url = null;
        try {
            url = new URL(props.getProperty("saucelabsUrl"));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        try {
            this.driver = new AppiumDriver(url, caps);
            System.out.println("SauceLabs session id: " + driver.getSessionId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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

    protected void click(WebElement webElement) {
        waitToBeVisible(webElement);
        webElement.click();
    }

    protected void sendKeys(WebElement webElement, String text) {
        waitToBeVisible(webElement);
        webElement.sendKeys(text);
    }

    protected void waitToBeVisible(WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

}
