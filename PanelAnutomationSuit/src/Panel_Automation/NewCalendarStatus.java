package Panel_Automation;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Driver;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.gargoylesoftware.htmlunit.WebWindow;
import com.gargoylesoftware.htmlunit.javascript.background.JavaScriptExecutor;
public class NewCalendarStatus {
public static WebDriver driver;
public static WebDriverWait wait;
public static Properties prop;
public static Properties gen;
public static Properties stat;

public static FileInputStream pageObjectGen;
public static String value;
public static String year;
public static String day;
public static String month;
public static String mon;
public static String Endmon;
public static String endyear;
public static String endmonth;
public static String enddaycal;
public static String[] monthformate={"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
public static boolean flag1=true;
public static String QA;
public static String dateValue;
public static int check1=0;
public static int check2=0;
public static int check3=0;
public static boolean drivercheck=true;

public static void date()
{
	
}


/*public static void beforesetup() throws InterruptedException, FindFailed, AWTException, IOException
{
	   /*System.out.println("anil");
	    SimpleDateFormat DF = new SimpleDateFormat("dd/MM/yyyy");
	    Calendar RC = Calendar.getInstance();
	    String start=DF.format(RC.getTime());
	    year=start.substring(6,10);
	    mon=start.substring(3,5);
	    month=monthformate[Integer.parseInt(start.substring(3,5))-1];
	    day=start.substring(0,2);
	    
	    
	    RC.add(Calendar.DATE, 20);
		String end=DF.format(RC.getTime());
	    endyear=end.substring(6,10);
	    Endmon=end.substring(3,5);
		endmonth=monthformate[Integer.parseInt(end.substring(3,5))-1];
		enddaycal=end.substring(0,2);
	    
        boolean ratebool=false;
 		/*status = new FileInputStream("D:/WorkSpace/ibv4_code/Enroll_Hotel_Test/src/LiveRepository/SwitchQAandLIVE.properties");
 		stat=new Properties();
 		stat.load(status);
 		if(stat.getProperty("Status").equalsIgnoreCase("QA"))
 		{
 			value="getDataQA";
 			FileInputStream pageObjectGen = new FileInputStream("D:/WorkSpace/ibv4_code/Enroll_Hotel_Test/src/Repository/Generic.properties");
 			gen=new Properties();
 			gen.load(pageObjectGen);
 		}
 		else if(stat.getProperty("Status").equalsIgnoreCase("LIVE"))
 		{
 			value="getDataLIVE";
 			pageObjectGen = new FileInputStream("D:/WorkSpace/ibv4_code/Enroll_Hotel_Test/src/LiveRepository/Generic.properties");
 			gen=new Properties();
 			gen.load(pageObjectGen);
 		}
 		wait=new WebDriverWait(driver,40);
 		//FileInputStream pageObject = new FileInputStream("D:/WorkSpace/ibv4_code/Enroll_Hotel_Test/src/Repository/Promocode.properties");
 		//prop = new Properties();
 	    //prop.load(pageObject);
        
 		/*System.setProperty("webdriver.chrome.driver", "D://chromedriver.exe");
 		ChromeOptions options = new ChromeOptions();
 		options.addArguments("--disable-extensions");
 		driver=new ChromeDriver(options);
 	
 		
 		driver.manage().window().maximize();
 		driver.get(gen.getProperty("Url"));
		
		Thread.sleep(10000);
		driver.findElement(By.xpath(".//*[@id='login-form']/fieldset/section[1]/label[2]/input")).sendKeys(gen.getProperty("UserNameValue"));
		driver.findElement(By.xpath(".//*[@id='password']")).sendKeys(gen.getProperty("PasswordValue"));
		driver.findElement(By.xpath(".//*[@id='login-form']/footer/div/button")).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(gen.getProperty("searchField"))));
		
		driver.findElement(By.xpath("//*[@id='search-fld']")).sendKeys(gen.getProperty("hotelcode"));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='header']/div[2]/form/div/table/tbody/tr[2]")));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='header']/div[2]/form/div/table/tbody/tr[2]")));
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
		Thread.sleep(2000);
		scn.click(ptr);
		}
		catch(Exception e)
		{
			
		}*/
 	    
//}






@Test(dataProvider="getData")
public static void  selectcalendar(String value,String status,String ratecodetype,String rateCodeVAlue,String RoomType,String Channelid,String mdate,String mMonth,String StartDate ,String EndDate,String Type,String DayType) throws InterruptedException, IOException
{
	
	 
		if(check1==1)
 	   {
 		  
 		    flag1=true;
 		  
 	   }
		else if(check2==1)
		{
			
		    flag1=true;
		   
		}
		else if(check3==1)
		{
		   
		   flag1=true;
		  
		}	
		check1=0;
		 check2=0;
		 check3=0;

    if(drivercheck)
    {
    	driver=RateCreation.driver;
    	drivercheck=false;
    }
	 
	if(flag1)
	{
		System.out.println("inside");
	
	    gen=RateCreation.gen;
	    wait=RateCreation.wait;
	    
	   driver.navigate().to(gen.getProperty("NewCalendarURl"));
	   flag1=false;
	   //} 06-03-20017 Rate code,roomtype channels will be selected only for first input and making flag true ,so from second input onwards these condition will not be checked.
	   System.out.println(mdate);
	   System.out.println(mMonth);
	   System.out.println(StartDate);
	   System.out.println(EndDate);
	  
	  
		//Thread.sleep(1000);
		//select rate type
		String RateType=ratecodetype;
		//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='wid-id-Calc']/div/div[2]/div/div[1]/div[1]/div/select")));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='wid-id-Calc']/div/div[2]/div/div[1]/div[1]/div/select")));
		WebElement rateType=driver.findElement(By.xpath("//*[@id='wid-id-Calc']/div/div[2]/div/div[1]/div[1]/div/select"));
		Select selectRateType=new Select(rateType);
		selectRateType.selectByVisibleText(RateType);
		//Thread.sleep(1000);
		String RateCode=rateCodeVAlue;
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='wid-id-Calc']/div/div[2]/div/div[1]/div[2]/div/select")));
		WebElement rateCode=driver.findElement(By.xpath("//*[@id='wid-id-Calc']/div/div[2]/div/div[1]/div[2]/div/select"));
		Select selectRateCode=new Select(rateCode);
		selectRateCode.selectByVisibleText(RateCode);
		//Thread.sleep(1000);
		String room=RoomType;
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='roomtyp']")));
		WebElement roomType=driver.findElement(By.xpath("//*[@id='roomtyp']"));
		Select selectRoomType=new Select(roomType);
		selectRoomType.selectByVisibleText(room);
		Thread.sleep(2000);
		//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='wid-id-Calc']/div/div[2]/div/div[1]/div[4]/div/marquee/ul")));
		WebElement channel=driver.findElement(By.xpath("//*[@id='wid-id-Calc']/div/div[2]/div/div[1]/div[4]/div/marquee/ul"));
		List<WebElement>channelid=channel.findElements(By.tagName("a"));
		String channelno=Channelid;
		//select channel
		WebDriverWait wait=new WebDriverWait(driver,20);
		for(WebElement channel1:channelid)
		{
			
			String cha=channel1.getAttribute("id");
			By cha1=By.id(cha);
			wait.until(ExpectedConditions.presenceOfElementLocated(cha1));
		  if(channelno.equals(cha))
		  {
			  channel1.findElement(cha1).click();
		  }
			
			
		}
    }
	
		Thread.sleep(1000);
		//select calendar
		JavascriptExecutor js = ((JavascriptExecutor) driver);

		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		String date1=mdate;
		
		System.out.println("date"+date1);
		String month=mMonth;
		
		System.out.println("month"+month);
		WebElement date=driver.findElement(By.xpath("//*[@id='ratecalendar']"));
		List<WebElement> SelectDate=date.findElements(By.tagName("div"));
		//*[@id="date-20"]/div/div[1]/div
		dateValue="//*[@id='date-"+date1+month+"']/div";
		
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dateValue)));
		
		
	    System.out.println(dateValue);
		Actions action =new Actions(driver);
		Thread.sleep(2000);
		 //robot=new Robot();
		//robot.keyPress(KeyEvent.VK_CONTROL);
		  List<String>ARI=new ArrayList<String>();
		  ARI.add("Change Rate");
		  ARI.add("Change Inventory");
		  ARI.add("Close Rooms");
		  int flag=0;
		  //for(String ARIValue :ARI)
		  //{
			if(flag==0)
        	{
			Thread.sleep(3000);
			System.out.println("Click");
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dateValue)));
			action.moveToElement(driver.findElement(By.xpath(dateValue))).click().build().perform();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dateValue)));
		    //action.click(driver.findElement(By.xpath(dateValue))).build().perform();
		    action.contextClick(driver.findElement(By.xpath(dateValue))).build().perform();
		    //xpath of change rate
		    String rate1="//*[@id='menu-"+date1+month+"']";
            WebElement cell=driver.findElement(By.xpath(rate1));
            List<WebElement>cell1=cell.findElements(By.tagName("a"));
            String updateName=status;
            
            for(WebElement cell2:cell1)
            {
        	
            	 
        	    String cellname=cell2.getText();
        	   
        	    System.out.println(cellname);
        	
        	     if(cellname.equalsIgnoreCase(updateName))
        	     {
        		    if(updateName.equalsIgnoreCase("Change Rate"))
        		    {
        		    
        		      Thread.sleep(2000);
        		      System.out.println(cell2);
        		      cell2.click();
                      updateRate(StartDate,EndDate,value,Type,DayType);
                      wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dateValue)));
                      flag=1;
                      break;
        		     }
        		    else
        		  if(updateName.equalsIgnoreCase("Change Inventory"))
        		  {
        			Thread.sleep(2000);
            		System.out.println("change inv");
            		cell2.click();
            		updateInv(StartDate,EndDate,value,Type,DayType);
            		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dateValue)));
            		flag=1;
            		break;
        		 }
        		  else
        		 if(updateName.equalsIgnoreCase("Open/Close Rooms"))
        		 {
        			Thread.sleep(2000);
            		System.out.println("change inv");
            		cell2.click();
            		OpenOrClose(StartDate,EndDate,value,Type,DayType);
            		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dateValue)));
            		flag=1;
            		break;
        		}
        		
        	}
        	}
        	
         }
		
}
   



@DataProvider
public String[][] getData()
{
	    //Change Rate
	    //Change Inventory
	    //Open/Close Rooms
	   
	  
	    SimpleDateFormat DF = new SimpleDateFormat("dd/MM/yyyy");
	    Calendar RC = Calendar.getInstance();
	    RC.add(Calendar.DATE, 1);
	    String start=DF.format(RC.getTime());
	    year=start.substring(6,10);
	    mon=start.substring(3,5);
	    month=monthformate[Integer.parseInt(start.substring(3,5))-1];
	    day=start.substring(0,2);
	    
	   
	    RC.add(Calendar.DATE, 20);
		String end=DF.format(RC.getTime());
	    endyear=end.substring(6,10);
	    Endmon=end.substring(3,5);
		endmonth=monthformate[Integer.parseInt(end.substring(3,5))-1];
		
		enddaycal=end.substring(0,2);
	if((RateCreation.QALIVE).equalsIgnoreCase("QA"))
	{
         String[][] data ={
		{"5500","Change Rate","B2B","B2B00001","Deluxe","50",day,mon,day+"-"+month+"-"+year,day+"-"+month+"-"+year,"Single","singleday"},
		{"5000","Change Rate","B2B","B2B00001","Deluxe","50",day,mon,day+"-"+month+"-"+year,enddaycal+"-"+endmonth+"-"+endyear,"Multi","multipleday"},
		{"6000","Change Rate","B2B","B2B00001","Deluxe","50",day,mon,day+"-"+month+"-"+year,day+"-"+month+"-"+year,"Single","singleday"},
		{"6500","Change Rate","B2B","B2B00001","Deluxe","50",day,mon,day+"-"+month+"-"+year,enddaycal+"-"+endmonth+"-"+endyear,"Multi","multipleday"},
		{"10","Change Inventory","B2B","B2B00001","Deluxe","50",day,mon,day+"-"+month+"-"+year,day+"-"+month+"-"+year,"Single","singleday"},
		{"15","Change Inventory","B2B","B2B00001","Deluxe","50",day,mon,day+"-"+month+"-"+year,enddaycal+"-"+endmonth+"-"+endyear,"Multi","multipleday"},
        {"close","Open/Close Rooms","B2B","B2B00001","Deluxe","50",day,mon,day+"-"+month+"-"+year,day+"-"+month+"-"+year,"Single","singleday"},
		{"close","Open/Close Rooms","B2B","B2B00001","Deluxe","50",day,mon,day+"-"+month+"-"+year,enddaycal+"-"+endmonth+"-"+endyear,"Multi","multipleday"},
        {"open","Open/Close Rooms","B2B","B2B00001","Deluxe","50",day,mon,day+"-"+month+"-"+year,day+"-"+month+"-"+year,"Single","singleday"},
        {"open","Open/Close Rooms","B2B","B2B00001","Deluxe","50",day,mon,day+"-"+month+"-"+year,enddaycal+"-"+endmonth+"-"+endyear,"Multi","multipleday"}};
    /*
		String[][] data ={
				{"5500","Change Rate","B2C","B2C00001","Deluxe","50",day,mon,day+"-"+month+"-"+year,day+"-"+month+"-"+year,"Single","singleday"},
				{"5000","Change Rate","B2C","B2C00001","Deluxe","50",day,mon,day+"-"+month+"-"+year,enddaycal+"-"+endmonth+"-"+endyear,"Multi","multipleday"},
				{"6000","Change Rate","B2C","B2C00001","Deluxe","50",day,mon,day+"-"+month+"-"+year,day+"-"+month+"-"+year,"Single","singleday"},
				{"6500","Change Rate","B2C","B2C00001","Deluxe","50",day,mon,day+"-"+month+"-"+year,enddaycal+"-"+endmonth+"-"+endyear,"Multi","multipleday"},
				{"10","Change Inventory","B2C","B2C00001","Deluxe","50",day,mon,day+"-"+month+"-"+year,day+"-"+month+"-"+year,"Single","singleday"},
				{"15","Change Inventory","B2C","B2C00001","Deluxe","50",day,mon,day+"-"+month+"-"+year,enddaycal+"-"+endmonth+"-"+endyear,"Multi","multipleday"},
		        {"close","Open/Close Rooms","B2C","B2C00001","Deluxe","50",day,mon,day+"-"+month+"-"+year,day+"-"+month+"-"+year,"Single","singleday"},
				{"close","Open/Close Rooms","B2C","B2C00001","Deluxe","50",day,mon,day+"-"+month+"-"+year,enddaycal+"-"+endmonth+"-"+endyear,"Multi","multipleday"},
		        {"open","Open/Close Rooms","B2C","B2C00001","Deluxe","50",day,mon,day+"-"+month+"-"+year,day+"-"+month+"-"+year,"Single","singleday"},
		        {"open","Open/Close Rooms","B2C","B2C00001","Deluxe","50",day,mon,day+"-"+month+"-"+year,enddaycal+"-"+endmonth+"-"+endyear,"Multi","multipleday"}};
    */
return data;
	}
	else
	{
		String[][] data ={
				{"5500","Change Rate","B2C","B2C00006","Business Club","3",day,mon,day+"-"+month+"-"+year,day+"-"+month+"-"+year,"Multi","singleday"},
				{"5000","Change Rate","B2C","B2C00006","Business Club","3",day,mon,day+"-"+month+"-"+year,enddaycal+"-"+endmonth+"-"+endyear,"Multi","multipleday"},
				{"6000","Change Rate","B2C","B2C00006","Business Club","3",day,mon,day+"-"+month+"-"+year,day+"-"+month+"-"+year,"Multi","singleday"},
				{"6500","Change Rate","B2C","B2C00006","Business Club","3",day,mon,day+"-"+month+"-"+year,enddaycal+"-"+endmonth+"-"+endyear,"Multi","multipleday"},
				{"10","Change Inventory","B2C","B2C00006","Business Club","3",day,mon,day+"-"+month+"-"+year,day+"-"+month+"-"+year,"Single","singleday"},
				{"15","Change Inventory","B2C","B2C00006","Business Club","3",day,mon,day+"-"+month+"-"+year,enddaycal+"-"+endmonth+"-"+endyear,"Multi","multipleday"},
		        {"close","Open/Close Rooms","B2C","B2C00006","Business Club","3",day,mon,day+"-"+month+"-"+year,day+"-"+month+"-"+year,"Single","singleday"},
				{"close","Open/Close Rooms","B2C","B2C00006","Business Club","3",day,mon,day+"-"+month+"-"+year,enddaycal+"-"+endmonth+"-"+endyear,"Single","multipleday"},
		        {"open","Open/Close Rooms","B2C","B2C00006","Business Club","3",day,mon,day+"-"+month+"-"+year,day+"-"+month+"-"+year,"Single","singleday"},
		        {"open","Open/Close Rooms","B2C","B2C00006","Business Club","3",day,mon,day+"-"+month+"-"+year,enddaycal+"-"+endmonth+"-"+endyear,"Single","multipleday"}
		        };

		return data;
	}
}
  
        
        
        
      //rate
        public static void updateRate(String StartDate,String EndDate,String value,String Type,String DayType) throws InterruptedException
        {
        check1=1;
        
        WebElement modelDialog=driver.findElement(By.xpath("//*[@id='custompopup']/div"));
        WebElement modelRAte=driver.findElement(By.xpath("//*[@id='collapseRtU']/div/div[3]"));
        Thread.sleep(3000);
        for(int i=0;i<0;i++)
        {
        	
        	modelRAte.findElement(By.xpath("//*[@id='collapseRtU']/div/div[3]/div[3]/div[3]/div/div/label[1]")).click();
        }
        List<WebElement>ratesection=modelRAte.findElements(By.tagName("input"));
        WebElement fromdateElement =ratesection.get(0);
        Thread.sleep(2000);
        String sdate[]=StartDate.split("-");
        String edate[]=EndDate.split("-");
        /*int rates[]={5000,6000,6100,6200,6300,6400,1000,500,5000,6000,6100,6200,6300,6400,2000,1000,
        		     5000,6000,6100,6200,6300,6400,1000,500,5000,6000,6100,6200,10000,12000,2000,1004,
        		     5000,6000,6100,6200,6300,6400,1000,500,5000,6000,6100,6200,10000,12000,2000,1004};*/
        
        int j=0;
       
        
        int ratflag=0;
   
       
         	if(DayType.equalsIgnoreCase("singleday"))
        	{
         	 for(int i=0;i<10;i++)
             {	
         
        	switch(i)
        	{
        	case 0:ratesection.get(i).click();
                   date(sdate[1],sdate[2],sdate[0]);
                   break;
            case 1:ratesection.get(i).click();
                   date(edate[1],edate[2],edate[0]);
                   break;
            case 2:ratesection.get(i).sendKeys(value);
                   break;
            case 3:ratesection.get(i).sendKeys(value);
                   break;
            case 4:ratesection.get(i).sendKeys(value);
                   break;
           case 5:ratesection.get(i).sendKeys(value);
                   break;
           case 6:ratesection.get(i).sendKeys(value);
                   break;
           case 7:ratesection.get(i).sendKeys(value);
                   break;
           case 8:ratesection.get(i).sendKeys(value);
                  break;
            case 9:ratesection.get(i).sendKeys(value);
                  break;
            case 10:ratesection.get(i).sendKeys(value);
                  break;
            default:System.out.println("anil");
                 break;
                 
        	}
      
        	}
        	}
        	else
        	{
        		for(int i=0;i<ratesection.size();i++)
        		{
        		switch(i)
            	{
        	      case 0:ratesection.get(i).click();
	                     date(sdate[1],sdate[2],sdate[0]);
	                      break;
 	              case 1:ratesection.get(i).click();
	                     date(edate[1],edate[2],edate[0]);
	                      break;
 	              case 2:ratesection.get(i).sendKeys(value);
                          break;
 	              case 3:ratesection.get(i).sendKeys(value);
                          break;
 	              case 4:ratesection.get(i).sendKeys(value);
                          break;
 	              case 5:ratesection.get(i).sendKeys(value);
                          break;
 	              case 6:ratesection.get(i).sendKeys(value);
                          break;
 	              case 7:ratesection.get(i).sendKeys(value);
                          break;
 	              case 8:ratesection.get(i).sendKeys(value);
                          break;
 	              case 9:ratesection.get(i).sendKeys(value);
                          break;
 	              case 10:ratesection.get(i).sendKeys(value);
 	                      break;
 	              case 11:ratesection.get(i).sendKeys(value);
                          break;
 	              case 12:ratesection.get(i).sendKeys(value);
                          break;
 	              case 13:ratesection.get(i).sendKeys(value);
                           break;
 	              case 14:ratesection.get(i).sendKeys(value);
                           break;
 	              case 15:ratesection.get(i).sendKeys(value);
                           break;
 	              case 16:ratesection.get(i).sendKeys(value);
                          break;
 	              case 17:ratesection.get(i).sendKeys(value);
                          break;
                  default:System.out.println("");
                          break;
        		
        	}
        }
        }
        
        	/*	if(i==0)
        	{
        		ratesection.get(i).click();
        		 date(sdate[1],sdate[2],sdate[0]);
        	}
        	else
        	//if(i==1||i==19||i==37)
        		if(i==1)
        	{
        		ratesection.get(i).click();
        		 date(edate[1],edate[2],edate[0]);
        	}
        	else
        	{
        		if(Type.equalsIgnoreCase("single"))
        		{
        			if(i>9)
        			{
        			   ratesection.get(i).sendKeys(value);
        			}
        		}
        		else
        		{
        	       ratesection.get(i).sendKeys(value);
        		}
     
        	}
        	j++;
       
        }
        
        }
       catch(Exception e)
       {
        	
        }*/
      
        System.out.println("Size"+ratesection.size());
        System.out.println("hello");
       if(Type.equalsIgnoreCase("multi"))
        {
        	//WebElement ele=driver.findElement(By.xpath("*[class^='col-sm-2']"));
     	    
        	Thread.sleep(2000);
        	driver.findElement(By.xpath("//*[@id='collapseRtU']/div/div[6]/label")).click();
        	
        	
        }
      

        //save button
      
        modelRAte.findElement(By.xpath("//*[@id='collapseRtU']/div/div[9]/div/a[2]")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='Msg1']")));
        WebElement msgbox=driver.findElement(By.xpath("//*[@id='Msg1']"));
        Thread.sleep(2000);
        String msg=msgbox.findElement(By.tagName("p")).getText();
        System.out.println(msg+"------------------------------------------------------------>");
        if(msg.equalsIgnoreCase("There seems to be some overlapping range of Date(s)"))
        {
        	System.out.println("There seems to be some overlapping range of Date(s)");
        	msgbox.findElement(By.tagName("button")).click();
        }
        if(msg.equalsIgnoreCase("Your Request has been Processed Successfully"))
        {
        	System.out.println("Data Saved Sucessfully");
        	msgbox.findElement(By.id("bot1-Msg1")).click();
        }
        check1=2;
       
       
       
        }
        
        
        
        
        
        
        
        public static void updateInv(String StartDate,String EndDate,String value,String Type,String DayType) throws InterruptedException
        {
        	check2=1;
        	
        	/*int[]inv={10,20,
        			   10,25,
        			   44,50};*/
        
        	 int j=0;
        		
        	 WebElement modelDialog=driver.findElement(By.xpath("//*[@id='custompopup']/div"));
        	 WebElement modelInv=driver.findElement(By.xpath("//*[@id='collapseUInv']/div/div[3]"));
         	 Thread.sleep(3000);
        	 for(int i=0;i<0;i++)
        	 {
        		 System.out.println("in");
        		//*[@id="collapseUInv"]/div/div[3]/div[3]/div[4]/div/div/label[1]
        		 modelInv.findElement(By.xpath("//*[@id='collapseUInv']/div/div[3]/div[3]/div[4]/div/div/label[1]")).click();
        	 }
        
        	 List<WebElement>inp=modelInv.findElements(By.tagName("input"));
        	 System.out.println(inp.size());
        	 //date range for inv
        	 //click plus button
   
             String sdate[]=StartDate.split("-");
             String edate[]=EndDate.split("-");
        	 Thread.sleep(2000);
        	 for(int i=0;i<inp.size();i++)
        	 {
        		 if(DayType.equalsIgnoreCase("singleday"))
             	{
        			 switch(i)
        	        	{
        	        	case 0:inp.get(i).click();
        	                   date(sdate[1],sdate[2],sdate[0]);
        	                   break;
        	            case 1:inp.get(i).click();
        	                   date(edate[1],edate[2],edate[0]);
        	                   break;
        	            case 2:inp.get(i).sendKeys(value);
        	                   break;
        	             default:System.out.println("");
        	                    break;
             	          }
             	}
        			 else
        			 {
        				 switch(i)
         	        	{
        			 case 0:inp.get(i).click();
	                   date(sdate[1],sdate[2],sdate[0]);
	                   break;
	                 case 1:inp.get(i).click();
	                   date(edate[1],edate[2],edate[0]);
	                   break;
	                case 2:inp.get(i).sendKeys(value);
	                   break;
	                case 3:inp.get(i).sendKeys(value);
	                   break;
	                default:System.out.println("");
	                    break;
        			 }
        			}
             }
        	 
        	 /* try
        	  {
        	 for(int i=0;i<inp.size();i++)
        	 {
        		 if(i==0||i==4||i==8)
             	{
             		inp.get(i).click();
             		 date(sdate[1],sdate[2],sdate[0]);
             	}
             	else
             	if(i==1||i==5||i==9)
             	{
             		inp.get(i).click();
             		 date(edate[1],edate[2],edate[0]);
             	}
             	else
            	
         
             
             			inp.get(i).sendKeys(value);
            
            	
           
              
              j++; 
        	 }
        	  }
        	  catch(Exception e)
        	  {
        		  
        	  }*/
        	 
        	
          if(Type.equalsIgnoreCase("Multi"))
           {
        	   Thread.sleep(2000);
        	
        	 //*[@id="collapseUInv"]/div/div[4]/label
           	   driver.findElement(By.xpath("//*[@id='collapseUInv']/div/div[4]/label")).click();
           
           }
          WebElement save= modelDialog.findElement(By.xpath("//*[@id='collapseUInv']/div"));
          save.findElement(By.xpath("//*[@id='collapseUInv']/div/div[6]/div/a[2]")).click();
          wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='Msg1']")));
          WebElement msgbox=driver.findElement(By.xpath("//*[@id='Msg1']"));
          Thread.sleep(2000);
          String msg=msgbox.findElement(By.tagName("p")).getText();
          if(msg.equalsIgnoreCase("There seems to be some overlapping range of Date(s)"))
          {
          	System.out.println("There seems to be some overlapping range of Date(s)");
          	msgbox.findElement(By.tagName("button")).click();
          }
          if(msg.equalsIgnoreCase("Your Request has been Processed Successfully"))
          {
          	System.out.println("Data Saved Sucessfully");
          	msgbox.findElement(By.tagName("button")).click();
          }
          
          check2=2;
          
        	
        }
        
        
        
        
        public static void OpenOrClose(String StartDate,String EndDate,String value,String Type,String DayType) throws InterruptedException
        {
        	 check3=1;
        	//try
        //	{
        	 WebElement modelDialog=driver.findElement(By.xpath("//*[@id='custompopup']/div"));
        	 WebElement modelOpen=modelDialog.findElement(By.xpath("//*[@id='collapseOPR']/div"));
        	
        	 
        	 Thread.sleep(2000);
        	 for(int i=0;i<0;i++)
        	 {
        		
        		 modelOpen.findElement(By.xpath("//*[@id='collapseOPR']/div/div[1]/div[1]/div[6]/div/div/label[1]")).click();
        	 }
        	 String sdate[]=StartDate.split("-");
             String edate[]=EndDate.split("-");
        	 String openValue=value;
        	 int range =1;
        	 ArrayList<String>days=new ArrayList<String>();
        	 //days.add("M");
        	 //days.add("Sa");
        	 String check="check";
        	 int size=days.size();
        	 for(int i=1;i<=range;i++)
        	 {
        		
        		 WebElement opendiv=modelOpen.findElement(By.xpath("//*[@id='collapseOPR']/div/div["+i+"]"));
        		 List<WebElement>open1=opendiv.findElements(By.tagName("i"));
        		 List<WebElement>open=opendiv.findElements(By.tagName("input"));
        		 for(int j=0;j<2;j++)
        		 {
        			 if(j==0)
        			 {
        				 open.get(j).click();
        				 date(sdate[1],sdate[2],sdate[0]);
        			 }
        			 else
        			 if(j==1)
        			 {
        				 open.get(j).click();
        				 date(edate[1],edate[2],edate[0]);
        			 }
        		 }
        		for(int j=2;j<12;j++)
        		{
        			 //open checkbox
        			 if(j==2)
        			 {
        				 Thread.sleep(2000);
        				 if(openValue.equalsIgnoreCase("open"))
        				 {
        			   
        			     open1.get(j).click();
        				
        				 }
        			 }
        			 else
        			 {//close checkbox
        			 if(j==3)
        			 {
        				 if(openValue.equalsIgnoreCase("close"))
        				 {
        				 open1.get(j).click();
        				 }
        			 }
        			 else
        				 try
        			 {
        			 if(j==4)
        			 {
        		      if(check.equalsIgnoreCase("uncheck"))
        			 
    				 {
        				 open1.get(j).click();
    				 }
        			 }
        			 else
        		     if(j==5)
        		     {
        			 for(String m:days)
        			 {
        				 if(m.equalsIgnoreCase("M"))
        				 {
        					 open1.get(j).click();
        					
        				 }
        			 }
        		     }
        			 if(j==6)
        			 {
        			 for(String m:days)
        			 {
        				 if(m.equalsIgnoreCase("T"))
        				 {
        					 open1.get(j).click();
        					 
        				 }
        			 }
        			 }
        			 if(j==7)
        			 {
        			 for(String m:days)
        			 {
        				 if(m.equalsIgnoreCase("W"))
        				 {
        					 open1.get(j).click();
        					 
        				 }
        			 }
        			 }
        			 if(j==8)
        			 {
        			 for(String m:days)
        			 {
        				 if(m.equalsIgnoreCase("Th"))
        				 {
        					 open1.get(j).click();
        					
        				 }
        			 }
        			 }
        			 if(j==9)
        			 {
        			 for(String m:days)
        			 {
        				 if(m.equalsIgnoreCase("Fr"))
        				 {
        					 open1.get(j).click();
        					
        				 }
        			 }
        			 }
        			 if(j==10)
        			 {
        			 for(String m:days)
        			 {
        				 if(m.equalsIgnoreCase("Sa"))
        				 {
        					 open1.get(j).click();
        					
        				 }
        			 }
        			 }
        			 if(j==11)
        			 {
        			 for(String m:days)
        			 {
        				 if(m.equalsIgnoreCase("Su"))
        				 {
        					 open1.get(j).click();
        					 
        				 }
        			 }
        			 }
        			 }
        			 catch(Exception ex)
        			 {
        				 System.out.println(ex);
        			 }
        			 
        			 } 
        		 }
        	 }
        	 
   
        	 Thread.sleep(2000);
        	 if(Type.equalsIgnoreCase("multi"))
             {
        		Thread.sleep(2000);
        		//*[@id="collapseOPR"]/div/div[3]/label
            	   driver.findElement(By.xpath("//*[@id='collapseOPR']/div/div[3]/label")).click();
             }
        	 WebElement save= modelDialog.findElement(By.xpath("//*[@id='collapseOPR']/div"));
            
        	 List<WebElement>i=save.findElements(By.tagName("i"));
        	 System.out.println(i.size());
        	 Thread.sleep(2000);
 
        	 modelOpen.findElement(By.cssSelector("*[class^='btn btn-labeled btn-success']")).click();
        	 Thread.sleep(2000);
        	// WebElement modelbox=driver.findElement(By.cssSelector("*[class^='modal-dialog']"));
        	//*[@id="Msg1"]
        	 driver.findElement(By.cssSelector("*[class^='btn  btn-success']")).click();
        	 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='Msg1']")));
        	 WebElement msgBox=driver.findElement(By.xpath("//*[@id='Msg1']"));
        	 Thread.sleep(2000);
        	 String msg=msgBox.findElement(By.tagName("p")).getText();
        	 System.out.println(msg);
        	 if(msg.equals("We did not find any valid Rate Plans"))
        	 {
        		 System.out.println("We did not find any valid Rate Plans");
        		 msgBox.findElement(By.tagName("button")).click();
        	 }
        	 Thread.sleep(2000);
        	 if(msg.equalsIgnoreCase("Your Request has been Processed Successfully"))
        	 {
        		 System.out.println(msg);
        		 msgBox.findElement(By.tagName("button")).click();
        	 }
        	 if(msg.equals("ERROR : [object Object]"))
        	 {
        		 System.out.println(msg);
        		 msgBox.findElement(By.tagName("button")).click();
        	 }
        	 
        	 check3=2;
        	
        	 //}
        	
         
        	
        }
        
        

        public static void date(String mon1,String Year1,String dayStr)
        {
           String mon=mon1;
           String year1=Year1;
            WebElement ele =driver.findElement(By.xpath("//*[@id='ui-datepicker-div']"));
            WebElement head=ele.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/div"));
            
            
     /*     //  List<WebElement>span=head.findElements(By.tagName("span"));
            String monthXpath="//*[@id='ui-datepicker-div']/div/div/span[1]";
          //*[@id="ui-datepicker-div"]/div/div/select[1]
            String yearXpath="//*[@id='ui-datepicker-div']/div/div/span[2]";
            String month=ele.findElement(By.xpath(monthXpath)).getText();
            System.out.println(month);
             String year=ele.findElement(By.xpath(yearXpath)).getText();
             System.out.println(year);
             while(!(month.equals(mon)&&year.equals(year1)))
             {
    	       System.out.println("inside");
    	//*[@id="ui-datepicker-div"]/div/a[2]/span/i
    	      ele.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/a[2]/span/i")).click();
    	       month=ele.findElement(By.xpath(monthXpath)).getText();
    	       year=ele.findElement(By.xpath(yearXpath)).getText();
     }*/
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
     /*for(WebElement e:day1)
     {
    	 List<WebElement>day2=e.findElements(By.tagName("td"));
          System.out.println("in");
    	 for(WebElement e2:day2)
    	 {
    		 try
    		 {
    		 String dayValue=e2.findElement(By.tagName("a")).getText();
    		 if(dayValue.equals(day))
    		 {
    			 System.out.println("got");
    			 e2.findElement(By.tagName("a")).click();
    		 }
    		 }
    		 catch(Exception exe)
    		 {
    			 System.out.println(exe);
    		 }
    		 
    	 }
     }*/
    /*   @AfterMethod
       public static void aftertest()
       {
    	 
       		if(check1==1)
        	   {
        		  
        		    flag1=true;
        		  
        	   }
       		else if(check2==1)
       		{
       			
    		    flag1=true;
    		   
       		}
       		else if(check3==1)
       		{
       		   
    		   flag1=true;
    		  
       		}
       	
       }*/
       
   }



