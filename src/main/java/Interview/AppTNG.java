package Interview;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeSuite;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;

public class AppTNG {

	//Public variables that can be used for any below methods
	public WebDriver driver;
	public JavascriptExecutor je;
	public Actions actions;
	public WebDriverWait wait;
	
	//declare and initialize new ChromeDrive, JavascriptExecutor, Actions, and WebDriver wait
	@BeforeSuite
	public void initializeDrivers() {
		System.setProperty("webdriver.chrome.driver", "C:\\My Stuff\\Learning Selenium\\chromedriver.exe");
		driver = new ChromeDriver();
		je = (JavascriptExecutor) driver;
		wait = new WebDriverWait (driver, 10);
	}	
	
	@Test
	public void highlightImage() throws InterruptedException {
		//Google search Frank Pound & open the first link to his page
    	driver.get("https://www.google.com/");
    	driver.findElement(By.xpath("//input[@title='Search']")).sendKeys("Frank Pound" + Keys.ENTER);
    	WebElement apolloLink = driver.findElement(By.xpath("//h3[contains(text(),'Apollo - Apollo.io')]"));
    	apolloLink.click();
    	driver.get("https://www.apollo.io/people/Frank/Pound/54a3bd557468692fa215f30e");
    	
    	//Waiting for the page and element of interest to load +
    	//Try+Catch b/c sometimes the image does not load
    	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h5[contains(text(),'Marine')]")));
    	WebElement marine = driver.findElement(By.xpath("//h5[contains(text(),'Marine')]"));
		
		//Scrolling to the marine text
		actions = new Actions(driver);
    	actions.moveToElement(marine);
		actions.perform();
    	
		//Highlighting and outlining the marine picture
		try {
    		WebElement marineLogo = driver.findElement(By.xpath("//img[@src='https://zenprospect-production.s3.amazonaws.com/uploads/pictures/5e98ac2e02ec0600011548b9/picture']"));
    		je.executeScript("arguments[0].setAttribute('style','background: yellow; border: 5px solid red;')", marineLogo);
    	} catch (Exception e) {
    		System.out.println("Image not loaded");
    		je.executeScript("arguments[0].setAttribute('style','background: yellow; border: 5px solid red;')", marine);
    	}	
	}
  
	//Take screenshot & verify the screenshot exists
	@AfterTest
	public void screenshot() throws Exception {
		String imageFilePath = "C:\\My Stuff\\Learning Selenium\\Screenshots\\screenshot.png";
		takeSnapShot(driver, imageFilePath);
		
		File f = new File(imageFilePath);
		if (f.exists())
			System.out.println("Screenshot saved");
		else
			System.out.println("Screenshot not saved");
	}
	
	//Close WebDriver window
	@AfterSuite
	public void afterSuite() {
		driver.quit();
	}
	
	//Screenshot method
    public static void takeSnapShot(WebDriver webdriver,String fileWithPath) throws Exception{
    	TakesScreenshot scrShot =((TakesScreenshot)webdriver);
    	File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
    	File DestFile=new File(fileWithPath);
    	FileUtils.copyFile(SrcFile, DestFile);
    }
}
