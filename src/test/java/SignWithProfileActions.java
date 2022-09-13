import base.BaseTest;
import base.factories.DriverFactory;
import constants.DriverTypes;
import io.appium.java_client.AppiumDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.NonLogged.FreeTrailPayWall;
import pages.NonLogged.FreeTrailOption;
import pages.NonLogged.ParentSignUpPage;

public class SignWithProfileActions extends BaseTest {

    private AppiumDriver driver;

    @BeforeClass(description = "starting IOS Driver")
    public void startDriver() {
        driver = DriverFactory.initDriver(DriverTypes.IOS);
    }

    @Test(priority = 1, description = "Launch DIY app and verify home page", groups = "regression, sanity", alwaysRun = true)
    public void verifyHomePage() {
        FreeTrailOption freeTrailOption = new FreeTrailOption(driver);
        freeTrailOption.waitBeforeClickingTrailButton();
    }

    @Test(priority = 2, description = "Launch SignUp free trail user page", groups = "regression, sanity", alwaysRun = true)
    public void verifyFreeTrail() {
        FreeTrailPayWall freeTrailOption = new FreeTrailPayWall(driver);
        freeTrailOption.waitBeforeClickingTrailButton();
    }

    @Test(priority = 3, description = "Enter user details", groups = "regression", alwaysRun = true, dependsOnMethods = "verifyFreeTrail")
    public void SignUpFreeTrail() throws InterruptedException {
        ParentSignUpPage parentSignUpPage = new ParentSignUpPage(driver);
        parentSignUpPage.enterDetails("Sudhanshu", "Singh", "sudhanshu11@gmail.com");
    }
}
