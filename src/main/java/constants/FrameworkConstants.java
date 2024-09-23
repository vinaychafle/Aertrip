package constants;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * Framework Constants holds all the constant values used within the framework. If some value needs to be changed
 * or modified often, then it should be stored in the property files
 * 
 * @author Vinay
 * Feb 03, 2021
 * @version 1.0
 * @since 1.0
 * @see utils.PropertyUtils
 */
public final class FrameworkConstants {
	static  Logger LOGGER = LogManager.getLogger(FrameworkConstants.class.getName());
	
	/**
	 * Private constructor to avoid external instantiation
	 */
	private FrameworkConstants() {
		
	}
	//Waits
	private static final int IMPLICITWAIT = 10;
	private static final int EXPLICITWAIT = 20;
	//Screenshots
		public static final String SCREENSHOTPATH = System.getProperty("user.dir")+"/Screenshots";
		
	public static int getImplicitwait() {
		return IMPLICITWAIT;
	}
	
	public static int getExplicitwait() {
		return EXPLICITWAIT;
	}
	
	public static String getScreenshotpath() {
		return SCREENSHOTPATH;
	}
}
