package Panel_Automation;

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
	//public static String[] monthformate={"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
	public static String[] monthformate={"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
	@Test
	public static void createTicket() throws IOException, InterruptedException, AWTException
	{
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
		/*FileInputStream pageObject = new FileInputStream("D:/WorkSpace/ibv4_code/Enroll_Hotel_Test/src/Repository/Promocode.properties");
		prop = new Properties();
	    prop.load(pageObject);*/
	    driver=RateCreation.driver;
	    
		wait=new WebDriverWait(driver,40);
		driver.get(gen.getProperty("Ticket"));
		//*[@id="wid-id-ticketGrid"]/header/div/button
		//*[@id="wid-id-ticketGrid"]/header/div/button
		//wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("*[class^='btn newPackage']")));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("wid-id-ticketGrid")));
		Thread.sleep(4000);
		//driver.findElement(By.cssSelector("*[class^='btn newPackage']")).click();
		WebElement ele=driver.findElement(By.id("wid-id-ticketGrid"));
		ele.findElement(By.xpath("//*[@id='wid-id-ticketGrid']/header/div/button/span[1]/i")).click();
		//driver.findElement(By.xpath("//*[@id='wid-id-ticketGrid']/header/div/button/span[2]")).click();
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
		int inputyear=Integer.parseInt(start.substring(6,10));
		int month=Integer.parseInt(start.substring(3,5));
		int daycal=Integer.parseInt(start.substring(0,2));
		
		driver.findElement(By.xpath("//*[@id='startdate']")).click();
		//*[@id="enddate"]
		
		
		
		
		
		
		
	    //driver.findElement(By.xpath(prop.getProperty("StartDate"))).click();
	    //*[@id="ui-datepicker-div"]
		WebElement calStart=driver.findElement(By.xpath("//*[@id='ui-datepicker-div']"));
	
		//-------------------New changes in date picker------------------------19-06-2017
		String year=driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/div/span[2]")).getText();
		int year1=Integer.parseInt(year);
		
		while(inputyear!=year1)
		{
			if(inputyear>year1)
			{
				
				driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/a[2]/span/i")).click();
				String year2=driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/div/span[2]")).getText();
				int year3=Integer.parseInt(year2);
				year1=year3;
			}
		}
		if(inputyear==year1)
		{
			String month1=driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/div/span[1]")).getText();
			
			while(!month1.equalsIgnoreCase(monthformate[month-1]))
			{
				driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/a[2]/span")).click();
				month1=driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/div/span[1]")).getText();
			}
		
			
				
		
		}
		int flag1=0;
		WebElement ele4=driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/table"));
		List<WebElement> tr=ele4.findElements(By.tagName("tr"));
		for(WebElement e:tr)
		{
			if(flag1==0)
			{
			List<WebElement> td=e.findElements(By.tagName("td"));
			for(WebElement e1:td)
			{
				String day=e1.getText();
				
			
				if(String.valueOf(daycal).equalsIgnoreCase(day))
				{
					e1.click();
					flag1=1;
					break;
					
				}
			}
			}
		}
		
		
		
		
		RC.add(Calendar.DATE, 20);
		String end=DF.format(RC.getTime());
		int endyear=Integer.parseInt(end.substring(6,10));
		int endmonth=Integer.parseInt(end.substring(3,5));
		int enddaycal=Integer.parseInt(end.substring(0,2));
		driver.findElement(By.xpath("//*[@id='enddate']")).click();
		
	
		Thread.sleep(3000);
		String yearend=driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/div/span[2]")).getText();
	
		
		int yearend1=Integer.parseInt(yearend);
		int inputyearend=endyear;
		
		//int inputyearend=Integer.parseInt(gen.getProperty("EndYear"));
		while(inputyearend!=yearend1)
		{
			if(inputyearend>yearend1)
			{
		        
				driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/a[2]/span/i")).click();
				String yearend2=driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/div/span[2]")).getText();
				int yearend3=Integer.parseInt(yearend2);
				yearend1=yearend3;
			}
		}
		if(inputyearend==yearend1)
		{
			
			String month1=driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/div/span[1]")).getText();
			
		
			while(!month1.equalsIgnoreCase(monthformate[endmonth-1]))
			{
				 driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/a[2]/span")).click();
		         month1=driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/div/span[1]")).getText();
			}
		}
		WebElement ele6=driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/table"));
		List<WebElement> tr1=ele6.findElements(By.tagName("tr"));
		for(WebElement e1:tr1)
		{System.out.println("14");
			List<WebElement> td1=e1.findElements(By.tagName("td"));
			for(WebElement e2:td1)
			{
				String day=e2.getText();
				System.out.println(day);
				//if(gen.getProperty("EndDay").equalsIgnoreCase(day))
				if(String.valueOf(enddaycal).equalsIgnoreCase(day))
				{
					e1.click();
					break;
				}
			}
		}
			
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
		      //*[@id="Msg1"]
		   wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='Msg1']")));
		   
		   WebElement msg=driver.findElement(By.xpath("//*[@id='Msg1']"));
		   Thread.sleep(3000);
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



