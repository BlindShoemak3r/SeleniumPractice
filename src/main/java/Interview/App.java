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
    	System.setProperty("webdriver.chrome.driver", 
    	"C:\\My Stuff\\Learning Selenium\\chromedriver.exe");
    	WebDriver driver = new ChromeDriver();
    	JavascriptExecutor je = (JavascriptExecutor) driver;
    	Actions actions = new Actions(driver);
    	WebDriverWait wait= new WebDriverWait (driver, 10);
    	 
    	driver.get("https://www.google.com/");
    	driver.findElement(By.xpath("//input[@title='Search']")).sendKeys("Frank Pound" + Keys.ENTER);
    	WebElement apolloLink = driver.findElement(By.xpath("//h3[contains(text(),'Apollo - Apollo.io')]"));
    	apolloLink.click();
    	
    	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Marine')]")));
    	WebElement marine = driver.findElement(By.xpath("//span[contains(text(),'Marine')]"));
    	
    	actions.moveToElement(marine);
    	actions.perform();
    	
    	WebElement marineLogo = driver.findElement(By.xpath("//img[@src='https://zenprospect-production.s3.amazonaws.com/uploads/pictures/5e548fc7f6ce870001e07271/picture']"));
    	je.executeScript("arguments[0].setAttribute('style','background: yellow; border: 5px solid red;')", marineLogo);
        takeSnapShot(driver, "C:\\My Stuff\\Learning Selenium\\screenshot.png") ;     

    }
    
    public static void takeSnapShot(WebDriver webdriver,String fileWithPath) throws Exception{
    	TakesScreenshot scrShot =((TakesScreenshot)webdriver);
    	File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
    	File DestFile=new File(fileWithPath);
    	FileUtils.copyFile(SrcFile, DestFile);
    }
}

/*
google serach frank pound
go to apollo website
find marine corps icon
highlight it

//h3[contains(text(),'Apollo - Apollo.io')]
 
WebElement apolloLink = driver.findElement(By.xpath("//*[@id='rso']/div[3]/div/div[3]/div/div/div[1]/a/h3"));  
WebElement preceeding = driver.findElement(By.xpath("//*[@id='rso']/div[3]/div/div[2]/div/div/div[1]/a/h3"));  
  
//je.executeScript("arguments[0].scrollIntoView(true)", apolloLink);  
  
//WebElement invicta = driver.findElement(By.xpath("//*[@id=\"experience\"]/div[2]/div/div[5]/div/a/div/div[1]/div[2]/span"));
//je.executeScript("arguments[0].scrollIntoView(true)", invicta);  
  
//je.executeScript("arguments[0].scrollIntoView(true)", marine);  

//String marineXpath = "//span[contains(text(),'Marine')]";
//WebElement marine = driver.findElement(By.xpath(marineXpath));
//marine = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Marine')]")));  
  
find 3 search engines for a phrase
count total results
store results in a hashmap (mange strings, integers, strings & strings)
open the one with the most results  
 */
