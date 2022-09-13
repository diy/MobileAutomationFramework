package pages.Freemium;

import base.Interface.ILogger;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import util.CommonPageActions;

import java.time.Duration;

public class FreemiumBlockerScreen implements ILogger {

    private static final String pageHeading = "Welcome to DIY";
    private static final String pageSubHeading = "The Largest Interest-Based Community For Kids";

    private AppiumDriver driver;
    private CommonPageActions pageActions;

    public FreemiumBlockerScreen(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(8)), this);
        pageActions = new CommonPageActions();
    }

    @FindBy(xpath = "//XCUIElementTypeButton[@name='Sign In']")
    private WebElement signIn;

    @FindBy(xpath = "/XCUIElementTypeButton[@name='Join for FREE']")
    private WebElement joinFreeButton;

    @FindBy(xpath = "//XCUIElementTypeStaticText[@name='Welcome to DIY']")
    private WebElement pageHeader;

    private void waitForVisibility(WebElement pageHeader) {
        FluentWait wait = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(8))
                .pollingEvery(Duration.ofSeconds(1));
        wait.until(ExpectedConditions.visibilityOf(pageHeader));
    }

    public void clickJoinInForFree(){
        if (pageHeader.isEnabled()){
            String text = driver.findElementByName("Welcome to DIY").getText();
            Assert.assertEquals(pageHeading, text, " Blocker screen Page header failed Match with expected text ");
            log.info("Freemium Blocker screen visible with text --::::--" + text + " : - and now initiating signup..... ");
            joinFreeButton.click();
        }
    }

    public void clickSignIn(){
        waitForVisibility(pageHeader);
        if (pageHeader.isEnabled()){
            String text = driver.findElementByName("Welcome to DIY").getText();
            Assert.assertEquals(pageHeading, text, " Blocker screen Page header failed Match with expected text ");
            log.info("Freemium Blocker screen visible with text --::::--" + text + " :- and now initiating singin.........");
            signIn.click();
        }
    }
}
