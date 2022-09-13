package base.factories;

import base.Interface.IDrivers;
import base.Interface.ILogger;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

class BrowserStackManager implements ILogger, IDrivers {

    private static AppiumDriver driver;

    private DesiredCapabilities loadDesiredCap() {
        DesiredCapabilities caps = new DesiredCapabilities();

        // Set your access credentials
        caps.setCapability("browserstack.user", "sudhanshusingh_AwsM8N");
        caps.setCapability("browserstack.key", "bVzsTXy6J8eKCDP1CKqL");

        // Set URL of the application under test
        caps.setCapability("app", "bs://9c93bfa23f8fb86493cf3323ca1985dc7d4384ce");

        // Specify device and os_version for testing
        /*caps.setCapability("device", "Google Pixel 3");
        caps.setCapability("os_version", "9.0");*/

        caps.setCapability("device", "iPhone 13 Pro Max");
        caps.setCapability("os_version", "15");

        // Set other BrowserStack capabilities
        caps.setCapability("project", "DIY Project");
        caps.setCapability("build", "browserstack-build-1");
        caps.setCapability("name", "Testing Iphone 15");
        return caps;
    }

    @Override
    public AppiumDriver<MobileElement> startDriver() {
        try {
            driver = new IOSDriver(
                    new URL("http://hub.browserstack.com/wd/hub"), loadDesiredCap());
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public void createDriver() {}

    @Override
    public void stopDriver() {
        driver.quit();
    }
}