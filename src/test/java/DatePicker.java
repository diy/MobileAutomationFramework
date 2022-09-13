import base.factories.DriverFactory;
import constants.DriverTypes;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.Freemium.ParentDetailsOnTrial;

public class DatePicker {

    private AppiumDriver driver;

    @BeforeClass
    @Step("Initialise Appium Driver")
    public void startDriver() {
        driver = DriverFactory.initDriver(DriverTypes.IOS);
    }

    /*@Test(priority = 1, description = "Launch DIY app and SignIn", groups = "regression, sanity", alwaysRun = true)
    public void verifyHomePage() {
        List<WebElement> list = driver.findElementsByXPath("//XCUIElementTypeStaticText");
        String name = list.get(9).getText();
        String name1 = "//XCUIElementTypeStaticText[@name='$name']";
        System.out.println(name1.replace("$name", name));
        driver.findElementByXPath(name1.replace("$name", name).trim()).click();
        System.out.println(driver.getPageSource());
        new ActivityUserStories(driver).verifyUserStoryActions();
    }*/

    @Test
    public void check() throws InterruptedException {
        ParentDetailsOnTrial parentDetailsOnTrial = new ParentDetailsOnTrial(driver);
        parentDetailsOnTrial.enterParentSignUpDetails("Sudhanshu", "Singh", "sudhanshu@google.com");
    }
}
