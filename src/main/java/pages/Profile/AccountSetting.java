package pages.Profile;

import base.Interface.ILogger;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.CommonPageActions;

import static java.time.Duration.ofSeconds;

public class AccountSetting implements ILogger {


    private AppiumDriver driver;
    private CommonPageActions pageActions;

    public AccountSetting(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, ofSeconds(8)), this);
        pageActions = new CommonPageActions();
    }

    @FindBy(xpath = "//XCUIElementTypeStaticText[@name='Account']")
    private WebElement changeAvatar;

    @FindBy(xpath = "(//XCUIElementTypeButton[@name='Change Password']")
    private WebElement changePassword;

    @FindBy(xpath = "//XCUIElementTypeStaticText[@name='Parent Dashboard']")
    private WebElement parentDashBoard;

    @FindBy(xpath = "(//XCUIElementTypeButton[@name='Community Guidelines']")
    private WebElement communityGuidelines;

    @FindBy(xpath = "(//XCUIElementTypeButton[@name='Privacy Policy']")
    private WebElement privacyPolicy;

    @FindBy(xpath = "(//XCUIElementTypeButton[@name='Switch User']")
    private WebElement switchUsers;

    @FindBy(xpath = "(//XCUIElementTypeButton[@name='Rate Us ★★★★★']")
    private WebElement rateUs;

    @FindBy(xpath = "//XCUIElementTypeButton[@name='Sign Out All Users']")
    private WebElement signOutAllUsers;

    @FindBy(xpath = "//XCUIElementTypeStaticText[@name='Settings']")
    private WebElement pageHeader;

    @FindBy(xpath = "//XCUIElementTypeButton[@name='nav back']")
    private WebElement backButton;

    private boolean waitForVisibility(WebElement webElement) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 8);
            wait.until(ExpectedConditions.visibilityOf(webElement));
            return true;
        } catch (NoSuchElementException noSuchElementException) {
            log.info(noSuchElementException.getLocalizedMessage());
        }
        return false;
    }

    /**
     * SignOut all user from account settings
     */
    public void signOutAllUsers() {
        if (waitForVisibility(pageHeader)) {
            signOutAllUsers.click();
        } else {
            log.error("failed to get the Accounts page ....");
        }
    }
}
