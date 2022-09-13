package base.factories;

import base.Interface.IDrivers;
import base.Interface.ILogger;
import constants.Common;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import util.ConfigUtil;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

class IOSDriverManager implements ILogger, IDrivers {

        private static IOSDriver<MobileElement> iosDriver;
        private DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        private ConfigUtil configUtil = new ConfigUtil();
        private File file;
        private Properties properties;
        private static volatile IOSDriverManager iosDriverManager;

        private IOSDriverManager(){}

        public DesiredCapabilities loadDesiredCap() {
            desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 13 Pro");
            desiredCapabilities.setCapability(MobileCapabilityType.UDID, "237CB16C-A94E-4730-A4CE-B3A43D8CB0F3" );
            desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
            desiredCapabilities.setCapability("appPackage", Common.APP_PACKAGE);
            desiredCapabilities.setCapability("appActivity", Common.APP_ACTIVITY);
            desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.IOS);
            return desiredCapabilities;
        }

        @Override
        public void createDriver() {
            startDriver();
        }

        @Override
        public IOSDriver<MobileElement> startDriver() {
            if (null == iosDriver){
                try {
                    return iosDriver = new IOSDriver<>(new URL(Common.LOCAL_HOST), loadDesiredCap());
                }catch (Exception e){
                    log.error("failed to load Android driver ....:::" + e.getMessage());
                }
            }
            return null;
        }

        @Override
        public void stopDriver() {
            if (null != iosDriver){
                iosDriver.quit();
            }
        }

        /**
         * Get instance of this class
         * @return 'this'
         */
        public static IOSDriverManager getInstance(){
            if (null == iosDriverManager){
                synchronized (IOSDriverManager.class){
                    iosDriverManager = new IOSDriverManager();
                    return iosDriverManager;
                }
            }else{
                throw new RuntimeException("Use getInstance() method to get the single instance of this class");
            }
        }

    /**
     * Retrieve Performance data for an APP
     * @param sessionId
     * @throws MalformedURLException
     */
    public void getPerformanceData(String sessionId) throws MalformedURLException {
        desiredCapabilities.setCapability("packageName", Common.APP_PACKAGE);
        desiredCapabilities.setCapability("dataType", "json");
        iosDriver = new IOSDriver<>(new URL("/session/:" + sessionId + "/appium/getPerformanceData"), desiredCapabilities);
    }
}
