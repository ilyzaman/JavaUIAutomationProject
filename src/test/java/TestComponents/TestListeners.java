package TestComponents;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener; // It's one of the interfaces provided by testNG
import org.testng.ITestResult;
import resources.ExtentReporterNG;

import java.io.IOException;


public class TestListeners extends BaseTest implements ITestListener {

    ExtentReports extent = ExtentReporterNG.getReportObject();
    ExtentTest test;

    ThreadLocal <ExtentTest> extentTest = new ThreadLocal<>();//When doing parralel test, it won't override some variables

    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test); //unique thread id
    }

    public void onTestSuccess(ITestResult result) {
        extentTest.get().log(Status.PASS,"Test passed");
    }

    public void onTestFailure(ITestResult result) {
       //test.log(Status.FAIL,"Test failed");
       extentTest.get().fail(result.getThrowable()); //Another way

        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*
        getTestClass => testng.xml
        getRealClass => Actual method
         */


        //Take a screenshot and attach it to reports
        String filePath;
        try {
            filePath = getScreenShot(result.getMethod().getMethodName(),driver);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // This method takes the path from my local system and attach to the ExtentReport
        extentTest.get().addScreenCaptureFromPath(filePath,result.getMethod().getMethodName());

    }
    public void onFinish(ITestContext context) {
        extent.flush();
    }

}
