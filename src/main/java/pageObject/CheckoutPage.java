package pageObject;

import org.openqa.selenium.By;

import enums.WaitStrategy;

public class CheckoutPage extends BasePage {
	/**
	 * Private constructor to avoid external instantiation
	 */
	
	private CheckoutPage() {
		}
	
	private static final  By confirmBooking =By.xpath("(//button[text()='Confirm Booking'])[1]");
	
	private static final  By otpField =By.cssSelector(".js-checkout-otp");
	private static final  By submitOTP =By.xpath("//button[text()='Submit']");
	private static final  By reconfirmFareButton =By.xpath("//button[text()='Reconfirming Fare']");
	
	public static void continueProcessing() {
		
		clickOn(confirmBooking, WaitStrategy.CLICKABLE, "Next continue Button");
		sendKeysOn(otpField, "123456", WaitStrategy.CLICKABLE, "OTP for flight booking");
		clickOn(submitOTP, WaitStrategy.CLICKABLE, "Payment Submit OTP Button");
		isElementNotDisplayed(reconfirmFareButton, WaitStrategy.INVISIBLE, "reconfirm fare button", 50);
		}
	
	

}
