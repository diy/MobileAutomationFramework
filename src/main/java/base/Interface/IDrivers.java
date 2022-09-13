package base.Interface;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public interface IDrivers {

    AppiumDriver<MobileElement> startDriver();
    void createDriver();
    void stopDriver();
}
