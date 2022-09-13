package pages.Activity;

import base.Interface.ILogger;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class ActivityUserStories implements ILogger {

    private AppiumDriver driver;

    public ActivityUserStories(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(8)), this);
    }

    @FindBy(xpath = "//*/XCUIElementTypeCell[2]/*/*/XCUIElementTypeImage")
    private WebElement firstUserStory;

    @FindBy(xpath = "//*/XCUIElementTypeCell[2]/*/XCUIElementTypeOther[3]/XCUIElementTypeImage")
    private WebElement firstUserStoryPost;

    @FindBy(xpath = "//XCUIElementTypeButton[@name='Follow']")
    private WebElement followUser;

    @FindBy(xpath = "//XCUIElementTypeButton[@name='Following']")
    private WebElement followingUser;

    @FindBy(xpath = "//XCUIElementTypeButton[@name='volume on']")
    private WebElement volumeOn;

    @FindBy(xpath = "//XCUIElementTypeButton[@name='volume off']")
    private WebElement volumeOff;

    @FindBy(xpath = "//XCUIElementTypeButton[@name='global close']")
    private WebElement globalClose;

    @FindBy(xpath = "//XCUIElementTypeButton[@name='post actions heart']")
    private WebElement likeStory;

    @FindBy(xpath = "//XCUIElementTypeButton[@name='post actions comment']")
    private WebElement commentStory;

    @FindBy(xpath = "//XCUIElementTypeButton[@name='global more']")
    private WebElement additionalOptions;

    @FindBy(xpath = "//XCUIElementTypeButton[@name='comments send']")
    private WebElement commentSend;

    @FindBy(xpath = "//XCUIElementTypeImage[@name='global-down']")
    private WebElement closeComment;

    @FindBy(xpath = "//XCUIElementTypeStaticText[@name='Add a comment...']")
    private WebElement addComment;

    private boolean waitForVisibility(WebElement webElement) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, TimeUnit.MILLISECONDS.toMillis(100));
            wait.until(ExpectedConditions.visibilityOf(webElement));
            return true;
        } catch (NoSuchElementException noSuchElementException) {
            System.out.println(noSuchElementException.getLocalizedMessage());
        }
        return false;
    }

    public void verifyUserStoryActions() {
        log.info("Performing free user story actions .......:---:....");

        if (waitForVisibility(firstUserStory)) {firstUserStory.click();}

        //Following or follow user
        if (waitForVisibility(followUser) && followUser.isDisplayed()) {followUser.click();}
        else {followingUser.click();}
        globalClose.click();
    }

    public void volumeActions() {

        if (waitForVisibility(firstUserStory)) {firstUserStory.click();}

        //Volume on and Off
        if (waitForVisibility(volumeOn) || volumeOn.isEnabled()) {volumeOn.click();}
        else {volumeOff.click();}
        globalClose.click();
    }

    public void likePost() {

       // if (waitForVisibility(firstUserStory)) {firstUserStory.click();}

        //like on story
        likeStory.click();
        //globalClose.click();
    }

    public void commentingOnStory(String postType) {

        switch (postType.toUpperCase()) {
            case "STORY":
                if (addComment.isEnabled()){
                    driver.findElementByXPath("//*/XCUIElementTypeOther[1]/XCUIElementTypeTextView").click();
                    System.out.println(driver.getPageSource());
                }
                commentStory.sendKeys("Commenting on user story");
                commentSend.click();
                break;
            case "POST":
                commentStory.click();
                commentStory.sendKeys("Commenting on Post");
                commentSend.click();
                break;
        }

        if (waitForVisibility(firstUserStory)) {firstUserStory.click();}
        globalClose.click();
    }
}
