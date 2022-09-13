package base.Listeners;

import base.BaseTest;
import base.Interface.ILogger;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.*;
import org.testng.annotations.ITestAnnotation;
import org.testng.xml.XmlSuite;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MobileListener extends TestListenerAdapter implements ITestNGListener, ITestListener, IAnnotationTransformer,
        ISuiteListener, IReporter, ILogger {

    @Override
    public void onStart(ITestContext context) {
        log.info(" starting test ...........::::" + context.getName());
        context.setAttribute("AppiumDriver", BaseTest.getDriver());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.error("Test Failed on Method :: " + result.getName());
        AppiumDriver driver = BaseTest.getDriver();
        try {
            if (driver != null) {
               FileUtils.copyFile(saveFailureScreenShotFile(driver), new File("src/test/FailureScreenshots/" + "My.jpg"));
            }
        } catch (Exception e) {
           saveText("failed to take screenshot ::->::" + getTestMethodName(result));
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        Set<ITestResult> filtered = new HashSet<>();
        log.info("total test cases skipped :::" + result.getSkipCausedBy().size());
        Set<ITestResult> iTestResultSet = result.getTestContext().getSkippedTests().getAllResults();
        if (!iTestResultSet.isEmpty()) {
             result.getTestContext().getSkippedTests().getAllResults().stream()
                     .filter(e -> e.getStatus() == 3).forEach(e-> System.out.println("Skipped test is :" + e.getName()));
        }
    }


    @Override
    public void onFinish(ISuite suite) {
        log.info("Finishing test case for the test suite.......:::: " + suite.getName().toUpperCase());
    }

    @Override
    public void onFinish(ITestContext context) {
      context.getSkippedTests().getAllResults().stream().filter(e-> e.getStatus() == 3).forEach(ITestResult::getName);
      context.getSkippedTests().getAllResults().stream().filter(e-> e.getStatus() == 1).forEach(ITestResult::getName);
    }

    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        xmlSuites.forEach(System.out::println);
    }

    @Override
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        if (isTestConfigured(annotation))
            annotation.setAlwaysRun(true);
       // annotation.setRetryAnalyzer(RetryAnalyse.class);
    }


    /**
     * @param iTestAnnotation
     * @return
     */
    private boolean isTestConfigured(ITestAnnotation iTestAnnotation) {
        return iTestAnnotation.getAlwaysRun();
    }

    @Attachment
    public byte[] saveFailureScreenShot(AppiumDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment
    public File saveFailureScreenShotFile(AppiumDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    }

    @Attachment(value = "{0}", type = "text/plain")
    public static String saveText(String message) {
        return message;
    }

    public String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getMethodName();
    }
}
