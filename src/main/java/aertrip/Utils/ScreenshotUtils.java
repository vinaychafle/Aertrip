package aertrip.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import constants.FrameworkConstants;
import driver.DriverManager;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

/**
 * Utility to take base64 screenshot.
 * 
 * 
 * @author Vinay 
 * Feb 03, 2021
 * @version 1.0
 * @since 1.0
 * @see com.cactus.paperpal.reports.ExtentLogger
 */
public final class ScreenshotUtils {
	static  Logger LOGGER = LogManager.getLogger(ScreenshotUtils.class.getName());
	public static String imagename;
	/**
	 * Private constructor to avoid external instantiation
	 */
	private ScreenshotUtils() {
	}
	
	public static String getScreenshot(String testCaseName) throws IOException {

		TakesScreenshot ts = (TakesScreenshot) DriverManager.getDriver();
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
	}
	
	/**
	 * Delete files from specific location
	 * @param filelocation
	 * @param filename
	 */
	public static void deleteImageFile(String filelocation,String filename) {
		File myobj = new File(filelocation);
		File[] listofFile = myobj.listFiles();
		
		for (File deletefile : listofFile) {
			if(deletefile.exists()) {
			if (deletefile.toString().contains(filename)) {
				deletefile.delete();
				LOGGER.info(filename+" File Deleted Successfully");				
			}
		}
	}
	
}

	/**
	 * This will delete Images from specific folder
	 * @author VInay July 9, 2021
	 */
	public static void deleteImages() {
		// DELETE IMAGE FROM FOLDER
		Path imagesPath = FileSystems.getDefault().getPath(FrameworkConstants.getScreenshotpath() + File.separator + imagename);
		try {
			if(imagesPath.toFile().exists()) {
				if(!imagesPath.toString().isEmpty()) {
					Files.delete(imagesPath);
					LOGGER.info("File " + imagesPath.toAbsolutePath().toString() + " successfully removed");
				}
			}
			else {
				LOGGER.info("Could Not Found File " + imagesPath.toAbsolutePath().toString() + " To Removed");
			}
			
		} catch (NoSuchFileException x) {
			LOGGER.error("Unable to delete " + imagesPath.toAbsolutePath().toString() + " due to...."+x);
		} catch (IOException x) {
			LOGGER.error("IOE:Unable to delete " + imagesPath.toAbsolutePath().toString() + " due to...."+x);
			deleteImages();
		} catch (Exception x) {
			LOGGER.error(x);
		}
	}
	
	
	/**
	 * Captures screenshot of the current page, constructs a base64 string from the image and return to the caller.
	 * There is no temporary screenshot image generated here. If user needs separate screenshot image, they can construct
	 * a new method. It is advisable to use this method for many reasons.
	 * 
	 * @author Vinay 
	 * Feb 03, 2021
	 * @return Image in the form of Base64 String which can be appended directly to report
	 */
	public static String getBase64Image() {
		return ((TakesScreenshot)DriverManager.getDriver()).getScreenshotAs(OutputType.BASE64);
	}
	
	
	/**
	 * This capture full page screenshot
	 * @author Vinay
	 * July 09, 2021
	 * @return
	 */
	public static String getFullPageBase64Image() {
		String base64 = null;
		// take screenshot of the entire page
		imagename = "TempImage_"+getRandomNumber(3)+".png";
		Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(DriverManager.getDriver());
		try {
			File filedirectory=new File(FrameworkConstants.getScreenshotpath()+File.separator +imagename);
			 ImageIO.write(screenshot.getImage(), "PNG",filedirectory);
			 File newfile= new File(filedirectory.toString());
			 if(newfile.exists()) {
				 base64=encodeFileToBase64Binary(newfile);
				 if(base64.isEmpty() || base64==null) {
					 base64=getBase64Image();
				 }
			 }
			 else {
				 base64=getBase64Image();
			 }
		} catch (IOException e) { 
			e.printStackTrace();
		}
		catch (Exception e) { 
			e.printStackTrace();
		}
		return base64;
		
	}
	public static String getRandomNumber(int no) {
		String str=RandomStringUtils.randomNumeric(no);
		while(str.startsWith("0")) {
			str=trimFirstChar(str, "0");
		}
		return str;
	}
	public static String trimFirstChar(String str, String tChar) {
		int len=tChar.length();
		if (str.startsWith(tChar)) {
			str = str.substring(len, str.length());
		}
		return str;
	}


	/**
	 * This method is to convert File to Base64
	 * @param file
	 * @return
	 * @author Vinay
	 * July 09, 2021
	 * @throws IOException 
	 */
	 public static String encodeFileToBase64Binary(File file) throws IOException{
         byte[] bytes=null;
         String encodedimage = null;
         FileInputStream fileInputStreamReader=null;
         try {
             fileInputStreamReader = new FileInputStream(file);
             bytes = new byte[(int)file.length()];
             fileInputStreamReader.read(bytes);
             encodedimage= new String(Base64.encodeBase64(bytes), "UTF-8");
             if(encodedimage.isEmpty() || encodedimage==null) {
            	 encodedimage=getBase64Image();
             }
         } catch (FileNotFoundException e) {
             e.printStackTrace();
         } catch (IOException e) {
             e.printStackTrace();
         }catch (Exception e) {
             e.printStackTrace();
         }
         fileInputStreamReader.close();
         return encodedimage;
         
     }
	
	 	/**
	 	 * This method will capture screentshot in JPEG to desire location.
		 * @author Vinay
		 * Jan 11, 2022  
		 * @return imagepath
	 	 */
		public static String getManualScreenshot() {
			imagename = "TestImage_" + getRandomNumber(3) + ".jpeg";
			TakesScreenshot ts = (TakesScreenshot) DriverManager.getDriver();
			File source = ts.getScreenshotAs(OutputType.FILE);
			String destPath = String.valueOf(FrameworkConstants.getScreenshotpath() +File.separator+ imagename);
			File target = new File(destPath);
			try {
				FileUtils.copyFile(source, target);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return destPath;
		}
}