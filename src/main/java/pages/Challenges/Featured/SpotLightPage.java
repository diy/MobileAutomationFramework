package pages.Challenges.Featured;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

public class SpotLightPage extends BasePage {

    public SpotLightPage(AppiumDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//XCUIElementTypeImage[@name='banner-detail-spotlight']")
    WebElement spotlightBanner;

    @FindBy(xpath = "//XCUIElementTypeButton[@name='Post']")
    WebElement postButton;

    @FindBy(xpath = "//XCUIElementTypeStaticText[@name='Live challenge has started']")
    WebElement futureLiveChallenge;

    @FindBy(xpath = "//XCUIElementTypeStaticText[@name='Challenge']")
    WebElement pageHeader;

    @FindBy(xpath = "//XCUIElementTypeButton[@name='Cancel']")
    WebElement cancelMeeting;

    @FindBy(xpath = "//XCUIElementTypeButton[@name='nav back']")
    WebElement navigateBack;


    @Override
    protected boolean waitForVisibility(WebElement webElement) {
        return super.waitForVisibility(webElement);
    }

    /**
     * Click on Join button based on cases e.g. Join now for active sessions or Time based action
     */
    public void clickSpotLight() {

        if (waitForVisibility(pageHeader)) {

        } else {
            log.info("challenge yet be started " + driver.findElementByXPath(futureLiveChallenge.getText()).getText());
        }
    }

    /**
     * Post any picture via gallery or live
     */
    public void makeAPost() {
        if (waitForVisibility(pageHeader)) {
            postButton.click();
        } else {
            log.info("failed to make a post " + driver.findElementByXPath(futureLiveChallenge.getText()).getText());
        }
    }
}
