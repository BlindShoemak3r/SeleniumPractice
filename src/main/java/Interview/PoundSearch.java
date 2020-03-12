package Interview;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PoundSearch {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "C:\\My Stuff\\Learning Selenium\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://www.google.com");
		driver.findElement(By.cssSelector("input[title='Search']")).sendKeys("Frank Pound" + Keys.ENTER);
		String googleText = driver.findElement(By.id("result-stats")).getText();
		String googleRemoveText = googleText.replaceAll("[^0-9]+","");
		String googleNumbers = googleRemoveText.substring(0, googleRemoveText.length() - 3);
		int google = Integer.parseInt(googleNumbers);
		System.out.println(google);
		
		driver.get("https://www.bing.com");
		driver.findElement(By.id("sb_form_q")).sendKeys("Frank Pound" + Keys.ENTER);
		String bingText = driver.findElement(By.className("sb_count")).getText();
		String bingNumbers = bingText.replaceAll("[^0-9]+","");
		int bing = Integer.parseInt(bingNumbers);
		System.out.println(bing);
		
		driver.get("https://www.wikipedia.org");
		driver.findElement(By.name("search")).sendKeys("Frank Pound" + Keys.ENTER);
		String wikiText = driver.findElement(By.xpath("//div[@class='results-info']/strong[2]")).getText();
		String wikiNumbers = wikiText.replaceAll("[^0-9]+","");
		int wiki = Integer.parseInt(wikiNumbers);
		System.out.println(wiki);
		
		driver.close();
	}

}
