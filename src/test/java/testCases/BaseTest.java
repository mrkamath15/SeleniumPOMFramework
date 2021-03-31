package test.java.testCases;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.Constants;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    public static WebDriver driver;
    public ExtentSparkReporter reporter;
    public static ExtentReports extentReports;
    public static ExtentTest logger;

    @BeforeTest
    public void beforeTest() {
        //Clean up the created data
        cleanUp();
        //Set up extent reports
        SimpleDateFormat myDateFormat = new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss-SSS");
        String finalReportPath = Constants.reportPath + File.separator + Constants.reportName + myDateFormat.format(new Date()) + ".html";
        reporter = new ExtentSparkReporter(finalReportPath);
        reporter.config().setReportName("Automation Report");
        reporter.config().setDocumentTitle("Automation Framework");
        reporter.config().setTheme(Theme.DARK);
        reporter.config().setEncoding("utf-8");
        extentReports = new ExtentReports();
        extentReports.attachReporter(reporter);
    }

    @BeforeMethod
    @Parameters({"browser"})
    public void beforeMethod(String browser, Method method) {
        //Create extent tests
        logger = extentReports.createTest(method.getName());
        //Driver set up
        setUpDriver(browser);
        driver.get(Constants.applicationUrl);
    }

    @AfterTest
    public void afterTest() {
        //Flush the extent reports after execution
        extentReports.flush();
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        //Log the results in extent reports
        String methodName = result.getMethod().getMethodName();
        if (result.getStatus() == ITestResult.SUCCESS) {
            String logString = "Test case " + methodName + " is Passed";
            Markup m = MarkupHelper.createLabel(logString, ExtentColor.GREEN);
            logger.log(Status.PASS, m);
        }
        else if (result.getStatus() == ITestResult.FAILURE) {
            String logString = "Test case " + methodName + " is Failed";
            Markup m = MarkupHelper.createLabel(logString, ExtentColor.RED);
            logger.log(Status.FAIL, m);
            logger.info(result.getThrowable());
            //logger.addScreenCaptureFromPath(methodName + ".png");
            //Attach screenshots to reports on failure
            logger.info("Screenshot attached here", MediaEntityBuilder.createScreenCaptureFromPath("../screenshots/" + methodName + ".png").build());
        }
        else if (result.getStatus() == ITestResult.SKIP) {
            String logString = "Test case " + methodName + " is Skipped";
            Markup m = MarkupHelper.createLabel(logString, ExtentColor.AMBER);
            logger.log(Status.SKIP, m);
        }
        driver.quit();
    }

    //Set up the driver with input from testng xml
    public void setUpDriver(String browserName) {
        if (browserName.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }

        else if (browserName.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        else if (browserName.equalsIgnoreCase("ie")) {
            WebDriverManager.iedriver().setup();
            driver = new InternetExplorerDriver();
        }
        else if (browserName.equalsIgnoreCase("opera")) {
            WebDriverManager.operadriver().setup();
            driver = new OperaDriver();
        }
        else {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    //To clean up the reports and screenshots from previous execution
    private void cleanUp() {
        if (new File(Constants.reportPath).exists()) {
            try {
                FileUtils.deleteDirectory(new File(Constants.reportPath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        new File(Constants.reportPath).mkdir();

        if (new File(Constants.screenshotsPath).exists()) {
            try {
                FileUtils.deleteDirectory(new File(Constants.screenshotsPath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        new File(Constants.screenshotsPath).mkdir();
    }
}
