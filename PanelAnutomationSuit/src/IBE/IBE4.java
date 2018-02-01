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
import java.util.Properties;
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
 public static int tktvoucherCheck=1;
 public static int PackageVoucherCheck=1;
 public static String SummaryPage=null;
 public static Properties stat;

 
//Google Code To fetch data From Excel 
@DataProvider
public Object[][] getData() throws IOException
{
	
	data= new ArrayList(); //This data will store the rows and columns in single dimensional array 
	FileInputStream file= new FileInputStream("C:/Users/anil.kumar/git/RezPanelAutomation/PanelAnutomationSuit/src/IBE/IBETestData.xlsx");//accessing excel sheet
	XSSFWorkbook workbook = new XSSFWorkbook(file);//converting to workbook
	XSSFSheet sheet = workbook.getSheet("Sheet1");//Fetching sheet from workbook 
	Iterator itr = sheet.iterator();
	while(itr.hasNext())
	{
		Row RowItr = (Row)itr.next();//taking entire row  
		Iterator cellItr=RowItr.cellIterator();//considering data horizontally cell by cell 
	
		while(cellItr.hasNext())
		{
			Cell cells= (Cell)cellItr.next();
			switch(cells.getCellType())//fetching cell type 
			{
			case Cell.CELL_TYPE_STRING:data.add(cells.getStringCellValue());//if celltype is String then data will be added to Arraylist ie, data 
			break;
			case Cell.CELL_TYPE_NUMERIC:data.add(cells.getNumericCellValue());//Similarly numeric and boolean also gets added to Arraylist, this will store numeric value as 2.00
			break;
			case Cell.CELL_TYPE_BOOLEAN:data.add(cells.getBooleanCellValue());
			break;
			case Cell.CELL_TYPE_BLANK:
			break;
			}
		}
	}
	int rw=data.size()/16; 
	Object a[][]=new Object[rw][16];//2-dimential array of rows and 16 columns 
	int j=0;
	for(int i=16;i<data.size();i=i+16)//The first 16 columns are skipped(heading are skipped)
	{
		
	    double a1=(double)data.get(i);//converting into double to make sure data is stored in double 
	    int DayCount=(int) Math.round(a1);//This code is used handle integer stored in double format, eg: 2.00//parsing double to integer
	    a[j][0]=DayCount;//put daycount data in (0,0) cell
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
	    String TestCaseName="("+(String) data.get(i+15)+")";//Last value ie, i+15 = 16 + 15 
		a[j][15]=TestCaseName;
		j=j+1;
		
	}
	return a;//Returns entire row in each iteration and gets binded with IBE method 
}


@Test(dataProvider="getData")
public static void IBE(int DayCount,int AdultCount,int ChildCount,int InfantCount,int RoomCount,String RoomName,String MealPlan,int PackageCount,String PackageName,String PackageMealPlanName, int AddOnsCount,String AddOnsName,int TicketCount,String TicketName,String PackageType,String TestCaseName) throws InterruptedException, InvalidFormatException, IOException
{    
	tktvoucherCheck=1;
    PackageVoucherCheck=1;
    xpathflag=0;
	System.out.println(TestCaseName);
    ticketflag=0;
	totalAmount=0;
	tktcount=0;
	ticketRates=new ArrayList<Integer>();
	
	if(intialFlag)
	{
		
	  FileInputStream status = new FileInputStream("C:/Users/anil.kumar/git/RezPanelAutomation/PanelAnutomationSuit/src/IBE/IBESetting.properties");//To switch between old and new Summary page, presently not implemented.
	  stat=new Properties();
	  stat.load(status);
	  System.setProperty("webdriver.chrome.driver", "D://Maxim Chrom Driver//chromedriver.exe");
	  DesiredCapabilities capabilities = DesiredCapabilities.chrome();
      ChromeOptions options = new ChromeOptions();
      options.addArguments("test-type");
      options.addArguments("--start-maximized");
      options.addArguments("--disable-web-security");
      options.addArguments("--allow-running-insecure-content");
      HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
      chromePrefs.put("profile.default_content_settings.popups", 0);

      options.setExperimentalOption("prefs", chromePrefs);
      capabilities.setCapability("chrome.binary","D://Maxim Chrom Driver//chromedriver.exe");
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
  
    driver.findElement(By.xpath("//*[@id='GuestDetails']/div/div[2]/div/button")).click();//click on done in adult child selection popup
    //search button
 
    driver.findElement(By.cssSelector("*[class^='btn btn-default searchbtn']")).click();
    WebDriverWait addmoreRoomwait=new WebDriverWait(driver, 3);
    if(RoomCount>0)
    {
    	tktvoucherCheck=0;
    	PackageVoucherCheck=0;
      // System.out.println(RoomName);
       String rmName[]=RoomName.split("\\+"); //in case of multiple room selection split by +
       String mpName[]=MealPlan.split("\\+");//same for meal plan like room
       for(int j=0;j<rmName.length;j++)
       {
    	   try //first time this try catch block is not working only after going to summary page it will work
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
    	   xpathflag=xpathflag+1; //this is for any combination + package
       }
    }
    
    if(PackageCount>0)
    {
	
    	tktvoucherCheck=0;
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
    	PackageVoucherCheck=0;
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
    	String AddName[]=AddOnsName.split("\\+");// addons are splitted with + in case of multiple addons
    	for(int ai=0;ai<AddName.length;ai++)
    	{
    		
    	    AddOnsBooking(AddName[ai],AddOnsCount);
    	    
    	    
        }
    }
    String amt=null;
    int totalAMTValue=0;
    String totalAmt=null;
  //  if(stat.getProperty("summaryPage").equalsIgnoreCase("old"))
	//{
    
       
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
	
       String BooingtotalAmt=driver.findElement(By.cssSelector("*[class^='total-amount']")).getText();//BooingtotalAmt refers to grand total in summary page
       totalAmt=BooingtotalAmt;
   
       for(String s :specialchar)
	   {
    	  totalAmt=totalAmt.replace(s, "");
	   }
	   String parseStringtotalAmt=totalAmt;
	   totalAMTValue=Integer.parseInt(parseStringtotalAmt);//totalAMTValue grand total fetched from summary page
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

    	
   
    if(totalAmount==totalAMTValue)//totalAmount calculated in the code and its compared with  grand total of booking summary 
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
   // btn btn-default rmbRds add-more-rooms
   // btn btn-default rmbRds booknow
  //*[@id="PaymentSubmit"]/div[2]/div[2]/div[2]/button
    

	JavascriptExecutor js = ((JavascriptExecutor) driver);
	WebElement element = driver.findElement(By.xpath("//*[@id='User_TermsAndConditions']"));
	//Now scroll to this element 
	js.executeScript("arguments[0].scrollIntoView(true);", element);
    driver.findElement(By.xpath("//*[@id='User_TermsAndConditions']")).click();
    Thread.sleep(2000);
  //*[@id="mainContainer"]/table/tbody/tr[5]/td[2]
   
    driver.findElement(By.xpath("//*[@id='PaymentSubmit']/div[2]/div[2]/div/button")).click();
	//}
    if(stat.getProperty("summaryPage").equalsIgnoreCase("new"))
   	{
    	
   	}
    if(tktvoucherCheck==1)
    {
    	
    	wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("*[class^='payDetails table-striped']")));//display voucher
        WebElement a1=driver.findElement(By.cssSelector("*[class^='payDetails table-striped']"));//voucher div
        List<WebElement> td1=a1.findElements(By.id("td"));///take all td with find elements
        amt=td1.get(7).getText();//i know that total amount is displayed in 7th td
    }else
 
    {
    	  wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='mainContainer']/table/tbody/tr[5]/td[2]")));//in case of combination booking
    	  amt=driver.findElement(By.xpath("//*[@id='mainContainer']/table/tbody/tr[5]/td[2]")).getText();//consider xpath to take amount
    	  
    	  
    	
    }
  
    
    
    String[] AmtStr=amt.split(" ");//INR 1180
    System.out.println("Booking Summary amount:-"+totalAmt);
    System.out.println("Voucher Page amount:-"+AmtStr[1]);
    if(AmtStr[1].equalsIgnoreCase(String.valueOf(totalAMTValue)))
    {
    	Reporter.log("<font font-family='Times New Roman'>IBE--</font><font color='blue'>"+TestCaseName+"-:( Booking Summary Page Total :-"+totalAMTValue+"and Voucher Page Amount :-"+AmtStr[1]+" is Matched ):-PASS</font></a>", true);
    	System.out.println("pass");
    }
    else
    {
    	System.out.println("amount mismatch");
    	Reporter.log("<font font-family='Times New Roman'>IBE--</font><font color='red'>"+TestCaseName+"-:( Booking Summary Page Total :-"+totalAMTValue+" and Voucher Page Amount :-"+AmtStr[1]+" is not Matched ):-FAIL</font></a>", true);
    }
    
     
    
}
    





   public static void bookroom(String roomtype1,String meal1) throws InterruptedException
   {
       //driver.findElement(By.xpath("//*[@id='ScrollRoom']/div[1]/div/div[1]/div[1]/div[1]/div/a")).click();
	   wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='content-loader']")));//loader should disappear
	   wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='rooms']/img")));//room loader should disappear
	   wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='flip_maindiv']/div/img"))); //meal plan loader should disappear
       wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='flip_maindiv']/div/div/img")));//meal plan loader should disappear
   	   
       //code for scroller to scroll page down start
       JavascriptExecutor js = ((JavascriptExecutor) driver);
   	   js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
       //code for scroller to scroll page down end
		List<WebElement>mealPlan1 =new LinkedList<WebElement>();
		List<WebElement>mealPlan2 =new LinkedList<WebElement>();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("*[class^='col-sm-12 pad-zero rate-inventory read-more rooms-info']")));
    	List<WebElement> roomtype=driver.findElements(By.cssSelector("*[class^='col-sm-12 pad-zero rate-inventory read-more rooms-info']"));
    	Boolean RoomNameflag=true;
    	Boolean RoomMealflag=true;
    	WebDriverWait mealwait=new WebDriverWait(driver,4);
    	for(WebElement web:roomtype)//using 2 div's of 2 different rooms in for each
    	{
    		if(RoomNameflag)
    		{
    		WebElement roomName=web.findElement(By.cssSelector("*[class^='label-Blue col-sm-8 pad-zero']"));// //room name inspect element
    		String roomname1=roomName.getText();//capture room name
    
    		if(roomname1.equalsIgnoreCase(roomtype1))//excel i/p room compared with captured room
    		{
    			RoomNameflag=false;
    			
    			Thread.sleep(6000);
    			
    			// 2 try blocks used here bcz meal plan css locator is different when there is only meal plan and meal plan with offer
    			try{
    			                              
    			mealwait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("*[class^='col-xs-12 pad-zero margin-align mealplan-rate-info']")));//waiting for i/p meal plan from excel to appear in UI
    			
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
    			mealPlan1.addAll(mealPlan2);//addall method adds mealplan2 to mealplan1
    		
    		
    			for(WebElement meal:mealPlan1)
    			{
    				if(RoomMealflag)
    				{
        			
    				String mealplanweb=meal.findElement(By.cssSelector("*[class^='day-roomlabel']")).getText();//capture meal plan name from UI
    				
    				if(mealplanweb.equalsIgnoreCase(meal1))//Compare this meal plan captured with excel i/p meal plan
    				{
    					
    					String price=meal.findElement(By.cssSelector("*[class^='promo-availablerate total-room-rate']")).getText();//capture meal plan rates
    					String specialchar[]={",","\""};//amount captured will be like this "1,600"  ....\ escape character to handle ""
    					for(String s :specialchar)
    					{
    						price=price.replace(s, "");//"1600"-----next time //1600
    					}
    					String parseString=price;
    					totalAmount=totalAmount+Integer.parseInt(parseString);//totalamount=0 for the first time
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
	 
	   Boolean PackageNameflag=true;
	   Boolean PackageTypeflag=true;
       WebDriverWait packtype=new WebDriverWait(driver,20);
	   wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='content-loader']")));
	   //wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='rooms']/img")));
	   //wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='flip_maindiv']/div/img")));
       //wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='flip_maindiv']/div/div/img")));
	   
       try 
     //package tab has got 2 xpaths 1. //*[@id='packagetab']/a, the home page will have this xpath in the first load 
       {
    	   if(xpathflag==0)  
    	   {
    		System.out.println("Inside");
    		Thread.sleep(20000);
    	   packtype.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='packagetab']/a")));
    	   driver.findElement(By.xpath("//*[@id='packagetab']/a")).click();
    	   }
       }
       catch(Exception e)
       {
    	   System.out.println();
       }
       try//This try-catch block for any combination + package includes package + package also
       //*[@id='hpackagetab']/a - this xpath will be thr xpath when navigated from summary to home page
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
	
       
       wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='package']/img")));
       Thread.sleep(2000);
       //Code for scroller startd
       JavascriptExecutor js = ((JavascriptExecutor) driver);

		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		//Code for scroll ends
	     wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("*[class^='lead margin-clear text-left']")));
	 //List<WebElement>WebPackageType=driver.findElements(By.cssSelector("*[class^='lead margin-clear text-left']"));
	 
	    List<WebElement>WebPackageType=driver.findElements(By.cssSelector("*[class^='overlay-container overlay-visible']"));//package type image addresses stored here
	 
	 for(WebElement a :WebPackageType)//for each takes each address
	 {
		
		 if(PackageTypeflag)
		 {
			 String name=a.findElement(By.cssSelector("*[class^='lead margin-clear text-left']")).getText();//package text capture
			
		 if(name.equalsIgnoreCase(PackageType))//comparing package text captured with excel i/p package type
		 {
			 PackageTypeflag=false;
			 a.findElement(By.cssSelector("*[class^='overlay-link popup-img']")).click();//clcik on package type image
			 wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("*[class^='col-sm-12 pad-zero packages-info']")));
			 List<WebElement>WebPackageName=driver.findElements(By.cssSelector("*[class^='col-sm-12 pad-zero packages-info']"));//package each div when clicked on package type image
			 for(WebElement b :WebPackageName)
			 {
				 
				 if(PackageNameflag)
				 {
					 String pkname=b.findElement(By.cssSelector("*[class^='label-Blue']")).getText();//cspture package name like package type
					 
				 if(pkname.equalsIgnoreCase(PackageName))//comparing captured package name with excel i/p package name
				 {
					 PackageNameflag=false;
					
					 
					 //code for drop down starts here
					 WebElement mealPlanSelectDropDown=b.findElement(By.cssSelector("*[class^='form-control selected-package-mealplan']"));
					
					 Select sel=new Select(mealPlanSelectDropDown);
					 sel.selectByVisibleText(MealType);
					 //code for drop down ends here
					 
					 String price=b.findElement(By.cssSelector("*[class^='package-price']")).getText();//capture package price
					String specialchar[]={" ",",","\""};//amount captured will be like this "1,600"  ....\ escape character to handle ""
 					for(String s :specialchar)
 					{
 						price=price.replace(s, "");//"1600"-----next time //1600
 					}
 					String parseString=price;
 					totalAmount=totalAmount+Integer.parseInt(parseString);//total amount previously has room total if any
 					// wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("*[class^='lead margin-clear text-left']")));
                    WebElement button=b.findElement(By.cssSelector("*[class^='btn btn-default  book-pakage']"));//consider reserve package button address
                    
                    //code for scroll till Reserve package	button--start
                    JavascriptExecutor js1 = ((JavascriptExecutor) driver);
             	   //Now scroll to this element 
             	    js1.executeScript("arguments[0].scrollIntoView(true);", button);
 				    
             	 //code for scroll till Reserve package	button--end
             	    
 					 b.findElement(By.cssSelector("*[class^='btn btn-default  book-pakage']")).click();//reserve package button click
 					 Thread.sleep(2000);
 					try//to handle LOS if any
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
	   WebDriverWait TicketType=new WebDriverWait(driver,4);// explicitely wait
	  
	   wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='content-loader']")));//to handle main loader on hoe page load
	   System.out.println("hello");
	 //*[@id="hofferstab"]/a
	   try
	   {
		   if(xpathflag==0)
			   //like package we will have 2 xpaths for ticket as well --please refer package
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
		   if(xpathflag!=0)//any combination with ticket
    	   {
		      TicketType.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='hofferstab']/a")));
		      driver.findElement(By.xpath("//*[@id='hofferstab']/a")).click();
    	   }
	   }
	  catch(Exception e)
	   {
		  System.out.println();
	   }
	   
	    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='offers']/img")));//ticket loaded should go off
	    JavascriptExecutor js = ((JavascriptExecutor) driver);
        
	    //page scroll full down--start
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		
		 //page scroll full down--ends
	    wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("*[class^='col-lg-4 rate-inventory col-md-4 col-sm-6 col-xs-12 rooms-info grid-roomtype']")));
        List<WebElement>Ticket1=driver.findElements(By.cssSelector("*[class^='col-lg-4 rate-inventory col-md-4 col-sm-6 col-xs-12 rooms-info grid-roomtype']"));//consider all main div addresses on click of ticket
       for(WebElement a :Ticket1)//consider each address one by one
       {
    	   if(tickettypeflag)
    	   {
    	   String WebticketNAme=a.findElement(By.tagName("span")).getText();//capture ticket name from span
    	   if(WebticketNAme.equalsIgnoreCase(Ticketname))//compare captured ticket name with i/p excel ticketname
    	   {
    		   
    		    List<WebElement>rates=a.findElements(By.cssSelector("*[class='weekrate rate-fnt']"));//contains address of amount in ticket (both week day and week end)
    		    for(WebElement e :rates)
    		    {
    		    	String Rates=e.getText();  //to handle rates --please refer package or room code
    		    	String specialchar[]={" ",",","\""};
    				for(String s :specialchar)
    				{
    					Rates=Rates.replace(s, "");
    				}
    				ticketRates.add(Integer.parseInt(Rates));
    				
    				 
    		    }
    		    	
				
				
				//totalAmount=totalAmount+Integer.parseInt(parseString);
    		   tickettypeflag=false;
    		   a.findElement(By.cssSelector("*[class^='btn btn-default btn-custom ticket-book pull-right']")).click();//click on add to summary
    	   }
    	   }
       }
     
       if(stat.getProperty("summaryPage").equalsIgnoreCase("old"))
       {
       JavascriptExecutor js1 = ((JavascriptExecutor) driver);
	   WebElement element = driver.findElement(By.cssSelector("*[class^='total-amount']"));
	   //Now scroll to this element 
	   js1.executeScript("arguments[0].scrollIntoView(true);", element);//scrolls till grand total in summary page
       
        String specialchar[]={" ",",","\""};
	    String BooingtotalAmt=driver.findElement(By.cssSelector("*[class^='total-amount']")).getText();///consider total amount
	    String totalAmt=BooingtotalAmt;//put BooingtotalAmt to totalAmt
	    for(String s :specialchar)
		{
	    	totalAmt=totalAmt.replace(s, "");
		}
		String parseStringtotalAmt=totalAmt;
	    int totalAMTValue=Integer.parseInt(parseStringtotalAmt);//totalAMTValue is booking summary display grand total value
		int bookingrateCheckFlag=0;
	    if(ticketRates.size()>0)
		{
		
	    for(int a :ticketRates) //code to cross verify to know whether week day or week end ticket is selected (based on hotel settings)..consider week end ticket is selected
	    {int FinalTotalAmount=0;
	    	if(bookingrateCheckFlag==0)
	    	{
	    	FinalTotalAmount=totalAmount+a;//ticket cost+ total amount
	    	//totalAmount=FinalTotalAmount;
	    	
	    	wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("*[class='table table-condensed borderless']")));
		    WebElement tax=driver.findElement(By.cssSelector("*[class='table table-condensed borderless']"));
		    WebElement TaxTd=tax.findElement(By.cssSelector("*[class='col-sm-4 text-right']"));
		    String TaxAmount=TaxTd.findElement(By.cssSelector("*[class='font-size-13 ']")).getText();//Tax get text
		    
			for(String s :specialchar)
			{
				TaxAmount=TaxAmount.replace(s, "");
			}
			String parseString=TaxAmount;
			//totalAmount=totalAmount+Integer.parseInt(parseString);
			FinalTotalAmount=FinalTotalAmount+Integer.parseInt(parseString);//ticket cost+ total amount+tax
			
	    	if(totalAMTValue==FinalTotalAmount)
	    	{
	    		bookingrateCheckFlag=1;//here when it is 1 , further no need to check with week day rates of ticket
	    		totalAmount=FinalTotalAmount;
	    	}
	    	}
	    }
	    
		}
	    if(tktcount>=0)//if ticket is >0 in excel i/p
	    {
	    	
	    	wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("*[class='table table-condensed borderless']")));
		    WebElement tax=driver.findElement(By.cssSelector("*[class='table table-condensed borderless']"));
		    WebElement TaxTd=tax.findElement(By.cssSelector("*[class='col-sm-4 text-right']"));
		    String TaxAmount=TaxTd.findElement(By.cssSelector("*[class='font-size-13 ']")).getText();
	    	String parseString=TaxAmount;
	    	totalAmount=totalAmount-Integer.parseInt(parseString);//this is to avoid tax amount getting added twice when there are multiple tickets
	    	tktcount=tktcount-1;//this is also to handle tax in a proper way..to avoid tax getting calculated multiple times in case of multiple tickets
	    	//System.out.println("Total Amount Inside Ticket Count**----"+totalAmount);
	    }
	    
	    System.out.println("Total Amount Inside Ticket Count----"+totalAmount);
       }
	    
   }
   
   
   
   
   
   public static void AddOnsBooking(String AddonsService,int count) throws InterruptedException
   {
	   
	   
	   
	 if(stat.getProperty("summaryPage").equalsIgnoreCase("old"))
	 {
	//scroll and wait till add on option is visible
	   JavascriptExecutor js = ((JavascriptExecutor) driver);
	   WebElement element = driver.findElement(By.cssSelector("*[class^='col-sm-12 myaddons']"));
	   //Now scroll to this element 
	   js.executeScript("arguments[0].scrollIntoView(true);", element);
	   boolean addonsname=true;
	   //col-md-12 col-sm-12 addons list-group ng-scope addons-list
	   List<WebElement>addls=element.findElements(By.cssSelector("*[class^='col-md-12 col-sm-12 addons list-group ng-scope addons-list']"));//add address of all addons to the list
	   for(WebElement a :addls)
	   {
		  
		   if(addonsname)
		   {
		   String AddOnsName=a.findElement(By.cssSelector("*[class^='col-md-8 col-sm-8 addons-details pad-zero']")).getText();//capture addons name
		   System.out.println(AddOnsName);
		   System.out.println(AddonsService);
		   if(AddOnsName.equalsIgnoreCase(AddonsService))//compare captuired addon name with excel i/p addonservice
		   {
			   
		       addonsname=false;
		       
		       //code for drop down to select no of addons
			   WebElement addonscount=a.findElement(By.cssSelector("*[class^='update-addon']"));
			   Select sel=new Select(addonscount);
			   sel.selectByVisibleText(String.valueOf(count));
			   
			    List<WebElement> ls=a.findElements(By.cssSelector("*[class^='col-md-2 col-sm-2 addons-rate pad-zero ng-binding']"));//rate of 
			    String price=ls.get(2).getText();
			   
				String specialchar[]={" ",",","\""};
				for(String s :specialchar)
				{
					price=price.replace(s, "");
				}
				String parseString=price;
				totalAmount=totalAmount+Integer.parseInt(parseString);
				
			    a.findElement(By.cssSelector("*[class^='fa fa-plus']")).click();
			    Thread.sleep(6000);
			   
		   }
		   }
	   }
	 }
	 if(stat.getProperty("summaryPage").equalsIgnoreCase("new"))
	 {
	 
	 }
   }
   
   
   
   
   public  static void guestDetails() throws InterruptedException
   {
	   System.out.println("222");
		JavascriptExecutor js = ((JavascriptExecutor) driver);

		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		System.out.println("11");
	   WebElement guest= driver.findElement(By.xpath("//*[@id='guestinfo']"));//address stored in web element
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
	   for(WebElement e:first)//when there are multiple rooms in the reservation
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
	   String sd[]=Startdate.split("\\-");//Date split was not working with hiphen and therfore forward slashes were used.
	   String ed[]=Enddate.split("\\-");
	   String startday=sd[0];
	   String startmon=sd[1];
	   String startYear=sd[2];
	   String endday=ed[0];
	   String endMon=ed[1];
	   String endYear=ed[2];
	   
	   driver.findElement(By.xpath("//*[@id='checkin']")).click();//check in date calendar click
	   WebElement checkindropdown =driver.findElement(By.cssSelector("*[class^='datepicker datepicker-dropdown dropdown-menu datepicker-orient-left datepicker-orient-bottom']"));
	   
	   checkindropdown.findElement(By.cssSelector("*[class^='datepicker-switch']")).click();//clicks on Month header (Januarary 2018)
	   List<WebElement>checkinmon=checkindropdown.findElements(By.tagName("span"));//List of months are stored in checkinmon variable
	   boolean startmonflag=true;
	   for(WebElement a:checkinmon)
	   {
		   if(startmonflag)
		   {
		   String mon=a.getText();
		   if(mon.equalsIgnoreCase(startmon))//if current month matches to this span list month
		   {  
			   startmonflag=false;
			   List<WebElement>curday=new LinkedList<WebElement>();
		       List<WebElement>furday=new LinkedList<WebElement>();
			   a.click();
			   curday=checkindropdown.findElements(By.cssSelector("*[class^='active day']"));//Stores the current date address
			   try//if current day is 31st, then there wont be any future days in that month and try throws error 
			   {
			   furday=checkindropdown.findElements(By.cssSelector("*[class^='day']"));//stores all the future date address
			   }
			   catch(Exception e)
			   {
				   
			   }
			   //upper loop will only store the address of the dates
			   curday.addAll(furday);//adding all future days with Current day 
			  
			   boolean startdayflag=true;
			   for(WebElement b : curday)
			   {
				   if(startdayflag)
				   {
				   String day=b.getText();//
				 //using the address of the dates, we store the values in day variable
				   if(day.equalsIgnoreCase(startday))//this loop is used to click on current date based startday definition used up.
				   {
					   b.click();
					   startdayflag=false;
				   }
				   }
			   }
		   }
		   }
	   }
	   
	   
	   
	   
	   //CheckOut, please refer checkin comments 
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
	   
	   
	   
	   
	   

   }


}
