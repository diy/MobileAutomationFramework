package pages.NonLogged;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class ParentSignUpPage {

    private AppiumDriver driver;

    public ParentSignUpPage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(8)), this);
    }

    @FindBy(xpath = "//XCUIElementTypeTextField[@value= 'Parent First Name']")
    private WebElement parentFirstName;

    @FindBy(xpath = "//XCUIElementTypeTextField[@value= 'Parent Last Name' ]")
    private WebElement parentSecondName;

    @FindBy(xpath = "//XCUIElementTypeTextField[@value= 'Parent Email' ]")
    private WebElement parentEmail;

    @FindBy(xpath = "//XCUIElementTypeStaticText[@name= 'Sign In' ]")
    private WebElement signIn;

    @FindBy(xpath = "//XCUIElementTypeStaticText[@name='Parent Powers']")
    private WebElement pageHeader;

    @FindBy(xpath = "//XCUIElementTypeStaticText[@name= 'Next' ]")
    private WebElement next;

    private boolean isEnabled() {
        return pageHeader.isEnabled();
    }

    private void waitForVisibility(WebElement pageHeader) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 8);
        wait.until(ExpectedConditions.visibilityOf(pageHeader));
    }

    private void setNext() {
        next.click();
    }

    private void FirstName(String firstname){
        parentFirstName.sendKeys(firstname);
    }

    private void secondName(String secondName){
        parentSecondName.sendKeys(secondName);
    }

    private void email(String email){
        parentEmail.sendKeys(email);
    }


    public void waitBeforeClickingTrailButton() throws InterruptedException {
        waitForVisibility(pageHeader);
        isEnabled();
    }

    public void enterDetails(String firstname, String secondname, String email) throws InterruptedException {
        waitBeforeClickingTrailButton();
        FirstName(firstname);
        secondName(secondname);
        email(email);
        setNext();
    }
}
