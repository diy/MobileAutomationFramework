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

public class SwitchUsers implements ILogger {

    private AppiumDriver driver;
    private CommonPageActions pageActions;

    public SwitchUsers(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, ofSeconds(8)), this);
    }

    @FindBy(xpath = "//XCUIElementTypeStaticText[@name='Add User']")
    private WebElement addNewUser;

    @FindBy(xpath = "//XCUIElementTypeStaticText[@name='Switch User']")
    private WebElement switchUserHeader;

    @FindBy(xpath = "//XCUIElementTypeButton[@name='nav back']")
    private WebElement backButton;

    @FindBy(xpath = "//XCUIElementTypeButton[@name='Sign Out All Users']")
    private WebElement signOutAllUsers;

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

    public void AddNewUser() {
        if (waitForVisibility(switchUserHeader)) {
            addNewUser.click();
        } else {
            log.error("failed to get the switch user page ....");
        }
    }
}
