package Dev_progress;

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
		FileInputStream pageObject = new FileInputStream("C:/Users/anil.kumar/git/RezPanelAutomation/PanelAnutomationSuit/src/Repository/Promotion.properties");
		prop = new Properties();
	    prop.load(pageObject);
	    wait=new WebDriverWait(driver,40);
		/*System.setProperty("webdriver.chrome.driver", "D://chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-extensions");
		driver=new ChromeDriver(options);
		
		driver.manage().window().maximize();
		driver.get(gen.getProperty("Url"));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(gen.getProperty("Button"))));
		driver.findElement(By.xpath(gen.getProperty("username"))).sendKeys(gen.getProperty("UserNameValue"));
		driver.findElement(By.xpath(gen.getProperty("password"))).sendKeys(gen.getProperty("PasswordValue"));
		driver.findElement(By.xpath(gen.getProperty("Button"))).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(gen.getProperty("searchField"))));
		driver.findElement(By.xpath(gen.getProperty("searchField"))).sendKeys(gen.getProperty("hotelcode"));
		try
		{
		driver.findElement(By.xpath("//*[@id='header']/div[2]/form/div/table/tbody/tr[2]")).click();
		}
		catch(Exception e)
		{
			
		}
		try
		{
		Screen scn= new Screen();
		String img="D://sikili//Ban.PNG";
		Pattern ptr= new Pattern(img);
		Thread.sleep(3000);
		scn.click(ptr);
		}
		catch(Exception e)
		{
			
		}*/
		
		driver.navigate().to(gen.getProperty("promoUrl"));
		Thread.sleep(6000);
		driver.navigate().refresh();
		Thread.sleep(6000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(prop.getProperty("addnewButton"))));
		driver.findElement(By.xpath(prop.getProperty("addnewButton"))).click();
		
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
		
		Thread.sleep(2000);
		WebElement promoChannel= driver.findElement(By.xpath(prop.getProperty("Channel")));
		Select sel2=new Select(promoChannel);
		if(gen.getProperty("Chain").equalsIgnoreCase("1"))
		{
			sel2.selectByVisibleText(RateCreation.channelid2);                                                               //--changed--
		}
		else
		{
			sel2.selectByVisibleText(gen.getProperty("channelTypeValue"));
		}
		
	
		WebElement promoType=driver.findElement(By.xpath(prop.getProperty("Promotion")));
		Select sel3=new Select(promoType);
		String promoName=gen.getProperty("PromotionType");
		sel3.selectByVisibleText(promoName);
		String promocode=gen.getProperty("PromotionValue");
		driver.findElement(By.xpath(prop.getProperty("PromocodeValuePath"))).sendKeys(promocode);
		String PromoName=gen.getProperty("PromotionName");
		
		driver.findElement(By.xpath(prop.getProperty("promocodeNamePath"))).sendKeys(PromoName);
		//meal plan and room type
		int d=4;
	     for(int i=0;i<1;i++)
	     {  
		   int m=4;
		   m=m+i;	
	       if(i==1)
	 		{
	    	    driver.findElement(By.cssSelector("*[class^='fa  fa-plus cursor-pointer ng-scope']")).click();
	 			//driver.findElement(By.xpath("//*[@id='collapse-basic']/div/div[4]/div[3]/div/label")).click();
	 		}
	       if(i>1)
	       {
	    	   driver.findElement(By.cssSelector("*[class^='fa  fa-plus cursor-pointer ng-scope']")).click();
	    	   //String label1="//*[@id='collapse-basic']/div/div["+d+"]/div[3]/div/label[2]";
				//driver.findElement(By.xpath(label1)).click();
	       }

    	       System.out.println(m);
	       if(i==0)
	       {
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
			   meal.selectByVisibleText(RateCreation.mealplan2);
		   }
		   else
		   {
			
			   String mlPlan=gen.getProperty("mealPlanValuePro");
			   meal.selectByVisibleText(mlPlan);
		   }
		  
	       }
		if(i>=1)
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
        	String BookingStartDate = DF.format(BD.getTime());
	        //String BookingStartDate = gen.getProperty("BookingStartDate");
        	StringSelection stringSelection2 = new StringSelection(BookingStartDate);
        	Clipboard clipboard2 = Toolkit.getDefaultToolkit().getSystemClipboard();
        	clipboard2.setContents(stringSelection2, stringSelection2);
        	robot3.keyPress(KeyEvent.VK_CONTROL);
        	robot3.keyPress(KeyEvent.VK_V);
        	robot3.keyRelease(KeyEvent.VK_V);
        	robot3.keyRelease(KeyEvent.VK_CONTROL);
	        Thread.sleep(2000);
	        Thread.sleep(2000);
	        //booking end date
	        robot3.keyPress(KeyEvent.VK_TAB);
        	robot3.keyRelease(KeyEvent.VK_TAB);
        	BD.add(Calendar.DATE, 90);
        	String BookingEndDate = DF.format(BD.getTime());
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
	       
	        //blackout start date
	       int  m1=0;
	        for(int i=0;i<1;i++)
	        {
	        	int val=i;
	        	 driver.findElement(By.xpath(prop.getProperty("BookingBlackOutDatesLabel"))).click();
	        	int w=m1;
	    


	        	Robot robot = new Robot();
	        	if(val==0)
	        	{
	        		
	        	robot.keyPress(KeyEvent.VK_TAB);
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
	        	  for(int j=1;j<=w+1;j++)
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

 	        	    //String EndDate = gen.getProperty("BookingBlackOutEndDate");
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
	        	  for(int j=1;j<=w+1;j++)
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
	        ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView();", e4);
	        driver.findElement(By.xpath(prop.getProperty("BookingDaysConstraintsAccordian"))).click();
	        //WebElement BookingDays=driver.findElement(By.xpath(prop.getProperty("BookingDaysConstraintsLabel")));
	        //List<WebElement>BookingDays1=BookingDays.findElements(By.tagName("label"));
	        List<WebElement>checkallDiv=driver.findElements(By.cssSelector("*[class^='col-sm-12 smart-form margin-bottom-10 margin-left-10']"));
	        WebElement bookConstarintDays=checkallDiv.get(0);
	        WebElement checkinConstarintDays=checkallDiv.get(1);
            String day=gen.getProperty("BookInDays");
            String[] days=day.split(",");
	        for(int i=0;i<days.length;i++)
	        {
	         WebElement ele=bookConstarintDays.findElement(By.cssSelector("*[class^='checkbox ng-binding']"));
	         List<WebElement> ele1=bookConstarintDays.findElements(By.cssSelector("*[class^='checkbox ng-binding ng-scope']"));
	         for(WebElement e2:ele1)
	         {
	        	 String str2=e2.getText();
	        	 if(str2.equalsIgnoreCase("Mon"))
	        	 {
	        		 e2.click();
	        	 }
	         }
	         
	        String val1=days[i];
	        System.out.println(val1);
	        String check=ele.getText();
	        System.out.println("hi"+check);
	        if(val1.equalsIgnoreCase(check))
	        {
	        	System.out.println("in");
	        	ele.click();
	        	Thread.sleep(2000);
	        	ele.click();
	        }
	        else
	        {
	        for(WebElement e:ele1)
	        {
	        	String days1=e.getText();
	        	if(val1.equalsIgnoreCase(days1))
	        	{
	        		e.click();
	        	}
	        }
	        }
	        /* for(WebElement Bdays:BookingDays1)
	        { 
	        	Thread.sleep(2000);
	        	String val=Bdays.getText();
	        	System.out.println(val);
	        	if(val.equals(val1))
	        	{
	        	  WebElement bdayValue=Bdays.findElement(By.tagName("input"));
	        	  if(!bdayValue.isSelected())
	        	  {
	        		  Thread.sleep(1000);
	        		 if(val.equals("Check All"))
	        		 {
	        			 Bdays.findElement(By.tagName("i")).click();
	        			 Bdays.findElement(By.tagName("i")).click();
	        			 break;
	        		 }
	        		 else
	        		 {	
	        		
	        		 Bdays.findElement(By.tagName("i")).click();
	        		 break;
	        		 }
	        	  }
	        	}
	        	
	        } */
	      	
	     }
	        //click on apply checkin days constraints
	        WebElement e3=   driver.findElement(By.xpath(prop.getProperty("CheckInDaysConstraintsAccordian")));
	        ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView();", e3);
	        driver.findElement(By.xpath(prop.getProperty("CheckInDaysConstraintsAccordian"))).click();
	       // WebElement checkinDays=driver.findElement(By.xpath("//*[@id='collapseConChild3']/div/div[2]/div"));
	        //List<WebElement>checkinDays1=checkinDays.findElements(By.tagName("label"));
	        //String[] days1={"Check All","Mon","Tue"};
	        String ChekindayCon=gen.getProperty("CheckInDays");
            String[] Cday=ChekindayCon.split(",");
	        for(int i=0;i<Cday.length;i++)
	        {
	        	WebElement ele3=checkinConstarintDays.findElement(By.cssSelector("*[class^='checkbox ng-binding']"));
		         List<WebElement> ele4=checkinConstarintDays.findElements(By.cssSelector("*[class^='checkbox ng-binding ng-scope']"));
		         for(WebElement e2:ele4)
		         {
		        	 String str2=e2.getText();
		        	 if(str2.equalsIgnoreCase("Mon"))
		        	 {
		        		 e2.click();
		        	 }
		         }

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
	       /* for(WebElement Bdays:checkinDays1)
	        { 
	        	Thread.sleep(1000);
	        	String val=Bdays.getText();
	        	System.out.println(val);
	        	if(val.equals(val1))
	        	{
	        	  WebElement bdayValue=Bdays.findElement(By.tagName("input"));
	        	  if(!bdayValue.isSelected())
	        	  {
	        		  Thread.sleep(1000);
	        		 if(val.equals("Check All"))
	        		 {
	        			 Bdays.findElement(By.tagName("i")).click();
	        			 Bdays.findElement(By.tagName("i")).click();
	        			 break;
	        		 }
	        		 else
	        		 {	
	        		
	        		 Bdays.findElement(By.tagName("i")).click();
	        		 break;
	        		 }
	        	  }
	        	}
	        	
	        } */
	      	
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
	        	//driver.findElement(By.xpath("//*[@id='wizard-discount']/div[2]/div[3]/span/label/input")).click();
	        	}
	        	driver.findElement(By.xpath("//*[@id='wizard-discount']/div[2]/div[2]/div/input")).sendKeys("20");
	        }
	        if(BookingNight.equalsIgnoreCase("For every X nights Discount on Y nights"))
	        {
	        	if(amount.equals("Amount"))
	        	{
	        	//driver.findElement(By.xpath("//*[@id='wizard-discount']/div[3]/div[3]/span/label/i")).click();
	        	}
	        	driver.findElement(By.xpath("//*[@id='wizard-discount']/div[2]/div[2]/div/input")).sendKeys("20");
	            //Y th night
	        	driver.findElement(By.xpath("//*[@id='wizard-discount']/div[4]/div[5]/input")).sendKeys("1");
	        }
	        if(BookingNight.equalsIgnoreCase("For Xth night onwards"))
	        {
	        	if(amount.equals("Amount"))
	        	{
	        	//driver.findElement(By.xpath("//*[@id='wizard-discount']/div[3]/div[3]/span/label/i")).click();
	        	}
	        	driver.findElement(By.xpath("//*[@id='wizard-discount']/div[2]/div[2]/div/input")).sendKeys("20");
	        	driver.findElement(By.xpath("//*[@id='wizard-discount']/div[6]/div[5]/input")).sendKeys("1");
	        }
	     //save button
	        WebElement e=driver.findElement(By.xpath("//*[@id='wid-newpromotions']/div/div[2]/div[3]/div/div/a[1]"));
	        ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView();", e);
	        driver.findElement(By.xpath("//*[@id='wid-newpromotions']/div/div[2]/div[3]/div/div/a[1]")).click();
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
