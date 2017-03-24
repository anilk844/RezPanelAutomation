package Enroll_hotel_test;

import java.awt.List;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

public class HotelContent {
  public static Properties prop;
	public static void hotelContent(ArrayList<String> data,String path,int row) throws IOException, FindFailed, InterruptedException
	{   
		hotel_content(data,row,path);  
	}
	public static void hotelContent(ArrayList data,String path) throws IOException, FindFailed, InterruptedException
	{         
		System.out.println("hello");
		      int rw=0;
		      ArrayList data1 = new ArrayList();
			  for(int i=0;i<data.size();i++)
			  {
				 
				  if(data.get(i).equals("PASS"))
				  {
					  rw++;
				  }
				  if(data.get(i).equals("FAIL"))
				  {
					  Double hotelcode= (Double) data.get(i+1);
					  
					  int hotelcode2=hotelcode.intValue();
					
					  
					  String hotelcode1=String.valueOf(hotelcode2);
					  data1.add(hotelcode1);
					  System.out.println(hotelcode1);
					  int j=2;
					  rw++;
					  while(j<6)
				      {
						  String m= (String) data.get(i+j);
						  System.out.println(m);
						  data1.add(m);
						  j++;
						 
						 }
					 hotel_content(data1,rw,path);
					 
				  }
			 }			  
	}
	
	public static void hotel_content(ArrayList data,int row,String path) throws IOException, InterruptedException, FindFailed
	{
		//try
		//{
			System.out.println("hi");
			FileInputStream pageObject = new FileInputStream("D:/WorkSpace/ibv4_code/Enroll_Hotel_Test/src/Repository/repository.properties");
			
			prop = new Properties();
			
			prop.load(pageObject);
			
			System.setProperty("webdriver.chrome.driver", "D://software//chromedriver.exe");
			
			ChromeDriver driver= new ChromeDriver();
			
		    driver.get(prop.getProperty("Url"));
		    
		    driver.findElement(By.xpath(prop.getProperty("username"))).sendKeys("superuser@reznext.com");
		    
		    driver.findElement(By.xpath(prop.getProperty("password"))).sendKeys("Test@123");
		    
		    Thread.sleep(2000);
		    
		    driver.findElement(By.xpath(prop.getProperty("Button"))).click();
		    
		    WebDriverWait wait=new WebDriverWait(driver,30);
            
		    wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("search_field"))));
        
           //int propertycode=Generic.gettIntValue(xlpath, sheetname1,i,3);
            String hotelcode=(String) data.get(0);
           driver.findElement(By.id(prop.getProperty("search_field"))).sendKeys(hotelcode);
           
           driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
       
           WebElement hotelnamelist = null; 
         //wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='header']/div[2]/form/div/table/tbody/tr[2]/td[1]")));
          try 
          {
        	  
            hotelnamelist= driver.findElement(By.xpath(".//*[@id='header']/div[2]/form/div/table/tbody/tr[2]/td[1]"));
            
           } 
        
        catch (Exception e) 
        { 
          
          System.out.println("2058"+" Property does not exist, check for next property");
          
          driver.findElement(By.id("search-fld")).clear();
          
        }
        Thread.sleep(3000);
       
        Screen scn1=new Screen();
        
        String img1="D://sikili//Ban.PNG";
        
        Pattern pt1=new Pattern(img1);
        
        scn1.click(pt1);
        
        driver.get(prop.getProperty("enroll_URl"));
		
        driver.manage().window().maximize();
		
        Thread.sleep(8000);
		    
	       
	  /*  Screen  scn2= new Screen();
	       
	    //String img2=prop.getProperty("addnew");
	    String img2="D://sikili//addnew.PNG";    
	    Pattern pt2=new Pattern(img2);
	        
	    scn2.click(pt2);*/
	    
	    WebElement div=driver.findElement(By.xpath("//*[@id='main']"));
	   // WebElement table=div.findElement(By.xpath("//*[@id='datatable_cm']"));
	    java.util.List<WebElement> lst = div.findElements(By.tagName("tbody"));
	    WebElement tbody=lst.get(0);
	    java.util.List<WebElement> lst1 =tbody.findElements(By.tagName("tr"));
	    
	    WebElement tr=lst1.get(0);
	    java.util.List<WebElement> lst2 =tr.findElements(By.tagName("td"));
	    WebElement td=lst2.get(0);
	    System.out.println(td.getText());
	    td.click();
	    WebElement ele = driver.findElement(By.xpath("//*[@id='content']"));
	    ele.findElement(By.xpath("//*[@id='hotelcontent']")).click();
	    System.out.println("hello");
        
        Thread.sleep(3000);
	//	}
	/*	catch(Exception e)
		{
			
				FileInputStream file1= new FileInputStream(path);

				XSSFWorkbook workbook1 = new XSSFWorkbook (file1);
				
				XSSFSheet sheet1 = workbook1.getSheetAt(0);
				
				XSSFRow row1 = sheet1.getRow(row);
				
				XSSFCell r1c1 = row1.createCell(0);
				
				r1c1.setCellValue("FAIL");
				
				file1.close();
				
				FileOutputStream fos =new FileOutputStream(path);
			    
				workbook1.write(fos);
			    
				fos.close();
			  }*/
			       
		}
		
		
		
	}
	
	


