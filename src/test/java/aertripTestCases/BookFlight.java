package aertripTestCases;

import java.io.IOException;

import org.testng.annotations.Test;

import pageObject.ConfirmBooking;
import pageObject.FlightBooking;
import pageObject.FlightSelectionPage;
import pageObject.LoginPage;
import pageObject.SearchFlight;
import pageObject.TravellerDetailsPage;
import resources.ExcelDataRead;

public class BookFlight extends BaseTest {

	/**
	 * Private constructor to avoid external instantiation
	 */

	private BookFlight() {
	}

	@Test(priority=1 ,dataProvider = "loginDetails", dataProviderClass = ExcelDataRead.class)
	public void loginAertrip(String userName, String password,String otp) {
		LoginPage.loginApp(userName, password);
		LoginPage.enterOTP(otp);
	}
	
	@Test(priority=2 ,dataProvider = "flightBookingDetails", dataProviderClass = ExcelDataRead.class)
	public void searchFlights(String fromCity, String ToCity,
			String firstName1, String LastName1, String salutation, String firstName2, String LastName2) throws InterruptedException, IOException {

		
	//	LoginPage.loginApp(userID, password);
//		  Set<String> ele
//		  = DriverManager.getDriver().getWindowHandles(); Iterator<String> it =
//		  ele.iterator(); String parentid = it.next();
//		  
//		 
//		  OutlookOTP otp = new OutlookOTP(); String OTP = otp.getOTP();
//		  DriverManager.getDriver().switchTo().window(parentid);
	//	LoginPage.enterOTP(otp);
		SearchFlight.addNumberOfPassengers("2");
		SearchFlight.clearDefaultCity();
		SearchFlight.sendFromCity(fromCity);
		SearchFlight.clickOnFromRequiredCity(fromCity);
		SearchFlight.sendToCity(ToCity);
		SearchFlight.clickOnCalender();
		SearchFlight.selectDate();
		SearchFlight.searchFlights();
		FlightSelectionPage.scrollToBookButton();
		FlightSelectionPage.clickOnBookButton();
		FlightBooking.verifyTotalFare();
		TravellerDetailsPage.passengerDeatils(salutation, firstName1, LastName1,firstName2, LastName2);
		ConfirmBooking.continueProcessing();

	}
}
