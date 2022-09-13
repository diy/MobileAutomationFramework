package pages.Freemium;

import base.Interface.ILogger;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import util.CommonPageActions;

import java.time.Duration;
import java.util.List;

public class FreemiumSignUpPage implements ILogger {

    private AppiumDriver driver;
    private CommonPageActions pageActions;

    public FreemiumSignUpPage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(8)), this);
        pageActions = new CommonPageActions();
    }

    @FindBy(xpath = "//*[@value='First Name']")
    private WebElement firstName;

    @FindBy(xpath = "//*[@value='Birthday']")
    private WebElement birthday;

    @FindBy(xpath = "//*[@value='Create a Username']")
    private WebElement createUser;

    @FindBy(xpath = "//*[@value='Create a Password']")
    private WebElement createPassword;

    @FindBy(xpath = "//XCUIElementTypeTextField[@value='Parent Email']")
    private WebElement parentEmail;

    @FindBy(xpath = "//XCUIElementTypeStaticText[@name='Learning is FREE on DIY']")
    private WebElement pageHeader;

    @FindBy(xpath = "//XCUIElementTypeButton[@name='Done']")
    private WebElement datePickerDone;

    @FindBy(xpath = "//XCUIElementTypeButton[@name='Cancel']")
    private WebElement datePickerCancel;

    @FindBy(xpath = "//XCUIElementTypePickerWheel")
    private WebElement datePickerWheel;

    @FindBy(xpath = "//XCUIElementTypeButton[@name='Join Now']")
    private WebElement joinNow;

    @FindBy(xpath = "(//XCUIElementTypeButton[@name='intercom close button'])[2]")
    private WebElement intercomWindowClose;


    private boolean waitForVisibility(WebElement pageHeader) {
        try {
            FluentWait wait = new FluentWait(driver)
                    .withTimeout(Duration.ofSeconds(8))
                    .pollingEvery(Duration.ofSeconds(1));
            wait.until(ExpectedConditions.visibilityOf(pageHeader));
            return true;
        } catch (NoSuchElementException noSuchElementException) {
            log.error(noSuchElementException.getLocalizedMessage());
        }
        return false;
    }

    /**
     * Free User sign up details
     * @param firstname
     * @param birthDay
     * @param userName
     * @param password
     * @param parentemail
     */
    public void signUpWithDetails(String firstname, String[] birthDay, String userName, String password, String parentemail) {
        if (waitForVisibility(pageHeader)) {
            pageHeader.isEnabled();
            firstName.sendKeys(firstname);
            selectDOB(birthDay);
            createUser.sendKeys(userName);
            createPassword.sendKeys(password);
            parentEmail.sendKeys(parentemail);
            driver.hideKeyboard();
            joinNow.click();
        }
        if (waitForVisibility(intercomWindowClose) && intercomWindowClose.isDisplayed()){
            intercomWindowClose.click();
        }else {log.info("Intercom welcome popup not appeared......Moving forward");}
    }


    public void selectDOB(String[] dateArray) {
        birthday.click();
        List<WebElement> stringList = driver.findElements(By.xpath(datePickerWheel.toString()));
        for (int i = 0; i < stringList.size(); i++) {
            stringList.get(i).sendKeys(dateArray[i]);
        }
        datePickerDone.click();
    }
}
