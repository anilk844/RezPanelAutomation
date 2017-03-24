package Dev_progress;

import java.util.List;
import java.io.FileInputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
 
import java.net.URL;
 
import java.util.ArrayList;
 
import java.util.HashSet;
 
import java.util.Iterator;
 
import java.util.NoSuchElementException;
 
import java.util.Properties;
import java.util.Set;
 
import java.util.concurrent.TimeUnit;
 









import org.openqa.selenium.By;
 
import org.openqa.selenium.JavascriptExecutor;
 
import org.openqa.selenium.Keys;
 
import org.openqa.selenium.WebDriver;
 
import org.openqa.selenium.WebDriver.Navigation;
 
import org.openqa.selenium.WebDriver.Options;
 
import org.openqa.selenium.WebElement;
 
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
 
import org.openqa.selenium.firefox.FirefoxProfile;
 
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.Test;

import com.google.common.base.Function;
 
import com.google.common.base.Predicate;
 
//import org.openqa.selenium.*;
 
//import org.openqa.selenium.firefox.*;
 
public class VerifyAllLinks {
  public static WebDriver driver;
  public static Properties gen;
  public static Properties stat;
  public static WebDriverWait wait;
  public static List<WebElement> findAllLinks(WebDriver driver)
 
  {
 
	
 
	 List<WebElement> elementList = driver.findElements(By.tagName("a"));
 
	  //elementList.addAll(driver.findElements(By.tagName("a")));
 
	  List<WebElement> finalList = new ArrayList<WebElement>(); ;
 
	  for (WebElement element : elementList)
 
	  {
 
		  if(element.getAttribute("href") != null)
 
		  {
 
			  finalList.add(element);
 
		  }		  
 
	  }	
 
	  return finalList;
 
  }
 
	public static String isLinkBroken(URL url) throws Exception
 
	{
 
		//url = new URL("http://yahoo.com");
 
		String response = "";
 
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
 
		try
 
		{
 
		    connection.connect();
 
		     response = connection.getResponseMessage();	        
 
		    connection.disconnect();
 
		    return response;
 
		}
 
		catch(Exception exp)
 
		{
 
			return exp.getMessage();
 
		}  				
 
	}
    @Test
	public static void start() throws Exception {
 
		// TODO Auto-generated method stub
 

		FileInputStream status = new FileInputStream("C:/Users/anil.kumar/git/RezPanelAutomation/PanelAnutomationSuit/src/LiveRepository/SwitchQAandLIVE.properties");
		stat=new Properties();
		stat.load(status);
		if(stat.getProperty("Status").equalsIgnoreCase("QA"))
		{
			FileInputStream pageObjectGen = new FileInputStream("C:/Users/anil.kumar/git/RezPanelAutomation/PanelAnutomationSuit/src/Repository/Generic.properties");
			gen=new Properties();
			gen.load(pageObjectGen);
		}
		else if(stat.getProperty("Status").equalsIgnoreCase("LIVE"))
		{
			FileInputStream pageObjectGen = new FileInputStream("C:/Users/anil.kumar/git/RezPanelAutomation/PanelAnutomationSuit/src/LiveRepository/Generic.properties");
			gen=new Properties();
			gen.load(pageObjectGen);
		}
		System.setProperty("webdriver.chrome.driver", "D://chromedriver.exe");
		driver=new ChromeDriver();
		FileInputStream pageObjectGen = new FileInputStream("C:/Users/anil.kumar/git/RezPanelAutomation/PanelAnutomationSuit/src/Repository/Generic.properties");
		gen=new Properties();
		gen.load(pageObjectGen);
		
		driver.manage().window().maximize();
		driver.get(gen.getProperty("Url"));
		wait=new WebDriverWait(driver,40);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(gen.getProperty("username"))));
		driver.findElement(By.xpath(gen.getProperty("username"))).sendKeys(gen.getProperty("UserNameValue"));
		driver.findElement(By.xpath(gen.getProperty("password"))).sendKeys(gen.getProperty("PasswordValue"));
		driver.findElement(By.xpath(gen.getProperty("Button"))).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(gen.getProperty("searchField"))));	    
 
		    List<WebElement> allImages = findAllLinks(driver);    
 
		    System.out.println("Total number of elements found " + allImages.size());
 
		    for( WebElement element : allImages){
 
		    	try
 
		    	{
 
			        System.out.println("URL: " + element.getAttribute("href")+ " returned " + isLinkBroken(new URL(element.getAttribute("href"))));
 
		    		//System.out.println("URL: " + element.getAttribute("outerhtml")+ " returned " + isLinkBroken(new URL(element.getAttribute("href"))));
 
		    	}
 
		    	catch(Exception exp)
 
		    	{
 
		    		System.out.println("At " + element.getAttribute("innerHTML") + " Exception occured -&gt; " + exp.getMessage());	    		
 
		    	}
 
		    }
 
	    }
 
	}