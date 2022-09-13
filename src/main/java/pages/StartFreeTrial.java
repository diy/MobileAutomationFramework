package pages;

import base.Interface.ILogger;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import util.CommonPageActions;

import java.time.Duration;

public class StartFreeTrial implements ILogger {

    private static final String monthlyPlanSelected = "plan-monthly-selected";
    private static final String yearlyPlanSelected = "plan-yearly-selected";
    private static final String monthlyPlanUnSelected = "plan-monthly-unselected";
    private static final String yearlyPlanUnSelected = "plan-yearly-unselected";
    private static final String headerText = "Start 7-Day FREE Trial For DIY Plus";

    private AppiumDriver driver;
    private CommonPageActions pageActions;

    public StartFreeTrial(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(8)), this);
        pageActions = new CommonPageActions();
    }

    @FindBy(xpath = "//XCUIElementTypeStaticText[@name='Start 7-Day FREE Trial For DIY Plus']")
    private WebElement pageHeader;

    @FindBy(xpath = "//XCUIElementTypeImage[@name='plan-monthly-unselected']")
    private WebElement monthlyPlan;

    @FindBy(xpath = "//XCUIElementTypeImage[@name='plan-yearly-selected']")
    private WebElement yearlyPlan;

    @FindBy(xpath = "//XCUIElementTypeButton[@name='Restore Purchases']")
    private WebElement restorePurchases;

    @FindBy(xpath = "//XCUIElementTypeButton[@name='Terms & Privacy']")
    private WebElement termsAndPrivacy;

    @FindBy(xpath = "//XCUIElementTypeButton[@name='Get Started']")
    private WebElement getStartedButton;

    @FindBy(xpath = "//XCUIElementTypeButton[@name='Back']")
    private WebElement backButton;

    private boolean waitForVisibility(WebElement pageHeader) {
        try {
            FluentWait wait = new FluentWait(driver)
                    .withTimeout(Duration.ofSeconds(8))
                    .pollingEvery(Duration.ofSeconds(1));
            wait.until(ExpectedConditions.visibilityOf(pageHeader));
            return true;
        }catch (NoSuchElementException noSuchElementException){
            log.info("failed to detect start trial page header :: " + noSuchElementException.getLocalizedMessage());
        }
        return false;
    }

    private void selectPlan() {
        if (yearlyPlan.isEnabled() && yearlyPlan.isDisplayed()) {
            monthlyPlan.click();
        } else {
            yearlyPlan.click();
        }
    }

    public void verifyPageAndSelectPlan() {
        if (waitForVisibility(pageHeader)){
            selectPlan();
        }
    }
}
