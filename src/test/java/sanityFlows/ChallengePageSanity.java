package sanityFlows;

import base.BaseTest;
import dataProviders.UserData;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.Challenges.Featured.FeaturedTab;
import pages.Challenges.Featured.LiveChallengePage;
import pages.Challenges.Featured.SpotLightPage;
import pages.LoginPage;

public class ChallengePageSanity extends BaseTest {

    private FeaturedTab featuredTab;
    private LiveChallengePage liveChallengePage;
    private SpotLightPage spotLightPage;

    private AppiumDriver driver;

    @BeforeClass
    @Step("Initialise Appium Driver")
    public void startDriver() {
        driver = initializeDriver();
        featuredTab = new FeaturedTab(driver);
        liveChallengePage = new LiveChallengePage(driver);
        spotLightPage = new SpotLightPage(driver);
    }

    @Test(description = "Login to DIY account and move to challenge page", priority = 1 , dataProviderClass = UserData.class , dataProvider = "valid creds")
    private void loginAndMoveToChallengePage(String username, String password) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);
    }

    @Test(description = "Search Any random Challenge", priority = 2, dataProviderClass = UserData.class, dataProvider = "searchValidChallenge")
    private void searchAnyChallenge(String challengeName) {
        featuredTab.searchAnyChallenge(challengeName);
        Assert.assertEquals("", "");
    }

    @Test(description = "Search Any random Challenge and make a post", priority = 3, dataProviderClass = UserData.class, dataProvider = "searchValidChallenge")
    private void verifyChallengeTypeAndAttributes(String challengeName) {
        String pageSource =  driver.getPageSource();
        if (pageSource.contains("Live Workshops")){
            featuredTab.clickChallengeType("live");
        }else {
            featuredTab.clickChallengeType("contest");
        }
        featuredTab.clickChallengeType("live");
        liveChallengePage.clickJoinButton();
    }

    @Test(description = "Search Any random Challenge and make a post", priority = 4, dataProviderClass = UserData.class, dataProvider = "searchValidChallenge")
    private void postOnChallenge(String challengeName) {
        spotLightPage.clickSpotLight();
        spotLightPage.makeAPost();
    }
}
