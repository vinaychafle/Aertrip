package pageObject;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OutlookOTP extends BasePage {
	
	
	private OutlookOTP() {
		}
	
	public String getOTP() throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		
		driver.get("https://www.microsoft.com/en-us/microsoft-365/outlook/log-in");
		driver.manage().window().maximize();
		driver.findElement(By.linkText("Sign in")).click();
		Set<String> ele=driver.getWindowHandles(); 
		Iterator<String>it=ele.iterator();
		String parentid=it.next();
		String childid=it.next();
		driver.switchTo().window(childid);
		driver.findElement(By.id("i0116")).sendKeys("planittesting.com");
		driver.findElement(By.id("idSIButton9")).click();
		//driver.findElement(By.id("i0118")).sendKeys("Planit@0711");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5000));
		WebElement origin = wait.until(ExpectedConditions.elementToBeClickable(By.id("i0118")));
		origin.sendKeys("");
		driver.findElement(By.id("idSIButton9")).click();
		driver.findElement(By.id("idBtn_Back")).click();
//		driver.findElement(By.id("topSearchInput")).sendKeys("adiTaaS");
//		driver.findElement(By.xpath("(//div[@id='owaSearchBox'] //span[@data-automationid='splitbuttonprimary'])[3]")).click();
		Thread.sleep(20000);
		List<WebElement>email=driver.findElements(By.xpath("(//div[@class='S2NDX']/div/div)[1]"));
		for(WebElement mail:email)
		{
			if(mail.getText().equalsIgnoreCase("Aertrip"))
			{
				mail.click();
				break;
			}
		}
		Thread.sleep(3000);
		WebElement highlight=driver.findElement(By.xpath("//div[@id='UniqueMessageBody_1']/div/div/div/div/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[1]/td"));
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid red;');",highlight );
        try {
            Thread.sleep(1000); // Highlight for 1 second
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        js.executeScript("arguments[0].setAttribute('style','border: solid 2px white');", highlight);
    
		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(5000));
		WebElement text = wait1.until(ExpectedConditions.visibilityOf(highlight));
		String otp=text.getText();
		
		//Thread.sleep(5000);
//		List<WebElement>otp=driver.findElements(By.xpath("//div[@class='rps_1c35']//p/b"));
//		
//		FileInputStream fs=new FileInputStream(null);
		//driver.findElement(By.xpath("//div[@class='rps_1c35']//p[1]/b"));
				//String otp=driver.findElement(By.xpath("//div[@class='rps_1c35']//p[1]")).getText();
		//System.out.println(otp+"this is otp");
		//System.out.println("Element clicked");
		return otp;
	}

}
