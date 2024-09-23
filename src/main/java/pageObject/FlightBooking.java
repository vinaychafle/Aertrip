package pageObject;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

import aertrip.Utils.ScreenshotUtils;
import driver.DriverManager;
import enums.WaitStrategy;
import resources.Listeners;

public class FlightBooking extends BasePage{
	private static ExtentTest test=Listeners.getTest();
	/**
	 * Private constructor to avoid external instantiation
	 */
	
	private FlightBooking() {
		}
	
	private static final  By totalFare =By.xpath("//div[@class='css-faretotal-cont']/div[2]/div/span[2]");
	
	
	public static void verifyTotalFare() {
		Set<String> window=	DriverManager.getDriver().getWindowHandles();
		Iterator<String>it=	window.iterator();
		String parentID=it.next();
		String childID=it.next();
		DriverManager.getDriver().switchTo().window(childID);
		String totalF=getTextOnWithoutFail(totalFare, WaitStrategy.PRESENCE, "Total fare extracted successfully");
		System.out.println(totalF);
	}
}
