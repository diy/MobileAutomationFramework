package pages.Profile;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.CommonPageActions;

import static java.time.Duration.ofSeconds;

public class ProfilePage {

    private AppiumDriver driver;
    private CommonPageActions pageActions;

    public ProfilePage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, ofSeconds(8)), this);
        pageActions = new CommonPageActions();
    }

    @FindBy(xpath = "((//XCUIElementTypeStaticText[@name='Profile'])[4]")
    private WebElement profileButton;

    @FindBy(xpath = "//XCUIElementTypeStaticText[@name='diya05']")
    private WebElement userName;

    @FindBy(xpath = "//XCUIElementTypeSearchField[@name='Search Nicknames or Hashtags']")
    private WebElement searchBar;

    @FindBy(xpath = "(//XCUIElementTypeImage[@name='profile-mode-gems'])[1]")
    private WebElement gems;

    @FindBy(xpath = "(//XCUIElementTypeStaticText[@name='Button'])[1]")
    private WebElement accountSettings;

    @FindBy(xpath = "(//XCUIElementTypeStaticText[@name='Button'])[2]")
    private WebElement postButton;

    @FindBy(xpath = "(//XCUIElementTypeStaticText[@name='profile-mode-gems'])[2]")
    private WebElement modGems;

    @FindBy(xpath = "(//XCUIElementTypeStaticText[@name='profile-mode-posts'])[2]")
    private WebElement modPosts;

    @FindBy(xpath = "(//XCUIElementTypeStaticText[@name='profile-mode-followers'])[2]")
    private WebElement modFollowers;

    @FindBy(xpath = "(//XCUIElementTypeStaticText[@name='profile-mode-likes'])[2]")
    private WebElement modLikes;

    @FindBy(xpath = "(//XCUIElementTypeStaticText[@name='profile-mode-achievements'])[2]")
    private WebElement modAchievements;

    @FindBy(xpath = "//XCUIElementTypeButton[@name='Hashtags']")
    private WebElement hashTags;

    @FindBy(xpath = "//XCUIElementTypeButton[@name='Clear text']")
    private WebElement clearText;

    private boolean isEnabled() {
        return userName.isEnabled();
    }

    public void selectProfilePage(){
        profileButton.click();
    }


    private void waitForVisibility(WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(driver, 8);
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    public void waitBeforeClicking(WebElement webElement) {
        waitForVisibility(webElement);
    }

    /** Search Nick Name and Click Follow the first Element
     * @param nickname
     */
    public void searchNickNamesAndSignOut(String nickname) {
        //validateAndClickProfilePage();
        searchNickNamesAndFollowFirstResult(nickname);
        hasTagActions();
        clearTextAndCancel();

    }


    private void hasTagActions(){
        //select hastags
        hashTags.click();
        waitForVisibility(hashTags);
        //select first tag
        pageActions.TouchActions(driver, 38, 247, 3);
        //click on first tag
        pageActions.TouchActions(driver, 15,118, 2);
        //Move back from tag view
        pageActions.TouchActions(driver, 8,47, 2);
    }


    private void searchNickNamesAndFollowFirstResult(String nickname){
        searchBar.click();
        searchBar.sendKeys(nickname);
        pageActions.TouchActions(driver, 291, 259, 4);
    }

    private void clearTextAndCancel(){
        waitForVisibility(clearText);
        clearText.click();
        pageActions.TouchActions(driver, 329,59, 2);
    }
}
