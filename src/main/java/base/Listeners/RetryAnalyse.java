package base.Listeners;


import base.Interface.ILogger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyse implements IRetryAnalyzer, ILogger {

    int retryCount =0;
    int maxCount = 2;

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < maxCount){
            log.error("Retrying failed TC for :: " + retryCount );
            retryCount++;
            return true;
        }
        return false;
    }
}