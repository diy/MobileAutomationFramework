package util;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import pages.Freemium.PaywallScreen;

import java.util.HashMap;
import java.util.Map;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofMillis;
import static java.time.Duration.ofSeconds;

public class CommonPageActions {

    /**
     * Touch Actions Func
     * @param driver
     * @param x  height
     * @param y  width
     * @param timeOut
     */
    public void TouchActions(AppiumDriver driver, int x, int y, int timeOut) {
        new TouchAction(driver)
                .press(point(x, y))
                .waitAction(waitOptions(ofSeconds(timeOut)))
                .release()
                .perform();
    }

    public void tapWithCoordinates(AppiumDriver driver, int x, int y, int timeout){
        new TouchAction(driver).tap(new PointOption().withCoordinates(x, y)).perform();
    }

    /**
     * Touch Actions Func
     * @param driver
     */
    public void singleTapAction(AppiumDriver driver, WebElement element) {
        new TouchActions(driver)
                .singleTap(element)
                .perform();
    }

    /**
     * Touch Actions Func
     * @param driver
     */
    public void singleTapActionWithCoordinates(AppiumDriver driver, WebElement element) {
        new TouchActions(driver)
                .singleTap(element)
                .perform();
    }

    /**
     * Touch Actions Func
     * @param driver
     */
    public void scrollScreen(AppiumDriver driver, int x, int y) {
        new TouchActions(driver)
                .scroll(x ,y)
                .perform();
    }

    /**
     * Touch Actions Func
     * @param driver
     */
    public void scrollScreenJS(AppiumDriver driver, WebElement element , String direction)  {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Map<String, Object> params = new HashMap<>();
        params.put("direction", direction);
        params.put("element", ((RemoteWebElement) element).getId());
        js.executeScript("mobile: swipe", params);
    }



    /**
     * @param locator
     * @return
     */
    public ExpectedCondition<Boolean> elementFoundAndClicked(By locator) {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                WebElement element = driver.findElement(locator);
                element.click();
                return true;
            }
        };
    }


    public static void paywallScreen(AppiumDriver driver) {
        PaywallScreen paywallScreen = new PaywallScreen(driver);
        paywallScreen.verifyScreenAndClose();
    }

    public static void datePickerWheelScrollAction(AppiumDriver driver, String direction, WebElement pickerElement) {
        String offset = "0.15";
        Map<String, Object> params = new HashMap<>();
        params.put("order", direction);
        params.put("offset", offset);
        params.put("elementId", ((RemoteWebElement) pickerElement).getId());
        //driver.executeScript("mobile: scroll", params);
        driver.executeScript("mobile: selectPickerWheelValue", params);
    }


    public void swipeByElement (AppiumDriver driver, WebElement element) {
        int startX = element.getLocation().getX() + (element.getSize().getWidth() / 2);
        int startY = element.getLocation().getY() + (element.getSize().getHeight() / 2);
        new TouchAction(driver)
                .press((PointOption) element)
                .waitAction(waitOptions(ofMillis(1000)))
                .moveTo(point(startX,startY))
                .release().perform();
    }
}
