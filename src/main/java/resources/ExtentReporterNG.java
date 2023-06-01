package resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {


    public static ExtentReports getReportObject()
    // We put "static" so we can access this method without even declaring an object of this class
    // So we can call it directly by ExtentReporterNG.getReportObject
    {

        //ExtentReports, ExtentSparkReporter
        //This is responsible for creating the report
        String path = System.getProperty("user.dir") + "//reports/index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("Web Automation Results");
        reporter.config().setDocumentTitle("Test Results");

        ExtentReports extent = new ExtentReports(); // It will be responsible to drive all your reporting execution
        extent.attachReporter(reporter); // Attach that complete report
        extent.setSystemInfo("Tester", "Ilyas"); // Set tester name

        return extent;
    }
}
