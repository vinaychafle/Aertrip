package pageObject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

import aertrip.Utils.ScreenshotUtils;
import enums.WaitStrategy;
import resources.Listeners;

public class SearchFlight extends BasePage {
	
	private static ExtentTest test=Listeners.getTest();
	static  Logger LOGGER = LogManager.getLogger(BasePage.class.getName());
	/**
	 * Private constructor to avoid external instantiation
	 */
	private SearchFlight() {
		
		}
	
	
	private static final  By fromCity =By.id("search-field-From");
	private static final  By selectFromCity =By.xpath("//strong[text()='Delhi']");
	private static final  By toCity =By.id("search-field-To");
	private static final  By plusSign =By.xpath("//ul[@class='loc-search']/li[1]/div[2]/span");
	private static final  By plusSign1 =By.xpath("//ul[@class='loc-search']/li[1]/div[2]/span");
	
	//"//ul[@class='loc-search']/li[1]/div[1]/div[2]/strong"
	//ul[@class='loc-search']/li[1]/div[2]/span
	//div[text()='HYD']
	
	//div[text()='Hyderabad, IN']/span
	private static final  By destCity =By.xpath("//div[text()='Hyderabad, IN']");
	private static final  By selectTOCity =By.xpath("//ul[@class='loc-search']/li[1]/div[1]/div[2]/strong");
	private static final  By date =By.id("date-depart-id-");
	private static final  By dateSelect =By.xpath("//div[@aria-label='Calendar']/div[2]/div/div[2]/div/table/tbody/tr[4]/td[5]");
	private static final  By searchFlights =By.xpath("//span[text()='Search']");
	
	private static final  By passengerSelect =By.xpath("//span[text()='Passenger']/span");
	private static final  By numberOfPassenger =By.xpath("(//input[@type='text'])[1]");
	
	public static void addNumberOfPassengers(String text) {
		clickOn(passengerSelect, WaitStrategy.CLICKABLE, "Passenger Button");
		clickOn(numberOfPassenger, WaitStrategy.CLICKABLE, "Number of Passengers");
		clearTextOnInputFieldUsingJS(numberOfPassenger, WaitStrategy.PRESENCE);
		sendKeysOn(numberOfPassenger,  text, WaitStrategy.CLICKABLE, text+" Number of passengers");
	}
	
	public static void sendFromCity(String fromCityName) throws InterruptedException {
		waitForElement();
		clickOn(fromCity, WaitStrategy.PRESENCE, "From city");
		clearTextOnInputFieldUsingJS(fromCity, WaitStrategy.PRESENCE);
		clickOn(fromCity, WaitStrategy.PRESENCE, "Again from city");
		clearTextOnInputFieldUsingJS(fromCity, WaitStrategy.PRESENCE);
		sendKeysOn(fromCity,  fromCityName, WaitStrategy.CLICKABLE, "From "+fromCityName);
	}
	
	public static void clearDefaultCity() {
		clearTextOnInputFieldUsingJS(fromCity, WaitStrategy.PRESENCE);
	}
	
	public static void clickOnFromRequiredCity(String fromCityName) throws InterruptedException {
		clickOn(selectFromCity, WaitStrategy.VISIBLE, "From "+fromCityName);
		waitForElement();
	}
	
	public static void sendToCity(String ToCityName) throws InterruptedException {
		clickOn(toCity, WaitStrategy.PRESENCE, "To city");
		clearTextOnInputFieldUsingJS(toCity, WaitStrategy.PRESENCE);
		sendKeysOn(toCity,  ToCityName, WaitStrategy.CLICKABLE, "To "+ToCityName);
		clickUsingJS(destCity, WaitStrategy.CLICKABLE, ToCityName);
		waitForElement();
		String base64code=	ScreenshotUtils.getBase64Image();
		test.pass(MediaEntityBuilder.createScreenCaptureFromBase64String(base64code, "Flight details entered").build());
	}
	
//	public static void mouseHoverToPlusSign() {
//		mouseHoverOn(plusSign, WaitStrategy.PRESENCE, "element displayed");
//		//clickOn(destCity, WaitStrategy.CLICKABLE, "To city is Selected");
//	}
	
	public static void waitForCityToDisplay() {
		
		isElementDisplayed(selectTOCity, WaitStrategy.PRESENCE, "element displayed");
	}
	
	public static void clickOnCalender() {
		clickOn(date, WaitStrategy.CLICKABLE, "calender");
	}
	
	public static void selectDate() {
		clickOn(dateSelect, WaitStrategy.VISIBLE, "Date");
	}

	public static void searchFlights() {
		clickOn(searchFlights, WaitStrategy.CLICKABLE, "Search flight button");
	}

}
