package base.factories;

import constants.DriverTypes;
import base.Interface.ILogger;
import io.appium.java_client.AppiumDriver;

public class DriverFactory implements ILogger {

    /**
     * Initialise driver of custom type
     * @param driverType e.g IOS, ANDROID etc.
     * @return
     */
    public static AppiumDriver initDriver(DriverTypes driverType){

        AppiumDriver driverManager;

        switch (driverType.name()) {
            case "IOS":
                driverManager = IOSDriverManager.getInstance().startDriver();
                return driverManager;
            case "ANDROID":
                driverManager = AndroidDriverManager.getInstance().startDriver();
                return driverManager;
        }
        return null;
    }
}