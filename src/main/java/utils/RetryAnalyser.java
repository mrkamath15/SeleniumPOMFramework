package utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyser implements IRetryAnalyzer {
    int count = 0;
    int retryCount = 1;

    //Retry once if test is failed
    @Override
    public boolean retry(ITestResult iTestResult) {
        while (count < retryCount) {
            count++;
            return true;
        }
        return false;
    }
}
