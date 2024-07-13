package base;

import Reporter.ExtentReporterNG;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

public class Listeners extends BaseTest implements ITestListener {

    private ExtentReports extent = ExtentReporterNG.getReportObject();
    private ExtentTest test;

    public void onTestStart(ITestResult result) {

        test = extent.createTest(result.getMethod().getMethodName());

    }

    public void onTestSuccess(ITestResult result) {
        test.log(Status.PASS, "Test passed");
    }

    public void onTestFailure(ITestResult result) {
        test.log(Status.FAIL, "Test failed");
        test.fail(result.getThrowable());
        System.out.println("Test failed");

        /*
        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }

         */
        File destFile = null;
        try {
            destFile = getScreenshot(result.getMethod().getMethodName(), driver);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        test.fail(result.getName()).addScreenCaptureFromPath(destFile.getAbsolutePath());

    }

    public void onStart(ITestContext context) {
        System.out.println("Test started");
    }

    public void onFinish(ITestContext context) {
        System.out.println("Test finished");
        extent.flush();

    }
}