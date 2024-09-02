package driver;

import java.time.Duration;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import constants.FrameworkConstants;

/**
 * 
 * Driver class is responsible for invoking and closing the browsers.
 * 
 * It is also responsible for setting the driver variable to DriverManager which
 * handles the thread safety for the webdriver instance.
 * 
 * @author Karan Parmar Feb 03, 2021
 * @version 1.0
 * @since 1.0
 * @see DriverManager
 * @see com.cactus.paperpalpreflight.tests.BaseTest
 */

public final class Driver {

	static Logger LOGGER = LogManager.getLogger(Driver.class.getName());

	/**
	 * Private constructor to avoid external instantiation
	 */
	private Driver() {
	}

	/**
	 * Gets the browser and url value and initialize the browser based on that
	 * 
	 * @author Vinay Feb 03, 2021
	 * @param browser
	 *            and url Value will be passed from
	 *            {@link com.cactus.paperpalpreflight.tests.BaseTest}. Values can be
	 *            ie,chrome and firefox
	 * @throws Exception 
	 * 
	 */

	public static void initDriver(String browser, String url) throws Exception {
		if (Objects.isNull(DriverManager.getDriver())) {
			try {
				DriverManager.setDriver(factories.DriverFactory.getDriver(browser));
			} catch (Exception e) {
				throw new Exception("Please Check Browser Capabilities." + e.getMessage());
			}
			DriverManager.getDriver().get(url);
			maximizeBrowser();
			setImplicitWait();
		}
	}

	

	/**
	 * Set the URL
	 * 
	 * @param url
	 */
	public static void setURL(String url) {
		DriverManager.getDriver().get(url);
	}

	/**
	 * Terminates the browser instance. Sets the threadlocal to default value, i.e null.
	 * 
	 * @author Vinay Feb 03, 2021
	 */
	public static void quitDriver() {
		try {
			if (Objects.nonNull(DriverManager.getDriver())) {
				DriverManager.getDriver().quit();
				DriverManager.unload();
				LOGGER.info("**** Close All Browser Successfully. ****");
			}
		} catch (Exception exception) {
			LOGGER.info("Got some exception while closing all browser and reason is... " + "\n" + exception.getMessage());
		}
	}

	/**
	 * To Maximize Browser for chrome,firefox and ie etc.
	 * 
	 * @author Vinay Feb 03, 2021
	 */
	private static void maximizeBrowser() {
		try {
			if (Objects.nonNull(DriverManager.getDriver())) {
				DriverManager.getDriver().manage().window().maximize();
//				DriverManager.getDriver().manage().deleteAllCookies();
				Thread.sleep(2000);
			}
		} catch (InterruptedException exception) {
			LOGGER.info("Got some exception while Maximize browser and reason is... " + "\n" + exception.getMessage());
		}
		// DriverManager.getDriver().manage().window().setSize(new Dimension(1920,1080)));
		LOGGER.info("**** Maximize Browser ****");
	}
	

	/**
	 * It tell web driver to wait for a certain amount of time before it throws a "No Such Element Exception".
	 * 
	 * @author Vinay Feb 03, 2021
	 * Updated on 09 July 2024
	 */
	private static void setImplicitWait() {
//		DriverManager.getDriver().manage().timeouts().implicitlyWait(FrameworkConstants.getImplicitwait(),
//				TimeUnit.SECONDS);
		DriverManager.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(FrameworkConstants.getImplicitwait()));
	}

	/**
	 * close the browser instance. Sets the threadlocal to default value, i.e null.
	 * 
	 * @author Vinay July 09, 2021
	 */
	public static void closeDriver() {
		if (Objects.nonNull(DriverManager.getDriver())) {
			DriverManager.getDriver().close();
			DriverManager.unload();
			LOGGER.info("**** Close Browser Successfully. ****");
		}
	}
	
}
