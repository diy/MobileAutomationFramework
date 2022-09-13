package pages.Challenges.Featured;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

public class FeaturedTab extends BasePage {

    public FeaturedTab(AppiumDriver driver){
        super(driver);
    }

    private AppiumDriver driver;


    @FindBy(xpath = "//XCUIElementTypeImage[@name='banner-live'])[1]")
    WebElement pageHeader;

    @FindBy(xpath = "//XCUIElementTypeStaticText[@name='Live Workshops']")
    WebElement liveChallenge;

    @FindBy(xpath = "//XCUIElementTypeStaticText[@name='Contests']")
    WebElement contest;

    @FindBy(xpath = "//XCUIElementTypeImage[@name='banner-spotlight'])[1]")
    WebElement spotLight;

    @FindBy(xpath = "//XCUIElementTypeSearchField[@name='Search Challenges or Skills']")
    WebElement searchChallenges;

    @FindBy(xpath = "//XCUIElementTypeStaticText[@name='All']")
    WebElement allTab;

    @FindBy(xpath = "//XCUIElementTypeStaticText[@name='We couldn’t find any Challenge or Skill with that name.']")
    WebElement noChallengeInSearch;

    @Override
    protected boolean waitForVisibility(WebElement webElement) {
        return super.waitForVisibility(webElement);
    }

    /**
     * Select Challenge Type i.e Live , SpotLight, Contest etc.
     */
    public void clickChallengeType(String challengeType) {

        if (waitForVisibility(searchChallenges)) {
            if (challengeType.equalsIgnoreCase("live")) {
                liveChallenge.click();
            } else if (challengeType.equalsIgnoreCase("contest")) {
                contest.click();
            } else if (challengeType.equalsIgnoreCase("spotlight")) {
                spotLight.click();
            } else {
                log.info("Incorrect challenge type selected or challenge not supported currently");
            }
        } else {
            log.error("Failed to get Challenge Page !!!");
        }
    }

    /**
     * search any challenge
     * @param challengeName
     */
    public void searchAnyChallenge(String challengeName) {
        if (waitForVisibility(searchChallenges)) {
            searchChallenges.click();
            searchChallenges.sendKeys(challengeName);
        }
    }
}
