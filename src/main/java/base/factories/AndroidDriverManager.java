package base.factories;

import base.Interface.IDrivers;
import base.Interface.ILogger;
import util.ConfigUtil;
import constants.Common;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.URL;
import java.util.Properties;

class AndroidDriverManager implements ILogger, IDrivers {

    private static AppiumDriver androidDriver;
    private final DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
    private final ConfigUtil configUtil = new ConfigUtil();
    private static volatile AndroidDriverManager androidDriverManager;

    private AndroidDriverManager(){}

    private DesiredCapabilities loadDesiredCap() {
        Properties properties = configUtil.getAllProperties(Common.CONFIG_PATH);
        File loadfile = new File("src");
        File file = new File(loadfile, Common.DIY_APK);
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, properties.getProperty(Common.ANDROID_DEVICE_NAME));
        desiredCapabilities.setCapability(MobileCapabilityType.APP, file.getAbsolutePath());
        desiredCapabilities.setCapability(MobileCapabilityType.UDID, "2ac92ed8");
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, Common.UIAUTOMATOR);
        desiredCapabilities.setCapability("APP_PACKAGE", Common.APP_PACKAGE);
        desiredCapabilities.setCapability("APP_ACTIVITY", Common.APP_ACTIVITY);
        desiredCapabilities.setCapability("app", "bs://9c93bfa23f8fb86493cf3323ca1985dc7d4384ce");
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "ANDROID");
        return desiredCapabilities;
    }

    @Override
    public void createDriver() {
        startDriver();
    }

    @Override
    public AppiumDriver startDriver() {
        if (null == androidDriver){
            try {
                return androidDriver = new AndroidDriver<>(new URL(Common.LOCAL_HOST), loadDesiredCap());
            }catch (Exception e){
                log.error("failed to load Android driver ....:::" + e.getMessage());
            }
        }
        return null;
    }

    @Override
    public void stopDriver() {
        if (null != androidDriver){
            androidDriver.quit();
        }
    }

    /**
     * Get instance of this class
     * @return 'this'
     */
    public static AndroidDriverManager getInstance(){
        if (null == androidDriverManager){
            synchronized (AndroidDriverManager.class){
                androidDriverManager = new AndroidDriverManager();
                return androidDriverManager;
            }
        }else{
            throw new RuntimeException("Use getInstance() method to get the single instance of this class");
        }
    }
}
