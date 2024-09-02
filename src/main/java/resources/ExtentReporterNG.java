package resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	public static ExtentReports getReportObject(){
	String path =System.getProperty("user.dir")+"//reports//AertripReport.html";
	ExtentSparkReporter reporter = new ExtentSparkReporter (path);
	reporter.config().setReportName("Aertrip Automation Report"); 
	reporter.config().setDocumentTitle("Aertrip Flight Booking Reports");
	ExtentReports extent =new ExtentReports(); extent.attachReporter (reporter);
	extent.setSystemInfo("Tester", "Vinay Chafle");
	return extent;
}
}
