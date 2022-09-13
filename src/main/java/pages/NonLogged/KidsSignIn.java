package pages.NonLogged;

import base.Interface.ILogger;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

public class KidsSignIn implements ILogger {

    private AppiumDriver driver;

    public KidsSignIn(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(8)), this);

    }

    @FindBy(xpath = "//XCUIElementTypeTextField[@value= 'Nickname']")
    private WebElement nickName;

    @FindBy(xpath = "//XCUIElementTypeSecureTextField[@value= 'Password' ]")
    private WebElement password;

    @FindBy(xpath = "//XCUIElementTypeStaticText[@name= 'Kid Sign In' ]")
    private WebElement pageHeader;

    @FindBy(xpath = "//*[@name= 'Sign In']")
    private MobileElement SignIn;

    private boolean isEnabled() {
        return pageHeader.isEnabled();
    }

    private void waitForVisibility(WebElement pageHeader) {
        FluentWait wait = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(8))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.visibilityOf(pageHeader));
    }

    private void userName(String username) {
        nickName.sendKeys(username);
    }

    private void password(String pass) {
        password.sendKeys(pass);
    }

    private void setSignIn() {
        SignIn.click();
    }

    private void waitBeforeClickingTrailButton(WebElement webElement) {
        waitForVisibility(webElement);
        isEnabled();
    }

    public void enterDetails(String name, String password) {
        waitBeforeClickingTrailButton(pageHeader);
        userName(name);
        password(password);
        setSignIn();
    }
}
