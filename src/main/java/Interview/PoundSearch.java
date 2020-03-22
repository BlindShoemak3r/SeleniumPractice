package Interview;

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

public class PoundSearch {

	public static int i = 0;
	public static String fileParth = "C:\\My Stuff\\Learning Selenium\\Screenshots\\PoundSearch\\pic";
	public static String fileType = ".png";
	
	public static void main(String[] args) throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:\\My Stuff\\Learning Selenium\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		String googleWebpage = "https://www.google.com"; 
		driver.get(googleWebpage);
		driver.findElement(By.cssSelector("input[title='Search']")).sendKeys("Frank Pound" + Keys.ENTER);
		takeSnapShot(driver, fileParth + i + fileType);
		String googleText = driver.findElement(By.id("result-stats")).getText();
		String googleRemoveText = googleText.substring(0, googleText.length() - 16);
		int google = stringConvert(googleRemoveText);
		
		String bingWebpage = "https://www.bing.com";
		driver.get(bingWebpage);
		driver.findElement(By.id("sb_form_q")).sendKeys("Frank Pound" + Keys.ENTER);
		takeSnapShot(driver, fileParth + i + fileType);
		String bingText = driver.findElement(By.className("sb_count")).getText();
		int bing = stringConvert(bingText);
		
		String wikiWebpage = "https://www.wikipedia.org";
		driver.get(wikiWebpage);
		driver.findElement(By.name("search")).sendKeys("Frank Pound" + Keys.ENTER);
		takeSnapShot(driver, fileParth + i + fileType);
		String wikiText = driver.findElement(By.xpath("//div[@class='results-info']/strong[2]")).getText();
		int wiki = stringConvert(wikiText);
		
		HashMap<String, Integer> map = new HashMap<>(); 

		map.put(googleWebpage, google); 
		map.put(bingWebpage, bing); 
		map.put(wikiWebpage, wiki);
		 
		Map.Entry<String, Integer> maxEntry = null; //stand alone entry

		for (Map.Entry<String, Integer> entry : map.entrySet()){   //for every entry in this set of entries
		    if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) >= 0) {
		        
		    	maxEntry = entry;
		    }
		}
		String maxEntryWeb = maxEntry.getKey();
		driver.get(maxEntryWeb);
		
		takeSnapShot(driver, fileParth + i + fileType);

		driver.close();
	}
	
	public static void takeSnapShot(WebDriver webdriver,String fileWithPath) throws Exception {
    	TakesScreenshot scrShot =((TakesScreenshot)webdriver);
    	File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
    	File DestFile=new File(fileWithPath);
    	FileUtils.copyFile(SrcFile, DestFile);
    	i++;
    }
	
	public static int stringConvert(String numbers) {
		numbers = numbers.replaceAll("[^0-9]+","");
		return Integer.parseInt(numbers);
	}

}

/*
create hash map
put values in a list
get highest value
get key of that value
open the webpage of that value

remove all text, but keep commas: replaceAll("[^0-9\\,]+",""); 
 
driver.get("https://www.msn.com");
driver.findElement(By.id("header-search-input")).sendKeys("Frank Pound" + Keys.ENTER);
String yahoo = driver.findElement(By.xpath("//div[@class='compPagination']/span")).getText();
System.out.println(yahoo); 
 
 //int maxMap = (Collections.max(map.values()));
 //Entry<String, int> maxEntry = Collections.max(map.entrySet(), )
 /*map.entrySet().stream().max((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1).get().getValue();
 
 List listOfMax = map.entrySet().stream().filter(entry -> entry.getValue() == max).map(map.Entry::getKey).collect(Collectors.toList());
 System.out.println(listOfMax);
 
 
 Set set = map.entrySet();
 Iterator iterator = set.iterator();
 while(iterator.hasNext()) {
	 Map.Entry me = (Map.Entry)iterator.next();
	 System.out.print(me.getKey() + ": ");
	 System.out.println(me.getValue());
 }
 
 
 
 
 
 */