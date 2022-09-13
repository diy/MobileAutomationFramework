package pages.Library;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class Library {

    private AppiumDriver driver;
    private static final String pageHeaderText = "Library";

    public Library(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(8)), this);
    }

    @FindBy(xpath = "//*[@name= 'Sign In' ]")
    private WebElement signInButton;

    @FindBy(xpath = "//XCUIElementTypeStaticText[@name='Start FREE Trial']")
    private WebElement presignInButton;

    @FindBy(xpath = "(//XCUIElementTypeStaticText[@name='Library'])[1]")
    private WebElement pageHeader;

    @FindBy(xpath = "//XCUIElementTypeButton[@name='Get DIY Plus']")
    private WebElement diyPlusStickyCTA;

    @FindBy(xpath = "//XCUIElementTypeStaticText[@name='Drawing, ROBLOX, Minecraft, Lego, Podcasting - find videos and tutorials on ANY skill here.']")
    private WebElement libUserJourney;


    private boolean isEnabled() {
        return pageHeader.isEnabled();
    }

    private boolean isVisible() {
        return pageHeader.isDisplayed();
    }

    private boolean waitForVisibility(WebElement nfcElement) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 8);
            wait.until(ExpectedConditions.visibilityOf(nfcElement));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void clickDiyPlus() {
        diyPlusStickyCTA.click();
    }

    private void waitAndVerifyPage() {
        if (waitForVisibility(pageHeader))
            Assert.assertTrue(pageHeader.isEnabled(), "failed to get Library Header text");
            String text = driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name='Library']")).getText();
            Assert.assertEquals(text, pageHeaderText, "Library Page Header text failed to Match....");
    }

    public void verifyPageAndClickStickyCTA() {
        if (waitForVisibility(libUserJourney) && libUserJourney.isDisplayed()) {
            libUserJourney.click();
        }
        waitAndVerifyPage();
        clickDiyPlus();
    }

    public void verifyPageHeader(){
        waitForVisibility(pageHeader);
    }

    public void nonLoggedUser() {
        isVisible();
        presignInButton.click();
    }
}
