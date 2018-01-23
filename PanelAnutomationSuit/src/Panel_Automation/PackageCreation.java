package Panel_Automation;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.testng.annotations.Test;

public class PackageCreation {
	public static Properties prop;
	public static WebDriver driver;
	public static WebDriverWait wait;
	public static Properties gen;
	public static Properties stat;
	@Test
	public static void start() throws InterruptedException, FindFailed, AWTException, IOException
	{
	
		driver=RateCreation.driver;
		
		
		
		System.out.println("PAckageCreation");
		FileInputStream status = new FileInputStream("C:/Users/qa.test/git/RezPanelAutomation/PanelAnutomationSuit/src/LiveRepository/SwitchQAandLIVE.properties");
		stat=new Properties();
		stat.load(status);
		if(stat.getProperty("Status").equalsIgnoreCase("QA"))
		{
			FileInputStream pageObjectGen = new FileInputStream("C:/Users/qa.test/git/RezPanelAutomation/PanelAnutomationSuit/src/Repository/Generic.properties");
			gen=new Properties();
			gen.load(pageObjectGen);
		}
		else if(stat.getProperty("Status").equalsIgnoreCase("LIVE"))
		{
			FileInputStream pageObjectGen = new FileInputStream("C:/Users/qa.test/git/RezPanelAutomation/PanelAnutomationSuit/src/LiveRepository/Generic.properties");
			gen=new Properties();
			gen.load(pageObjectGen);
		}
	
		FileInputStream pageObject = new FileInputStream("C:/Users/qa.test/git/RezPanelAutomation/PanelAnutomationSuit/src/Repository/Package_Creation.properties");
		
	
		prop = new Properties();
	    prop.load(pageObject);
	    wait=new WebDriverWait(driver,40);
		/*System.setProperty("webdriver.chrome.driver", "D://chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-extensions");
		driver=new ChromeDriver();
		
		
		driver.manage().window().maximize();
		driver.get(gen.getProperty("Url"));
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(gen.getProperty("username"))));
		
		driver.findElement(By.xpath(gen.getProperty("username"))).sendKeys(gen.getProperty("UserNameValue"));
		driver.findElement(By.xpath(gen.getProperty("password"))).sendKeys(gen.getProperty("PasswordValue"));
		driver.findElement(By.xpath(gen.getProperty("Button"))).click();
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(gen.getProperty("searchField"))));
		driver.findElement(By.xpath(gen.getProperty("searchField"))).sendKeys(gen.getProperty("hotelcode"));
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='header']/div[2]/form/div/table/tbody/tr[2]")).click();
		/*Screen scn= new Screen();
		String img="D://sikili//Ban.PNG";
		Pattern ptr= new Pattern(img);
		Thread.sleep(4000);
		scn.click(ptr);*/

	    
		driver.get(gen.getProperty("pkgUrl"));
		//driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(prop.getProperty("newbutton"))));
		Thread.sleep(3000);
		driver.findElement(By.xpath(prop.getProperty("newbutton"))).click();
		WebElement packtype=driver.findElement(By.xpath(prop.getProperty("pkgClassification")));
		String pkg=gen.getProperty("PackageType");
		Select sel=new Select(packtype);
		sel.selectByVisibleText(pkg);
		Thread.sleep(2000);
		WebElement NoOfNight=driver.findElement(By.xpath(prop.getProperty("NoOfNights")));
		Select sel1=new Select(NoOfNight);
		String daysNight=gen.getProperty("NoOfNightsValue");
		sel1.selectByVisibleText(daysNight);
		 WebElement ratecode=driver.findElement(By.xpath(prop.getProperty("RateCode")));
		 Select sel2=new Select(ratecode);
		 if(gen.getProperty("Chain").equalsIgnoreCase("1"))
		 {
			 sel2.selectByVisibleText(gen.getProperty(RateCreation.text));
		 }
		 else
		 {
		 sel2.selectByVisibleText(gen.getProperty("RateCodeValue"));                                                           //--Changed--
		 }
		 Thread.sleep(2000);
		 WebElement roomType=driver.findElement(By.xpath(prop.getProperty("RoomType")));
		 Select sel3=new Select(roomType);
		 if(gen.getProperty("Chain").equalsIgnoreCase("1"))
		 {
			 sel3.selectByVisibleText(RateCreation.room);
		 }
		 else
		 {
			 sel3.selectByVisibleText(gen.getProperty("RoomTypeValue"));                                                      //  --Changed--
		 }
		                                                       
		 if(gen.getProperty("Chain").equalsIgnoreCase("1"))
		 {
			 driver.findElement(By.xpath(prop.getProperty("ChannelType"))).sendKeys(RateCreation.channelid2);
		 }
		 else
		 {
			 driver.findElement(By.xpath(prop.getProperty("ChannelType"))).sendKeys(gen.getProperty("channelTypeValue"));         // --Changed--
		 }
	
		 driver.findElement(By.xpath(prop.getProperty("ChannelType"))).sendKeys(Keys.TAB);
		// driver.findElement(By.xpath(prop.getProperty("ChannelType"))).sendKeys(Keys.ENTER);
		 Thread.sleep(2000);
		
		 driver.findElement(By.xpath(prop.getProperty("MealPlanBasedSetup"))).click();
		 String room;
		 if(gen.getProperty("Chain").equalsIgnoreCase("1"))
		 {
			 room=RateCreation.mealplan2;
		 }
		 else
		 {
			  room=gen.getProperty("mealPlanValuePAck");                                                                     //--Changed--
		 }
		 
		 
		 driver.findElement(By.xpath(prop.getProperty("MealPlan"))).sendKeys(room);
		 driver.findElement(By.xpath(prop.getProperty("MealPlan"))).sendKeys(Keys.TAB);
		 String occupancy =gen.getProperty("OccupancyValue");
		 Thread.sleep(2000);
		 driver.findElement(By.xpath(prop.getProperty("OccupancyBasedSetup"))).click();
		 
		 WebElement ocuupancySelect=driver.findElement(By.xpath(prop.getProperty("Occupancy")));
		 Select sel4=new Select(ocuupancySelect);
		 
		 sel4.selectByValue(occupancy);
		 //child max count
		 driver.findElement(By.xpath(prop.getProperty("ChildMAx"))).sendKeys(gen.getProperty("ChildMaxCount"));
		 
		 //additional services
		 driver.findElement(By.xpath(prop.getProperty("additionalServices"))).sendKeys(gen.getProperty("AdditionalServicesValue"));
		 driver.findElement(By.xpath(prop.getProperty("additionalServices"))).sendKeys(Keys.TAB);
		 //click on set package rate tab
		 driver.findElement(By.xpath(prop.getProperty("PkgpriceAccordian"))).click();
		 
		 //count no of occupany 
		 WebElement occp=driver.findElement(By.xpath(prop.getProperty("PriceDetails")));
		 List<WebElement> occp1=occp.findElements(By.tagName("input"));
		 int occu=Integer.parseInt(occupancy);
		 int[] rate=new int[occu];
		 int ratevalue=Integer.parseInt(gen.getProperty("SingleOccupancyRate"));
		 for(int i=0;i<rate.length;i++)
		 {
			 rate[i]=ratevalue;
			 ratevalue=ratevalue+500;
		 }
		 for(int i =0;i<occp1.size();i++)
		 {
			 String value=String.valueOf(rate[i]);
		     WebElement occp2=occp1.get(i);
		     occp2.sendKeys(value);
		 }
		 
		//TAx 
		 int tax=Integer.parseInt(gen.getProperty("TaxValue"));
		 
		 driver.findElement(By.xpath(prop.getProperty("Tax"))).sendKeys(String.valueOf(tax));
		 
		 //package code
		 String packCode=pkg+"_"+room+"_"+"_"+daysNight+"new1";
		 driver.findElement(By.xpath(prop.getProperty("PackageCode"))).sendKeys(packCode);
		 //package name 
		 driver.findElement(By.xpath("//*[@id='priceDetails']/div/div[4]/div[2]/div/input")).sendKeys(packCode);
		 Thread.sleep(1000);
		 driver.findElement(By.xpath("//*[@id='priceDetails']/div/div[4]/div[2]/div/input")).sendKeys(Keys.TAB);
		 Thread.sleep(3000);
		 //enter package description field 
		 StringSelection stringSelection = new StringSelection(packCode);
		   Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		 Robot robot =new Robot();
		 //robot.keyPress(KeyEvent.VK_TAB);
		 //robot.keyRelease(KeyEvent.VK_TAB);
		 robot.keyPress(KeyEvent.VK_CONTROL);
		 robot.keyPress(KeyEvent.VK_V);
		 robot.keyRelease(KeyEvent.VK_CONTROL);
		 robot.keyRelease(KeyEvent.VK_V);
		  
		 Thread.sleep(3000);
		 //click on choose file button
		 JavascriptExecutor js = ((JavascriptExecutor) driver);

	        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		  WebElement e1=driver.findElement(By.xpath(prop.getProperty("PackageMessage")));
	        ((JavascriptExecutor) driver).executeScript(
                  "arguments[0].scrollIntoView();", e1);
	      
	     //Image Save Code(15-11-2016)
		/* driver.findElement(By.xpath(prop.getProperty("PackageMessage"))).click();
		 Thread.sleep(2000);
		 StringSelection stringSelection1 = new StringSelection(gen.getProperty("packageImagePath"));
		   Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection1, null);
		 Robot robot1 =new Robot();
		 robot1.keyPress(KeyEvent.VK_CONTROL);
		 robot1.keyPress(KeyEvent.VK_V);
		 robot1.keyRelease(KeyEvent.VK_CONTROL);
		 robot1.keyRelease(KeyEvent.VK_V);
		 robot1.keyPress(KeyEvent.VK_ENTER);
		 robot1.keyRelease(KeyEvent.VK_ENTER);*/
		 Thread.sleep(6000);
		 
		 WebElement e2=driver.findElement(By.xpath(prop.getProperty("SaveButton")));
	        ((JavascriptExecutor) driver).executeScript(
               "arguments[0].scrollIntoView();", e2);
		 driver.findElement(By.xpath(prop.getProperty("SaveButton"))).click();
		 Thread.sleep(6000);
		 WebElement msgbox=driver.findElement(By.xpath(prop.getProperty("MessageBox")));
		 WebElement pg=msgbox.findElement(By.tagName("p"));
		 String msg=pg.getText();
		 Assert.assertEquals("Data Saved Sucessfully", msg);
		 msgbox.findElement(By.xpath(prop.getProperty("MessageSaveButton"))).click();
		 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(prop.getProperty("newbutton"))));
		 Thread.sleep(10000);
		 
		 ///html/body/p
	
		//String CreateNewRate ="False";
		//Create New Rate Code 
	
	}
	
}
