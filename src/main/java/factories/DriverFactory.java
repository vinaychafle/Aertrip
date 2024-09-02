package factories;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public final class DriverFactory {
	

	/**
	 * Private constructor to avoid external instantiation
	 */
	private DriverFactory() {

	}

	

	/**
	 * 
	 * @author Vinay Feb 04, 2021
	 * @param browser
	 * @return driver to DriverManager
	 * @throws MalformedURLException
	 */

	public static WebDriver getDriver(String browser) throws MalformedURLException {

		
		
		WebDriver driver = null;

		

//					System.setProperty("webdriver.chrome.driver",FrameworkConstants.getChromeDriverPath());
					WebDriverManager.chromedriver().setup();
					driver = new ChromeDriver();
					
				


			return driver;

		
	}

	
}
