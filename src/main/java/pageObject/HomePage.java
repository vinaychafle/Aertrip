package pageObject;

import org.openqa.selenium.By;

import enums.WaitStrategy;

public class HomePage extends BasePage {
	/**
	 * Private constructor to avoid external instantiation
	 */
	private HomePage() {}
	
	private static final  By fromCity =By.id("search-field-From");
	private static final  By selectFromCity =By.xpath("//strong[text()='Delhi']");
	private static final  By toCity =By.id("search-field-To");
	private static final  By plusSign =By.xpath("//ul[@class='loc-search']/li[1]/div[2]/span");
	//"//ul[@class='loc-search']/li[1]/div[1]/div[2]/strong"
	//ul[@class='loc-search']/li[1]/div[2]/span
	private static final  By selectTOCity =By.xpath("//ul[@class='loc-search']/li[1]/div[1]/div[2]/strong");
	private static final  By date =By.id("date-depart-id-");
	private static final  By dateSelect =By.xpath("//div[@aria-label='Calendar']/div[2]/div/div[2]/div/table/tbody/tr[4]/td[5]");
	private static final  By searchFlights =By.xpath("//span[text()='Search']");
	
	public static void sendFromCity(String fromCityName) {
		clickOn(fromCity, WaitStrategy.PRESENCE, "Clicked on city");
		clearTextOnInputFieldUsingJS(fromCity, WaitStrategy.PRESENCE);
		clickOn(fromCity, WaitStrategy.PRESENCE, "Clicked on city");
		sendKeysOn(fromCity,  fromCityName, WaitStrategy.CLICKABLE, "From"+fromCityName+" city name is added successfully");
	}
	
	public static void clearDefaultCity() {
		clearTextOnInputFieldUsingJS(fromCity, WaitStrategy.PRESENCE);
	}
	
	public static void clickOnFromRequiredCity() {
		clickOn(selectFromCity, WaitStrategy.VISIBLE, "From city is Selected");
	}
	
	public static void sendToCity(String ToCityName) {
		sendKeysOn(toCity,  ToCityName, WaitStrategy.CLICKABLE, "To"+ToCityName+" city name is added successfully");
	}
	
	public static void mouseHoverToPlusSign() {
		mouseHoverOn(plusSign, WaitStrategy.PRESENCE, "element displayed");
		clickOn(plusSign, WaitStrategy.CLICKABLE, "To city is Selected");
	}
	
	public static void waitForCityToDisplay() {
		isElementDisplayed(selectTOCity, WaitStrategy.PRESENCE, "element displayed");
	}
	
	public static void clickOnCalender() {
		clickOn(date, WaitStrategy.CLICKABLE, "Clicked on calender");
	}
	
	public static void selectDate() {
		clickOn(dateSelect, WaitStrategy.VISIBLE, "Date is selected");
	}

	public static void searchFlights() {
		clickOn(searchFlights, WaitStrategy.CLICKABLE, "Date is selected");
	}

}
