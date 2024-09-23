package pageObject;

import org.openqa.selenium.By;

import enums.WaitStrategy;

public class FlightSelectionPage extends BasePage{
	/**
	 * Private constructor to avoid external instantiation
	 */
	
	private FlightSelectionPage() {
		}
	
	private static final  By bookFlightButton =By.xpath("(//span[text()='Book'])[1]");
	private static final  By processBookButton =By.xpath("//button[contains(@class,'dark-btn btn_process')]");
	private static final  By scroll =By.xpath("//div[@class='content-wrapper']/div/div[2]/div[2]/div[4]/div[7]/div/div[1]/div[1]/div[2]/div[1]/div/div/div/div[1]/div[2]/div[1]");
	private static final  By guestLogin =By.xpath("//span[text()='Continue as guest']");
	private static final  By loadingBar =By.xpath("//*[@id=\"app\"]/div/div/div/div/div[1]/div[1]/section/div[2]/div");
	
	public static void scrollToBookButton() {
		moveTOElement(bookFlightButton, WaitStrategy.VISIBLE, "Page is scrolled" );
		isElementDisplayed(bookFlightButton, WaitStrategy.VISIBLE, "Book flight button is displayed");
	}
	
	public static void clickOnBookButton() {
		isElementNotDisplayed(loadingBar, WaitStrategy.INVISIBLE, "loading bar",70);
		clickOn(bookFlightButton, WaitStrategy.VISIBLE, "Book flight button is clicked");
		isElementNotDisplayed(processBookButton,  WaitStrategy.INVISIBLE, "processing Button state", 30);
	}
	
	
}
