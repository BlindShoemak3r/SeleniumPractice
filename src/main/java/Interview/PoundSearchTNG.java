package Interview;

import org.testng.annotations.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class PoundSearchTNG {
  
  //Public variables that can be used for any below methods
  public WebDriver driver;
  public static int i = 0;
  public static String fileParth = "C:\\My Stuff\\Learning Selenium\\Screenshots\\PoundSearch\\pic";
  public static String fileType = ".png";
  public String googleWebpage = "https://www.google.com";
  public String bingWebpage = "https://www.bing.com";
  public String wikiWebpage = "https://www.wikipedia.org";
  public int google;
  public int bing;
  public int wiki;
	
  //Initialize and declare new ChromeDriver FIRST
  @BeforeSuite
  public void initializeWebDriver() {
	  System.setProperty("webdriver.chrome.driver", "C:\\My Stuff\\Learning Selenium\\chromedriver.exe");
	  driver = new ChromeDriver();
  }
	
  //Google search "Frank Pound" + retrieve the number of search results + remove
  //extraneous data (time it took search to complete) + convert string to integer
  @Test
  public void googleTest() throws Exception {
	  driver.get(googleWebpage);
	  driver.findElement(By.cssSelector("input[title='Search']")).sendKeys("Frank Pound" + Keys.ENTER);
	  takeSnapShot(driver, fileParth + i + fileType);
	  String googleText = driver.findElement(By.id("result-stats")).getText();
	  String googleRemoveText = googleText.substring(0, googleText.length() - 16);
	  google = stringConvert(googleRemoveText);
  }
  
  //Bing search "Frank Pound" + retrieve the number of search results + remove
  //extraneous data (time it took search to complete) + convert string to integer
  @Test 
  public void bingTest() throws Exception {
	  driver.get(bingWebpage);
	  driver.findElement(By.id("sb_form_q")).sendKeys("Frank Pound" + Keys.ENTER);
	  takeSnapShot(driver, fileParth + i + fileType);
	  String bingText = driver.findElement(By.className("sb_count")).getText();
	  bing = stringConvert(bingText);
  }
  
  //Wikipedia search "Frank Pound" + retrieve the number of search results + remove
  //extraneous data (time it took search to complete) + convert string to integer
  @Test 
  public void wikiTest() throws Exception {
	  driver.get(wikiWebpage);
	  driver.findElement(By.name("search")).sendKeys("Frank Pound" + Keys.ENTER);
	  takeSnapShot(driver, fileParth + i + fileType);
	  String wikiText = driver.findElement(By.xpath("//div[@class='results-info']/strong[2]")).getText();
	  wiki = stringConvert(wikiText);
  }
  
  //Run this test 4th, w/o the priority it will run before wikiTest
  //See below for detailed explanation
  @Test (priority=4)
  public void hashTest() throws Exception {
	    HashMap<String, Integer> map = new HashMap<>(); 

		map.put(googleWebpage, google); 
		map.put(bingWebpage, bing); 
		map.put(wikiWebpage, wiki);
		 
		Map.Entry<String, Integer> maxEntry = null; //stand alone entry

		for (Map.Entry<String, Integer> entry : map.entrySet()) {
		    if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) >= 0) {
		    	maxEntry = entry;
		    }
		}
		String maxEntryWeb = maxEntry.getKey();
		driver.get(maxEntryWeb);
		
		takeSnapShot(driver, fileParth + i + fileType);
  }
  
  //After all tests have completed, close the webdriver window
  @AfterSuite
  public void closeDriver() {
	  driver.close();
  }

  //Screenshot method
  public static void takeSnapShot(WebDriver webdriver,String fileWithPath) throws Exception {
	  TakesScreenshot scrShot =((TakesScreenshot)webdriver);
	  File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
	  File DestFile=new File(fileWithPath);
	  FileUtils.copyFile(SrcFile, DestFile);
	  i++;
  }
  
  //Regex to remove all commas from a string, but keeping the numbers
  public static int stringConvert(String numbers) {
	  numbers = numbers.replaceAll("[^0-9]+","");
	  return Integer.parseInt(numbers);
  }

}

/*

Create HashMap with String (Key), Integer (Value) pair

for loop completes for every entry in this set of entries.
First loop: compare int google to null value, since using the compareTo method
returns a positive 1, then maxEntry is the int google value.
Second loop: compare int bing value to google value, since using the compareTo method
returns a negative 1, then maxEntry is still the int google value.
Process repeats for a third time, same outcome w/ int wiki value.

*/