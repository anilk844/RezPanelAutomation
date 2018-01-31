package Panel_Automation;

import java.awt.AWTException;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
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

import com.thoughtworks.selenium.webdriven.commands.Click;

public class PromocodeCreation 
{
	public static Properties prop;
	public static WebDriver driver;
	public static WebDriverWait wait;
	public static Properties gen;
	public static Properties stat;
	public static String[] monthformate={"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
	@Test
	public static void promo() throws InterruptedException, FindFailed, AWTException, IOException
	{
		
		
		FileInputStream status = new FileInputStream("C:/Users/anil.kumar/git/Panel&IBEAutomationSuit/PanelAnutomationSuit/src/LiveRepository/SwitchQAandLIVE.properties");
		stat=new Properties();
		stat.load(status);
		if(stat.getProperty("Status").equalsIgnoreCase("QA"))
		{
			FileInputStream pageObjectGen = new FileInputStream("C:/Users/anil.kumar/git/Panel&IBEAutomationSuit/PanelAnutomationSuit/src/Repository/Generic.properties");
			gen=new Properties();
			gen.load(pageObjectGen);
		}
		else if(stat.getProperty("Status").equalsIgnoreCase("LIVE"))
		{
			FileInputStream pageObjectGen = new FileInputStream("C:/Users/anil.kumar/git/Panel&IBEAutomationSuit/PanelAnutomationSuit/src/LiveRepository/Generic.properties");
			gen=new Properties();
			gen.load(pageObjectGen);
		}
		FileInputStream pageObject = new FileInputStream("C:/Users/anil.kumar/git/Panel&IBEAutomationSuit/PanelAnutomationSuit/src/Repository/Promocode.properties");
		prop = new Properties();
	    prop.load(pageObject);
	    driver=RateCreation.driver;    
		wait=new WebDriverWait(driver,40);	
		driver.get(gen.getProperty("PromocodeUrl"));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(prop.getProperty("newbutton"))));
		Thread.sleep(3000);
		driver.findElement(By.xpath(prop.getProperty("newbutton"))).click();
		
		//Rate code is stored here
		WebElement ratecodetype=driver.findElement(By.xpath(prop.getProperty("RateCodeType")));
	
		
		//rate code selection from drop down
		Select sel=new Select(ratecodetype);
		
		if(gen.getProperty("Chain").equalsIgnoreCase("1"))//if chain in generic=0 then all operation happens on existing ratecode else if its 1 it happens on created rate code
		{
			sel.selectByVisibleText(RateCreation.text);//text contains newly created ratecode name
			
		}
		else
		{
			sel.selectByVisibleText(gen.getProperty("RateCodeValue"));    //already created ratecode taken from  RateCodeValue from generic                                                         //--Changed--
		}
		
		
		Thread.sleep(2000);
		
		
		//roomtype field address  is stored here
		WebElement roomtype=driver.findElement(By.xpath(prop.getProperty("RoomType")));
		
		Select sel1=new Select(roomtype);
		if(gen.getProperty("Chain").equalsIgnoreCase("1"))
		{
			sel1.selectByVisibleText(RateCreation.room);// newly created ratecode and assigned room type when chain=1
		}
		else
		{
			
			sel1.selectByVisibleText(gen.getProperty("PromocodeRoomTypeValue"));               //when chain =0 considers    PromocodeRoomTypeValue from generic                                 //--Changed--
		}
		
		WebElement mealtype=driver.findElement(By.xpath(prop.getProperty("MealPlanType")));//same thing for meal plan
		
		Select sel2=new Select(mealtype);
		if(gen.getProperty("Chain").equalsIgnoreCase("1"))
		{
			sel2.selectByVisibleText(RateCreation.mealplan2);                                                                     //--Changed--
		}
		else
		{
			sel2.selectByVisibleText(gen.getProperty("PromocodeMealTypeValue"));
		}
		
		//date selection function starts here (refer create rate for more details)
		
		SimpleDateFormat DF = new SimpleDateFormat("dd/MM/yyyy");
		Calendar RC = Calendar.getInstance();
		String start=DF.format(RC.getTime());
		int inputyear=Integer.parseInt(start.substring(6,10));
		int month=Integer.parseInt(start.substring(3,5));
		int daycal=Integer.parseInt(start.substring(0,2));
		
		WebElement ele=driver.findElement(By.xpath("//*[@id='promoCodeContent']/div/div[2]/div[2]/div[3]/label"));
		ele.findElement(By.tagName("input")).click();
		
	
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
		
		WebElement ele1=driver.findElement(By.xpath("//*[@id='promoCodeContent']/div/div[2]/div[2]/div[4]/label"));
	
		ele1.findElement(By.tagName("input")).click();
	
		Thread.sleep(3000);
		
		String yearend=driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/div/span[2]")).getText();
	
		int yearend1=Integer.parseInt(yearend);
		
		int inputyearend=endyear;
		
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
		{
			
		    List<WebElement> td1=e1.findElements(By.tagName("td"));
			
		    for(WebElement e2:td1)
			{
				String day=e2.getText();
		
				System.out.println(day);
		
				if(String.valueOf(enddaycal).equalsIgnoreCase(day))
				{
					e1.click();
					break;
				}
			}
		}
		
		//date selection function ends here (refer create rate for more details)
        driver.findElement(By.xpath(prop.getProperty("Promocode"))).sendKeys(gen.getProperty("PromocodeValue"));
		
		driver.findElement(By.xpath(prop.getProperty("PromocodeName"))).sendKeys(gen.getProperty("PromocodeNameValue"));
		
		driver.findElement(By.xpath(prop.getProperty("NoOfUses"))).sendKeys(gen.getProperty("NoOfUsesValue"));
		
		driver.findElement(By.xpath(prop.getProperty("NoOfpplFrom"))).sendKeys(gen.getProperty("NoOfpplFromValue"));
		
		driver.findElement(By.xpath(prop.getProperty("NoOfpplTo"))).sendKeys(gen.getProperty("NoOfpplToValue"));
		
		driver.findElement(By.xpath(prop.getProperty("MinNoOfRoom"))).sendKeys(gen.getProperty("MinNoOfRoomValue"));
		
		driver.findElement(By.xpath(prop.getProperty("PromocodeDiscount"))).sendKeys(gen.getProperty("PromocodeDiscountValue"));
		
		driver.findElement(By.xpath(prop.getProperty("PerSwitch"))).click();
		
		driver.findElement(By.cssSelector("*[class^='btn btn-labeled btn-success']")).click();//save click
		
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[@id='Msg1']")));
		
		WebElement msgbox=driver.findElement(By.xpath("//*[@id='Msg1']"));
		
		msgbox.findElement(By.id("bot1-Msg1")).click();
		
		Thread.sleep(10000);
		
		
}
}