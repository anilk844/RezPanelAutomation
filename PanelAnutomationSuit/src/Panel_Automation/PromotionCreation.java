package Panel_Automation;

import java.awt.AWTException;


import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

public class PromotionCreation {
	public static Properties prop;
	public static Properties gen;
	public static Properties stat;
	public static WebDriver driver;
	public static WebDriverWait wait;
	@Test
	public static void start() throws InterruptedException, FindFailed, AWTException, IOException
	{
		
		
		
		driver=RateCreation.driver;
		
		
		FileInputStream status = new FileInputStream("C:/Users/anil.kumar/git/Panel&IBEAutomationSuit/PanelAnutomationSuit/src/LiveRepository/SwitchQAandLIVE.properties");
		stat=new Properties();
		stat.load(status);
		if(stat.getProperty("Status").equalsIgnoreCase("QA"))
		{
			System.out.println("1");
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
		
		//promotion.properties file gets loaded here contains all xpaths and i/p
		FileInputStream pageObject = new FileInputStream("C:/Users/anil.kumar/git/Panel&IBEAutomationSuit/PanelAnutomationSuit/src/Repository/Promotion.properties");
		prop = new Properties();
	    prop.load(pageObject);
	    wait=new WebDriverWait(driver,40);//explicit wait

		driver.navigate().to(gen.getProperty("promoUrl"));//promotion page loads here
		Thread.sleep(6000);
		driver.navigate().refresh();//just to handle some issue while loading
		Thread.sleep(15000);
	
		//wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(prop.getProperty("addnewButton"))));
		driver.findElement(By.cssSelector(prop.getProperty("addnewButton"))).click();
		
		
		//refer promocode program for comments from HERE
		WebElement promoRateCode=driver.findElement(By.xpath(prop.getProperty("RateCode")));
		Select sel1=new Select(promoRateCode);
		if(gen.getProperty("Chain").equalsIgnoreCase("1"))
		{
			sel1.selectByVisibleText(RateCreation.text);
		}
		else
		{
			sel1.selectByVisibleText(gen.getProperty("RateCodeValue"));                                                      //--changed--
		}
		
		
		
		//refer promocode program for comments till HERE
		Thread.sleep(2000);
		WebElement promoChannel= driver.findElement(By.xpath(prop.getProperty("Channel")));
		Select sel2=new Select(promoChannel);
		if(gen.getProperty("Chain").equalsIgnoreCase("1"))//when chain=1  works on new channel
		{
			sel2.selectByVisibleText(RateCreation.channelid2);                                                               //--changed--
		}
		else
		{
			sel2.selectByVisibleText(gen.getProperty("channelTypeValue"));//when chain=0  works on existing channel
		}
		
	
		WebElement promoType=driver.findElement(By.xpath(prop.getProperty("Promotion")));
		Select sel3=new Select(promoType);
		String promoName=gen.getProperty("PromotionType");//takes i/p from promotion.properties
		sel3.selectByVisibleText(promoName);
		String promocode=gen.getProperty("PromotionValue");
		driver.findElement(By.xpath(prop.getProperty("PromocodeValuePath"))).sendKeys(promocode);
		String PromoName=gen.getProperty("PromotionName");
		
		driver.findElement(By.xpath(prop.getProperty("promocodeNamePath"))).sendKeys(PromoName);
		//meal plan and room type
		int d=4;
	     for(int i=0;i<1;i++) //For loop1 STARTS HERE
	     {  
		   int m=4;//original div=4 i.e //*[@id="collapse-basic"]/div/div[4]/div[1]/div/select
		   m=m+i;	
	       if(i==1)
	 		{
	    	    driver.findElement(By.cssSelector("*[class^='fa  fa-plus cursor-pointer ng-scope']")).click();//to click on + button using class path
	 			
	 		}
	       if(i>1)
	       {
	    	   driver.findElement(By.cssSelector("*[class^='fa  fa-plus cursor-pointer ng-scope']")).click();//
	    	   
	       }

    	       System.out.println(m);
	       if(i==0)//when one room type and one meal plan is selected with out clicking on + icon
	       {
	    	 
	    	
	       String roomid="//*[@id='collapse-basic']/div/div["+m+"]/div[1]/div/select"; //m initially considered as 4 bcz div=4
		   String mealid="//*[@id='collapse-basic']/div/div["+m+"]/div[2]/div/select";
		   
		   
		   WebElement roomType=driver.findElement(By.xpath(roomid));
		   Select room=new Select(roomType);
		   if(gen.getProperty("Chain").equalsIgnoreCase("1"))//chain=1 new room type created in create rate
		   {
			   
			   room.selectByVisibleText(RateCreation.room);                                                                          //--changed--
		   }
		   else
		   {
		   String rmType=gen.getProperty("RoomTypeValue");//chain=0 new room type existing in create rate
		   room.selectByVisibleText(rmType);
		   }
           WebElement mealPlan=driver.findElement(By.xpath(mealid));
		   Select meal=new Select(mealPlan);
		   if(gen.getProperty("Chain").equalsIgnoreCase("1"))
		   {
			   meal.selectByVisibleText(RateCreation.mealplan2);//same as room for for meal plan chain =0 and 1
		   }
		   else
		   {
			
			   String mlPlan=gen.getProperty("mealPlanValuePro");
			   meal.selectByVisibleText(mlPlan);
		   }
		  
	       }
		if(i>=1)//more than one room selected by clicking on +icon
		{
			Thread.sleep(2000);
			System.out.println(m);
			System.out.println(d);
 			
			 String roomid="//*[@id='collapse-basic']/div/div["+m+"]/div[1]/div/select";
			 String mealid="//*[@id='collapse-basic']/div/div["+m+"]/div[2]/div/select";
			 WebElement roomType=driver.findElement(By.xpath(roomid));
			 Select room=new Select(roomType);
			 if(gen.getProperty("Chain").equalsIgnoreCase("1"))
			   {
				   
				   room.selectByVisibleText(RateCreation.room);                                                                          //--changed--
			   }
			   else
			   {
			   String rmType=gen.getProperty("RoomTypeValue");
			   room.selectByVisibleText(rmType);
			   }
	           WebElement mealPlan=driver.findElement(By.xpath(mealid));
			   Select meal=new Select(mealPlan);
			   if(gen.getProperty("Chain").equalsIgnoreCase("1"))
			   {
				   meal.selectByVisibleText(RateCreation.mealplan2);                                                                     //--changed--
			   }
			   else
			   {
				
				   String mlPlan=gen.getProperty("mealPlanValuePro");
				   meal.selectByVisibleText(mlPlan);
			   }
		       
			Thread.sleep(2000);
			d++;
		}
		
		
	  }
	   //For loop1 ENDS HERE
	     driver.findElement(By.xpath(prop.getProperty("ConditionsAccordian"))).click();   
	     if(promoName.equals("Early Bird Promo"))
	     {
	    	WebElement MinDays = driver.findElement(By.xpath(prop.getProperty("MinDays")));
	        MinDays.sendKeys(gen.getProperty("MinDaysValue"));
	        WebElement MaxDays = driver.findElement(By.xpath(prop.getProperty("MaxDays")));
	        MaxDays.sendKeys(gen.getProperty("MaxDaysValue"));
	     }
	     if(promoName.equals("Last Minute"))
	     {
	    
	        WebElement MaxDays = driver.findElement(By.xpath(prop.getProperty("MaxDays")));
	        MaxDays.sendKeys(gen.getProperty("MaxDaysValue"));
	     }
	        Robot robot3=new Robot();
	        
	        //booking start date
	
	        //24-01-2017(generating auto date)
	        SimpleDateFormat DF = new SimpleDateFormat("dd/MM/yyyy");
	    	Calendar BD = Calendar.getInstance();
	   
	        robot3.keyPress(KeyEvent.VK_TAB);
        	robot3.keyRelease(KeyEvent.VK_TAB);
        	String BookingStartDate = DF.format(BD.getTime());//i/p date is saved here in dd/mm/yyyy format
	        //String BookingStartDate = gen.getProperty("BookingStartDate");
        	StringSelection stringSelection2 = new StringSelection(BookingStartDate);
        	Clipboard clipboard2 = Toolkit.getDefaultToolkit().getSystemClipboard();//creating clipboard
        	clipboard2.setContents(stringSelection2, stringSelection2);//stringSelection2 is copied to clip board
        	robot3.keyPress(KeyEvent.VK_CONTROL); // to copy VK_CONTROL and VK_V press
        	robot3.keyPress(KeyEvent.VK_V); 
        	robot3.keyRelease(KeyEvent.VK_V);
        	robot3.keyRelease(KeyEvent.VK_CONTROL);// to copy VK_CONTROL and VK_V release
	        Thread.sleep(2000);
	        Thread.sleep(2000);
	        
	        
	        //booking end date
	        robot3.keyPress(KeyEvent.VK_TAB);
        	robot3.keyRelease(KeyEvent.VK_TAB);
        	BD.add(Calendar.DATE, 90);//adding 90 days to start date
        	String BookingEndDate = DF.format(BD.getTime()); //calendar date +90days
	        //String BookingEndDate = gen.getProperty("BookingEndDate");
        	StringSelection stringSelection3 = new StringSelection(BookingEndDate);
        	Clipboard clipboard3 = Toolkit.getDefaultToolkit().getSystemClipboard();
        	clipboard3.setContents(stringSelection3, stringSelection3);
        	robot3.keyPress(KeyEvent.VK_CONTROL);
        	robot3.keyPress(KeyEvent.VK_V);
        	robot3.keyRelease(KeyEvent.VK_V);
        	robot3.keyRelease(KeyEvent.VK_CONTROL);
	        Thread.sleep(3000);
	        //add blackout label
	       
	        //blackout start date when we have one black out range
	       int  m1=0;
	        for(int i=0;i<1;i++)//for only one date range of black out
	        {
	        	int val=i;
	        	 driver.findElement(By.xpath(prop.getProperty("BookingBlackOutDatesLabel"))).click();
	        	int w=m1;
	    


	        	Robot robot = new Robot();
	        	if(val==0)//when one blackout range we have
	        	{
	        		
	        	robot.keyPress(KeyEvent.VK_TAB);//navigating to blackout start date field using tab press and release
		        robot.keyRelease(KeyEvent.VK_TAB); 
		        String StartDate1 =BookingStartDate;
		        //String StartDate1 =gen.getProperty("BookingBlackOutStartDate");
		        System.out.println(StartDate1);
		        StringSelection stringSelection6 = new StringSelection(StartDate1);
		        Clipboard clipboard6 = Toolkit.getDefaultToolkit().getSystemClipboard();
		        clipboard6.setContents(stringSelection6, stringSelection6);
		        robot.keyPress(KeyEvent.VK_CONTROL);
	        	robot.keyPress(KeyEvent.VK_V);
	        	robot.keyRelease(KeyEvent.VK_V);
	        	robot.keyRelease(KeyEvent.VK_CONTROL);
	        	Thread.sleep(2000);
	        	}
	        	else
	        	{
	        	  for(int j=1;j<=i*2-1;j++) //for 2nd range black out...blackout startdate
	        	  {
	        		robot.keyPress(KeyEvent.VK_TAB);
	  	        	robot.keyRelease(KeyEvent.VK_TAB);  
	  	        	
	        	  }
	        	    String StartDate =BookingStartDate;
	        	    //String StartDate =gen.getProperty("BookingBlackOutStartDate");
	 		        StringSelection stringSelection5 = new StringSelection(StartDate);
	 		        Clipboard clipboard5 = Toolkit.getDefaultToolkit().getSystemClipboard();
	 		        clipboard5.setContents(stringSelection5, stringSelection5);
	 	            robot.keyPress(KeyEvent.VK_CONTROL);
		        	robot.keyPress(KeyEvent.VK_V);
		        	robot.keyRelease(KeyEvent.VK_V);
		        	robot.keyRelease(KeyEvent.VK_CONTROL);
		        	Thread.sleep(1000);
	        	}
	        	    robot.keyPress(KeyEvent.VK_TAB);
 	        	    robot.keyRelease(KeyEvent.VK_TAB);
 	        	  
 	        	    String EndDate = BookingStartDate;

 	        	    //String EndDate = gen.getProperty("BookingBlackOutEndDate"); //for 2nd range black out...blackout enddate
	 		        StringSelection stringSelection4 = new StringSelection(EndDate);
	 		        Clipboard clipboard4 = Toolkit.getDefaultToolkit().getSystemClipboard();
	 		        clipboard4.setContents(stringSelection4, stringSelection4);
	        	    robot.keyPress(KeyEvent.VK_CONTROL);
	        	    robot.keyPress(KeyEvent.VK_V);
	        	    robot.keyRelease(KeyEvent.VK_V);
	        	    robot.keyRelease(KeyEvent.VK_CONTROL);
	        	    m1=m1+2;
	        	
	        	

	        	
	        }
	        WebElement e5=driver.findElement(By.xpath(prop.getProperty("checkinconstraintsAccordian")));
	        ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView();", e5);
	        driver.findElement(By.xpath(prop.getProperty("checkinconstraintsAccordian"))).click();
            Robot robot4=new Robot();
	        
	        // start date
            Calendar CD = Calendar.getInstance();
            
	        robot4.keyPress(KeyEvent.VK_TAB);
        	robot4.keyRelease(KeyEvent.VK_TAB);
        	String CheckinStartDate =DF.format(CD.getTime());//----------------------------------------------------
	        //String CheckinStartDate = gen.getProperty("CheckinStartDate");
        	StringSelection stringSelection = new StringSelection(CheckinStartDate);
        	Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        	clipboard.setContents(stringSelection, stringSelection);
        	robot4.keyPress(KeyEvent.VK_CONTROL);
        	robot4.keyPress(KeyEvent.VK_V);
        	robot4.keyRelease(KeyEvent.VK_V);
        	robot4.keyRelease(KeyEvent.VK_CONTROL);
	        Thread.sleep(2000);
	       
	        //checkin end date
	        CD.add(Calendar.DATE, 90);
	        robot4.keyPress(KeyEvent.VK_TAB);
        	robot4.keyRelease(KeyEvent.VK_TAB);
        	String ChekinEndDate =DF.format(CD.getTime());
	        //String ChekinEndDate =gen.getProperty("ChekinEndDate");
        	StringSelection stringSelection5 = new StringSelection(ChekinEndDate);
        	Clipboard clipboard5 = Toolkit.getDefaultToolkit().getSystemClipboard();
        	clipboard5.setContents(stringSelection5, stringSelection5);
        	robot4.keyPress(KeyEvent.VK_CONTROL);
        	robot4.keyPress(KeyEvent.VK_V);
        	robot4.keyRelease(KeyEvent.VK_V);
        	robot4.keyRelease(KeyEvent.VK_CONTROL);
	        Thread.sleep(2000);
	        
	        int m2=0;
	        //Checkin Black Out Dates 
	        for(int i=0;i<1;i++)
	        {
	        	int val=i;
	        	 driver.findElement(By.xpath(prop.getProperty("CheckinBlackOutDatesLabel"))).click();
	        	int w=m2;
	    

                Thread.sleep(1000);
	        	Robot robot = new Robot();
	        	if(val==0)
	        	{
	        	
	        	robot.keyPress(KeyEvent.VK_TAB);
		        robot.keyRelease(KeyEvent.VK_TAB); 
		       
		        String StartDate1 =CheckinStartDate;
		        //String StartDate1 =gen.getProperty("CheckinBlackOutStartDate");
		        System.out.println(StartDate1);
		        StringSelection stringSelection6 = new StringSelection(StartDate1);
		        Clipboard clipboard6 = Toolkit.getDefaultToolkit().getSystemClipboard();
		        clipboard6.setContents(stringSelection6, stringSelection6);
		        robot.keyPress(KeyEvent.VK_CONTROL);
	        	robot.keyPress(KeyEvent.VK_V);
	        	robot.keyRelease(KeyEvent.VK_V);
	        	robot.keyRelease(KeyEvent.VK_CONTROL);
	        	Thread.sleep(2000);
	        	
	        	}
	        	else
	        	{
	        	  for(int j=1;j<=i*2-1;j++)
	        	  {
	        		robot.keyPress(KeyEvent.VK_TAB);
	  	        	robot.keyRelease(KeyEvent.VK_TAB);  
	  	        	
	        	  }
	        	    String StartDate =CheckinStartDate;
	        	    //String StartDate = gen.getProperty("CheckinBlackOutStartDate");
	 		        StringSelection stringSelection7 = new StringSelection(StartDate);
	 		        Clipboard clipboard7 = Toolkit.getDefaultToolkit().getSystemClipboard();
	 		        clipboard7.setContents(stringSelection7, stringSelection7);
	 	            robot.keyPress(KeyEvent.VK_CONTROL);
		        	robot.keyPress(KeyEvent.VK_V);
		        	robot.keyRelease(KeyEvent.VK_V);
		        	robot.keyRelease(KeyEvent.VK_CONTROL);
		        	Thread.sleep(1000);
	        	}
	        	    robot.keyPress(KeyEvent.VK_TAB);
 	        	    robot.keyRelease(KeyEvent.VK_TAB);
 	        	    String EndDate =CheckinStartDate;
 	        	   // String EndDate =gen.getProperty("CheckinBlackOutEndDate");
	 		        StringSelection stringSelection4 = new StringSelection(EndDate);
	 		        Clipboard clipboard4 = Toolkit.getDefaultToolkit().getSystemClipboard();
	 		        clipboard4.setContents(stringSelection4, stringSelection4);
	        	    robot.keyPress(KeyEvent.VK_CONTROL);
	        	    robot.keyPress(KeyEvent.VK_V);
	        	    robot.keyRelease(KeyEvent.VK_V);
	        	    robot.keyRelease(KeyEvent.VK_CONTROL);
	        	    Thread.sleep(1000);
	        	    m2=m2+2;
	        	
	        	

	        	}
	        Thread.sleep(1000);
	        //click on apply booking days constraints
	        WebElement e4=driver.findElement(By.xpath(prop.getProperty("BookingDaysConstraintsAccordian")));
	        
	        //scroller code starts here...to scroll..it scrolls till it find web element BookingDaysConstraintsAccordian
	        ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView();", e4);
	        
	      //scroller code ends here...
	        driver.findElement(By.xpath(prop.getProperty("BookingDaysConstraintsAccordian"))).click();
	        
	      
	        List<WebElement>checkallDiv=driver.findElements(By.cssSelector("*[class^='col-sm-12 smart-form margin-bottom-10 margin-left-10']"));
	        WebElement bookConstarintDays=checkallDiv.get(0);
	        WebElement checkinConstarintDays=checkallDiv.get(1);
            String day=gen.getProperty("BookInDays");
            String[] days=day.split(",");
	        for(int i=0;i<days.length;i++)
	        {
	           WebElement ele=bookConstarintDays.findElement(By.cssSelector("*[class^='checkbox ng-binding']"));// class name of check all is seperate than remaing all days class ...so its stored here
	           List<WebElement> ele1=bookConstarintDays.findElements(By.cssSelector("*[class^='checkbox ng-binding ng-scope']"));//remaining all days class name adress
	         
	        String val1=days[i];
	        System.out.println(val1);
	        String check=ele.getText();
	        System.out.println("hi"+check);
	        if(val1.equalsIgnoreCase(check))
	        {
	        	System.out.println("in");
	        	ele.click();
	        	//Thread.sleep(2000);
	        	//ele.click();
	        }
	        else
	        {
	        for(WebElement e:ele1)// considering each day 
	        {
	        	String days1=e.getText();
	        	if(val1.equalsIgnoreCase(days1))
	        	{
	        		e.click();//when i/p day is matched with list day ...click on that day check box
	        	}
	        }
	        }
	       
	      	
	     }
	        //click on apply checkin days constraints
	        WebElement e3=   driver.findElement(By.xpath(prop.getProperty("CheckInDaysConstraintsAccordian")));
	        ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView();", e3);
	        driver.findElement(By.xpath(prop.getProperty("CheckInDaysConstraintsAccordian"))).click();
	       
	        String ChekindayCon=gen.getProperty("CheckInDays");
            String[] Cday=ChekindayCon.split(",");
	        for(int i=0;i<Cday.length;i++)
	        {
	        	WebElement ele3=checkinConstarintDays.findElement(By.cssSelector("*[class^='checkbox ng-binding']"));
		         List<WebElement> ele4=checkinConstarintDays.findElements(By.cssSelector("*[class^='checkbox ng-binding ng-scope']"));
		         

	        	 String val1=Cday[i];
	 	        System.out.println(val1);
	 	        String check=ele3.getText();
	 	        System.out.println("hi"+check);
	 	        if(val1.equalsIgnoreCase(check))
	 	        {
	 	        	System.out.println("in");
	 	        	ele3.click();
	 	        	Thread.sleep(2000);
	 	        	ele3.click();
	 	        }
	 	        else
	 	        {
	 	        for(WebElement e:ele4)
	 	        {
	 	        	String days2=e.getText();
	 	        	if(val1.equalsIgnoreCase(days2))
	 	        	{
	 	        		e.click();
	 	        	}
	 	        }
	 	        }
	       
	      	
	     }
	        WebElement e1= driver.findElement(By.xpath("//*[@id='accordionConditionChild5']/div/div[1]/h6/a"));
	        ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView();", e1);
	        driver.findElement(By.xpath("//*[@id='accordionConditionChild5']/div/div[1]/h6/a")).click();
	        WebElement NoOfNgt=driver.findElement(By.xpath("//*[@id='wizard-minimumnights']/div/div[2]/div/select"));
	        Select NoNight=new Select(NoOfNgt);
	        NoNight.selectByVisibleText("1 Night");
	      
	        WebElement e2= driver.findElement(By.xpath("//*[@id='accordion-benefits']/div/div[1]/h4/a"));
	        ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView();", e2);
	        driver.findElement(By.xpath("//*[@id='accordion-benefits']/div/div[1]/h4/a")).click();
	        
	        String BookingNight="Booking/All nights";
	        String amount="Amount";
	        if(BookingNight.equals("Booking/All nights")||BookingNight.equalsIgnoreCase("For every X nights Discount on Xth night")||BookingNight.equalsIgnoreCase("For Xth night onwards"))
	        {
	        	if(amount.equals("Amount"))
	        	{
	        		Thread.sleep(2000);
	        	driver.findElement(By.xpath("//*[@id='wizard-discount']/div[2]/div[3]/span/label/input")).click();//%age  and flat toggle switch
	        	}
	        
	        	driver.findElement(By.xpath("//*[@id='wizard-discount']/div[2]/div[2]/div/input")).sendKeys("20");//giving discount amount value in flat
	        }
	        if(BookingNight.equalsIgnoreCase("For every X nights Discount on Y nights"))
	        {
	        	if(amount.equals("Amount"))
	        	{
	        	driver.findElement(By.xpath("//*[@id='wizard-discount']/div[3]/div[3]/span/label/i")).click();
	        	}
	        	driver.findElement(By.xpath("//*[@id='wizard-discount']/div[2]/div[2]/div/input")).sendKeys("20");
	            //Y th night
	        	driver.findElement(By.xpath("//*[@id='wizard-discount']/div[4]/div[5]/input")).sendKeys("1");
	        }
	        if(BookingNight.equalsIgnoreCase("For Xth night onwards"))
	        {
	        	if(amount.equals("Amount"))
	        	{
	        	driver.findElement(By.xpath("//*[@id='wizard-discount']/div[3]/div[3]/span/label/i")).click();
	        	}
	        	driver.findElement(By.xpath("//*[@id='wizard-discount']/div[2]/div[2]/div/input")).sendKeys("20");
	        	driver.findElement(By.xpath("//*[@id='wizard-discount']/div[6]/div[5]/input")).sendKeys("1");
	        }
	     //save button
	        WebElement e=driver.findElement(By.xpath("//*[@id='wid-newpromotions']/div/div[2]/div[3]/div/div/a[1]"));
	        ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView();", e);
	        driver.findElement(By.xpath("//*[@id='wid-newpromotions']/div/div[2]/div[3]/div/div/a[1]")).click();//save address
	        Thread.sleep(3000);
	        //*[@id="Msg1"]
	        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='Msg1']")));
	        WebElement msgbox1=driver.findElement(By.xpath("//*[@id='Msg1']"));
	        String msg=msgbox1.findElement(By.tagName("p")).getText();
	        Assert.assertEquals("Are you sure do you want save Promotion?", msg);
	        msgbox1.findElement(By.xpath("//*[@id='bot2-Msg1']")).click();
	        Thread.sleep(3000);
	        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='Msg1']")));
	        WebElement msgbox2=driver.findElement(By.xpath("//*[@id='Msg1']"));
	        msgbox2.findElement(By.xpath("//*[@id='bot1-Msg1']")).click();
	        Thread.sleep(10000);
			
	        //driver.close();
	        //Ok save MSG book     
	}
}
