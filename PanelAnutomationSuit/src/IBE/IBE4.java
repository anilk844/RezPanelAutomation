package IBE;



import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.sun.org.apache.bcel.internal.generic.NEW;

public class IBE4
{
	
	
public static int xpathflag=0;
public static WebDriver driver;
static boolean flag=true;
public static ArrayList data;
public static WebDriverWait wait;
public static boolean intialFlag=true;

public static int totalAmount=0;
public static List<Integer>ticketRates;
 public static int ticketflag;
 public static int tktcount=0;

@DataProvider
public Object[][] getData() throws IOException
{
	
	data= new ArrayList();
	FileInputStream file= new FileInputStream("D://IBE TestCase//IBETestData.xlsx");
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
	int rw=data.size()/16;
	Object a[][]=new Object[rw][16];
	int j=0;
	for(int i=16;i<data.size();i=i+16)
	{
		
	    double a1=(double)data.get(i);
	    int DayCount=(int) Math.round(a1);
	    a[j][0]=DayCount;
	    double b=(double)data.get(i+1);
	    int AdultCount=(int) Math.round(b);
	    a[j][1]=AdultCount;
	    double c=(double)data.get(i+2);
	    int ChildCount=(int) Math.round(c);
	    a[j][2]=ChildCount;
	    double d=(double)data.get(i+3);
	    int InfantCount=(int) Math.round(d);
	    a[j][3]=InfantCount;
	    double e=(double)data.get(i+4);
	    int RoomCount=(int) Math.round(e);
	    a[j][4]=RoomCount;
	    String RoomName=(String) data.get(i+5);
	    a[j][5]=RoomName;
	    String MealPlan=(String) data.get(i+6);
	    a[j][6]=MealPlan;
	    double f=(double)data.get(i+7);
	    int PackageCount=(int) Math.round(f);
	    a[j][7]=PackageCount;
	    String PackageName=(String) data.get(i+8);
	    a[j][8]=PackageName;
	    String PackageMealPlanName=(String) data.get(i+9);
	    a[j][9]=PackageMealPlanName;
	    double g=(double)data.get(i+10);
	    int AddOnsCount=(int) Math.round(g);
	    a[j][10]=AddOnsCount;
	    String AddOnsName=(String) data.get(i+11);
	    a[j][11]=AddOnsName;
	    double h=(double)data.get(i+12);
	    int TicketCount=(int) Math.round(h);
	    a[j][12]=TicketCount;
	    String TicketName=(String) data.get(i+13);
	    a[j][13]=TicketName;
	    String PackageType=(String) data.get(i+14);
	    a[j][14]=PackageType;
	    String TestCaseName="("+(String) data.get(i+15)+")";
		a[j][15]=TestCaseName;
		j=j+1;
		
	}
	return a;
}


@Test(dataProvider="getData")
public static void IBE(int DayCount,int AdultCount,int ChildCount,int InfantCount,int RoomCount,String RoomName,String MealPlan,int PackageCount,String PackageName,String PackageMealPlanName, int AddOnsCount,String AddOnsName,int TicketCount,String TicketName,String PackageType,String TestCaseName) throws InterruptedException, InvalidFormatException, IOException
{    
     xpathflag=0;
	System.out.println(TestCaseName);
    ticketflag=0;
	totalAmount=0;
	tktcount=0;
	ticketRates=new ArrayList<Integer>();
	if(intialFlag)
	{
		
		
	  System.setProperty("webdriver.chrome.driver", "D://chrome//chromedriver.exe");
	  DesiredCapabilities capabilities = DesiredCapabilities.chrome();
      ChromeOptions options = new ChromeOptions();
      options.addArguments("test-type");
      options.addArguments("--start-maximized");
      options.addArguments("--disable-web-security");
      options.addArguments("--allow-running-insecure-content");
      HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
      chromePrefs.put("profile.default_content_settings.popups", 0);

      options.setExperimentalOption("prefs", chromePrefs);
      capabilities.setCapability("chrome.binary","D://chrome//chromedriver.exe");
      capabilities.setCapability(ChromeOptions.CAPABILITY, options);
      driver = new ChromeDriver(capabilities);
      wait=new WebDriverWait(driver, 100);
      intialFlag=false;
	
	
	}
    
    
    driver.get("https://rezev4qa.azurewebsites.net/IBE-QA");
    
    
    SimpleDateFormat DF = new SimpleDateFormat("dd-MMM-yyyy");
    Calendar RC = Calendar.getInstance();
    String Startdate=DF.format(RC.getTime());
    
    RC.add(Calendar.DATE, DayCount);
	String Enddate=DF.format(RC.getTime());
	
	datecreste(Startdate,Enddate);
    driver.findElement(By.xpath("//*[@id='guestdetailsbtn']")).click();
    for(int AD=AdultCount;AD>1;AD--)
    {
    	
    	driver.findElement(By.xpath("//*[@id='GuestDetails']/div/div[1]/div[1]/div[2]/span[2]/i")).click();
    }
   //child count selection
   
    for(int CD=ChildCount;CD>0;CD--)
    {
    	driver.findElement(By.xpath("//*[@id='GuestDetails']/div/div[1]/div[2]/div[2]/span[2]/i")).click();
    }
    //Infant count selection
    
    for(int ID=InfantCount;ID>0;ID--)
    {
    	driver.findElement(By.xpath("//*[@id='GuestDetails']/div/div[1]/div[3]/div[2]/span[2]/i")).click();
    }
    //guest Done Button
  
    driver.findElement(By.xpath("//*[@id='GuestDetails']/div/div[2]/div/button")).click();
    //search button
 
    driver.findElement(By.cssSelector("*[class^='btn btn-default searchbtn']")).click();
    WebDriverWait addmoreRoomwait=new WebDriverWait(driver, 3);
    if(RoomCount>0)
    {
    	
      // System.out.println(RoomName);
       String rmName[]=RoomName.split("\\+");
       String mpName[]=MealPlan.split("\\+");
       for(int j=0;j<rmName.length;j++)
       {
    	   try
       	{
    		JavascriptExecutor js = ((JavascriptExecutor) driver);
			WebElement element = driver.findElement(By.cssSelector("*[class^='btn btn-default rmbRds add-more-rooms']"));
			//Now scroll to this element 
			js.executeScript("arguments[0].scrollIntoView(true);", element);
       		addmoreRoomwait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("*[class^='btn btn-default rmbRds add-more-rooms']")));
       		driver.findElement(By.cssSelector("*[class^='btn btn-default rmbRds add-more-rooms']")).click();
       	}
       	catch(Exception z)
       	{
       		System.out.println(z);
       	}
    	   bookroom(rmName[j],mpName[j]);
    	   xpathflag=xpathflag+1;
       }
    }
    
    if(PackageCount>0)
    {
	
     
       String pkName[]=PackageName.split("\\+");
       String pmName[]=PackageMealPlanName.split("\\+");
       String pkType[]=PackageType.split("\\+");
       for(int j=0;j<pkName.length;j++)
       {
    	   try
       	{
    		JavascriptExecutor js = ((JavascriptExecutor) driver);
			WebElement element = driver.findElement(By.cssSelector("*[class^='btn btn-default rmbRds add-more-rooms']"));
			   //Now scroll to this element 
			js.executeScript("arguments[0].scrollIntoView(true);", element);   
       		addmoreRoomwait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("*[class^='btn btn-default rmbRds add-more-rooms']")));
       		driver.findElement(By.cssSelector("*[class^='btn btn-default rmbRds add-more-rooms']")).click();
       	}
       	catch(Exception z)
       	{
       		System.out.println(z);
       	}
    	   System.out.println("inside"+pkType[j]+" "+pkName[j]+" "+pmName[j]);
    	   packageBook(pkType[j],pkName[j],pmName[j]);
    	   xpathflag=xpathflag+1;
       }
    }
    if(TicketCount>0)
    {
    	System.out.println(TicketCount);
    	tktcount=TicketCount;
    	String TkName[]=TicketName.split("\\+");
    	for(int ai=0;ai<TkName.length;ai++)
    	{
    		try
           	{
    			 JavascriptExecutor js = ((JavascriptExecutor) driver);
    			   WebElement element = driver.findElement(By.cssSelector("*[class^='btn btn-default rmbRds add-more-rooms']"));
    			   //Now scroll to this element 
    			   js.executeScript("arguments[0].scrollIntoView(true);", element);
           		addmoreRoomwait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("*[class^='btn btn-default rmbRds add-more-rooms']")));
           		driver.findElement(By.cssSelector("*[class^='btn btn-default rmbRds add-more-rooms']")).click();
           	}
           	catch(Exception z)
           	{
           		System.out.println(z);
           	}
    		System.out.println("hello");
    	    TicketBooking(TkName[ai]);
    	    xpathflag=xpathflag+1;
        }
    }
    
    if(AddOnsCount>0)
    {
    	int amount=totalAmount;
    	String AddName[]=AddOnsName.split("\\+");
    	for(int ai=0;ai<AddName.length;ai++)
    	{
    		
    	    AddOnsBooking(AddName[ai],AddOnsCount);
    	    
    	    
        }
    }
    Thread.sleep(2000);
    wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("*[class='table table-condensed borderless']")));
    WebElement tax=driver.findElement(By.cssSelector("*[class='table table-condensed borderless']"));
    WebElement TaxTd=tax.findElement(By.cssSelector("*[class='col-sm-4 text-right']"));
    String TaxAmount=TaxTd.findElement(By.cssSelector("*[class='font-size-13 ']")).getText();
    String specialchar[]={" ",",","\""};
	for(String s :specialchar)
	{
		TaxAmount=TaxAmount.replace(s, "");
	}
	String parseString=TaxAmount;
	totalAmount=totalAmount+Integer.parseInt(parseString);
	
    String BooingtotalAmt=driver.findElement(By.cssSelector("*[class^='total-amount']")).getText();
    String totalAmt=BooingtotalAmt;
   
    for(String s :specialchar)
	{
    	totalAmt=totalAmt.replace(s, "");
	}
	String parseStringtotalAmt=totalAmt;
	int totalAMTValue=Integer.parseInt(parseStringtotalAmt);
	/*int bookingrateCheckFlag=0;
	if(ticketRates.size()>0)
	{
	int FinalTotalAmount=0;
    for(int a :ticketRates)
    {
    	if(bookingrateCheckFlag==0)
    	{
    	FinalTotalAmount=totalAmount+a;
    	totalAmount=FinalTotalAmount;
    	if(totalAMTValue==totalAmount)
    	{
    		bookingrateCheckFlag=1;
    	}
    	}
    }
	}*/
    if(totalAmount==totalAMTValue)
    {
    	System.out.println("total Amount:- "+totalAmount);
    	System.out.println("Booing Summary Amount:-"+totalAMTValue);
    	Reporter.log("<font font-family='Times New Roman'>IBE--</font><font color='blue'>"+TestCaseName+"-:( Total Amount :-"+totalAmount+" and Booking Summary Page Total Amount :-"+totalAMTValue+"is Matched ):-PASS</font></a>", true);
    	System.out.println("pass");
    }
    else
    {
    	System.out.println("amount mismatch");
    	Reporter.log("<font font-family='Times New Roman'>IBE--</font><font color='red'>"+TestCaseName+"-:( Total Amount :-"+totalAmount+"and Booking Summary Page Total Amount :-"+totalAMTValue+" is not Matched ):-FAIL</font></a>", true);
    }
    
    guestDetails();
    
    

	JavascriptExecutor js = ((JavascriptExecutor) driver);
	WebElement element = driver.findElement(By.xpath("//*[@id='User_TermsAndConditions']"));
	//Now scroll to this element 
	js.executeScript("arguments[0].scrollIntoView(true);", element);
    driver.findElement(By.xpath("//*[@id='User_TermsAndConditions']")).click();
    Thread.sleep(2000);
    driver.findElement(By.xpath("//*[@id='PaymentSubmit']/div[2]/div[2]/div/button")).click();
    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='amount1']")));
    String amt=driver.findElement(By.xpath("//*[@id='amount1']")).getText();
    System.out.println("Booking Summary amount:-"+totalAmt);
    System.out.println("Voucher Page amount:-"+amt);
    if(amt.equalsIgnoreCase(BooingtotalAmt))
    {
    	Reporter.log("<font font-family='Times New Roman'>IBE--</font><font color='blue'>"+TestCaseName+"-:( Booking Summary Page Total :-"+amt+"and Voucher Page Amount :-"+BooingtotalAmt+" is Matched ):-PASS</font></a>", true);
    	System.out.println("pass");
    }
    else
    {
    	System.out.println("amount mismatch");
    	Reporter.log("<font font-family='Times New Roman'>IBE--</font><font color='red'>"+TestCaseName+"-:( Booking Summary Page Total :-"+amt+" and Voucher Page Amount :-"+BooingtotalAmt+" is not Matched ):-FAIL</font></a>", true);
    }
    
     
    
}
    



public static  void execute() throws IOException, InvalidFormatException
{
	data= new ArrayList();
	System.out.println("Inside dataextractor");
	FileInputStream file= new FileInputStream("D://IBE TestCase//IBETestData.xlsx");
	
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
	System.out.println(data);
	
}



   public static void bookroom(String roomtype1,String meal1) throws InterruptedException
   {
       //driver.findElement(By.xpath("//*[@id='ScrollRoom']/div[1]/div/div[1]/div[1]/div[1]/div/a")).click();
	   wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='content-loader']")));
	   wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='rooms']/img")));
	   wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='flip_maindiv']/div/img")));
       wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='flip_maindiv']/div/div/img")));
   	   JavascriptExecutor js = ((JavascriptExecutor) driver);

		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	
		List<WebElement>mealPlan1 =new LinkedList<WebElement>();
		List<WebElement>mealPlan2 =new LinkedList<WebElement>();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("*[class^='col-sm-12 pad-zero rate-inventory read-more rooms-info']")));
    	List<WebElement> roomtype=driver.findElements(By.cssSelector("*[class^='col-sm-12 pad-zero rate-inventory read-more rooms-info']"));
    	Boolean RoomNameflag=true;
    	Boolean RoomMealflag=true;
    	WebDriverWait mealwait=new WebDriverWait(driver,4);
    	for(WebElement web:roomtype)
    	{
    		if(RoomNameflag)
    		{
    		WebElement roomName=web.findElement(By.cssSelector("*[class^='label-Blue col-sm-8 pad-zero']"));
    		String roomname1=roomName.getText();
    
    		if(roomname1.equalsIgnoreCase(roomtype1))
    		{
    			RoomNameflag=false;
    			
    			Thread.sleep(6000);
    			try{
    			                              
    			mealwait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("*[class^='col-xs-12 pad-zero margin-align mealplan-rate-info']")));
    			
    			mealPlan1= web.findElements(By.cssSelector("*[class^='col-xs-12 pad-zero margin-align mealplan-rate-info']"));
    			
    			}
    			catch(Exception e)
    			{
    				System.out.println(e);
    			}
    			try{
    				mealwait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("*[class^='col-xs-12 pad-zero margin-align promotion-rate-info']")));
    			
        		     mealPlan2=web.findElements(By.cssSelector("*[class^='col-xs-12 pad-zero margin-align promotion-rate-info']"));
        		
        		}
        		catch(Exception e)
        		{
        			System.out.println(e);
        		}
    			mealPlan1.addAll(mealPlan2);
    		
    		
    			for(WebElement meal:mealPlan1)
    			{
    				if(RoomMealflag)
    				{
        			
    				String mealplanweb=meal.findElement(By.cssSelector("*[class^='day-roomlabel']")).getText();
    				
    				if(mealplanweb.equalsIgnoreCase(meal1))
    				{
    					
    					String price=meal.findElement(By.cssSelector("*[class^='promo-availablerate total-room-rate']")).getText();
    					String specialchar[]={",","\""};
    					for(String s :specialchar)
    					{
    						price=price.replace(s, "");
    					}
    					String parseString=price;
    					totalAmount=totalAmount+Integer.parseInt(parseString);
    					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    					RoomMealflag=false;
    					wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("button")));
    	                
    					meal.findElement(By.tagName("button")).click();
    					
    					try
    					{
    						
    						WebElement popup=driver.findElement(By.xpath("//*[@id='checkindate-popup']/div"));
    						popup.findElement(By.xpath("//*[@id='checkindate-popup']/div/div/div[3]/button")).click();
    					}
    					catch(Exception e)
    					{
    						System.out.println(e);
    					}
    					flag=false;
    					break;
    			      
    				}
    				}
    			}
    		 }
    	    } 
    	}
    	
    }
   
  
   
   
   
   public static void packageBook(String PackageType,String PackageName,String MealType) throws InterruptedException
   {
	 //*[@id="flip_maindiv"]/div/div/img
	 //*[@id="rooms"]/img
	   Boolean PackageNameflag=true;
	   Boolean PackageTypeflag=true;
       WebDriverWait packtype=new WebDriverWait(driver,4);
	   wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='content-loader']")));
	   //wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='rooms']/img")));
	   //wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='flip_maindiv']/div/img")));
       //wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='flip_maindiv']/div/div/img")));
       try
       {
    	   if(xpathflag==0)
    	   {
    		System.out.println("Inside");
    		//*[@id="packagetab"]/a
    	   packtype.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='packagetab']/a")));
    	   driver.findElement(By.xpath("//*[@id='packagetab']/a")).click();
    	   }
       }
       catch(Exception e)
       {
    	   System.out.println();
       }
       try
       {
    	   if(xpathflag!=0)
    	   {
    	      packtype.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='hpackagetab']/a")));
    	      driver.findElement(By.xpath("//*[@id='hpackagetab']/a")).click();
    	   }
       }
       catch(Exception e)
       {
    	   System.out.println();
       }
	//*[@id="hpackagetab"]/a
	//*[@id="packagetab"]/a
       
       wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='package']/img")));
       Thread.sleep(2000);
       JavascriptExecutor js = ((JavascriptExecutor) driver);

		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	     wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("*[class^='lead margin-clear text-left']")));
	 //List<WebElement>WebPackageType=driver.findElements(By.cssSelector("*[class^='lead margin-clear text-left']"));
	 
	    List<WebElement>WebPackageType=driver.findElements(By.cssSelector("*[class^='overlay-container overlay-visible']"));
	 
	 for(WebElement a :WebPackageType)
	 {
		
		 if(PackageTypeflag)
		 {
			 String name=a.findElement(By.cssSelector("*[class^='lead margin-clear text-left']")).getText();
			
		 if(name.equalsIgnoreCase(PackageType))
		 {
			 PackageTypeflag=false;
			 a.findElement(By.cssSelector("*[class^='overlay-link popup-img']")).click();
			 wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("*[class^='col-sm-12 pad-zero packages-info']")));
			 List<WebElement>WebPackageName=driver.findElements(By.cssSelector("*[class^='col-sm-12 pad-zero packages-info']"));
			 for(WebElement b :WebPackageName)
			 {
				 
				 if(PackageNameflag)
				 {
					 String pkname=b.findElement(By.cssSelector("*[class^='label-Blue']")).getText();
					 
				 if(pkname.equalsIgnoreCase(PackageName))
				 {
					 PackageNameflag=false;
					
					 WebElement mealPlanSelectDropDown=b.findElement(By.cssSelector("*[class^='form-control selected-package-mealplan']"));
					
					 Select sel=new Select(mealPlanSelectDropDown);
					 sel.selectByVisibleText(MealType);
					
					 String price=b.findElement(By.cssSelector("*[class^='package-price']")).getText();
					String specialchar[]={" ",",","\""};
 					for(String s :specialchar)
 					{
 						price=price.replace(s, "");
 					}
 					String parseString=price;
 					totalAmount=totalAmount+Integer.parseInt(parseString);
 					// wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("*[class^='lead margin-clear text-left']")));
                    WebElement button=b.findElement(By.cssSelector("*[class^='btn btn-default  book-pakage']"));
                    JavascriptExecutor js1 = ((JavascriptExecutor) driver);
             	   //Now scroll to this element 
             	    js1.executeScript("arguments[0].scrollIntoView(true);", button);
 				    
 					 b.findElement(By.cssSelector("*[class^='btn btn-default  book-pakage']")).click();
 					 Thread.sleep(2000);
 					try
 					{
 						System.out.println("-----------------------");
 						WebElement dialogbox =driver.findElement(By.xpath("//*[@id='checkindate-popup']"));
 						dialogbox.findElement(By.xpath("//*[@id='checkindate-popup']/div/div/div[3]/button")).click();
 						
 						
 					}catch(Exception e)
 					{
 						System.out.println(e);
 					}
					
					 
				 }
			 }
			 }
		 }
		 }
	 }
	   
   } 
   
   
   
   
   
   public static void TicketBooking(String Ticketname)
   {
	   ticketRates=new ArrayList<Integer>();
	   boolean tickettypeflag=true;
	   WebDriverWait TicketType=new WebDriverWait(driver,4);
	  
	   wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='content-loader']")));
	   System.out.println("hello");
	 //*[@id="hofferstab"]/a
	   try
	   {
		   if(xpathflag==0)
    	   {
			   System.out.println("hello111");
		      TicketType.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='offerstab']/a")));
		      driver.findElement(By.xpath("//*[@id='offerstab']/a")).click();
    	   }
	   }
	  catch(Exception e)
	   {
		  System.out.println();
	   }
	   
	   try
	   {
		   if(xpathflag!=0)
    	   {
		      TicketType.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='hofferstab']/a")));
		      driver.findElement(By.xpath("//*[@id='hofferstab']/a")).click();
    	   }
	   }
	  catch(Exception e)
	   {
		  System.out.println();
	   }
	   
	    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='offers']/img")));
	    JavascriptExecutor js = ((JavascriptExecutor) driver);

		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	    wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("*[class^='col-lg-4 rate-inventory col-md-4 col-sm-6 col-xs-12 rooms-info grid-roomtype']")));
        List<WebElement>Ticket1=driver.findElements(By.cssSelector("*[class^='col-lg-4 rate-inventory col-md-4 col-sm-6 col-xs-12 rooms-info grid-roomtype']"));
       for(WebElement a :Ticket1)
       {
    	   if(tickettypeflag)
    	   {
    	   String WebticketNAme=a.findElement(By.tagName("span")).getText();
    	   if(WebticketNAme.equalsIgnoreCase(Ticketname))
    	   {
    		   
    		    List<WebElement>rates=a.findElements(By.cssSelector("*[class='weekrate rate-fnt']"));
    		    for(WebElement e :rates)
    		    {
    		    	String Rates=e.getText();
    		    	String specialchar[]={" ",",","\""};
    				for(String s :specialchar)
    				{
    					Rates=Rates.replace(s, "");
    				}
    				ticketRates.add(Integer.parseInt(Rates));
    				
    				 
    		    }
    		    	
				
				
				//totalAmount=totalAmount+Integer.parseInt(parseString);
    		   tickettypeflag=false;
    		   a.findElement(By.cssSelector("*[class^='btn btn-default btn-custom ticket-book pull-right']")).click();
    	   }
    	   }
       }
       
       JavascriptExecutor js1 = ((JavascriptExecutor) driver);
	   WebElement element = driver.findElement(By.cssSelector("*[class^='total-amount']"));
	   //Now scroll to this element 
	   js1.executeScript("arguments[0].scrollIntoView(true);", element);
       
        String specialchar[]={" ",",","\""};
	    String BooingtotalAmt=driver.findElement(By.cssSelector("*[class^='total-amount']")).getText();
	    String totalAmt=BooingtotalAmt;
	    for(String s :specialchar)
		{
	    	totalAmt=totalAmt.replace(s, "");
		}
		String parseStringtotalAmt=totalAmt;
	    int totalAMTValue=Integer.parseInt(parseStringtotalAmt);
		int bookingrateCheckFlag=0;
	    if(ticketRates.size()>0)
		{
		
	    for(int a :ticketRates)
	    {int FinalTotalAmount=0;
	    	if(bookingrateCheckFlag==0)
	    	{
	    	FinalTotalAmount=totalAmount+a;
	    	//totalAmount=FinalTotalAmount;
	    	
	    	wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("*[class='table table-condensed borderless']")));
		    WebElement tax=driver.findElement(By.cssSelector("*[class='table table-condensed borderless']"));
		    WebElement TaxTd=tax.findElement(By.cssSelector("*[class='col-sm-4 text-right']"));
		    String TaxAmount=TaxTd.findElement(By.cssSelector("*[class='font-size-13 ']")).getText();
		    
			for(String s :specialchar)
			{
				TaxAmount=TaxAmount.replace(s, "");
			}
			String parseString=TaxAmount;
			//totalAmount=totalAmount+Integer.parseInt(parseString);
			FinalTotalAmount=FinalTotalAmount+Integer.parseInt(parseString);
			System.out.println("FinalTotalAmount "+FinalTotalAmount);
			System.out.println("totalAMTValue "+totalAMTValue);
	    	if(totalAMTValue==FinalTotalAmount)
	    	{
	    		bookingrateCheckFlag=1;
	    		totalAmount=FinalTotalAmount;
	    	}
	    	}
	    }
	    
		}
	    if(tktcount>=0)
	    {
	    	wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("*[class='table table-condensed borderless']")));
		    WebElement tax=driver.findElement(By.cssSelector("*[class='table table-condensed borderless']"));
		    WebElement TaxTd=tax.findElement(By.cssSelector("*[class='col-sm-4 text-right']"));
		    String TaxAmount=TaxTd.findElement(By.cssSelector("*[class='font-size-13 ']")).getText();
	    	String parseString=TaxAmount;
	    	totalAmount=totalAmount-Integer.parseInt(parseString);
	    	tktcount=tktcount-1;
	    	  //System.out.println("Total Amount Inside Ticket Count**----"+totalAmount);
	    }
	    
	    System.out.println("Total Amount Inside Ticket Count----"+totalAmount);
	    
   }
   
   
   
   
   
   public static void AddOnsBooking(String AddonsService,int count) throws InterruptedException
   {
	   
	   System.out.println("1");
	   JavascriptExecutor js = ((JavascriptExecutor) driver);
	   WebElement element = driver.findElement(By.cssSelector("*[class^='col-sm-12 myaddons']"));
	   //Now scroll to this element 
	   js.executeScript("arguments[0].scrollIntoView(true);", element);
	   boolean addonsname=true;
	   //col-md-12 col-sm-12 addons list-group ng-scope addons-list
	   List<WebElement>addls=element.findElements(By.cssSelector("*[class^='col-md-12 col-sm-12 addons list-group ng-scope addons-list']"));
	   for(WebElement a :addls)
	   {
		   System.out.println("1");
		   if(addonsname)
		   {
		   String AddOnsName=a.findElement(By.cssSelector("*[class^='col-md-8 col-sm-8 addons-details pad-zero']")).getText();
		   System.out.println(AddOnsName);
		   System.out.println(AddonsService);
		   if(AddOnsName.equalsIgnoreCase(AddonsService))
		   {
			   System.out.println("1");
		       addonsname=false;
			   WebElement addonscount=a.findElement(By.cssSelector("*[class^='update-addon']"));
			   Select sel=new Select(addonscount);
			   sel.selectByVisibleText(String.valueOf(count));
			   
			    List<WebElement> ls=a.findElements(By.cssSelector("*[class^='col-md-2 col-sm-2 addons-rate pad-zero ng-binding']"));
			    String price=ls.get(2).getText();
			    System.out.println("----------------"+price);
				String specialchar[]={" ",",","\""};
				for(String s :specialchar)
				{
					price=price.replace(s, "");
				}
				String parseString=price;
				totalAmount=totalAmount+Integer.parseInt(parseString);
			    a.findElement(By.cssSelector("*[class^='fa fa-plus']")).click();
			    Thread.sleep(3000);
			   
		   }
		   }
	   }
   }
   
   
   
   
   public  static void guestDetails() throws InterruptedException
   {
	   
		JavascriptExecutor js = ((JavascriptExecutor) driver);

		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	   WebElement guest= driver.findElement(By.xpath("//*[@id='guestinfo']"));
	   WebElement FirstName=guest.findElement(By.id("User_Firstname"));
	   WebElement LastName=guest.findElement(By.id("User_Lastname"));
	   WebElement MobileNumber=guest.findElement(By.id("User_Mobilenumber"));
	   WebElement Email=guest.findElement(By.id("User_EmailID"));
	   String FirstName1="TestBooking";
	   String LastName1="PleaseIgnore";
	   String MobileNumber1="9632704106";
	   String Email1="anil.kumar@reznext.com";
	   List<WebElement>first=driver.findElements(By.xpath("//*[contains(@id, 'firstName')]"));
	   List<WebElement>last=driver.findElements(By.xpath("//*[contains(@id, 'lastName')]"));
	   FirstName.sendKeys(FirstName1);
	   LastName.sendKeys(LastName1);
	   MobileNumber.sendKeys(MobileNumber1);
	   Email.sendKeys(Email1);
	   for(WebElement e:first)
	   {
		   e.sendKeys(FirstName1);
	   }
	   for(WebElement e1:last)
	   {
		   e1.sendKeys(LastName1);
	   }
	   
	   System.out.println("hello"+first.size());
   }
   
   
   
   
   
   public static void datecreste(String Startdate,String Enddate)
   {
	   String sd[]=Startdate.split("\\-");
	   String ed[]=Enddate.split("\\-");
	   String startday=sd[0];
	   String startmon=sd[1];
	   String startYear=sd[2];
	   String endday=ed[0];
	   String endMon=ed[1];
	   String endYear=ed[2];
	   
	   driver.findElement(By.xpath("//*[@id='checkin']")).click();
	   WebElement checkindropdown =driver.findElement(By.cssSelector("*[class^='datepicker datepicker-dropdown dropdown-menu datepicker-orient-left datepicker-orient-bottom']"));
	   
	   checkindropdown.findElement(By.cssSelector("*[class^='datepicker-switch']")).click();
	   List<WebElement>checkinmon=checkindropdown.findElements(By.tagName("span"));
	   boolean startmonflag=true;
	   for(WebElement a:checkinmon)
	   {
		   if(startmonflag)
		   {
		   String mon=a.getText();
		   if(mon.equalsIgnoreCase(startmon))
		   {  
			   startmonflag=false;
			   List<WebElement>curday=new LinkedList<WebElement>();
		       List<WebElement>furday=new LinkedList<WebElement>();
			   a.click();
			   curday=checkindropdown.findElements(By.cssSelector("*[class^='active day']"));
			   try
			   {
			   furday=checkindropdown.findElements(By.cssSelector("*[class^='day']"));
			   }
			   catch(Exception e)
			   {
				   
			   }
			   curday.addAll(furday);
			   System.out.println(curday.size());
			   boolean startdayflag=true;
			   for(WebElement b : curday)
			   {
				   if(startdayflag)
				   {
				   String day=b.getText();
				   System.out.println(day);
				   if(day.equalsIgnoreCase(startday))
				   {
					   b.click();
					   startdayflag=false;
				   }
				   }
			   }
		   }
		   }
	   }
	   
	   
	   
	   
	   //CheckOut
	   driver.findElement(By.xpath("//*[@id='checkout']")).click();
	   WebElement checkOutdropdown =driver.findElement(By.cssSelector("*[class^='datepicker datepicker-dropdown dropdown-menu datepicker-orient-left datepicker-orient-bottom']"));
	  
	   checkOutdropdown.findElement(By.cssSelector("*[class^='datepicker-switch']")).click();
	   List<WebElement>checkoutmon=checkOutdropdown.findElements(By.tagName("span"));
	   boolean endmonflag=true;
	   for(WebElement a:checkoutmon)
	   {
		   if(endmonflag)
		   {
		   String mon=a.getText();
		   if(mon.equalsIgnoreCase(endMon))
		   {  
			   endmonflag=false;
			   List<WebElement>curday=new LinkedList<WebElement>();
		       List<WebElement>furday=new LinkedList<WebElement>();
			   a.click();
			   curday=checkOutdropdown.findElements(By.cssSelector("*[class^='active day']"));
			   try
			   {
			   furday=checkOutdropdown.findElements(By.cssSelector("*[class^='day']"));
			   }
			   catch(Exception e)
			   {
				   
			   }
			   curday.addAll(furday);
			   boolean enddayflag=true;
			   for(WebElement b : curday)
			   {
				   if(enddayflag)
				   { 
				   String day=b.getText();
				   if(day.equalsIgnoreCase(endday))
				   {
					   b.click();
					   enddayflag=false;
				   }
				   }
			   }
		   }
		   }
	   }
	   
	   
	   
	   
	   
	   /*System.out.println(startmon);
	   System.out.println(startday);
	   System.out.println(startYear);
	   System.out.println(endMon);
	   System.out.println(endday);
	   System.out.println(endYear);*/
	   
	   
   }


}
