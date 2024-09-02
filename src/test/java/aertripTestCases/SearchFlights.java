package aertripTestCases;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import pageObject.FlightResultPage;
import pageObject.HomePage;

public class SearchFlights extends BaseTest {
	/**
	 * Test Name mentioned here should match the column name "testname" in excel sheet.This is mandatory to run this
	 * test. Otherwise it will be ignored. <p>
	 * The match has to be there in both of the RUNMANAGER and TESTDATA sheet.
	 * Set the authors who have the created the test which will be logged to the reports.
	 * Set the category which this particular test case belongs to
	 * @author Vinay
	 * @param data HashMap containing all the values of test data needed to run the tests
	 */
	
	/**
	 * Private constructor to avoid external instantiation
	 */	
	
	private SearchFlights(){}
	
	
	
	@Test
	public void searchFlights()
	{
		 ExtentTest test = Listeners.getTest();
		HomePage.clearDefaultCity();
		test.log(Status.INFO, "Default city cleared successfully");
		HomePage.sendFromCity("Delhi");
		test.log(Status.INFO, "From city name entered successfully");
		HomePage.clickOnFromRequiredCity();
		test.log(Status.INFO, "From city selected successfully");
		HomePage.sendToCity("Hyderabad");
		test.log(Status.INFO, "To city name entered successfully");
		HomePage.waitForCityToDisplay();;
		HomePage.mouseHoverToPlusSign();
		test.log(Status.INFO, "From city selected successfully");
		HomePage.clickOnCalender();
		HomePage.selectDate();
		test.log(Status.INFO, "Journy date entered successfully");
		HomePage.searchFlights();
		FlightResultPage.scrollToBookButton();
		FlightResultPage.clickOnBookButton();
		test.log(Status.INFO, "Clicked on Book button successfully");
		FlightResultPage.continueAsGuest();
		FlightResultPage.verifyTotalFare();
		test.log(Status.INFO, "Total fare  captured successfully");
}
}
