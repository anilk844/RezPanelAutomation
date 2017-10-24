package panelAutomationInprogress;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;

import DataExtractor.DataExtractor;

import com.thoughtworks.selenium.SeleniumException;

public class enroll_test
{

	public static Properties prop;
	public static WebDriver driver;
public static void main(String args[]) throws IOException, InterruptedException, FindFailed, InvalidFormatException
{
	FileInputStream pageObject = new FileInputStream("D:/WorkSpace/ibv4_code/Enroll_Hotel_Test/src/Repository/Enroll_hotel_repository.properties");
	prop = new Properties();
	prop.load(pageObject);
	FileInputStream file= new FileInputStream(prop.getProperty("enroll_hotel_file"));
	System.setProperty("webdriver.chrome.driver", "D://software//chromedriver.exe");
	
	ArrayList data= new ArrayList();
	XSSFWorkbook workbook = new XSSFWorkbook(file);
	XSSFSheet sheet = workbook.getSheet("Sheet1");
	Iterator itr = sheet.iterator();
	while(itr.hasNext())
	{
		Row RowItr = (Row)itr.next();
		Iterator cellItr=RowItr.cellIterator();
		while(cellItr.hasNext())
		{
			Cell cells= (Cell)cellItr.next();
			switch(cells.getCellType())
			{
			case Cell.CELL_TYPE_STRING:data.add(cells.getStringCellValue());
			break;
			case Cell.CELL_TYPE_NUMERIC:data.add(cells.getNumericCellValue());
			break;
			case Cell.CELL_TYPE_BOOLEAN:data.add(cells.getBooleanCellValue());
			break;
			case Cell.CELL_TYPE_BLANK:
			break;
			}
		}
	}
	file.close();
	System.out.println(data.size());
	String enroll_hotel_status=prop.getProperty("enroll_hotel_status");
	if(enroll_hotel_status=="0")
	{
	int rw=0;
	for(int i=0;i<data.size();i++)
    {
	   
		try
		{
	     if(data.get(i).equals("PASS"))
	     {
	    	 rw++;
	     }
	     else
			
		  if(data.get(i).equals("FAIL"))
		  { 
			
			  rw++;
				   //-------------------------------
				    driver= new ChromeDriver();
				    
				    driver.get(prop.getProperty("Url"));
				    
				    driver.findElement(By.xpath(prop.getProperty("username"))).sendKeys("superuser@reznext.com");
				    
				    driver.findElement(By.xpath(prop.getProperty("password"))).sendKeys("Test@123");
				    
				    Thread.sleep(2000);
				    
				    driver.findElement(By.xpath(prop.getProperty("Button"))).click();
				   
				    //-------------------------------------------
				    WebDriverWait wait=new WebDriverWait(driver,30);
	                
				    wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("search_field"))));
	            
                   //int propertycode=Generic.gettIntValue(xlpath, sheetname1,i,3);
	                
	               driver.findElement(By.id(prop.getProperty("search_field"))).sendKeys("139156");
	               
	               driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	           
	            WebElement hotelnamelist = null; 
	            //wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='header']/div[2]/form/div/table/tbody/tr[2]/td[1]")));
	            try {
	              hotelnamelist= driver.findElement(By.xpath(".//*[@id='header']/div[2]/form/div/table/tbody/tr[2]/td[1]"));
	                } 
	            
	            catch (Exception e) 
	            { 
	              
	              System.out.println("2058"+" Property does not exist, check for next property");
	              
	              driver.findElement(By.id("search-fld")).clear();
	              
	              continue; 
	            }
	            Thread.sleep(3000);
	           
	            Screen scn1=new Screen();
	            
	            String img1="D://sikili//Ban.PNG";
	            
	            Pattern pt1=new Pattern(img1);
	            
	            scn1.click(pt1);
	            
	            Thread.sleep(3000);
				    //-------------------------------------------
				
	            driver.get(prop.getProperty("enroll_URl"));
				
	            driver.manage().window().maximize();
				
	            Thread.sleep(8000);
				    
			       
			    Screen  scn2= new Screen();
			       
			    //String img2=prop.getProperty("addnew");
			    String img2="D://sikili//addnew.PNG";    
			    Pattern pt2=new Pattern(img2);
			        
			    scn2.click(pt2);
			        
			  
			  driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
			  
			  Double hotelcode= (Double) data.get(i+1);
			  
			  int hotelcode2=hotelcode.intValue();
			
			  
			  String hotelcode1=String.valueOf(hotelcode2);
			  
			  System.out.println(hotelcode1);
			  
			  driver.findElement(By.xpath(prop.getProperty("hotelcode"))).sendKeys(hotelcode1);
			  
			  String hotelname=(String) data.get(i+2);

			  driver.findElement(By.xpath(prop.getProperty("hotelname"))).sendKeys(hotelname);
			  
			  String country=(String) data.get(i+6);
			  
			  StringSelection country1=new StringSelection(country);
			  
			  Thread.sleep(4000);
			 
			/*  Toolkit.getDefaultToolkit().getSystemClipboard().setContents(country1, null);
			  Robot robot = new Robot();
			  robot.keyPress(KeyEvent.VK_CONTROL);
			  robot.keyPress(KeyEvent.VK_V);
			  robot.keyRelease(KeyEvent.VK_CONTROL);
			  robot.keyRelease(KeyEvent.VK_V);*/
			   
			  driver.findElement(By.xpath(prop.getProperty("country_name"))).sendKeys(country.trim());
			
			  driver.findElement(By.xpath(prop.getProperty("country_name"))).sendKeys(Key.DOWN);
			  
			  Thread.sleep(4000);
		
			  String state=(String) data.get(i+5);
		
			
			  driver.findElement(By.xpath(prop.getProperty("state_name"))).sendKeys(state.trim());
			
		      driver.findElement(By.xpath(prop.getProperty("state_name"))).sendKeys(Key.DOWN);
		
			  Thread.sleep(4000);
			 
			  String city=(String) data.get(i+4);

		      driver.findElement(By.xpath(prop.getProperty("city_name"))).sendKeys(city.trim());
			 
		      driver.findElement(By.xpath(prop.getProperty("city_name"))).sendKeys(Key.DOWN);
		
			  Thread.sleep(4000);
			  
			  String address=(String)data.get(i+3);
			 
			  driver.findElement(By.xpath(prop.getProperty("Hotel_address_1"))).sendKeys(address);
			  
		
			  driver.findElement(By.xpath(prop.getProperty("Phone_number_1"))).sendKeys(prop.getProperty("phone_no"));
			  //------------------------------product 
			
			
			  
			  driver.findElement(By.xpath(prop.getProperty("product_catagory"))).sendKeys(prop.getProperty("product_cat"));
			  
			  
			  //------------------------------Currency
			  
	
			  
			  driver.findElement(By.xpath(prop.getProperty("currency"))).sendKeys(prop.getProperty("currency_type"));;
			  
			  Thread.sleep(2000);
			  //---------------------------------sold by
		
			 
			  driver.findElement(By.xpath(prop.getProperty("Sold_by"))).sendKeys(prop.getProperty("sold_by_value"));
			 
			  //managed by
	
			  
			  driver.findElement(By.xpath(prop.getProperty("managed_by"))).sendKeys(prop.getProperty("managed_by_value"));
			  
		
			  
			  //click on continue
			  
			  driver.findElement(By.xpath(prop.getProperty("customer_info_continue"))).click();
		
			  int err=error();
			  System.out.println(err);
			  if(err!=0)
			  {
				  
				  if(err==1)
				  {
					 
					  statusupdate( rw , "country not matching");
				  }
				  else
				  if(err==2)
				  {
					 ;
					  statusupdate( rw , "state not matching");
				  }
				  else
				  if(err==3)
				  {
					 
					  statusupdate( rw , "city not matching");
				  }
				
			  }
			  //select title
			  Thread.sleep(4000); 
			  
			  WebElement ele4=driver.findElement(By.xpath(prop.getProperty("Title")));
			  
			  Select sel4=new Select(ele4);
			  
			  sel4.selectByVisibleText("Mr");
			  
			  //select first name
			  
			  driver.findElement(By.xpath(prop.getProperty("firstname"))).sendKeys(prop.getProperty("firstname_value"));
			  //last name
			  
			  driver.findElement(By.xpath(prop.getProperty("lastname"))).sendKeys(prop.getProperty("lastname_value"));
			 
			  
			  //select designation
			  
			  WebElement ele5=driver.findElement(By.xpath(prop.getProperty("designation")));
			  
			  
			  Select sel5=new Select(ele5);
			  
			  sel5.selectByVisibleText(prop.getProperty("designation_value"));
			  
			  //email id
			  
			  driver.findElement(By.xpath(prop.getProperty("contact_info_email_id"))).sendKeys(prop.getProperty("contact_info_email_id_value"));
			  
			  //Contact type
			  WebElement ele6=driver.findElement(By.xpath(prop.getProperty("contact_type")));
			  
			  Select sel6=new Select(ele6);
			  
			  sel6.selectByVisibleText("Primary");
			  
			  driver.findElement(By.xpath(prop.getProperty("contact_info_phone"))).sendKeys(prop.getProperty("contact_info_phone_no"));
			  
			  //continue
			  driver.findElement(By.xpath(prop.getProperty("contact_info_continue"))).click();
			  
			  //valid username
			  driver.findElement(By.xpath(prop.getProperty("Voucher_emailid"))).sendKeys(prop.getProperty("Voucher_emailid_value"));
			  
			  //continue
			  driver.findElement(By.xpath(prop.getProperty("voucher_continue"))).click();
			  
			  
			  //Latitude
			  driver.findElement(By.xpath(prop.getProperty("latitude"))).sendKeys(prop.getProperty("latitude_value"));
			  
			  //Longitude
			  driver.findElement(By.xpath(prop.getProperty("longitude"))).sendKeys(prop.getProperty("longitude_value"));
			  
			  //continue
			  // driver.findElement(By.xpath(".//*[@id='wid-id-cms']/header/div[3]/button")).click();
			  
			    Screen  scn3= new Screen();
			    
			    //String img3=prop.getProperty("continue_map");
			    String img3="D://sikili//Continue_map.PNG";
			    Pattern pt3=new Pattern(img3);
				
			    scn3.click(pt3);
				
			    Thread.sleep(3000);
				
			    //save button in get review page
			    Screen  scn4= new Screen();
				
			   // String img4=prop.getProperty("save");
			    String img4 ="D://sikili//save.PNG";
			    Pattern pt4=new Pattern(img4);
			    
			    scn4.click(pt4);
					
			    Thread.sleep(3000);
			
			    //check if custcode already exist are not
					 int err1=error();
					 if(err1!=0)
					  {
						  
						  if(err1==4)
						  {
							  statusupdate( rw , "custcode already exits");
						  }
						  
					  }
             //save
			 Screen  scn5= new Screen();
		    
			//String img5=prop.getProperty("white_save");
			 String img5 ="white_save";
			 Pattern pt5=new Pattern(img5);
			 
			 scn5.click(pt5);

		      //driver.findElement(By.xpath(".//*[@id='bootstrap-wizard-1']/div[2]/div/div/a[1]")).click();
			  
			 /* Thread.sleep(6000);
			  Screen  scn6= new Screen();
			  String img6="D://sikili//whitesave.PNG";
			  Pattern pt6=new Pattern(img6);
			  scn6.click(pt6);
			  Thread.sleep(5000);
			  /*Screen  scn8= new Screen();
			  String img8="D://sikili//success.PNG";
			  Match m=scn8.exists(img8);
			  if(m!=null)
			  {
			  Thread.sleep(3000);
			  Screen  scn7= new Screen();
			  String img7="D://sikili//white_ok.PNG";
			  Pattern pt7=new Pattern(img7);
			  scn7.click(pt7);*/
			  
			  
			   Thread.sleep(20000);
			   
			   FileInputStream file1= new FileInputStream(prop.getProperty("enroll_hotel_file"));

			   XSSFWorkbook workbook1 = new XSSFWorkbook (file1);
				
			   XSSFSheet sheet1 = workbook1.getSheetAt(0);
				
			   XSSFRow row1 = sheet1.getRow(rw);
				
			   XSSFCell r1c1 = row1.createCell(0);
				
			   r1c1.setCellValue("PASS");
				
			   file1.close();
				
			   FileOutputStream fos =new FileOutputStream(prop.getProperty("enroll_hotel_file"));
			    
			   workbook1.write(fos);
			    
			   fos.close();
			    
			   driver.close();
			   //hotel content code (6-07-2016)
			   if(prop.getProperty("HotelContent_status")=="0")
			   {
				   ArrayList list = new ArrayList();
				   String enroll_status =  prop.getProperty("enroll_hotel_file");
				   list.add(hotelcode1);
				   for(int j=6;j<11;j++)
				   {  
					
					  String value=(String)data.get(i+j);
					  list.add(value);	  
				   }
				   HotelContent.hotel_content(list,rw,enroll_status);
			   }
			 
			   //end 
			  }
		} 
		
		  catch(Exception sel)
		  {
			  if(data.get(i).equals("FAIL"))
			  {
				FileInputStream file1= new FileInputStream(prop.getProperty("enroll_hotel_file"));

				XSSFWorkbook workbook1 = new XSSFWorkbook (file1);
				
				XSSFSheet sheet1 = workbook1.getSheetAt(0);
				
				XSSFRow row1 = sheet1.getRow(rw);
				
				XSSFCell r1c1 = row1.createCell(0);
				
				r1c1.setCellValue("FAIL");
				
				file1.close();
				
				FileOutputStream fos =new FileOutputStream(prop.getProperty("enroll_hotel_file"));
			    
				workbook1.write(fos);
			    
				fos.close();
			  }
			    driver.close();    
			   
		  } 
		  
			
	
		}//for loop
	
	}//if loop
	  if("1"=="1")
	   {
		  String content_path=prop.getProperty("enroll_hotel_file");
		
		   ArrayList value1 = DataExtractor.extractor(content_path);
		 
		   HotelContent.hotelContent(value1,content_path);
		   
	   }
	}

public static void statusupdate(int m ,String status) throws IOException
  {
	  

	   FileInputStream file3= new FileInputStream(prop.getProperty("enroll_hotel_file"));
       
		XSSFWorkbook workbook3 = new XSSFWorkbook (file3);
	
		XSSFSheet sheet3 = workbook3.getSheetAt(0);
		
		XSSFRow row3 = sheet3.getRow(m);
		
		XSSFCell r1c9 = row3.createCell(9);
		
		r1c9.setCellValue(status);
		
		file3.close();
		
		FileOutputStream fos =new FileOutputStream(prop.getProperty("enroll_hotel_file"));
		
		workbook3.write(fos);
		
		fos.close();
  }
  
  public static  int error() throws InterruptedException
  {
	  
      
	  ArrayList<String> str=new ArrayList<String>();
	  str.add("D://sikili//countryname.PNG");
	  str.add("D://sikili//statename.PNG");
	  str.add("D://sikili//cityname.PNG");
	  str.add(" D://sikili//custcodealready.PNG");
	  int i=1;
	  Screen scn=new Screen();
	  for(String s:str)
	  {
		  Match m=scn.exists(s);

		  Thread.sleep(2000);
		  
		  if(m!=null)
		  {
			  
			 return i; 
			
		  }
		  i++;
	  }
	  return 0;
  }


}

