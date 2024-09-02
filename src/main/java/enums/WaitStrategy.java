package enums;

/**
 * Enums to restrict the users to choose an appropriate waiting strategy before operating an element.
 * 
 * 
 * @author Karan Parmar
 * Feb 05, 2021
 * @version 1.0
 * @since 1.0
 * @see com.cactus.paperpal.factories.ExplicitWaitFactory
 * @see com.cactus.paperpal.pages.BasePage 
 */
public enum WaitStrategy {
	
	
	CLICKABLE,
	PRESENCE,
	VISIBLE,
	INVISIBLE,
	NUMBEROFWINDOWS,
	NONE

}
