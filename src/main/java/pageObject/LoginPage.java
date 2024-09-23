package pageObject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import driver.DriverManager;
import enums.WaitStrategy;

public class LoginPage extends BasePage {
	
	
	static  Logger LOGGER = LogManager.getLogger(BasePage.class.getName());
	/**
	 * Private constructor to avoid external instantiation
	 */
	
	private LoginPage() {
		}
	
	private static final  By loginButton =By.id("header-login-btn");
	private static final  By signIn =By.xpath("//span[text()='Sign in']");
	private static final  By emailId =By.xpath("(//div[contains(@class,'input-field-small')]/div/div/div/input)[1]");
	private static final  By password =By.xpath("(//div[contains(@class,'input-field-small')]/div/div/div/input)[2]");
	private static final  By login =By.xpath("//span[text()='Login']");
	private static final  By otpFiled =By.id("standard-helperText-0");
	private static final  By submitOtp =By.xpath("//div[@class='cta-btn']/button");
	
	
	public static void loginApp(String email, String pass) {
		clickOn(loginButton, WaitStrategy.VISIBLE, "login link");
		clickOn(signIn, WaitStrategy.VISIBLE, "signIn link");
		clickOn(emailId, WaitStrategy.VISIBLE, "emailText link");
		sendKeysOn(emailId, email, WaitStrategy.CLICKABLE, "Email");
		clickOn(password, WaitStrategy.VISIBLE, "Password Text link");
		sendKeysOn(password, pass, WaitStrategy.CLICKABLE, "Password");
		clickOn(login, WaitStrategy.CLICKABLE, "login link");
		
		}
	
	public static void enterOTP(String otp) {
		
		sendKeysOn(otpFiled, otp, WaitStrategy.CLICKABLE, "OTP");
		clickOn(submitOtp, WaitStrategy.PRESENCE, "submit Button");
		isElementNotDisplayed(submitOtp, WaitStrategy.INVISIBLE, "Submit OTP button", 5);
	}
	
	

}
