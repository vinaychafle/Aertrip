package pageObject;

import org.openqa.selenium.By;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

import aertrip.Utils.ScreenshotUtils;
import enums.WaitStrategy;
import resources.Listeners;

public class TravellerDetailsPage extends BasePage {
	private static ExtentTest test=Listeners.getTest();
	/**
	 * Private constructor to avoid external instantiation
	 */
	
	private TravellerDetailsPage() {
		}
	
	private static final  By Title1 =By.xpath("(//button[@title='Title'])[1]");
	private static final  By Title2 =By.xpath("//*[@id=\"js-tab-2\"]/view/form/div/div[2]/div[1]/div[1]/div/button");
	private static final  By salutatiunMR1 =By.xpath("//*[@id=\"checkout-page\"]/body/div[2]/div/ul/li[2]/a/span[1]");
	private static final  By salutatiunMR2 =By.xpath("//*[@id=\"checkout-page\"]/body/div[2]/div/ul/li[2]/a/span[1]");
	private static final  By firstName1 =By.name("js-fname[adult0]");
	private static final  By firstName2 =By.name("js-fname[adult1]");
	private static final  By lastName1 =By.name("js-lname[adult0]");
	private static final  By lastName2 =By.name("js-lname[adult1]");
	private static final  By continueButton =By.xpath("//div[@class='css-continue-button']/input");
	private static final  By nextContinue =By.xpath("//button[text()='Continue']");
	
	public static void passengerDeatils(String text, String firstNam1, String lastNam1,String firstNam2,String lastNam2) {
		moveTOElement(Title1, WaitStrategy.CLICKABLE, "scroll TO drop down");
		clickOn(Title1, WaitStrategy.CLICKABLE, "Title dropdown");
		isElementDisplayed(salutatiunMR1,  WaitStrategy.CLICKABLE, "MR salutation");
		clickOn(salutatiunMR1, WaitStrategy.VISIBLE, "Mr salutation");
		//getCurrentSelectionFromDropDown(Title, text, "Title dropdown");
		sendKeysOn(firstName1, firstNam1, WaitStrategy.CLICKABLE, "first name");
		sendKeysOn(lastName1, lastNam1, WaitStrategy.CLICKABLE, "last name");
		
		
		clickUsingJS(Title2, WaitStrategy.CLICKABLE, "Title");
		isElementDisplayed(salutatiunMR2,  WaitStrategy.CLICKABLE, "MR salutation");
		clickOn(salutatiunMR2, WaitStrategy.CLICKABLE, "Mr salutation");
		//getCurrentSelectionFromDropDown(Title, text, "Title dropdown");
		sendKeysOn(firstName2, firstNam2, WaitStrategy.CLICKABLE, "first name");
		sendKeysOn(lastName2, lastNam2, WaitStrategy.CLICKABLE, "last name");
		
		String base64code=	ScreenshotUtils.getBase64Image();
		test.pass(MediaEntityBuilder.createScreenCaptureFromBase64String(base64code, "Passenger details entered").build());
		moveTOElement(continueButton, WaitStrategy.VISIBLE, "scroll TO continue button");
		clickOn(continueButton, WaitStrategy.VISIBLE, "Clicked on continue Button");
		moveTOElement(nextContinue, WaitStrategy.VISIBLE, "scroll TO next continue button");
		clickOn(nextContinue, WaitStrategy.CLICKABLE, "Next continue Button");
		
		}
	
	

}
