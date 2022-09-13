package pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    public LoginPage(AppiumDriver driver){
        super(driver);
    }

    @FindBy(id = "username")
    private WebElement nameElement;
    @FindBy(id = "password")
    private WebElement passwordElement;
    @FindBy(id = "login")
    private WebElement loginElement;

    public boolean isDisplayed() {return loginElement.isDisplayed();}

    public void typeName(String name) {nameElement.sendKeys(name);}

    public void typePassword(String password) {passwordElement.sendKeys(password);}

    public void clickLogin() {loginElement.click();}


    public void login(String name, String password) {
        isDisplayed();
        typeName(name);
        typePassword(password);
        clickLogin();
    }
}
