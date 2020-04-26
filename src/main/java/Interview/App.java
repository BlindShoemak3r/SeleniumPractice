package Interview;

import org.apache.commons.io.FileUtils;

import java.io.File;

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

public class App 
{
    public static void main( String[] args ) throws Exception {
    	//declare and initialize new ChromeDrive, JavascriptExecutor, Actions, and WebDriver wait
    	System.setProperty("webdriver.chrome.driver", 
    	"C:\\My Stuff\\Learning Selenium\\chromedriver.exe");
    	WebDriver driver = new ChromeDriver();
    	JavascriptExecutor je = (JavascriptExecutor) driver;
    	Actions actions = new Actions(driver);
    	WebDriverWait wait= new WebDriverWait (driver, 10);
    	 
    	//Google search Frank Pound & open the first link to his page
    	driver.get("https://www.google.com/");
    	driver.findElement(By.xpath("//input[@title='Search']")).sendKeys("Frank Pound" + Keys.ENTER);
    	WebElement apolloLink = driver.findElement(By.xpath("//h3[contains(text(),'Apollo - Apollo.io')]"));
    	apolloLink.click();
    	
    	//Waiting for the page and element of interest to load
    	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h5[contains(text(),'Marine')]")));
    	WebElement marine = driver.findElement(By.xpath("//h5[contains(text(),'Marine')]"));
    	
    	//Scrolling to the marine picture
    	actions.moveToElement(marine);
    	actions.perform();
    	
    	//Highlighting and outlining either the marine picture or text
    	String imageFilePath = "C:\\My Stuff\\Learning Selenium\\Screenshots\\screenshot.png";
    	try {
    		WebElement marineLogo = driver.findElement(By.xpath("//img[@src='https://zenprospect-production.s3.amazonaws.com/uploads/pictures/5e98ac2e02ec0600011548b9/picture']"));
    		je.executeScript("arguments[0].setAttribute('style','background: yellow; border: 5px solid red;')", marineLogo);
    		takeSnapShot(driver, imageFilePath);
    	} catch(Exception e) {
    		je.executeScript("arguments[0].setAttribute('style','background: yellow; border: 5px solid red;')", marine);
    		takeSnapShot(driver, imageFilePath);
    	}
    	File f = new File(imageFilePath);
    	if (f.exists())
    		System.out.println("Screenshot saved");
    	else
    		System.out.println("Screenshot not saved");
    	
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