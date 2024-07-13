package Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
    public static ExtentReports getReportObject() {
        String path = System.getProperty("user.dir") + "\\TestNGReports\\index.html";
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(path);
        sparkReporter.config().setReportName("Web Automation Results");
        sparkReporter.config().setDocumentTitle("Test Results");

        ExtentReports extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter);
        extentReports.setSystemInfo("Tester", "Milena Tsonkova");

        return extentReports;
    }
}

