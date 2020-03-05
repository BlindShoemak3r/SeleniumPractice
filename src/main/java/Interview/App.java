package Interview;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class App 
{
    public static void main( String[] args )
    {
    	System.setProperty("webdriver.chrome.driver", 
    	"C:\\My Stuff\\Learning Selenium\\chromedriver.exe");
    	WebDriver driver = new ChromeDriver();
    	 
    	driver.get("https://www.google.com/");
    	driver.findElement(By.cssSelector("input[title='Search']"))
    		  .sendKeys("Frank Pound" + Keys.ENTER);
    	driver.findElement(By.linkText("Frank Pound | Apollo - Apollo.io")).click();
    	driver.findElement(By.xpath("//div[@id='rso']/div[3]/div/div[4]/div/div/div/a/h3")).click();
    	
    
    	
    	
    	
    	 
    }
}

/*
google serach frank pound
go to apollo website
find marine corps icon
highlight it
*/