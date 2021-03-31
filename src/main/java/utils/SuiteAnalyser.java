package main.java.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.IAnnotationTransformer;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;
import main.java.utils.Constants;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class SuiteAnalyser implements ITestListener,IAnnotationTransformer {
    @Override
    public void onTestStart(ITestResult result) {

    }

    @Override
    public void onTestSuccess(ITestResult result) {

    }

    @Override
    public void onTestFailure(ITestResult result) {
        //Capture screenshot on failure
        String destinationScreenshot = Constants.screenshotsPath + File.separator + result.getMethod().getMethodName() + ".png";
        TakesScreenshot tk = (TakesScreenshot) test.java.testCases.BaseTest.driver;
        File srcScreenshot = tk.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(srcScreenshot, new File(destinationScreenshot));
        }
        catch (IOException e) {
           e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {

    }

    @Override
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        //set retry analyzer
        annotation.setRetryAnalyzer(utils.RetryAnalyser.class);
    }
}
