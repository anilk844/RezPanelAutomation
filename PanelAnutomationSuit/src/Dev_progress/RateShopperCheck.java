package Dev_progress;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;









import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.gargoylesoftware.htmlunit.javascript.background.JavaScriptExecutor;

    public class RateShopperCheck
  {
	public static Properties prop;
	public static Properties gen;
	public static Properties stat;
    public static WebDriver driver;
    public static WebDriverWait wait;
    public static List<String>expectedcomp;
    
    
    @Test
	public static void RateShaopper() throws InterruptedException, FindFailed, IOException
	{
    	System.out.println("RateShopper");
	    driver=RateCreation.driver;
		System.out.println("PAckageCreation");
		FileInputStream status = new FileInputStream("C:/Users/anil.kumar/git/RezPanelAutomation/PanelAnutomationSuit/src/LiveRepository/SwitchQAandLIVE.properties");
		stat=new Properties();
		stat.load(status);
		if(stat.getProperty("Status").equalsIgnoreCase("QA"))
		{
			FileInputStream pageObjectGen = new FileInputStream("C:/Users/anil.kumar/git/RezPanelAutomation/PanelAnutomationSuit/src/Repository/Generic.properties");
			gen=new Properties();
			gen.load(pageObjectGen);
			expectedcomp=new ArrayList<String>();
			expectedcomp.add("Crowne Plaza New Delhi Okhla");
			expectedcomp.add("Eros Hotel");
			expectedcomp.add("Hilton Garden Inn");
			expectedcomp.add("Hotel SouthGate");
			expectedcomp.add("Iris Park");
		}
		else if(stat.getProperty("Status").equalsIgnoreCase("LIVE"))
		{
			FileInputStream pageObjectGen = new FileInputStream("C:/Users/anil.kumar/git/RezPanelAutomation/PanelAnutomationSuit/src/LiveRepository/Generic.properties");
			gen=new Properties();
			gen.load(pageObjectGen);
			expectedcomp=new ArrayList<String>();
			expectedcomp.add("Golden Tulip Lucknow");
			expectedcomp.add("Best Western Plus Levana");
			expectedcomp.add("Vivanta by Taj, Lucknow");
			expectedcomp.add("The Piccadily");
			expectedcomp.add("Hotel Clarks Avadh");
			expectedcomp.add("La Place Sarovar Portico");
		}
	
		FileInputStream pageObject = new FileInputStream("C:/Users/anil.kumar/git/RezPanelAutomation/PanelAnutomationSuit/src/Repository/Package_Creation.properties");
		prop = new Properties();
	    prop.load(pageObject);
	    wait=new WebDriverWait(driver,40);
		/*System.setProperty("webdriver.chrome.driver", "D://chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-extensions");
		driver=new ChromeDriver();
		
		wait=new WebDriverWait(driver,30);
		driver.manage().window().maximize();
		driver.get("https://redapptest.azurewebsites.net");
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='login-form']/fieldset/section[1]/label[2]/input")));
		
		driver.findElement(By.xpath(".//*[@id='login-form']/fieldset/section[1]/label[2]/input")).sendKeys("superuser@reznext.com");
		driver.findElement(By.xpath(".//*[@id='password']")).sendKeys("Test@321");
		driver.findElement(By.xpath(".//*[@id='login-form']/footer/div/button")).click();
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[@id='search-fld']")));
		driver.findElement(By.xpath("//*[@id='search-fld']")).sendKeys("3241");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='header']/div[2]/form/div/table/tbody/tr[2]")).click();
		try
		{
		Screen scn= new Screen();
		String img="D://sikili//Ban.PNG";
		Pattern ptr= new Pattern(img);
		Thread.sleep(4000);
		scn.click(ptr);
		}
		catch(Exception ex)
		{
			
		}*/
		dashboardpage();
		comparePage();
		rangepage();
	}
    
    
    
	public static void dashboardpage() throws InterruptedException
	{
		List<String>comp=new ArrayList<String>();
		List<String>comp1=new ArrayList<String>();
		driver.get(gen.getProperty("RateDashBoard"));
		Thread.sleep(5000);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("*[class^='col-sm-5 urcompetitors paddingzero']")));
		WebElement compitatorlist=driver.findElement(By.cssSelector("*[class^='col-sm-5 urcompetitors paddingzero']"));
		
		
		List<WebElement>ls=compitatorlist.findElements(By.cssSelector("*[class^='col-sm-6 charTruncate ng-binding']"));
		Thread.sleep(3000);
		for(WebElement e:ls)
		{
			
	        comp.add(e.getText());
			//System.out.println(e.getText());
		}
	   if(comp.equals(expectedcomp))
	   {
	 	  System.out.println("Dashbord Pass");
	 	  
	 	 
	   }
	  else
	  {
		  Assert.assertTrue(false);
		  
	  }
	  
	    JavascriptExecutor jse = (JavascriptExecutor) driver;
	    jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	    Thread.sleep(5000);
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("*[class^='dygraph-legend Lposition']")));
	    wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("*[class^='dygraph-legend Lposition']")));
	    WebElement graphComp=driver.findElement(By.cssSelector("*[class^='dygraph-legend Lposition']"));
	    List<WebElement>graphCompList=graphComp.findElements(By.tagName("span"));
	  
	    for(int i=0;i<graphCompList.size();i++)
	    {
	    	
	    	WebElement hotel=graphCompList.get(i);
	    	String value=hotel.getText();
	    	if(expectedcomp.contains(value))
	    	{
	    	comp1.add(hotel.getText());
	    	}
	    }
	    System.out.println(comp1);
	    System.out.println(expectedcomp);
	    
	    if(comp1.equals(expectedcomp))
	    {
	    	System.out.println("Dashboard Graph pass");
	    	
	    }
	    else
	    {
	    	Assert.assertTrue(false);
	    }
	  
	}
	
	
	
	public static void comparePage() throws InterruptedException
	{
		List<String>comp2=new ArrayList<String>();
		driver.get(gen.getProperty("RateCompare"));
		Thread.sleep(3000);
		JavascriptExecutor je = (JavascriptExecutor) driver;
        je.executeScript("window.scrollBy(0,400)", "");
        Thread.sleep(3000);
        
		WebElement tablemain=driver.findElement(By.xpath("//*[@id='wid-Rateglance']"));
		WebElement table=tablemain.findElement(By.xpath("//*[@id='wid-Rateglance']/div/div[2]/div/table/tbody[1]"));
		List<WebElement> tableTr=table.findElements(By.tagName("tr"));
		for(WebElement e2:tableTr)
		{
			comp2.add(e2.findElement(By.tagName("span")).getText());
		
		}
		if(comp2.equals(expectedcomp))
		{
			System.out.println("Compare Page Pass");
		}
		else
		{
			Assert.assertTrue(false);
		}
		
	}
	
	
	public static void rangepage() throws InterruptedException
	{
		
		List<String>comp3=new ArrayList<String>();
		driver.get(gen.getProperty("RateRangeView"));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("raterangechart")));
		WebElement maindiv=driver.findElement(By.id("raterangechart"));
		Thread.sleep(3000);
		List<WebElement>complist=driver.findElements(By.tagName("tspan"));
		for(WebElement e:complist)
		{
			String value=e.getText();
			if(expectedcomp.contains(value))
			{
				comp3.add(value);
			}
		}
		if(expectedcomp.equals(comp3))
		{
			System.out.println("RateRange Page Pass");
		}
		else
		{
			Assert.assertTrue(false);
		}
	
		
	}
	}

