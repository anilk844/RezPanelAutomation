package Dev_progress;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.SendKeysAction;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;



public class TicketCheck 
{
	public static Properties prop;
	public static WebDriver driver;
	public static WebDriverWait wait;
	public static Properties gen;
	public static Properties stat;
	public static String[] monthformate={"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
	
	@Test
	public static void createTicket() throws IOException, InterruptedException, AWTException
	{
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
		/*FileInputStream pageObject = new FileInputStream("D:/WorkSpace/ibv4_code/Enroll_Hotel_Test/src/Repository/Promocode.properties");
		prop = new Properties();
	    prop.load(pageObject);*/
	    driver=RateCreation.driver;
	    
		wait=new WebDriverWait(driver,40);
		driver.get(gen.getProperty("Ticket"));
		//*[@id="wid-id-ticketGrid"]/header/div/button
	
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("*[class^='btn newPackage']")));
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("*[class^='btn newPackage']")));
		driver.findElement(By.cssSelector("*[class^='btn newPackage']")).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='ddTktClassification']")));
		WebElement TicketType=driver.findElement(By.xpath("//*[@id='ddTktClassification']"));
		Select TicketSelect=new Select(TicketType);
		Thread.sleep(2000);
		TicketSelect.selectByVisibleText("Entertainment");
		driver.findElement(By.xpath("//*[@id='numberoftkts']")).clear();
		driver.findElement(By.xpath("//*[@id='numberoftkts']")).sendKeys(gen.getProperty("NoOfTickets"));
		
		   
		    SimpleDateFormat DF = new SimpleDateFormat("dd/MM/yyyy");
		    Calendar RC = Calendar.getInstance();
		    String start=DF.format(RC.getTime());
		    String year=start.substring(6,10);
		    String mon=start.substring(3,5);
		    String  month=monthformate[Integer.parseInt(start.substring(3,5))-1];
		    String day=start.substring(0,2);
		    driver.findElement(By.xpath("//*[@id='startdate']")).click();
		    date(month,year,day);
			
		    
		    RC.add(Calendar.DATE, 20);
			String end=DF.format(RC.getTime());
			String endyear=end.substring(6,10);
			String Endmon=end.substring(3,5);
			String endmonth=monthformate[Integer.parseInt(end.substring(3,5))-1];
			
			String enddaycal=end.substring(0,2);
			driver.findElement(By.xpath("//*[@id='enddate']")).click();
			date(endmonth,endyear,enddaycal);
			
			driver.findElement(By.xpath("//*[@id='pkgPriceAccordian']")).click();
			
			driver.findElement(By.xpath("//*[@id='tktprice']")).clear();
			driver.findElement(By.xpath("//*[@id='tktprice']")).sendKeys(gen.getProperty("TicketPrice"));
			driver.findElement(By.xpath("//*[@id='tkttax']")).clear();
			driver.findElement(By.xpath("//*[@id='tkttax']")).sendKeys(gen.getProperty("TicketTax"));
			driver.findElement(By.xpath("//*[@id='tktcode']")).sendKeys(gen.getProperty("TicketCode"));
			driver.findElement(By.xpath("//*[@id='tktname']")).sendKeys(gen.getProperty("TicketName"));
			
			StringSelection stringSelection = new StringSelection(gen.getProperty("TicketCode"));
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		     Robot robot =new Robot();
			 robot.keyPress(KeyEvent.VK_TAB);
			 robot.keyRelease(KeyEvent.VK_TAB);
			 robot.keyPress(KeyEvent.VK_CONTROL);
			 robot.keyPress(KeyEvent.VK_V);
			 robot.keyRelease(KeyEvent.VK_CONTROL);
			 robot.keyRelease(KeyEvent.VK_V);
			  
			 Thread.sleep(3000);
			 //click on choose file button
			 JavascriptExecutor js = ((JavascriptExecutor) driver);

		     js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
			  WebElement e1=driver.findElement(By.xpath("//*[@id='wid-id-NewTicket']/div/div[2]/div[2]/div/div/a[1]"));
		        ((JavascriptExecutor) driver).executeScript(
	                  "arguments[0].scrollIntoView();", e1);
		      
		        e1.click();
		   wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='Msg1']")));
		   WebElement msg=driver.findElement(By.xpath("//*[@id='Msg1']"));
		   msg.findElement(By.id("bot1-Msg1")).click();
		   
		
	}
	
	public static void date(String mon1,String Year1,String dayStr)
    {
        String mon=mon1;
        String year1=Year1;
        WebElement ele =driver.findElement(By.xpath("//*[@id='ui-datepicker-div']"));
        WebElement head=ele.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/div"));
        
 
 //select day
        
        Select month=new Select(driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/div/select[1]")));
        month.selectByVisibleText(mon1);
        Select year=new Select(driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/div/select[2]")));
        year.selectByVisibleText(year1);
        WebElement SelectDay=ele.findElement(By.xpath("//*[@id='ui-datepicker-div']/table/tbody"));
        List<WebElement>day1=SelectDay.findElements(By.tagName("a"));
        int daynew=Integer.parseInt(dayStr);
        String dayval=String.valueOf(daynew);
        for(WebElement e2:day1)
        {
	       System.out.println(e2.getText());
	       if(e2.getText().equals(dayval))
	       {
		      System.out.println("got");
		      e2.click();
	        }
	   }
  }
 
}



