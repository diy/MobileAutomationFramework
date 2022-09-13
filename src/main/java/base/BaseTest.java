package base;

import base.Interface.ILogger;
import base.factories.DriverFactory;
import constants.DriverTypes;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class BaseTest implements ILogger {

    AppiumServiceBuilder appiumServiceBuilder;
    Map<String, String> map = new HashMap<>();
    private static AppiumDriverLocalService server;


    public static ThreadLocal<AppiumDriver> driverThreadLocal = new ThreadLocal<>();

    @BeforeClass
    public void initAppium() {
        appiumServiceBuilder = new AppiumServiceBuilder();
        appiumServiceBuilder.usingAnyFreePort();
        appiumServiceBuilder.usingDriverExecutable(new File("/usr/local/bin/node"));
        appiumServiceBuilder.withAppiumJS(new File("/usr/local/bin/appium"));
        map.put("PATH", "/usr/local/bin:" + System.getenv("PATH"));
        appiumServiceBuilder.withEnvironment(map);
        server = AppiumDriverLocalService.buildService(appiumServiceBuilder);

        if (!server.isRunning()) {
            server.start();
        }
    }

    /**
     * Creating shareable driver across multiple classes
     * @return
     */
    protected AppiumDriver initializeDriver() {
        driverThreadLocal.set(DriverFactory.initDriver(DriverTypes.IOS));
        return getDriver();
    }

    public static synchronized AppiumDriver getDriver() {
        return driverThreadLocal.get();
    }

    @AfterClass
    public static void stopAppiumServer() {
        server.stop();
    }
}
