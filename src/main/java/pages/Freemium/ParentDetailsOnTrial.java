package pages.Freemium;

import base.Interface.ILogger;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import util.CommonPageActions;

import java.time.Duration;

public class ParentDetailsOnTrial implements ILogger {

    private AppiumDriver driver;
    private CommonPageActions pageActions;
    private static final String headertext = "Get FREE Access For 7 Days";
    private static final String whybottomsheettext = "Parent email id is used to manage DIY subscription via parent and to track your child's progress on DIY.";

    public ParentDetailsOnTrial(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(8)), this);
        pageActions = new CommonPageActions();
    }

    @FindBy(xpath = "//XCUIElementTypeTextField[@value='First Name']")
    private WebElement parentFirstName;

    @FindBy(xpath = "//XCUIElementTypeTextField[@value='Last Name']")
    private WebElement parentLastName;

    @FindBy(xpath = "//*/XCUIElementTypeOther[2]/XCUIElementTypeTextField")
    private WebElement parentEmailId;

    @FindBy(xpath = "//XCUIElementTypeStaticText[@name='Get FREE Access For 7 Days']")
    private WebElement pageHeader;

    @FindBy(xpath = "//XCUIElementTypeStaticText[@name='Unlock Live Challenges, Contests, and more!']")
    private WebElement pageSubHeader;

    @FindBy(xpath = "//XCUIElementTypeButton[@name='Why?']")
    private WebElement whyBottomSheet;

    @FindBy(xpath = "//XCUIElementTypeStaticText[@name='Parent email id is used to manage DIY subscription via parent and to track your child's progress on DIY.']")
    private WebElement whyBottomSheetText;

    @FindBy(xpath = "//XCUIElementTypeStaticText[@name='Next']")
    private WebElement pageNextButton;

    @FindBy(xpath = "//XCUIElementTypeButton[@name='Ok']")
    private WebElement okButtonOnWhy;


    private void waitForVisibility(WebElement pageHeader) {
        FluentWait wait = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(8))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.visibilityOf(pageHeader));
    }

    private void verifyPageHeader() {
        if (pageHeader.isEnabled()) {
            String text = driver.findElementByName("Get FREE Access For 7 Days").getText();
            Assert.assertEquals(headertext, text, "Page Header text match failed");
        } else {
            log.error("Parent Page post diy plus on signup failed to load.....");
        }
    }

    private void verifyWhyText() {
        if (whyBottomSheet.isEnabled()) {
            whyBottomSheet.click();
            /*String text = driver.findElementByXPath("//XCUIElementTypeStaticText[@value='Parent email id is used to manage DIY subscription via parent and to track your child's progress on DIY.']").getText();
            Assert.assertEquals(text, whybottomsheettext, "why bottom sheet text failed to match");*/
            okButtonOnWhy.click();
        } else {
            log.error(" 'why' button in Parent Page failed to load.....");
        }
    }

    public void enterParentSignUpDetails(String parentfirstname, String parentlastname, String email) {
        waitForVisibility(pageHeader);
        verifyPageHeader();
        parentFirstName.sendKeys(parentfirstname);
        parentLastName.sendKeys(parentlastname);
        verifyWhyText();
        parentEmailId.clear();
        parentEmailId.sendKeys(email);
        if (pageNextButton.isEnabled()){
            pageNextButton.click();
        }
    }
}
