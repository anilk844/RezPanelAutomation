package panelAutomationInprogress;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Driver;
import java.util.ArrayList;
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

import com.gargoylesoftware.htmlunit.WebWindow;
import com.gargoylesoftware.htmlunit.javascript.background.JavaScriptExecutor;

public class NewCalendar {
		
public static WebDriver driver;
public static WebDriverWait wait;
public static Properties prop;
public static Properties gen;
public static Properties stat;
public static void date()
{
	
}
public static void main(String []args) throws InterruptedException, FindFailed, AWTException, IOException
{
	
	   
	    
         boolean ratebool=false;

 		FileInputStream status = new FileInputStream("D:/WorkSpace/ibv4_code/Enroll_Hotel_Test/src/LiveRepository/SwitchQAandLIVE.properties");
 		stat=new Properties();
 		stat.load(status);
 		if(stat.getProperty("Status").equalsIgnoreCase("QA"))
 		{
 			FileInputStream pageObjectGen = new FileInputStream("D:/WorkSpace/ibv4_code/Enroll_Hotel_Test/src/Repository/Generic.properties");
 			gen=new Properties();
 			gen.load(pageObjectGen);
 		}
 		else if(stat.getProperty("status").equalsIgnoreCase("LIVE"))
 		{
 			FileInputStream pageObjectGen = new FileInputStream("D:/WorkSpace/ibv4_code/Enroll_Hotel_Test/src/LiveRepository/Generic.properties");
 			gen=new Properties();
 			gen.load(pageObjectGen);
 		}
 		FileInputStream pageObject = new FileInputStream("D:/WorkSpace/ibv4_code/Enroll_Hotel_Test/src/Repository/Promocode.properties");
 		prop = new Properties();
 	    prop.load(pageObject);
 		System.setProperty("webdriver.chrome.driver", "D://chromedriver.exe");
 		ChromeOptions options = new ChromeOptions();
 		options.addArguments("--disable-extensions");
 		driver=new ChromeDriver(options);
 		wait=new WebDriverWait(driver,40);
 		
 		driver.manage().window().maximize();
 		driver.get(gen.getProperty("Url"));
		wait=new WebDriverWait(driver,40);
		Thread.sleep(10000);
		driver.findElement(By.xpath(".//*[@id='login-form']/fieldset/section[1]/label[2]/input")).sendKeys("superuser@reznext.com");
		driver.findElement(By.xpath(".//*[@id='password']")).sendKeys("Test@321");
		driver.findElement(By.xpath(".//*[@id='login-form']/footer/div/button")).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(gen.getProperty("searchField"))));
		
		driver.findElement(By.xpath("//*[@id='search-fld']")).sendKeys("3241");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='header']/div[2]/form/div/table/tbody/tr[2]")));
		driver.findElement(By.xpath("//*[@id='header']/div[2]/form/div/table/tbody/tr[2]")).click();
		/*Screen scn= new Screen();
		String img="D://sikili//Ban.PNG";
		Pattern ptr= new Pattern(img);
		Thread.sleep(4000);
		scn.click(ptr);*/
		driver.navigate().to("https://redapptest.azurewebsites.net/index.html#/newcal/newcalendar");
		Thread.sleep(8000);
		//select rate type
		String RateType="B2C";
		WebElement rateType=driver.findElement(By.xpath("//*[@id='wid-id-Calc']/div/div[2]/div/div[1]/div[1]/div/select"));
		Select selectRateType=new Select(rateType);
		selectRateType.selectByVisibleText(RateType);
		Thread.sleep(3000);
		String RateCode="B2C00001";
		WebElement rateCode=driver.findElement(By.xpath("//*[@id='wid-id-Calc']/div/div[2]/div/div[1]/div[2]/div/select"));
		Select selectRateCode=new Select(rateCode);
		selectRateCode.selectByVisibleText(RateCode);
		Thread.sleep(2000);
		String room="Deluxe";
		WebElement roomType=driver.findElement(By.xpath("//*[@id='roomtyp']"));
		Select selectRoomType=new Select(roomType);
		selectRoomType.selectByVisibleText(room);
		Thread.sleep(2000);
		WebElement channel=driver.findElement(By.xpath("//*[@id='wid-id-Calc']/div/div[2]/div/div[1]/div[4]/div/marquee/ul"));
		List<WebElement>channelid=channel.findElements(By.tagName("a"));
		String channelno="50";
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
		Thread.sleep(2000);
		//select calendar
		JavascriptExecutor js = ((JavascriptExecutor) driver);

		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		String date1="14";
		String month="01";
		WebElement date=driver.findElement(By.xpath("//*[@id='ratecalendar']"));
		List<WebElement> SelectDate=date.findElements(By.tagName("div"));
		//*[@id="date-20"]/div/div[1]/div
		String dateValue="//*[@id='date-"+date1+month+"']/div/div[1]/div";
	
		Actions action =new Actions(driver);
		Thread.sleep(2000);
		//robot=new Robot();
		//robot.keyPress(KeyEvent.VK_CONTROL);
		List<String>ARI=new ArrayList<String>();
		ARI.add("Change Rate");
		ARI.add("Change Inventory");
		//ARI.add("Close Rooms");
		for(String ARIValue :ARI)
		{
			Thread.sleep(5000);
		action.click(driver.findElement(By.xpath(dateValue))).build().perform();
		action.contextClick(driver.findElement(By.xpath(dateValue))).build().perform();
		//xpath of change rate
		String rate1="//*[@id='menu-"+date1+month+"']";
        WebElement cell=driver.findElement(By.xpath(rate1));
        List<WebElement>cell1=cell.findElements(By.tagName("a"));
        for(WebElement cell2:cell1)
        {
        	String updateName="Change Rate";
        	String cellname=cell2.getText();
        	System.out.println(cellname);
        	if(cellname.equalsIgnoreCase(ARIValue))
        	{
        		if(ARIValue.equalsIgnoreCase("Change Rate"))
        		{
        		Thread.sleep(2000);
        		System.out.println(cell2);
        		cell2.click();
    
                updateRate();
                break;
        		}
        		if(ARIValue.equalsIgnoreCase("Change Inventory"))
        		{
        			Thread.sleep(4000);
            		System.out.println("change inv");
            		cell2.click();
            		updateInv();
            		break;
        		}
        		if(ARIValue.equalsIgnoreCase("Close Rooms"))
        		{
        			Thread.sleep(4000);
            		System.out.println("change inv");
            		cell2.click();
            		OpenOrClose();
            		break;
        		}
        		
        	}
        	
        	 System.out.println(cell2);
         }
		}
}
   
        
        
        
        
        
        
        
      //rate
        public static void updateRate() throws InterruptedException
        {
        
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
        int count=0;
        int val=0;
        int values=1000;
        int rates[]={5000,6000,6100,6200,6300,6400,1000,500,5000,6000,6100,6200,6300,6400,2000,1000,
        		     5000,6000,6100,6200,6300,6400,1000,500,5000,6000,6100,6200,10000,12000,2000,1004,
        		     5000,6000,6100,6200,6300,6400,1000,500,5000,6000,6100,6200,10000,12000,2000,1004};
        
        int j=0;
        for(int i=0;i<ratesection.size();i++)
        {	
        	if(i==0||i==18||i==36)
        	{
        		ratesection.get(i).click();
        		 date("Jan","2017","20");
        	}
        	else
        	if(i==1||i==19||i==37)
        	{
        		ratesection.get(i).click();
        		 date("Jan","2017","28");
        	}
        	else
        	{
        
          try
          {
            String values1=String.valueOf(rates[j]);
        	ratesection.get(i).sendKeys(values1);
          }
          catch(Exception exe1)
          {
        	  System.out.println(exe1);
          }
        	j++;
        	}
      
        }
      
        Thread.sleep(2000);
        System.out.println(ratesection.size());
        //save button
      //*[@id="collapseRtU"]/div/div[9]/div/a[2]
        modelRAte.findElement(By.xpath("//*[@id='collapseRtU']/div/div[9]/div/a[2]")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='Msg1']")));
      //*[@id="Msg1"]
        WebElement msgbox=driver.findElement(By.xpath("//*[@id='Msg1']"));
        String msg=msgbox.findElement(By.tagName("p")).getText();
        if(msg.equalsIgnoreCase("There seems to be some overlapping range of Date(s)"))
        {
        	System.out.println("There seems to be some overlapping range of Date(s)");
        	msgbox.findElement(By.tagName("button")).click();
        }
        if(msg.equalsIgnoreCase("Your Request has been Processed Successfully!"))
        {
        	System.out.println("Data Saved Sucessfully");
        	msgbox.findElement(By.tagName("button")).click();
        }
        }
        
        
        
        
        
        
        
        public static void updateInv() throws InterruptedException
        {
        	
        	
        	int[]inv={10,20,
        			   10,25,
        			   44,50};
        	   int j=0;
        		
        	 WebElement modelDialog=driver.findElement(By.xpath("//*[@id='custompopup']/div"));
        	 WebElement modelInv=driver.findElement(By.xpath("//*[@id='collapseUInv']/div/div[3]"));
        	//*[@id="collapseUInv"]/div/div[3]
         	 Thread.sleep(3000);
         	 System.out.println("out");
        	 for(int i=0;i<0;i++)
        	 {
        		 System.out.println("in");
        		//*[@id="collapseUInv"]/div/div[3]/div[3]/div[4]/div/div/label[1]
        		 modelInv.findElement(By.xpath("//*[@id='collapseUInv']/div/div[3]/div[3]/div[4]/div/div/label[1]")).click();
        	 }
        	 
        	Thread.sleep(2000);
        	 List<WebElement>inp=modelInv.findElements(By.tagName("input"));
        	 System.out.println(inp.size());
        	 //date range for inv
        	 //click plus button
   
        
        	 Thread.sleep(3000);
        	 for(int i=0;i<inp.size();i++)
        	 {
        		 if(i==0||i==4||i==8)
             	{
             		inp.get(i).click();
             		 date("January","2017","20");
             	}
             	else
             	if(i==1||i==5||i==9)
             	{
             		inp.get(i).click();
             		 date("January","2017","30");
             	}
             	else
            	{
            
              try
              {
                String values1=String.valueOf(inv[j]);
            	inp.get(i).sendKeys(values1);
              }
              catch(Exception exe1)
              {
            	  System.out.println(exe1);
              }
              j++; 
        	 }
        			
        	 }
        	 
        WebElement save= modelDialog.findElement(By.xpath("//*[@id='collapseUInv']/div"));
        save.findElement(By.xpath("//*[@id='collapseUInv']/div/div[6]/div/a[2]")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='Msg1']")));
        //*[@id="Msg1"]
          WebElement msgbox=driver.findElement(By.xpath("//*[@id='Msg1']"));
          String msg=msgbox.findElement(By.tagName("p")).getText();
          if(msg.equalsIgnoreCase("There seems to be some overlapping range of Date(s)"))
          {
          	System.out.println("There seems to be some overlapping range of Date(s)");
          	msgbox.findElement(By.tagName("button")).click();
          }
          if(msg.equalsIgnoreCase("Your Request has been Processed Successfully!"))
          {
          	System.out.println("Data Saved Sucessfully");
          	msgbox.findElement(By.tagName("button")).click();
          }
        }
        
        
        
        
        public static void OpenOrClose() throws InterruptedException
        {
        	
        	
        	 WebElement modelDialog=driver.findElement(By.xpath("//*[@id='custompopup']/div"));
        	 WebElement modelOpen=modelDialog.findElement(By.xpath("//*[@id='collapseOPR']/div"));
        	
        	 
        	 Thread.sleep(3000);
        	 for(int i=0;i<0;i++)
        	 {
        		
        		 modelOpen.findElement(By.xpath("//*[@id='collapseOPR']/div/div[1]/div[1]/div[6]/div/div/label[1]")).click();
        	 }
        	 String openValue="close";
        	 int range =1;
        	 ArrayList<String>days=new ArrayList<String>();
        	 days.add("M");
        	 days.add("Sa");
        	 String check="uncheck";
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
        				 date("January", "2017", "22");
        			 }else
        			 if(j==1)
        			 {
        				 open.get(j).click();
        				 date("January", "2017", "30");
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
        	 
   
        	 Thread.sleep(3000);
        	
        	 WebElement save= modelDialog.findElement(By.xpath("//*[@id='collapseOPR']/div"));
            
        	 List<WebElement>i=save.findElements(By.tagName("i"));
        	 System.out.println(i.size());
        	 Thread.sleep(2000);
        	 modelOpen.findElement(By.cssSelector("*[class^='btn btn-labeled btn-success']")).click();
        	// i.get(26).click();
        	 
        	 Thread.sleep(2000);
        	// WebElement modelbox=driver.findElement(By.cssSelector("*[class^='modal-dialog']"));
        	 driver.findElement(By.cssSelector("*[class^='btn  btn-success']")).click();
        	 WebElement msgBox=driver.findElement(By.xpath("//*[@id='Msg1']"));
        	 String msg=msgBox.findElement(By.tagName("p")).getText();
        	 if(msg.equals("We did not find any valid Rate Plans"))
        	 {
        		 System.out.println("We did not find any valid Rate Plans");
        		 msgBox.findElement(By.tagName("button")).click();
        	 }
        	 if(msg.equals("Data Saved Sucessfully"))
        	 {
        		 System.out.println(msg);
        		 msgBox.findElement(By.tagName("button")).click();
        	 }
        	 if(msg.equals("ERROR : [object Object]"))
        	 {
        		 System.out.println(msg);
        		 msgBox.findElement(By.tagName("button")).click();
        	 }
        	 
    
        	
        }
        
        

        public static void date(String mon1,String Year1,String day)
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
     List<WebElement>day1=SelectDay.findElements(By.tagName("tr"));
     for(WebElement e:day1)
     {
    	 List<WebElement>day2=e.findElements(By.tagName("td"));
    
    	 for(WebElement e2:day2)
    	 {
    		 try
    		 {
    		 String dayValue=e2.findElement(By.tagName("a")).getText();
    		 
    		 System.out.println(dayValue);
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
     }
   }
}
