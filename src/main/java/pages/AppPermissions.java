package pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import util.CommonPageActions;

public class AppPermissions extends BasePage {

    private CommonPageActions commonPageActions;

   public AppPermissions(AppiumDriver driver){
       super(driver);
       commonPageActions = new CommonPageActions();
    }

    @FindBy(xpath = "//XCUIElementTypeButton[@name='Settings']")
    WebElement settingsPageHeader;

    @FindBy(xpath = "(//XCUIElementTypeSwitch[@name='Camera'])[1]")
    WebElement cameraAccess;

    @FindBy(xpath = "//XCUIElementTypeButton[@name='SIRI_AND_SEARCH']")
    WebElement siriSearch;

    @FindBy(xpath = "//XCUIElementTypeCell[@name='Notifications, Off']")
    WebElement notificationOff;

    @FindBy(xpath = "(//XCUIElementTypeSwitch[@name='Background App Refresh'])[1]")
    WebElement backgroundAppRefresh;

    @FindBy(xpath = "(//XCUIElementTypeSwitch[@name='Allow Tracking'])[1]")
    WebElement allowTracking;

    @FindBy(xpath = "//XCUIElementTypeStaticText[@name='“WIP” Would Like to Access the Microphone']")
    WebElement microPhoneAccessHeader;

    @FindBy(xpath = "//XCUIElementTypeButton[@name='OK']")
    WebElement microPhoneAccessAllow;

    @FindBy(xpath = "//XCUIElementTypeButton[@name='Don’t Allow']")
    WebElement microPhoneDeny;

    @FindBy(xpath = "//XCUIElementTypeButton[@name='global close']")
    WebElement closeLockedOutPopUp;

    @FindBy(xpath = "//XCUIElementTypeStaticText[@name='We're Locked Out!']")
    WebElement lockedOutPopUpHeader;

    @FindBy(xpath = "//XCUIElementTypeButton[@name='Go To Settings']")
    WebElement goToSettings;

    /**
     * Navigate to DIY APP from Phone settings Page via touch Actions
     */
    public void navigateToDiyAPP(){
        commonPageActions.TouchActions(driver, 10, 30, 1);
    }

}
