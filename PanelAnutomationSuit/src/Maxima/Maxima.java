package Maxima;

import java.awt.Frame;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Maxima {

	@Test
	public static void start() throws InterruptedException, ClassNotFoundException, SQLException, ParseException {
		 //TODO Auto-generated method stub
         int temp=0;
         SimpleDateFormat sm  = new SimpleDateFormat("yyyy-MM-dd");
		 Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");	
		 System.out.println("test1");
		 //Connection conn = DriverManager.getConnection("jdbc:sqlserver://he05ik8udk.database.windows.net;user=Rezstaging;password=Staging@123;database=redcoredblive28may16_test");
		 Connection conn = DriverManager.getConnection("jdbc:sqlserver://he05ik8udk.database.windows.net;user=RED;password=TechOperation_786;database=redcoredblive");
		 System.setProperty("webdriver.chrome.driver", "D://chrome//chromedriver.exe");
		
		 
		 String []custcode={"2070","2060","2050","2000","2080"};   //live live
		 String []Rezcode={"2901","595","13","47","2309"};          //live
		 //String []custcode={"2000"};
		 //String []custcode={"2050","2000"};
		 //String []Rezcode={"47"};
		 //String []Rezcode={"13","47"};
		 String[] str={"D:\\Maxima\\Green Park - Avasa","D:\\Maxima\\GreenParkChennai","D:\\Maxima\\Green Park - Hyderabad","D:\\Maxima\\Green Park - Visakhapatnam","D:\\Maxima\\Marigold by Green Park"};
		 //String[] str={"D:\\Maxima\\Green Park - Visakhapatnam"};
		 //String[] str={"D:\\Maxima\\Marigold by Green Park"};
         DesiredCapabilities capabilities = DesiredCapabilities.chrome();
         ChromeOptions options = new ChromeOptions();
         options.addArguments("test-type");
         options.addArguments("--start-maximized");
         options.addArguments("--disable-web-security");
         options.addArguments("--allow-running-insecure-content");
         int j=0;
         
         //Rate Upadet IP changes Date-24-10-2017
         Statement st = conn.createStatement();
         String UpdateURL= "update TBLRZNChannelCredentialInfo set RateURL='http://202.62.72.102/SynxisCrawler/api/Rate/RateUpdate' where channelid=20 and custcode in (13,47,595)";
    	 
    	 st.executeUpdate(UpdateURL);
         for(String s:str)
         {
        	 System.out.println("a1");
           String downloadFilepath = s;
           HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
           chromePrefs.put("profile.default_content_settings.popups", 0);
           chromePrefs.put("download.default_directory", downloadFilepath);
           options.setExperimentalOption("prefs", chromePrefs);
           capabilities.setCapability("chrome.binary","D://chrome//chromedriver.exe");
           capabilities.setCapability(ChromeOptions.CAPABILITY, options);
           WebDriver driver = new ChromeDriver(capabilities);
           WebDriverWait wait=new WebDriverWait(driver,40);
           driver.get("https://reznext.maximrms.net");
           wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/table[2]/tbody/tr/td/form/table/tbody/tr[2]/td[2]/input")));
           driver.findElement(By.xpath("/html/body/table[2]/tbody/tr/td/form/table/tbody/tr[2]/td[2]/input")).sendKeys("vinod@hotelgreenpark.com");
           driver.findElement(By.xpath("/html/body/table[2]/tbody/tr/td/form/table/tbody/tr[3]/td[2]/input")).sendKeys("shreya9176#");
           driver.findElement(By.xpath("/html/body/table[2]/tbody/tr/td/form/table/tbody/tr[5]/td/input[1]")).click();
           wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='menu']/ul/li[1]/a")));
           driver.findElement(By.xpath("//*[@id='menu']/ul/li[1]/a")).click();
       //*[@id="menu"]/ul/li[1]/div/ul/li[3]/a
           wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='menu']/ul/li[1]/div/ul/li[3]/a")));
           driver.findElement(By.xpath("//*[@id='menu']/ul/li[1]/div/ul/li[3]/a")).click();
           Thread.sleep(2000);
           wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='hotel_sel']")));
           WebElement selprop=driver.findElement(By.xpath("//*[@id='hotel_sel']"));
           Select sel=new Select(selprop);
        
           sel.selectByValue(custcode[j]);
         
	       SimpleDateFormat dat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	       String startdate=dat1.format(new Date());
	       Statement Cust = conn.createStatement();
           String Custstr1="select * from TBLRZNMaximOccupancyUpdateStatus where custcode="+Rezcode[j]+"";
	       ResultSet Custx = Cust.executeQuery(Custstr1);
	       if(Custx.next())
	       {
	    	 String custupdate= "update TBLRZNMaximOccupancyUpdateStatus set Rundate='"+sm.format(new Date())+"',StartTime='"+startdate+"',SStatus='Running',AzureUpdate='' where custcode="+Rezcode[j]+"";
	    	 System.out.println(custupdate);
	    	 Cust.executeUpdate(custupdate);
	     }
	     else
	     {
	    	 String CustInsert="INSERT INTO TBLRZNMaximOccupancyUpdateStatus " + "VALUES ("+Rezcode[j]+", '"+sm.format(new Date())+"', '"+startdate+"','','Running','')";
	    	 Cust.executeUpdate(CustInsert);
	     }
         for(int i=10;i<=12;i++)
         {
        
            driver.get("https://reznext.maximrms.net/main/NormalMonth?&sel_yr=2017&sel_mo="+i+"&sel_dy=1");
            Thread.sleep(2000);
            driver.switchTo().frame("child_iframe");
            Thread.sleep(4000);
            //driver.switchTo().frame("child_iframe");
            //WebElement frame= driver.findElement(By.xpath("//*[@id='child_iframe']"));
            //WebElement frame2= frame.findElement(By.xpath("//*[@id='child_iframe']"));
            //WebElement body= frame2.findElement(By.xpath("/html/body"));
            List<WebElement>tr= driver.switchTo().frame("child_iframe").findElements(By.tagName("tr"));
            int k=0;
            int flag=0;
            String monyear[]={};
            for(WebElement t:tr)
            {
        	
        	 
        	    if(flag==0)
        	    {
        		    List<WebElement>th=t.findElements(By.tagName("th"));
        		    System.out.println("Hotel Name:-"+th.get(0).getText());
        		    System.out.println("Date:-"+th.get(2).getText());
        		    monyear=th.get(2).getText().split(" ");
        		    System.out.println(monyear.length);
        		    flag=1;
        		
        	    }
        	    List<WebElement>td=t.findElements(By.tagName("td"));
        	 
        	 
        	    if(td.size()==25)
        	    {
        	
        	      System.out.print("Custcode:"+custcode[j]+" ");
        	      String date1=td.get(0).getText()+" "+monyear[0].substring(0, 3)+", "+monyear[1];
        	     
        	      Date d = DateFormat.getDateInstance().parse(date1);
        	      String strDate = sm.format(d);
        	      String strdate1[]=strDate.split("-");
        	      String Finaldate=strdate1[0]+"-"+strdate1[1]+"-"+strdate1[2];
        	      SimpleDateFormat sm1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        	      System.out.println("---->"+sm1.format(new Date()).toString());
        	      System.out.println("DATE -"+strDate);
        	      System.out.println("CustCode -"+Rezcode[j]);
        	      System.out.println("Occupancy -"+td.get(16).getText());
        	      System.out.println("Currrent Date -"+sm.format(new Date()));
        	  
        	      System.out.println();
        	      String actualOccup=td.get(16).getText().toString();
        	      Statement sta = conn.createStatement();
        	      String str1="select * from TBLRZNMaximOccupancy where custcode="+Rezcode[j]+" and RunDate = '"+strDate+"'";
        	      ResultSet x = sta.executeQuery(str1);
        	      if(x.next())
        	      {
        		       //String DBoccup="select Occupancy from TBLRZNMaximOccuapncy where custcode="+Rezcode[j]+" and RunDate = '"+strDate+"'";
        		       //ResultSet DBoccup1= sta.executeQuery(DBoccup);
        		       //int DBoccupint=Integer.parseInt(DBoccup);
        		       System.out.println("DBoccup--"+x.getFloat("Occupancy"));
        		       System.out.println("actualOccup--"+actualOccup);
        		       String DBoccupancyStr=String.valueOf(x.getFloat("Occupancy"));
        		       if(actualOccup.equalsIgnoreCase(DBoccupancyStr))
        		       {
        			       System.out.println("same");
        		       }
        		       else
        		      {
        			  
        			      String UpdateDBoccup="update TBLRZNMaximOccupancy set Occupancy="+actualOccup+",UpdatedOn='"+sm1.format(new Date()).toString()+"' where custcode="+Rezcode[j]+" and RunDate = '"+strDate+"'";
        			      sta.executeUpdate(UpdateDBoccup);
        		      }
        	     }
        	     else
        	     {
        		      String Sql = "INSERT INTO TBLRZNMaximOccupancy " + "VALUES ("+Rezcode[j]+", '"+Finaldate+"', "+td.get(16).getText()+",'"+sm1.format(new Date()).toString()+"','"+sm1.format(new Date()).toString()+"')";
            	      System.out.println(str1);
            	      sta.executeUpdate(Sql);
        	     }
        	          //String str1="INSERT INTO TBLRZNMaximOccuapncy " + "VALUES ("+Rezcode[j]+", '"+Finaldate+"', "+td.get(16).getText()+",'"+sm.format(new Date()).toString()+"')";
              }
        	 
        	 
        	 
          }
         
                     //driver.findElement(By.xpath("//*[@id='keep_alive_page']/table[2]/tbody/tr/td[3]/table/tbody/tr/td[2]/a")).click();
                     //HashSet<String>window=(HashSet<String>) driver.getWindowHandles();
                     //int len=window.size();
                     //Object[]window1=window.toArray();
                     //String s=(String) window1[window1.length-2];
                     //WebDriver pop=driver.switchTo().window(s);
                     //Thread.sleep(5000);
                     //driver.switchTo().window((String) window1[window1.length-1]).close();
                     //driver.switchTo().window((String) window1[window1.length-2]);
        
         }
         String enddate=dat1.format(new Date());
         String custupdate= "update TBLRZNMaximOccupancyUpdateStatus set endtime='"+enddate+"',SStatus='Completed' where custcode="+Rezcode[j]+"";
         System.out.println(custupdate);
	     Cust.executeUpdate(custupdate);
	     
         j=j+1;
         driver.close();
        
         
         }   
                    //*[@id="menu"]/ul/li[1]/a
                    //driver.get("https://reznext.maximrms.net/main/NormalMonth?&sel_hotel_id=2060");
         
	}

}
