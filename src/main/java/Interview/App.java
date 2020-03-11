package Interview;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class App 
{
    public static void main( String[] args ) throws InterruptedException
    {
    	System.setProperty("webdriver.chrome.driver", 
    	"C:\\My Stuff\\Learning Selenium\\chromedriver.exe");
    	WebDriver driver = new ChromeDriver();
    	 
    	driver.get("https://www.google.com/");
    	driver.findElement(By.cssSelector("input[title='Search']"))
    		  .sendKeys("Frank Pound" + Keys.ENTER);
    	
    	JavascriptExecutor je = (JavascriptExecutor) driver;
    	WebElement preceeding = driver.findElement(By.xpath("//*[@id='rso']/div[3]/div/div[2]/div/div/div[1]/a/h3"));
    	WebElement apolloLink = driver.findElement(By.xpath("//*[@id='rso']/div[3]/div/div[3]/div/div/div[1]/a/h3"));
    	je.executeScript("arguments[0].scrollIntoView(true)", preceeding);
    	apolloLink.click();

    	Thread.sleep(7000);

    	WebElement invicta = driver.findElement(By.xpath("//*[@id=\"experience\"]/div[2]/div/div[5]/div/a/div/div[1]/div[2]/span"));
    	je.executeScript("arguments[0].scrollIntoView(true)", invicta);
    	WebElement marine = driver.findElement(By.xpath("//*[@id='experience']/div[2]/div/div[6]/div/a/div/div[1]/div[1]"));
    	je.executeScript("arguments[0].setAttribute('style','background: yellow; border: 2px solid red;')", marine);
    	
    }
}

/*
google serach frank pound
go to apollo website
find marine corps icon
highlight it
*/