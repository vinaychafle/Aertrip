package driver;

import java.util.Objects;

import org.openqa.selenium.WebDriver;


public final class DriverManager {
	
	/**
	 * Private constructor to avoid external instantiation
	 */
	private DriverManager() {
		
	}
		
	private static ThreadLocal<WebDriver> dr = new ThreadLocal<>() ;
	
	/**
	 * Returns the thread safe {@link org.openqa.selenium.WebDriver} instance fetched from ThreadLocal variable.
	 * 
	 * @author Karan Parmar
	 * Feb 03, 2021
	 * @return {@link org.openqa.selenium.WebDriver} instance.
	 */
	public static WebDriver getDriver() {
		return dr.get();
	}
	
	/**
	 * Set the WebDriver instance to thread local variable
	 * 
	 * @author Karan Parmar
	 * Feb 03, 2021
	 * @param driverref {@link org.openqa.selenium.WebDriver} instance that needs to saved from Thread safety issues.
	 */
	static void setDriver(WebDriver driverref) {
		if(Objects.nonNull(driverref)) {
			dr.set(driverref);
		}
		
	}
	/**
	 * Calling remove method on Threadlocal variable ensures to set the default value to Threadlocal variable.
	 * It is much safer than assigning null value to ThreadLocal variable.
	 * @author Karan Parmar
	 * Feb 03, 2021
	 */
	static void unload() {
		dr.remove();
	}

}
