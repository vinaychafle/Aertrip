package aertripTestCases;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import driver.Driver;
import driver.DriverManager;
import pageObject.SearchFlight;
import pageObject.FlightSelectionPage;


/**
 * Acts as a parent class for all the test classes in this framework.
 * All the test classes needs to extend this class. This class is responsible for invoking and terminating
 * browser under test.
 * @author Vinay
 * Feb 03, 2021
 * @version 1.0
 * @since 1.0
 */
public class BaseTest {
	
	
	/**
	 * Protected constructor any other child classes or classes within the 
	 * same package can instantiate this class.
	 */
	protected BaseTest() {
		
	}
	//WebDriver driver= DriverManager.getDriver();
	
	@SuppressWarnings("unchecked")
	@BeforeSuite(alwaysRun=true)
	protected void setUp() throws Exception {

		
//		Driver.initDriver(map.get("browser"),env);
	
		Driver.initDriver("chrome", "https://beta.aertrip.com/v2/flights");
		//https://aertrip.com/v2/flights
		//https://beta.aertrip.com/v2/flights
		//https://b2a-beta.aertrip.com/v2/flights
	}
	
	
	
	public String getScreenshot(String testCaseName) throws IOException {

		TakesScreenshot ts = (TakesScreenshot) DriverManager.getDriver();
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
	}
	
	
	@AfterClass(alwaysRun=true)
	protected void tearDown() {
		Driver.quitDriver();	
	}

}
