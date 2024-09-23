package pageObject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;

import driver.DriverManager;
import enums.WaitStrategy;
import factories.ExplicitWaitFactory;
import resources.Listeners;

public class BasePage {
	
	//private static ExtentTest test=Listeners.getTest();
	static  Logger LOGGER = LogManager.getLogger(BasePage.class.getName());
	protected BasePage() {
	
	}

	protected static void clickOn(By by, WaitStrategy waitstrategy, String elementname) {
		 ExtentTest test=Listeners.getTest();
		try {
			WebElement element = ExplicitWaitFactory.performExplicitWait(waitstrategy, by);
			element.click();
			test.pass(elementname+" Is Clicked");
		} catch (Exception exception) {
			Assert.fail("Getting error while clicking on " + elementname + " and the reason for error is "
					+ exception.getMessage());
		}

	}
	
	protected static void sendKeysOn(By by, String value, WaitStrategy waitstrategy,String elementname) {
		 ExtentTest test=Listeners.getTest();
		try {
		WebElement element =ExplicitWaitFactory.performExplicitWait(waitstrategy, by);
		element.sendKeys(value);
		test.pass(elementname+" Entered Successfully");
		}
		catch(Exception exception) {
			test.fail(elementname+" unable to enter into field");
			Assert.fail("Getting error while sending some values into "+elementname+" and the reason for error is " +exception.getMessage());
		}
	}
	
	protected static void clearTextOnInputField(By by, WaitStrategy waitstrategy,String elementname) {
		try {
		WebElement element =ExplicitWaitFactory.performExplicitWait(waitstrategy, by);
		element.clear();
		//LogStatus.pass(elementname+" Is Clear Successfully", true);
	}
		catch(Exception exception) {
			Assert.fail("Getting error while clearing text in "+elementname+" and the reason for error is " +exception.getMessage());
		}
	}
	
	protected static void clearTextOnInputFieldUsingJS(By by, WaitStrategy waitstrategy) {
		WebElement element =ExplicitWaitFactory.performExplicitWait(waitstrategy, by);
		JavascriptExecutor js = (JavascriptExecutor)DriverManager.getDriver();
		js.executeScript("arguments[0].value = '';", element);
		}

	/**
	 * Element is Present or not
	 * 
	 * @param identifier
	 * @return boolean
	 */
	public static boolean isElementPresent(By identifier) {
		int len = DriverManager.getDriver().findElements(identifier).size();
		if (len == 0) {
			return false;
		} else {
			return true;
		}
	}
	
	public static void refreshPage() {
		DriverManager.getDriver().navigate().refresh();
		//LogStatus.info("Refresh Web Browser");
	}

	
	
	/**
	 * Checks whether the needed WebElement is visible or not.
	 * 
	 * @param by
	 * @param waitstrategy
	 * @param elementname
	 * @return true or false
	 */
	protected static boolean isElementDisplayed(By by, WaitStrategy waitstrategy, String elementname) {
		boolean flag = true;
		try {
			WebElement element = ExplicitWaitFactory.performExplicitWait(waitstrategy, by);
			flag = isElementPresent(by);
			if (element.isDisplayed()) {
				System.out.println(element+"is Displayed on page");
			}
		} catch (Exception exception) {
			Assert.fail("Wait For Element To Check If It Is " + waitstrategy + " For Interaction. Tried For Checking "
					+ elementname + " But Failed Due To " + exception.getMessage());
		}
		return flag;
	}
	
	protected static void waitForElement() throws InterruptedException{
	synchronized (DriverManager.getDriver()) {
		DriverManager.getDriver().wait(1000);
	}
	}
	
	/**
	 * loads a new web page in the existing browser window
	 * @param url  It accepts String as parameter to load specific url
	 */
	protected static void openNewBrowserTab() {
		((JavascriptExecutor)DriverManager.getDriver()).executeScript("window.open()");	
	}

	protected static void getCurrentSelectionFromDropDown(By by, String text,String elementname) {
		WebElement element = DriverManager.getDriver().findElement(by);
		
		try {
			Select select = new Select(element);
			 select.selectByVisibleText(text);
			
			//LogStatus.info(selectedtext + " Is Selected Option On " + elementname);
		} catch (Exception exception) {
			Assert.fail("Getting error while getting text from " + elementname + " and the reason for error is "+ exception.getMessage());
			
		}
		
	}
	
	protected static boolean waitForPageToLoad() {
       
           
                JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
                String readyState = (String) js.executeScript("return document.readyState");
                return "complete".equals(readyState);
            
       
    }
	/**
	 * This method is to click using Javascript
	 * @author Vinay 07 September 2023
	 * @param by
	 * @param value
	 * @param waitstrategy
	 * @param elementname
	 */
	protected static void clickUsingJS(By by, WaitStrategy waitstrategy, String elementname) {
		try {
			WebElement element = ExplicitWaitFactory.performExplicitWait(waitstrategy, by);
			JavascriptExecutor jse = (JavascriptExecutor) DriverManager.getDriver();
			jse.executeScript("arguments[0].click();", element);
			//LogStatus.pass(elementname + " Click Successfully", true);
		} catch (Exception exception) {
			Assert.fail("Getting error while clicking on " + elementname + " and the reason for error is "+ exception.getMessage());
		}
	}
	
	protected  static void sliderFunction(By by,WaitStrategy waitstrategy,String elementname) {
		WebElement element =ExplicitWaitFactory.performExplicitWait(waitstrategy, by);
		Actions act=new Actions(DriverManager.getDriver());
		act.dragAndDropBy(element, 93, 0).perform();
		
		
	}
	
	protected static void slideUsingJS(By by,WaitStrategy waitstrategy,String elementname) {
		WebElement slider =ExplicitWaitFactory.performExplicitWait(waitstrategy, by);
		 // Execute JavaScript to get slider attributes
		JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
		// Get slider attributes
		
		// Fetch slider attributes and dimensions
		double sliderTrackWidth=DriverManager.getDriver().findElement(By.cssSelector(".MuiSlider-rail")).getSize().getWidth();
      //  double sliderTrackWidth = ((Number) js.executeScript("return arguments[0].offsetWidth;", slider)).doubleValue();
        double minValue = Double.parseDouble((String) js.executeScript("return arguments[0].getAttribute('min');", slider));
        double maxValue = Double.parseDouble((String) js.executeScript("return arguments[0].getAttribute('max');", slider));

        // Desired value to set on the slider
        double desiredValue = 820.0; // Replace with the exact value you want

        // Calculate pixel offset
        double range = maxValue - minValue;
        double percentage = (desiredValue - minValue) / range;
        double pixelOffset = percentage * sliderTrackWidth;

        // Log the calculated values
        System.out.printf("Slider Track Width: %.2f pixels\n", sliderTrackWidth);
        System.out.printf("Pixel Offset: %.2f pixels\n", pixelOffset);

        // Use JavaScript to simulate dragging the slider handle
        js.executeScript( 
        		"function createMouseEvent(eventType, x, y) {" +
                "    var event = document.createEvent('MouseEvents');" +
                "    event.initMouseEvent(eventType, true, true, window, 0, 0, 0, x, y, false, false, false, false, 0, null);" +
                "    return event;" +
                "}" +
                "var slider = arguments[0];" +
                "var offset = arguments[1];" +
                "var rect = slider.getBoundingClientRect();" +
                "var startX = rect.left + (slider.offsetWidth / 2);" + // Start at the center of the slider handle
                "var startY = rect.top + (slider.offsetHeight / 2);" +
                "var endX = startX + offset;" + // End position
                "var endY = startY;" +
                "var mouseDownEvent = createMouseEvent('mousedown', startX, startY);" +
                "var mouseMoveEvent = createMouseEvent('mousemove', endX, endY);" +
                "var mouseUpEvent = createMouseEvent('mouseup', endX, endY);" +
                "slider.dispatchEvent(mouseDownEvent);" +
                "slider.dispatchEvent(mouseMoveEvent);" +
                "slider.dispatchEvent(mouseUpEvent);",
            slider, pixelOffset
        );
        
       
        System.out.println("Slider moved to value: " + desiredValue);
	}
	/**
	 * To check whether Inner HTML is available or not
	 */
	public static void scrollTillElement(By by, WaitStrategy waitstrategy,String elementname) {
		try {
		WebElement element =ExplicitWaitFactory.performExplicitWait(waitstrategy, by);
		JavascriptExecutor jse = (JavascriptExecutor)DriverManager.getDriver();
		jse.executeScript("arguments[0].scrollIntoView({block: 'start', inline: 'start'});", element);
		
		}
		catch(Exception exception) {
			Assert.fail("Unable To Scroll Till "+elementname+" And The Reason For Error Is " +exception.getMessage());
		}
	}
	
	public static void moveTOElement(By by, WaitStrategy waitstrategy,String elementname) {
		try {
			WebElement element =ExplicitWaitFactory.performExplicitWait(waitstrategy, by);
			Actions obj=new Actions(DriverManager.getDriver());
			
			obj.scrollToElement(element).perform();
			
			}
			catch(Exception exception) {
				Assert.fail("Unable To Scroll Till "+elementname+" And The Reason For Error Is " +exception.getMessage());
			}
		
	}
	
	
	/**
	 * Checks whether the needed WebElement is not visible.
	 * @param by
	 * @param waitstrategy
	 * @return true or false.
	 */
	protected static boolean isElementNotDisplayed(By by, WaitStrategy waitstrategy,String elementname, int timeout) {
		boolean flag = true;
		try {
		flag =ExplicitWaitFactory.performExplicitWaitTillInvisible(waitstrategy, by, timeout);
		//LogStatus.pass(elementname+" "+"is not displayed any more."  , true);
		}
		catch (Exception exception) {
			Assert.fail("Waited for Element for "+timeout+" SEC Hence, Element is " + waitstrategy+" but "+elementname+" did not disappear due to " + exception.getMessage());
		}
		return flag;
	}

	
	/**
	 * Perform Mouse Hover on element
	 * @param by
	 * @param waitstrategy
	 * @param elementname
	 */
	
		protected static void mouseHoverOn(By by, WaitStrategy waitstrategy, String elementname) {
			try {
				WebElement element = ExplicitWaitFactory.performExplicitWait(waitstrategy, by);
				Actions action = new Actions(DriverManager.getDriver());
				action.moveToElement(element).perform();
				//LogStatus.pass(elementname, true);
			} catch (Exception exception) {
				Assert.fail("Getting error while performing the action i.e " + elementname + " and the reason for error is " + exception.getMessage());
			}
		}
	
	
	protected static String getTextOnWithoutFail(By by,WaitStrategy waitstrategy,String elementname) {
	//	try {
		WebElement element =ExplicitWaitFactory.performExplicitWait(waitstrategy, by);
		//LogStatus.info(element.getText() +" Is Available Text On "+elementname);
		return element.getText();
	//	}
//		catch(Exception exception) {
//			System.out.println("Exception for the Getting Changes in suggetion "+exception.getMessage());
//			//LogStatus.info("Exception for the Getting Changes in suggetion "+exception.getMessage());
//			return null;
//		}
		
	}
	

	/**
	 * Pauses for given seconds.
	 * 
	 * @param secs
	 */
	protected static void pause(int secs) {
		try {
			Thread.sleep(secs * 1000);
		} catch (InterruptedException interruptedException) {
			interruptedException.printStackTrace();
		}
	}

	/**
	 * Get current URL
	 * 
	 * @return URL
	 */
	public static String getCurrentUrl() {
		String currenturl = DriverManager.getDriver().getCurrentUrl();
		System.out.println(currenturl);
		return currenturl;
	}
	
	

}
