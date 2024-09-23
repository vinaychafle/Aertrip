package resources;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import aertrip.Utils.ScreenshotUtils;
import driver.Driver;
import driver.DriverManager;


public class Listeners implements ITestListener {
	ExtentReports extent = resources.ExtentReporterNG.getReportObject();
	static ExtentTest test;
	static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

	
	
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		extentTest.get().log(Status.PASS, "Test Pass");
		String filePath = null;
		try {
			filePath =ScreenshotUtils.getScreenshot(result.getMethod().getMethodName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test.addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		extentTest.get().fail(result.getThrowable());

//		try {
//			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//
//		}

		String filePath = null;
		try {
			filePath =ScreenshotUtils.getScreenshot(result.getMethod().getMethodName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test.addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}
	 public static ExtentTest getTest() {
	        return extentTest.get();
	    }

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		extent.flush();
	}

}
