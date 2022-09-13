package pages.NonLogged;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FreeTrailPayWall {

    private AppiumDriver driver;

    public FreeTrailPayWall(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(8)), this);
    }

    @FindBy(xpath = "//*[@name= 'Start FREE Trial']")
    private WebElement freeTrialButton;

    @FindBy(xpath = "//*[@name='Full access. FREE for 14 days.']")
    private WebElement trialPeriod;

    @FindBy(xpath = "//*[@name= 'Sign In' ]")
    private WebElement signInButton;

    private boolean isEnabled() {
        return trialPeriod.isEnabled();
    }

    private void waitForVisibility(WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(driver, 8);
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    private void clickNfc() {
        freeTrialButton.click();
    }

    private void setSignInButton() {
        signInButton.click();
    }

    public void waitBeforeClickingTrailButton() {
        waitForVisibility(trialPeriod);
        isEnabled();
        setSignInButton();
    }
}

