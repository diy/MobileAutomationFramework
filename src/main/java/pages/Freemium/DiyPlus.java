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

import java.time.Duration;

public class DiyPlus implements ILogger {

    private AppiumDriver driver;
    private static final String paywalltext = "To connect with kids around the world & inspire others";
    private static final String subtodiy = "Subscribe to DIY";

    public DiyPlus(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(8)), this);
    }

    @FindBy(xpath = "//XCUIElementTypeButton[@name='Get DIY Plus']")
    private WebElement getDiyPlusButton;

    @FindBy(xpath = "//XCUIElementTypeStaticText[@name='To connect with kids around the world & inspire others'")
    private WebElement pageHeader;

    @FindBy(xpath = "//XCUIElementTypeStaticText[@name='Subscribe to DIY']")
    private WebElement subToDiy;



    private void waitForVisibility(WebElement pageHeader) {
        FluentWait wait = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(8))
                .pollingEvery(Duration.ofSeconds(1));
        wait.until(ExpectedConditions.visibilityOf(pageHeader));
    }

    public void verifyPageHeaderAndClick(){
        waitForVisibility(subToDiy);
        if (subToDiy.isEnabled()) {
            String text =  driver.findElementByName("Subscribe to DIY").getText();
            Assert.assertEquals(subtodiy, text, "paywall Header text match failed");
            getDiyPlusButton.click();
        }else{
            log.error("Parent Page post diy plus on signup failed to load.....");
        }
    }
}
