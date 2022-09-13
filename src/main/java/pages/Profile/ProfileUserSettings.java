package pages.Profile;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfileUserSettings {

    private AppiumDriver driver;

    public ProfileUserSettings(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(8)), this);
    }

    @FindBy(xpath = "//XCUIElementTypeStaticText[@name='View Course >']")
    private WebElement viewCourses;

    @FindBy(xpath = "//*[@name= 'Sign In' ]")
    private WebElement signInButton;

    private boolean isEnabled() {
        return viewCourses.isEnabled();
    }

    private void waitForVisibility(WebElement nfcElement) {
        WebDriverWait wait = new WebDriverWait(driver, 10000);
        wait.until(ExpectedConditions.visibilityOf(nfcElement));
    }

    private void clickNfc() {
        viewCourses.click();
    }

    public void waitBeforeClickingTrailButton() {
        waitForVisibility(viewCourses);
        isEnabled();
        clickNfc();
    }
}
