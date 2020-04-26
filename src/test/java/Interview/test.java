package Interview;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class test {

	
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", 
		    	"C:\\My Stuff\\Learning Selenium\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		Actions actions = new Actions(driver);
    	WebDriverWait wait= new WebDriverWait (driver, 10);
		//driver.get("https://www.apollo.io/people/Frank/Pound/54a3bd557468692fa215f30e");
    	driver.get("https://www.google.com/");
    	driver.findElement(By.xpath("//input[@title='Search']")).sendKeys("Frank Pound" + Keys.ENTER);
    	WebElement apolloLink = driver.findElement(By.xpath("//h3[contains(text(),'Apollo - Apollo.io')]"));
    	apolloLink.click();
    	
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h5[contains(text(),'Marine')]")));
    	Thread.sleep(2000);
		WebElement marine = driver.findElement(By.xpath("//h5[contains(text(),'Marine')]"));
    	
    	//Scrolling to the marine picture
    	actions.moveToElement(marine);
    	actions.perform();

	}

}
