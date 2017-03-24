package pageLoadTime;

import java.util.List;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
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
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import com.google.common.base.Function;
import com.google.common.base.Predicate;
	 
	//import org.openqa.selenium.*;
	 
	//import org.openqa.selenium.firefox.*;
	 
	public class PageLoadTime {
	  public static WebDriver driver;
	
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
	 
		/*public static String isLinkBroken(URL url) throws Exception
	 
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
	 
		}*/
	 
		public static void main(String[] args) throws Exception
		{
			Logger logger = Logger.getLogger("PageLoadTime");
			// TODO Auto-generated method stub
			PropertyConfigurator.configure("D:/WorkSpace/ibv4_code/Enroll_Hotel_Test/src/Repository/log4j.properties");
			
			int i=0;
			
			System.setProperty("webdriver.chrome.driver", "D://software//chromedriver.exe");
			
			driver=new ChromeDriver();
			
			driver.manage().window().maximize();
			
			driver.get("https://red.reznext.com/login.html");
			
			WebDriverWait wait=new WebDriverWait(driver,30);
			
			Thread.sleep(6000);
			
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='login-form']/footer/div/button")));
			
			driver.findElement(By.xpath(".//*[@id='login-form']/fieldset/section[1]/label[2]/input")).sendKeys("superuser@reznext.com");
			
			driver.findElement(By.xpath(".//*[@id='password']")).sendKeys("Tiger@321");
			
			driver.findElement(By.xpath(".//*[@id='login-form']/footer/div/button")).click();
			
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='search-fld']")));
         
            //int propertycode=Generic.gettIntValue(xlpath, sheetname1,i,3);
             
            driver.findElement(By.xpath("//*[@id='search-fld']")).sendKeys("77778");
            
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        
            WebElement hotelnamelist = null; 
         //wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='header']/div[2]/form/div/table/tbody/tr[2]/td[1]")));
         try {
           hotelnamelist= driver.findElement(By.xpath(".//*[@id='header']/div[2]/form/div/table/tbody/tr[2]/td[1]"));
             } 
         
         catch (Exception e) 
         { 
           
           System.out.println("2058"+" Property does not exist, check for next property");
           
           driver.findElement(By.id("search-fld")).clear();
           
           
         }
         Thread.sleep(3000);
        
         Screen scn1=new Screen();
         
         String img1="D://sikili//Ban.PNG";
         
         Pattern pt1=new Pattern(img1);
         
         scn1.click(pt1);
         
		 List<WebElement> allImages = findAllLinks(driver);    
	 
	     System.out.println("Total number of elements found " + allImages.size());
	 
	     for( WebElement element : allImages)
	      {
			long millisStart = Calendar.getInstance().getTimeInMillis();
			 try
	            {
			    		String url1=element.getAttribute("href");
			    		if(url1 !=""&&!url1.contains("javascript"))
			    		{
				        ///System.out.println("URL: " + element.getAttribute("href")+ " returned " + isLinkBroken(new URL(element.getAttribute("href"))));
	 
			    	     System.out.println(url1);
			    	     if(!url1.equalsIgnoreCase("https://red.reznext.com/index.html#/changepassword/changepassword")&&!url1.equalsIgnoreCase("https://red.reznext.com/login.html"))
			    	     {
			    	    	 try
			    	    	 {
			    	    	
			    	    	   driver.navigate().to(url1);
			    	       
			    	           if(((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"))
			    	           {
			    	    	
			    	    	       Thread.sleep(2000);
			    	    	       long millisEnd = Calendar.getInstance().getTimeInMillis();
			    	    	       Long millisDiff=millisEnd-millisStart;
	     		    	    	   logger.info(url1+"--Time Taken to execute--"+millisDiff);
			    	     }
			    	    
			    	     i++;
			    	    	 }
			    	    	 catch(Exception e)
			    	    	 {
			    	    		 System.out.println(e);
			    	    	 }
			    	    // WebDriverWait wait1=new WebDriverWait(driver,5);
			    	     //wait1.until(ExpectedConditions.)
			    	     }
			    		}
			    	}
			    
			    	catch(Exception exp)
	 
			    	{
	 
			    		//System.out.println("At " + element.getAttribute("innerHTML") + " Exception occured -&gt; " + exp.getMessage());	    		
	 
			    	}
	 
			    }
			    System.out.println(i);
	 
		    }
	}
	 
		

