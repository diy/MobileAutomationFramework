package pages.Freemium;

import base.Interface.ILogger;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

public class PaywallScreen extends BasePage implements ILogger {

    public PaywallScreen(AppiumDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//XCUIElementTypeWindow[@name='intercom window']")
    private WebElement intercomWindow;

    @FindBy(xpath = "//XCUIElementTypeStaticText[@name='Get DIY Plus']")
    private WebElement getDiyPlus;

    @FindBy(xpath = "//XCUIElementTypeButton[@name='global close']")
    private WebElement closeButton;

    @FindBy(xpath = "//XCUIElementTypeStaticText[@name='Subscribe to DIY']")
    private WebElement pageHeader;

    @Override
    protected boolean waitForVisibility(WebElement webElement) {
        return super.waitForVisibility(webElement);
    }

    public void verifyScreenAndClose() {

        if (waitForVisibility(pageHeader) && pageHeader.isEnabled() && pageHeader.isDisplayed()) {

            log.info("Pay wall screen displayed");
            //Closing screen
            closeButton.click();
        }
    }
}
