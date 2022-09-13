package pages.NonLogged;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FreeTrailOption {

    private AppiumDriver driver;

    public FreeTrailOption(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(8)), this);
    }

    @FindBy(xpath = "//*[@name= 'Start FREE Trial']")
    private WebElement freeTrialButton;

    @FindBy(xpath = "//*[@name='Get full access. FREE for 14 days.']")
    private WebElement trialPeriod;

    private boolean isEnabled() {
        return trialPeriod.isEnabled();
    }

    private void waitForVisibility(WebElement nfcElement) {
        WebDriverWait wait = new WebDriverWait(driver, 8);
        wait.until(ExpectedConditions.visibilityOf(nfcElement));
    }

    private void clickNfc() {
        freeTrialButton.click();
    }

    public void waitBeforeClickingTrailButton() {
        waitForVisibility(trialPeriod);
        isEnabled();
        clickNfc();
    }
}
