package factories;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import constants.FrameworkConstants;
import driver.DriverManager;
import enums.WaitStrategy;



public final class ExplicitWaitFactory {
	
	/**
	 * Private constructor to avoid external instantiation
	 */
	private ExplicitWaitFactory() {}

	/**
	 * 
	 * @author Vinay
	 * Feb 05, 2021
	 * @param waitstrategy Strategy to be applied to find a webelement {@link com.cactus.paperpal.enums.WaitStrategy}
	 * @param by By locator of the webelement
	 * @return webelement Locates and return the webelement
	 */
	public static WebElement performExplicitWait(enums.WaitStrategy waitstrategy, By by) {
		WebElement element = null;
		if(waitstrategy == enums.WaitStrategy.CLICKABLE) {
			element = 	new WebDriverWait(driver.DriverManager.getDriver(), Duration.ofSeconds(FrameworkConstants.getExplicitwait()))
					.ignoring(StaleElementReferenceException.class,NoSuchElementException.class)
					.until(ExpectedConditions.elementToBeClickable(by));
		}
		else if(waitstrategy == enums.WaitStrategy.PRESENCE) {
			element =	new WebDriverWait(driver.DriverManager.getDriver(), Duration.ofSeconds(FrameworkConstants.getExplicitwait()))
					.ignoring(StaleElementReferenceException.class,NoSuchElementException.class)
					.until(ExpectedConditions.presenceOfElementLocated(by));
		}
		else if(waitstrategy == enums.WaitStrategy.VISIBLE) {
			element =new WebDriverWait(driver.DriverManager.getDriver(), Duration.ofSeconds(FrameworkConstants.getExplicitwait()))
					.ignoring(StaleElementReferenceException.class,NoSuchElementException.class)
					.until(ExpectedConditions.visibilityOfElementLocated(by));
		}
		else if(waitstrategy == enums.WaitStrategy.NONE) {
			element = driver.DriverManager.getDriver().findElement(by);
		}
		return element;
	}
	
	public static Boolean performExplicitWaitForNumberOfWindowsToBe(WaitStrategy waitstrategy, int numberofwindow) {
		Boolean element = null;
		if(waitstrategy == WaitStrategy.NUMBEROFWINDOWS) {
			element=new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(FrameworkConstants.getExplicitwait()))
					.ignoring(StaleElementReferenceException.class,NoSuchElementException.class)
					.until(ExpectedConditions.numberOfWindowsToBe(numberofwindow));
		}
		return element;
	}
	
	public static Boolean performExplicitWaitTillInvisible(WaitStrategy waitstrategy, By by, int timeout) {

		Boolean element = null;
		if(waitstrategy == WaitStrategy.INVISIBLE) {
			element = 	new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeout))
					.ignoring(StaleElementReferenceException.class,NoSuchElementException.class)
					.until(ExpectedConditions.invisibilityOfElementLocated(by));
		}
		return element;
		
	}
	
	public static Boolean performExplicitWaitForAttributeContains(WaitStrategy waitstrategy, By by,String attributename,String elementname) {
		Boolean element = null;
		if(waitstrategy == WaitStrategy.VISIBLE || waitstrategy == WaitStrategy.NONE ) {
			element = 	new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(FrameworkConstants.getExplicitwait()))
					.ignoring(StaleElementReferenceException.class,NoSuchElementException.class)
					.until(ExpectedConditions.attributeContains(by, attributename, elementname));
		}
		return element;
	}
	
	public static Alert performExplicitWaitForAlertPresent() {
		Alert alert =null;
		alert = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(FrameworkConstants.getExplicitwait())).until(ExpectedConditions.alertIsPresent());
		return alert;
	}
	
	//Waits for text contained in the element to disappear from the page, or timeout.
	public static Boolean performExplicitWaitForDisappearOfText( By by,WaitStrategy waitstrategy, String texttobepresent) {
		Boolean element = null;
//		if(waitstrategy == WaitStrategy.VISIBLE || waitstrategy == WaitStrategy.NONE) {
			element =new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(FrameworkConstants.getExplicitwait()))
					.ignoring(StaleElementReferenceException.class,NoSuchElementException.class)
					.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElementLocated(by, texttobepresent)));
//					.until(ExpectedConditions.invisibilityOfElementWithText(by, texttobepresent));
//			System.out.println(element);
//		}
		return element;
	}
	
	/*
	 * public static void performExplicitWaitFor( By by,WaitStrategy waitstrategy,
	 * String texttobepresent) { WebElement element if(waitstrategy ==
	 * WaitStrategy.VISIBLE || waitstrategy == WaitStrategy.NONE ) { element =new
	 * WebDriverWait(DriverManager.getDriver(),
	 * FrameworkConstants.getExplicitwait())
	 * .until(ExpectedConditions.not(ExpectedConditions.visibilityOf(by))); } }
	 */
	
	public static void performExplicitWaitForVisibilityOfAnElement(By by) {
		WebElement element =null;
		element=DriverManager.getDriver().findElement(by);
	    new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(600))
	    .ignoring(StaleElementReferenceException.class,NoSuchElementException.class)
	    .until(ExpectedConditions.not(ExpectedConditions.visibilityOf(element)));
	}
	
	public static Boolean performExplicitWaitForURL(String urlcontains) {
		Boolean element = null;
		element = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(FrameworkConstants.getExplicitwait()))
				.ignoring(StaleElementReferenceException.class,NoSuchElementException.class)
				.until(ExpectedConditions.urlContains(urlcontains));
		return element;
	}
	
	public static void performExplicitWaitForVisibilityOfAnElements(By by) {
		WebElement element =null;
		element=DriverManager.getDriver().findElement(by);
	    new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(600))
	    .ignoring(StaleElementReferenceException.class,NoSuchElementException.class)
	    .until(ExpectedConditions.visibilityOf(element));
	}
	
	//Waits for text contained in the element to appear from the page, or timeout.
	public static Boolean performExplicitWaitForTextToBeAppear(By by, WaitStrategy waitstrategy, String texttobepresent) {
		Boolean element = null;
		if (waitstrategy == WaitStrategy.VISIBLE || waitstrategy == WaitStrategy.NONE) {
			element = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(FrameworkConstants.getExplicitwait()))
					.ignoring(StaleElementReferenceException.class, NoSuchElementException.class)
					.until(ExpectedConditions.textToBePresentInElementLocated(by, texttobepresent));
		}
		return element;
	}
	
	public static Boolean performExplicitWaitForAttributElementValue(By by, String initialelement, String changelement) {
		Boolean element = null;
		element = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(300))
				.ignoring(StaleElementReferenceException.class,NoSuchElementException.class)
				.until(ExpectedConditions.attributeToBe(by, initialelement, changelement));
		return element;
	}
	
	/**
	 * The ExpectedConditions.stalenessOf() static method allows you to check whether an element is attached to the DOM or not. 
	 * Again, this allows you to avoid a StaleElementReferenceException.
	 * @param by
	 * @return {@link WebElement}	 * 
	 * @author karan.parmar 20 March 2024
	 */
	public static WebElement performExplicitWaitInCaseOfStaleElement(By by) {
		WebElement element =null;
		element=DriverManager.getDriver().findElement(by);
		// verifying if the WebElement is stale
		boolean isNameHtmlElementStale = ExpectedConditions.stalenessOf(element).apply(DriverManager.getDriver());
		// if the element is stale
		if (isNameHtmlElementStale) {
		    // re-retrieving the desired input HTML element
			element=DriverManager.getDriver().findElement(by);
		}
		return element;
	}

}
