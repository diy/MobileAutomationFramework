package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.CommonPageActions;

import java.time.Duration;

public class PopUpScreen {

    private AppiumDriver driver;
    private CommonPageActions pageActions;

    public PopUpScreen(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(8)), this);
        pageActions = new CommonPageActions();
    }

    @FindBy(xpath = "//XCUIElementTypeWindow[@name='intercom window']")
    private WebElement intercomWindow;

    @FindBy(xpath = "//XCUIElementTypeButton[@name='intercom close button'])[1]")
    private WebElement closeButton;

    @FindBy(xpath = "//XCUIElementTypeStaticText[@name='See All Challenges'")
    private WebElement seeAllChallenges;

    @FindBy(xpath = "//XCUIElementTypeStaticText[@name='Enable Push Notifications']")
    private WebElement enablePushNotification;

    @FindBy(xpath = "//XCUIElementTypeStaticText[@name='Not now']")
    private WebElement disablePushNotification;


    public void checkScreenAndDisablePushNotification(){

        if (intercomWindow.isEnabled()){
            seeAllChallenges.click();
        }

        if (disablePushNotification.isEnabled())
            disablePushNotification.click();
    }
}
