package pages.Challenges.Featured;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

public class SkillSearchResult extends BasePage {

    SkillSearchResult(AppiumDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//XCUIElementTypeStaticText[@name='SKILLS']")
    WebElement skillsSearchPageHeader;

    @FindBy(xpath = "(//XCUIElementTypeStaticText[@name='Astronomy'])[1]")
    WebElement astronomySkill;

    @FindBy(xpath = "(//XCUIElementTypeButton[@name='Cancel']")
    WebElement cancelSkillSearch;

    @FindBy(xpath = "//XCUIElementTypeButton[@name='Clear text']")
    WebElement clearSearchText;

    @FindBy(xpath = " //XCUIElementTypeButton[@name='nav back']")
    WebElement navBack;




    @Override
    protected boolean waitForVisibility(WebElement webElement) {
        return super.waitForVisibility(webElement);
    }

    /**
     * Select Challenge Type i.e Live , SpotLight, Contest etc.
     */
    public void verifyChallengeSearchResult() {
        if(waitForVisibility(skillsSearchPageHeader)){
            log.info("Search results found !!!");
        }
    }
}
