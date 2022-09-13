import base.BaseTest;
import dataProviders.UserData;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.NonLogged.FreeTrailOption;
import pages.NonLogged.FreeTrailPayWall;
import pages.NonLogged.KidsSignIn;
import pages.Profile.ProfilePage;


public class ActivityPostUpload extends BaseTest {

    private AppiumDriver driver;

    @BeforeClass
    @Step("Initialized IOS Driver")
    public void startDriver() {
        driver = initializeDriver();
    }

    @Test(priority = 1, description = "Launch DIY app and select signIn", groups = "regression, sanity", alwaysRun = true)
    @Step("User Logging In") @Severity(SeverityLevel.BLOCKER)
    public void LaunchAppAndSignIn() {
        FreeTrailOption freeTrailOption = new FreeTrailOption(driver);
        freeTrailOption.waitBeforeClickingTrailButton();
    }

    @Test(priority = 2, description = "move to signIn option", groups = "regression, sanity", alwaysRun = true)
    @Step("User Logging In") @Severity(SeverityLevel.BLOCKER)
    public void signIn() {
        FreeTrailPayWall freeTrailNew = new FreeTrailPayWall(driver);
        freeTrailNew.waitBeforeClickingTrailButton();
    }

    @Test(priority = 3, description = "login successfully with valid user", groups = "regression, sanity",
            alwaysRun = true, dataProviderClass = UserData.class , dataProvider = "valid creds")
    public void enterDetailsAndValidateHomePage(String username, String password) {
        KidsSignIn kidsSignIn = new KidsSignIn(driver);
        kidsSignIn.enterDetails(username, password);
    }

    @Test(priority = 4, description = "move to profile Page and perform actions", groups = "regression, sanity", alwaysRun = true,
            dataProviderClass = UserData.class, dataProvider = "userNames")
    public void profilePageActions(String name) {
        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.searchNickNamesAndSignOut(name);
    }
}
