package pageObject;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;

import driver.DriverManager;
import enums.WaitStrategy;

public class FlightResultPage extends BasePage{
	/**
	 * Private constructor to avoid external instantiation
	 */
	private FlightResultPage() {}
	
	static String parentId;
	static String childId;
	
	//private static final  By bookFlightButton =By.xpath("//div[@class='content-wrapper']/div/div[2]/div[2]/div[4]/div[3]/div/div[1]/div[1]/div[2]/div[1]/div/div/div/div[1]/div[2]/div[2]/div[2]");
	private static final  By bookFlightButton =By.xpath("//div[@class='content-wrapper']/div/div[2]/div[2]/div[4]/div[6]/div/div[1]/div[1]/div[2]/div[1]/div/div/div/div/div[2]/div[2]/div[2]");
	private static final  By scroll =By.xpath("//div[@class='content-wrapper']/div/div[2]/div[2]/div[4]/div[7]/div/div[1]/div[1]/div[2]/div[1]/div/div/div/div[1]/div[2]/div[1]");
	//div[@class='content-wrapper']/div/div[2]/div[2]/div[4]/div[6]/div/div/div/div/div[1]
	private static final  By guestLogin =By.xpath("//span[text()='Continue as guest']");
	private static final  By totalFare =By.xpath("//div[@class='css-faretotal-cont']/div[2]/div/span[2]");
	private static final  By loadingElement =By.xpath("//*[@id=\"app\"]/div/div/div/div/div[1]/div[2]/div[2]/div");
	//div[@class='content-wrapper']/div/div[2]/div[2]/div[4]/div[6]/div/div/div/div/div/div[2]/div[2]/div[2]/div/button
	
	private static final  By closePop =By.cssSelector(".pop-close");
	
	public static void scrollToBookButton() {
//		isElementDisplayed(loadingElement, WaitStrategy.VISIBLE, getCurrentUrl());
//		isElementNotDisplayed(loadingElement, WaitStrategy.INVISIBLE,"Loading Bar", 20);
		moveTOElement(bookFlightButton, WaitStrategy.VISIBLE, "Page is scrolled" );
		//scrollTillElement(bookFlightButton, WaitStrategy.VISIBLE, "Page is scrolled");
		isElementDisplayed(bookFlightButton, WaitStrategy.VISIBLE, "Book flight button is displayed");
	}
	
	public static void clickOnBookButton() {
		//isElementDisplayed(bookFlightButton, WaitStrategy.VISIBLE, "Book flight button is displayed");
		clickOn(bookFlightButton, WaitStrategy.VISIBLE, "Book flight button is clicked");
	}
	
	public static void continueAsGuest() {
		clickOn(guestLogin, WaitStrategy.CLICKABLE, "Clicked on guest Login");
		Set<String> window=DriverManager.getDriver().getWindowHandles();
		Iterator<String> it=window.iterator();
		 parentId=it.next();
		 childId=it.next();
	}
	
	public static void verifyTotalFare() {
		DriverManager.getDriver().switchTo().window(childId);
		String totalF=getTextOnWithoutFail(totalFare, WaitStrategy.PRESENCE, "Total fare extracted successfully");
		System.out.println(totalF);
	}
}
