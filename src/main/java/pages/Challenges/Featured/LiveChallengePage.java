package pages.Challenges.Featured;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

public class LiveChallengePage extends BasePage {

    public LiveChallengePage(AppiumDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//XCUIElementTypeButton[@name='Join Now']")
    WebElement joinNow;

    @FindBy(xpath = "//XCUIElementTypeStaticText[@name='Live session starting soon']")
    WebElement liveSession;

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
    public void clickJoinButton() {

        if (waitForVisibility(pageHeader) && joinNow.isEnabled()) {
            joinNow.click();
        } else {
            log.info("challenge yet be started " + driver.findElementByXPath(futureLiveChallenge.getText()).getText());
        }
    }

    /**
     * For canceling the meeting ....
     * Note : this is not used as 'leave meeting' option in zoom sdk
     */
    public void cancelMeeting(){
        if (waitForVisibility(cancelMeeting)){
            cancelMeeting.click();
        }else {
            log.error("failed to cancel meeting");
        }
    }
}
