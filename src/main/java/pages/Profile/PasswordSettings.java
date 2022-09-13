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

public class PasswordSettings implements ILogger {

    private AppiumDriver driver;
    private CommonPageActions pageActions;

    public PasswordSettings(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, ofSeconds(8)), this);
    }

    @FindBy(xpath = "//XCUIElementTypeStaticText[@name='Add User']")
    private WebElement deleteUser;

    @FindBy(xpath = "//XCUIElementTypeStaticText[@name='Switch User']")
    private WebElement switchUserHeader;

    @FindBy(xpath = "(//XCUIElementTypeButton[@name='Change Password']")
    private WebElement changePassword;

    @FindBy(xpath = "//XCUIElementTypeStaticText[@name='Parent Dashboard']")
    private WebElement parentDashBoard;

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
            changePassword.click();
        } else {
            log.error("failed to get the switch user page ....");
        }
    }

    public void parentDashBoardLink() {
        if (waitForVisibility(switchUserHeader)) {
            parentDashBoard.click();
        } else {
            log.error("failed to get the switch user page ....");
        }
    }
}
