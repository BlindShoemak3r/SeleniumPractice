package Interview;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.LinkedHashMap;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PoundSearchOrder {

	public static void main(String[] args) throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:\\My Stuff\\Learning Selenium\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		int i = 0;
		driver.get("https://www.google.com");
		driver.findElement(By.cssSelector("input[title='Search']")).sendKeys("Frank Pound" + Keys.ENTER);
		takeSnapShot(driver, "C:\\My Stuff\\Learning Selenium\\Screenshots\\PoundSearch\\pic"+i+".png");
		i++;
		String googleText = driver.findElement(By.id("result-stats")).getText();
		String googleRemoveText = googleText.replaceAll("[^0-9]+","");
		String googleNumbers = googleRemoveText.substring(0, googleRemoveText.length() - 3);
		int google = Integer.parseInt(googleNumbers);
		
		driver.get("https://www.bing.com");
		driver.findElement(By.id("sb_form_q")).sendKeys("Frank Pound" + Keys.ENTER);
		takeSnapShot(driver, "C:\\My Stuff\\Learning Selenium\\Screenshots\\PoundSearch\\pic"+i+".png");
		i++;
		String bingText = driver.findElement(By.className("sb_count")).getText();
		String bingNumbers = bingText.replaceAll("[^0-9]+","");
		int bing = Integer.parseInt(bingNumbers);
		
		driver.get("https://www.wikipedia.org");
		driver.findElement(By.name("search")).sendKeys("Frank Pound" + Keys.ENTER);
		takeSnapShot(driver, "C:\\My Stuff\\Learning Selenium\\Screenshots\\PoundSearch\\pic"+i+".png");
		String wikiText = driver.findElement(By.xpath("//div[@class='results-info']/strong[2]")).getText();
		String wikiNumbers = wikiText.replaceAll("[^0-9]+","");
		int wiki = Integer.parseInt(wikiNumbers);
		
		 HashMap<String, Integer> map = new HashMap<>(); 

		 map.put("Google", google); 
		 map.put("Bing", bing); 
		 map.put("Wikipedia", wiki);
		 
		 Set<Entry<String, Integer>> entries = map.entrySet();
		 
		 System.out.println("Unsorted List:");
		 for (String s : map.keySet()) {
			 System.out.println(s + ": " + map.get(s));
		 }
		 
		 Comparator<Entry<String, Integer>> valueComparator = new Comparator<Entry<String, Integer>>() {
			 @Override
			 public int compare (Entry<String, Integer> e1, Entry<String, Integer> e2) {
				 Integer v1 = e1.getValue();
				 Integer v2 = e2.getValue();
				 return v1.compareTo(v2);		//ascending order
			   //return v2.compareTo(v1);		//descending order
			 }
		 };
		 
		 List<Entry<String, Integer>> listOfEntries = new ArrayList<Entry<String, Integer>>(entries); 
		 Collections.sort(listOfEntries, valueComparator);
		 LinkedHashMap<String, Integer> sortedByValue = new LinkedHashMap<String, Integer>(listOfEntries.size());
		 
		 for (Entry<String, Integer> entry : listOfEntries) {
			 sortedByValue.put(entry.getKey(), entry.getValue());
		 }
		 
		 Set<Entry<String, Integer>> entrySetSortedByValue = sortedByValue.entrySet();
		 
		 System.out.println("\n" + "Sorted List:");
		 for(Entry<String, Integer> mapping : entrySetSortedByValue) { 
			 System.out.println(mapping.getKey() + ": " + mapping.getValue()); 
		 }

		driver.close();
	}
	
	public static void takeSnapShot(WebDriver webdriver,String fileWithPath) throws Exception {
    	TakesScreenshot scrShot =((TakesScreenshot)webdriver);
    	File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
    	File DestFile=new File(fileWithPath);
    	FileUtils.copyFile(SrcFile, DestFile);
    }
	

}

/*
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