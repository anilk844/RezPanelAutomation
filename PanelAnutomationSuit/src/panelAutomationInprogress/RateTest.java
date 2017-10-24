package panelAutomationInprogress;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.SendKeysAction;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.python.antlr.PythonParser.list_for_return;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Location;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class RateTest {
	public static Properties prop;
	public static Properties gen;
	public static WebDriver driver;
	public static WebDriverWait wait;
	public static Properties stat;
	public static int k;
	public static ArrayList data;
	public static String RateCode;
	@Test
	public static void start() throws InterruptedException, FindFailed, AWTException, IOException, UnsupportedFlavorException
	{
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
		FileInputStream pageObject = new FileInputStream("C:/Users/anil.kumar/git/RezPanelAutomation/PanelAnutomationSuit/src/Repository/Rate_Creation_Repository.properties");
		prop = new Properties();
		prop.load(pageObject);
		FileInputStream file= new FileInputStream("D://1585.xlsx");
		//FileInputStream file= new FileInputStream("D://Keys1.xlsx");
		
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
		

		System.setProperty("webdriver.chrome.driver", "D://chrome//chromedriver.exe");
		 
         DesiredCapabilities capabilities = DesiredCapabilities.chrome();
         ChromeOptions options = new ChromeOptions();
         options.addArguments("test-type");
         options.addArguments("--start-maximized");
         options.addArguments("--disable-web-security");
         options.addArguments("--allow-running-insecure-content");
         capabilities.setCapability("chrome.binary","D://chrome//chromedriver.exe");
         capabilities.setCapability(ChromeOptions.CAPABILITY, options);
         driver = new ChromeDriver(capabilities);
         driver.get(gen.getProperty("Url"));
		 wait=new WebDriverWait(driver,10);
		
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(gen.getProperty("username"))));
		driver.findElement(By.xpath(gen.getProperty("username"))).sendKeys(gen.getProperty("UserNameValue"));
		driver.findElement(By.xpath(gen.getProperty("password"))).sendKeys(gen.getProperty("PasswordValue"));
		driver.findElement(By.xpath(gen.getProperty("Button"))).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(gen.getProperty("searchField"))));
		driver.findElement(By.xpath(gen.getProperty("searchField"))).sendKeys("1585");
		Screen scn= new Screen();
		String img="D://sikili//Ban.PNG";
		Pattern ptr= new Pattern(img);
		Thread.sleep(4000);
		scn.click(ptr);
		driver.get(gen.getProperty("Rate_creation_URL"));
		driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
		
		//Thread.sleep(2000);
		String stringVal="000";
		String roomName="";
	    Double fromDate;
	    Double toDate;
	    String Desc;
	    String Single;
	    String Double;
	    String ExtraAdult;
	    String ExtraChild;
	    String Meal;
	    int line=0;
		for(k=0;k<data.size();k=k+15)
		{
	      
			    System.out.println("ID---->"+data.get(k));
			    RateCode=data.get(k).toString();
		        if(stringVal.equalsIgnoreCase(data.get(k).toString()))
		        {
		        	
		       
		        	int m=k+3;
		        	if((data.get(k+3).toString()).equalsIgnoreCase(data.get(m-15).toString()))
		        	{
		        	System.out.println("Same rate Code");
		        	Meal=data.get(k+6).toString();
		        	stringVal=data.get(k).toString();
		        	roomName=data.get(k+3).toString();
		        	Single=data.get(k+8).toString();
		        	Double=data.get(k+9).toString();
		        	ExtraAdult=data.get(k+10).toString();
		        	ExtraChild=data.get(k+11).toString();
		        	System.out.println("String VAlue="+stringVal+"roomName="+roomName+"Single="+Single+"Double="+Double+"ExtraAdult="+ExtraAdult+"ExtraChild"+ExtraChild+"MealPlan="+Meal);
		        	Thread.sleep(1500);	
		        	AddRate(roomName,Single,Double,ExtraAdult,ExtraChild,Meal);
		        	}
		        	else
		        	{
		        		System.out.println("Same rate Code");
		        		Meal=data.get(k+6).toString();
			        	stringVal=data.get(k).toString();
			        	roomName=data.get(k+3).toString();
			        	Single=data.get(k+8).toString();
			        	Double=data.get(k+9).toString();
			        	ExtraAdult=data.get(k+10).toString();
			        	ExtraChild=data.get(k+11).toString();
		        		try
				        {
		        			//*[@id="addrates"]
		    
				        	JavascriptExecutor js = ((JavascriptExecutor) driver);
				        
					        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
					 
							driver.findElement(By.xpath("//*[@id='remoteModal-rate']/div/div/div[3]/a[1]")).click();
				        }
				        catch(Exception e)
				        {
				        	
				        }
		        		
		        	Thread.sleep(1500);	
		        	OldRate(roomName,Single,Double,ExtraAdult,ExtraChild,Meal);
		        	
		        	}
		        	
		        	line=line+1;
		  }
		        else
		        {
		          int m=k;
		          System.out.println("New RateCode");
		          stringVal=data.get(k).toString();
		          Meal=data.get(k+6).toString();
		          roomName=data.get(k+3).toString();
		   	      fromDate=(Double)data.get(k+4);
		   	      int frdate=fromDate.intValue();
		   	      String FROMDate=String.valueOf(frdate);
		   	      //System.out.println(Integer.parseInt(fromDate));
		   	      toDate=(Double)data.get(k+5);
		   	      int tdate=toDate.intValue();
		   	      String TODate=String.valueOf(tdate); 
		   	      
		   	      Desc=data.get(k+7).toString();
		   	      Single=data.get(k+8).toString();
	        	  Double=data.get(k+9).toString();
	        	  ExtraAdult=data.get(k+10).toString();
	        	  ExtraChild=data.get(k+11).toString();
	        	  System.out.println("String VAlue="+stringVal+"fromDate"+FROMDate+"toDate"+TODate+"roomName="+roomName+"Single="+Single+"Double="+Double+"ExtraAdult="+ExtraAdult+"ExtraChild="+ExtraChild+"MealPlan="+Meal);
		        try
		        {
		        	JavascriptExecutor js = ((JavascriptExecutor) driver);

			        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
			        wait=new WebDriverWait(driver,10);
        	        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[@id='remoteModal-rate']/div/div/div[3]/a[1]")));
					driver.findElement(By.xpath("//*[@id='remoteModal-rate']/div/div/div[3]/a[1]")).click();
		        }
		        catch(Exception e)
		        {
		        	
		        }
	        	  try
		        {
		            Thread.sleep(4000);
		  
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='bootstrap-wizard-1']/div/div[1]/div/div/a[1]")));
		            driver.findElement(By.xpath("//*[@id='bootstrap-wizard-1']/div/div[1]/div/div/a[1]")).click();
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='Msg1']")));
					WebElement msgbox=driver.findElement(By.xpath("//*[@id='Msg1']"));
					String msg=msgbox.findElement(By.tagName("p")).getText();
					Thread.sleep(2000);
					if(msg.equalsIgnoreCase("Are you sure do you want to save"))
					{
						msgbox.findElement(By.id("bot2-Msg1")).click();
						Thread.sleep(3000);
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='Msg1']")));
						WebElement SuccessMsgBox=driver.findElement(By.xpath("//*[@id='Msg1']"));
						SuccessMsgBox.findElement(By.id("bot1-Msg1")).click();
					}	
		        }
		        catch(Exception e)
		        {
		        	System.out.println(e);
		        }
		        CreateNewRate(roomName,FROMDate,TODate,Desc);
				Thread.sleep(1500);	
				AddRate(roomName,Single,Double,ExtraAdult,ExtraChild,Meal);
		  }
				
				
				
					
				//AssignRateToChannel();
		
	}
	}
	
	
	public static void click(int line) throws InterruptedException, AWTException, FindFailed, UnsupportedFlavorException, IOException
	{
		//NEW CODE 24-11-2016
		Screen sc= new Screen();
		String im="D://sikili//B2CRate.PNG";
		Pattern pt= new Pattern(im);
		Thread.sleep(4000);
		sc.doubleClick(pt);
		
		 Robot robot1 =new Robot();
		 robot1.keyPress(KeyEvent.VK_CONTROL);
		 robot1.keyPress(KeyEvent.VK_C);
		 robot1.keyRelease(KeyEvent.VK_CONTROL);
		 robot1.keyRelease(KeyEvent.VK_C);
		 robot1.keyPress(KeyEvent.VK_ENTER);
		 robot1.keyPress(KeyEvent.VK_CONTROL);
		 robot1.keyPress(KeyEvent.VK_V);
		 robot1.keyRelease(KeyEvent.VK_CONTROL);
		 robot1.keyRelease(KeyEvent.VK_V);
		 java.awt.datatransfer.Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		 Transferable contents = clipboard.getContents(null);
		 String result = (String)contents.getTransferData(DataFlavor.stringFlavor);
		 System.out.println(result);
		 write(line,result);
	}
	
	public static void write(int line,String result) throws IOException
	{
	        System.out.println("hi");
	        FileInputStream fis= new FileInputStream("D://Keys.xlsx");
			XSSFWorkbook workbook = new XSSFWorkbook (fis);
			XSSFSheet sheet = workbook.getSheetAt(0);
			//Create First Row
			XSSFRow row1 = sheet.createRow(line);
			int cell=17;
			XSSFCell r1c2 = row1.createCell(cell);
			r1c2.setCellValue(result);
			fis.close();
			FileOutputStream fos =new FileOutputStream("D://Keys.xlsx");
		    workbook.write(fos);
		    fos.close();
			System.out.println("Done");
	}
	
	
	private static String String(Object object) {
		// TODO Auto-generated method stub
		return null;
	}

 public static String[] dateconvert(String DateValue)
 {
	String mon[]={"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
    String Year=DateValue.substring(0,4);
    String Month=DateValue.substring(4,6);
    String day=DateValue.substring(6,8);
    int month1=Integer.parseInt(Month);
    String MonthFinal=mon[month1-1];
    
	
	String date[]=new String[3];
	date[0]=Year;
	date[1]=MonthFinal;
	date[2]=day;
	return date;
	
 }




	public static void CreateNewRate(String RoomName,String FromDate,String ToDate,String Desc) throws InterruptedException
	{  
	    String fdate[]=new String[3]; 
	    String tdate[]=new String[3];
	    
	    fdate= dateconvert(FromDate);
	    Thread.sleep(2000);
	    tdate= dateconvert(ToDate);
	    String fday1=fdate[2];
	    String tday1=tdate[2];
	    if(fday1.equalsIgnoreCase("01")||fday1.equalsIgnoreCase("02")||fday1.equalsIgnoreCase("03")||fday1.equalsIgnoreCase("04")||fday1.equalsIgnoreCase("05")||fday1.equalsIgnoreCase("06")||fday1.equalsIgnoreCase("07")||fday1.equalsIgnoreCase("08")||fday1.equalsIgnoreCase("09"))
	    {
	    	fday1=fday1.replace("0","");
	    }
	    if(tday1.equalsIgnoreCase("01")||tday1.equalsIgnoreCase("02")||tday1.equalsIgnoreCase("03")||tday1.equalsIgnoreCase("04")||tday1.equalsIgnoreCase("05")||tday1.equalsIgnoreCase("06")||tday1.equalsIgnoreCase("07")||tday1.equalsIgnoreCase("08")||tday1.equalsIgnoreCase("09"))
	    {
	    	tday1=tday1.replace("0","");
	    }
	   
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(prop.getProperty("Add_New_button"))));
		Thread.sleep(3000);
		driver.findElement(By.xpath(prop.getProperty("Add_New_button"))).click();
		
		WebElement ele=driver.findElement(By.xpath(prop.getProperty("Rate_type")));
		Select sel = new Select(ele);
		sel.selectByVisibleText("Corporate");
		
		WebElement ele1 =driver.findElement(By.xpath(prop.getProperty("Currency_type")));
		Select sel1= new Select(ele1);
		sel1.selectByVisibleText("INR");
		
		driver.findElement(By.xpath(prop.getProperty("Season"))).sendKeys("General Season");
		driver.findElement(By.xpath(prop.getProperty("Start_date"))).click();
		//start date
		String year=driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/div/span")).getText();
		
		int year1=Integer.parseInt(year);
		int inputyear=Integer.parseInt(fdate[0]);
		while(inputyear!=year1)
		{
			if(inputyear>year1)
			{
				
				driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/a[2]/span/i")).click();
				String year2=driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/div/span")).getText();
				int year3=Integer.parseInt(year2);
				year1=year3;
			}
		}
		if(inputyear==year1)
		{
			WebElement ele2=driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/div/select"));
			Select sel2 =new Select(ele2);
			sel2.selectByVisibleText(fdate[1]);
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
				
				if(fday1.equalsIgnoreCase(day))
				{
					
					Thread.sleep(1000);
					e1.click();
					flag1=1;
					break;
					
				}
			}
			}
		}
		driver.findElement(By.xpath(prop.getProperty("end_date"))).click();
		String yearend=driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/div/span")).getText();
		
		int yearend1=Integer.parseInt(yearend);
		int inputyearend=Integer.parseInt(tdate[0]);
		while(inputyearend!=yearend1)
		{
			if(inputyearend>yearend1)
			{
		
				driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/a[2]/span/i")).click();
				String yearend2=driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/div/span")).getText();
				int yearend3=Integer.parseInt(yearend2);
				yearend1=yearend3;
			}
		}
		if(inputyearend==yearend1)
		{
			WebElement ele5=driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/div/select"));
			Select sel5 =new Select(ele5);
			sel5.selectByVisibleText(tdate[1]);
		}
		WebElement ele6=driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/table"));
		List<WebElement> tr1=ele6.findElements(By.tagName("tr"));
		for(WebElement e1:tr1)
		{
			List<WebElement> td1=e1.findElements(By.tagName("td"));
			for(WebElement e2:td1)
			{
				String day=e2.getText();
				if(tday1.equalsIgnoreCase(day))
				{
					Thread.sleep(1000);
					e2.click();
					break;
				}
			}
		}
		driver.findElement(By.xpath(prop.getProperty("Rate_Name"))).sendKeys(Desc);
		driver.findElement(By.xpath(prop.getProperty("Rate_Des"))).sendKeys("Corporate Rates");
		driver.findElement(By.xpath(prop.getProperty("Room_Type"))).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(prop.getProperty("Room_Type"))).clear();
		Thread.sleep(3000);
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElement(By.xpath(prop.getProperty("Room_Type"))).sendKeys(RoomName);
        Thread.sleep(2000);
	}

		
	
	
	
	//click on existing ratecode 
	public static void ExistingRateCode() throws InterruptedException, FindFailed
	{
		
		WebElement a1=driver.findElement(By.xpath("//*[@id='wid-id-crg']/div/div[2]/div"));
		List<WebElement>div1 = a1.findElements(By.tagName("div"));
		int mul=0;
		int count=34;
		int total=0;
		for(int i=7;i<div1.size();i=i+8)
		{
			 System.out.println(div1.size());
		     WebElement dv=div1.get(i);
		     List<WebElement>div2 = dv.findElements(By.tagName("div"));
		     System.out.println(div2.size());
		     WebElement dv1= div2.get(1);
		     WebElement label = dv1.findElement(By.tagName("label"));
		     String ratecode=label.getText();
		
		 if(ratecode.equals(gen.getProperty("ExistingRateCode")))
		 {
		
			 WebElement lnk=div2.get(6);
			 WebElement lnk1=lnk.findElement(By.tagName("i"));
			 lnk1.click();
			 if(mul==1)
			 {
			 WebElement inline1=div1.get(20);
			 inline1.click();
			 
			 }
			 else
			 { 
				 count=count+total;
				 
				 WebElement inline2=div1.get(count);
				 WebElement lb=inline2.findElement(By.tagName("label"));
				 inline2.click();
				 
			 }
			 break;
		 }
		
		 if(mul>2)
		 {
		 total=total+16;
		 }
		 mul=mul+1;
		}
		
		
		
		//link.get(1).click();; 		
		//end on clicking existing rate code
		//click on edit meal plan 
		WebElement edit=driver.findElement(By.xpath("//*[@id='bootstrap-wizard-1']/div/div[1]/section[2]/fieldset/div/ol"));
		List<WebElement>edit1=edit.findElements(By.tagName("li"));
		System.out.println(edit1.size());
		for(int i=0;i<edit1.size();i++)
		{
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("window.scrollBy(0,250)", "");
			WebElement selection =edit1.get(i);
			List<WebElement>selection1 = selection.findElements(By.tagName("section"));
			System.out.println(selection1.size());
			WebElement section=selection1.get(0);
			List<WebElement>label =section.findElements(By.tagName("label"));
			String roomtype = label.get(4).getText();
			System.out.println(roomtype);
			if(roomtype.equalsIgnoreCase(gen.getProperty("ExistingRoomType")))
			{
			   WebElement section1 = selection1.get(1);
			   WebElement click=section1.findElement(By.tagName("a"));
			   click.click();
			   
			   //edit on meal plan rates 0r change rates
			   Thread.sleep(2000);
			   WebElement msgbox1=driver.findElement(By.xpath("//*[@id='remoteModal-rate']/div/div/div[2]/div"));
	           List<WebElement> lst2=msgbox1.findElements(By.tagName("div"));
			   for(int k=0;k<lst2.size();k=k+31)
				{
				   System.out.println(lst2.size());
					try
					  {
						WebElement e2=lst2.get(k);
						List<WebElement>st=e2.findElements(By.tagName("strong"));
						String str1=st.get(0).getText();
						System.out.println(st.get(0).getText());
						WebElement strong=e2.findElement(By.tagName("i"));
						if(str1.equals(gen.getProperty("ExixtingMealPlan")))
						{
							strong.click();
						/*	WebElement Tbody=e2.findElement(By.tagName("tbody"));
							List<WebElement>tr2=Tbody.findElements(By.tagName("tr"));
							for(int p=0;p<tr2.size();p++)
							{
								WebElement tr3=tr2.get(p);
								List<WebElement>td3=tr3.findElements(By.tagName("td"));
								for(int j=1;j<3;j++)
								{
									WebElement td4 =td3.get(j);
									WebElement input = td4.findElement(By.tagName("input"));
									//input.clear();
									//input.sendKeys("7000");
									JavascriptExecutor js = ((JavascriptExecutor) driver);

							        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
									driver.findElement(By.xpath("//*[@id='remoteModal-rate']/div/div/div[3]/a[1]")).click();
									//---**add save button or cancel button----*****
								}
								break;
							}*/
							JavascriptExecutor js = ((JavascriptExecutor) driver);

					        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
							driver.findElement(By.xpath("//*[@id='remoteModal-rate']/div/div/div[3]/a[1]")).click();
							break;
						}
					
					}
						catch(Exception e)
						{
							
						}
					}
				
				
				
				/*
				WebElement msgbox1=driver.findElement(By.xpath("//*[@id='remoteModal-rate']/div"));
			
				List<WebElement> lst2=msgbox1.findElements(By.tagName("div"));
				
				for(int k=0;k<lst2.size();k++)
				{
					WebElement e2=lst2.get(k);
					try
					{
					WebElement strong=e2.findElement(By.tagName("i"));
					String str=e2.getText();
					System.out.println(str);
					if(str.equals("Only Room"))
					{
						strong.click();
						WebElement Tbody=e2.findElement(By.tagName("tbody"));
						List<WebElement>tr2=Tbody.findElements(By.tagName("tr"));
						for(int m=0;m<tr2.size();m++)
						{
							WebElement tr3=tr2.get(m);
							List<WebElement>td3=tr3.findElements(By.tagName("td"));
							for(int j=1;j<3;j++)
							{
								WebElement td4 =td3.get(j);
								WebElement input = td4.findElement(By.tagName("input"));
								input.clear();
								input.sendKeys("1000");
								
								//driver.findElement(By.xpath("//*[@id='remoteModal-rate']/div/div/div[3]/a[1]")).click();
							}
						break;	
						}
						
						break;
					}
				
				}
					catch(Exception e)
					{
						
					}
				}
				*/
			   
			   
			   //end
			}
			System.out.println(label.size());
			
		}
	}
	
	
	
	

	
	
	
		public static void AddNewRatePlan() throws FindFailed, InterruptedException
		{
		
		   driver.findElement(By.xpath("//*[@id='addrates']")).click();
		   
		   Screen scn2 =new Screen();
		   String plus = "D://sikili//plus.PNG";
		   Pattern pt =new Pattern(plus);
		   scn2.click(pt);
		   Thread.sleep(2000);
		   //meal plan code
		   Screen scn3 =new Screen();
		   String meal1 = "D://sikili//mealplancode.PNG";
		   Pattern pt1 =new Pattern(meal1);
		   scn3.type(pt1,"MAP");
		   Thread.sleep(2000);
		   //meal plan description
		   Screen scn4 =new Screen();
		   String meal2 = "D://sikili//mealplan.PNG";
		   Pattern pt2 =new Pattern(meal2);
		   scn4.type(pt2,"with breakfast");
		   WebElement msgbox=driver.findElement(By.xpath("//*[@id='remoteModal-ratePlan']/div"));
		   msgbox.findElement(By.xpath("//*[@id='remoteModal-ratePlan']/div/div/div[3]/a[2]")).click();
		
		}
		
		
		

		
		
		
		
          public static void AddRate(String roomName,String Single,String Double,String ExtraAdult ,String ExtraChild,String Meal) throws InterruptedException, FindFailed
          {
        	  
        	  System.out.println("reznext");
        	 
		
			try
			{
			
			   driver.findElement(By.xpath("//*[@id='addrates']")).click();
			}
			catch(Exception e)
			{
				
			}
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='remoteModal-rate']/div/div/div[2]/div")));
			    WebElement msgbox1=driver.findElement(By.xpath("//*[@id='remoteModal-rate']/div/div/div[2]/div"));
			    List<WebElement> lst2=msgbox1.findElements(By.tagName("div"));
			for(int k=0;k<lst2.size();k=k+31)
			{
			  try
			    {
				 WebElement e2=lst2.get(k);
				 wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("strong")));
				 List<WebElement>st=e2.findElements(By.tagName("strong"));
				 String str1=st.get(0).getText();
				 WebElement strong=e2.findElement(By.tagName("i"));
				 Thread.sleep(1000);
				 if(str1.equalsIgnoreCase(Meal))
				 {
					strong.click();
					WebElement Tbody=e2.findElement(By.tagName("tbody"));
					List<WebElement>tr2=Tbody.findElements(By.tagName("tr"));
					for(int i=0;i<1;i++)
					{
					    	WebElement tr3=tr2.get(i);
						    List<WebElement>td3=tr3.findElements(By.tagName("td"));
						    WebElement td1 =td3.get(1);
							WebElement input1 = td1.findElement(By.tagName("input"));
							input1.clear();
							input1.sendKeys(Single);
							WebElement td2 =td3.get(2);
							WebElement input2 = td2.findElement(By.tagName("input"));
							input2.clear();
							input2.sendKeys(Double);
							WebElement td7 =td3.get(7);
							WebElement inputEA = td7.findElement(By.tagName("input"));
							inputEA.clear();
							inputEA.sendKeys(ExtraAdult);
							WebElement td8 =td3.get(8);
							WebElement inputEC = td8.findElement(By.tagName("input"));
							inputEC.clear();
							inputEC.sendKeys(ExtraChild);
							//---**add save button or cancel button----*****
							/*JavascriptExecutor js = ((JavascriptExecutor) driver);

					        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
							driver.findElement(By.xpath("//*[@id='remoteModal-rate']/div/div/div[3]/a[1]")).click();*/
							
						
						break;
					}
					break;
				}
				Thread.sleep(2000);
			
			}
				catch(Exception e)
				{
					
				}
			}
			
			
		}
		
          public static void OldRate(String RoomName,String Single,String Double,String ExtraAdult,String ExtraChild,String Meal) throws InterruptedException, FindFailed
          {
        	  
        	   driver.findElement(By.xpath(prop.getProperty("Room_Type"))).clear();
        	   wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(prop.getProperty("Room_Type"))));
        	   driver.findElement(By.xpath(prop.getProperty("Room_Type"))).sendKeys(RoomName);
               wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='addrates']")));
               wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='addrates']")));
               Thread.sleep(2000);
               driver.findElement(By.cssSelector("*[class^='btn  btn-primary addrate ng-scope']")).click();
			   wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='remoteModal-rate']/div/div/div[2]/div")));
			   WebElement msgbox1=driver.findElement(By.xpath("//*[@id='remoteModal-rate']/div/div/div[2]/div"));
			
			   List<WebElement> lst2=msgbox1.findElements(By.tagName("div"));
			
			for(int k=0;k<lst2.size();k=k+31)
			{
			    try
			    {
				WebElement e2=lst2.get(k);
				wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("strong")));
				List<WebElement>st=e2.findElements(By.tagName("strong"));
				String str1=st.get(0).getText();
				
				
				WebElement strong=e2.findElement(By.tagName("i"));
				Thread.sleep(1000);
				if(str1.equalsIgnoreCase(Meal))
				{
					
					strong.click();
					WebElement Tbody=e2.findElement(By.tagName("tbody"));
					List<WebElement>tr2=Tbody.findElements(By.tagName("tr"));
					for(int i=0;i<1;i++)
					{
						    WebElement tr3=tr2.get(i);
						    List<WebElement>td3=tr3.findElements(By.tagName("td"));
						    WebElement td1 =td3.get(1);
							WebElement input1 = td1.findElement(By.tagName("input"));
							input1.clear();
							input1.sendKeys(Single);
							WebElement td2 =td3.get(2);
							WebElement input2 = td2.findElement(By.tagName("input"));
							input2.clear();
							input2.sendKeys(Double);
							WebElement td7 =td3.get(7);
							WebElement inputEA = td7.findElement(By.tagName("input"));
							inputEA.clear();
							inputEA.sendKeys(ExtraAdult);
							WebElement td8 =td3.get(8);
							WebElement inputEC = td8.findElement(By.tagName("input"));
							inputEC.clear();
							inputEC.sendKeys(ExtraChild);
							//---**add save button or cancel button----*****
							//JavascriptExecutor js = ((JavascriptExecutor) driver);

   					        //js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
							//driver.findElement(By.xpath("//*[@id='remoteModal-rate']/div/div/div[3]/a[1]")).click();
							
						
						break;
					}
					break;
				}
				Thread.sleep(3000);
			
			}
				catch(Exception e)
				{
					
				}
			}
			
			
		}
		
          
          
          
          
          
          public static void AssignRateToChannel() throws InterruptedException
          {
        	  
        	  
        	    Thread.sleep(5000);
        		//*[@id="ratechannelstab"]/a
        		driver.findElement(By.xpath("//*[@id='ratechannelstab']/a")).click();
        		Thread.sleep(4000);
        		driver.findElement(By.xpath("//*[@id='RateAssignTab']/header/div/button")).click();
        		Thread.sleep(5000);
        	    WebElement ratecode=driver.findElement(By.xpath("//*[@id='bootstrap-wizard-1']/div[1]/div/fieldset/div[1]/div[1]/div/select"));
        	    Select selectratcode=new Select(ratecode);
        	    selectratcode.selectByVisibleText(gen.getProperty("ExistingRateCode"));
        	    WebElement leftelement=driver.findElement(By.xpath("//*[@id='leftValues']"));
        	    ((JavascriptExecutor) driver).executeScript(
        	            "arguments[0].scrollIntoView();", leftelement);
        	    WebDriverWait wait=new WebDriverWait(driver,20);
        	    Thread.sleep(2000);
        	    WebElement roomtype=driver.findElement(By.xpath("//*[@id='bootstrap-wizard-1']/div[1]/div/fieldset/div[2]/div[1]/div/select"));
        	    Select selectroomtype=new Select(roomtype);
        	    selectroomtype.selectByVisibleText(gen.getProperty("ExistingRoomType1"));
        	    Thread.sleep(3000);
        	  //  wait.until(ExpectedConditions.elementToBeSelected(By.xpath("//*[@id='bootstrap-wizard-1']/div[1]/div/fieldset/div[2]/div[2]/div/select")));
        	    WebElement mealplan=driver.findElement(By.xpath("//*[@id='bootstrap-wizard-1']/div[1]/div/fieldset/div[2]/div[2]/div/select"));
        	    Select selectmealplan=new Select(mealplan);
        	    selectmealplan.selectByVisibleText(gen.getProperty("ExixtingMealPlan"));
        	    Thread.sleep(5000);
        	    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='leftValues']")));
        	    WebElement leftelement1=driver.findElement(By.xpath("//*[@id='leftValues']"));
        	    java.util.List<WebElement>option =leftelement1.findElements(By.tagName("option"));
        	    Actions action =new Actions(driver);
        	    for(WebElement opt:option)
        	    {
        	    	System.out.println("inside");
        	    	if(opt.isEnabled())
        	    	{
        	    		String optname=opt.getText();
        	    		System.out.println(optname);
        	    		if(optname.equals(gen.getProperty("ExistingChannel")))
        	    		{
        	    			System.out.println("insideone");
        	    			action.moveToElement(opt).click(opt).build().perform();
        	    			Thread.sleep(3000);
        	    			driver.findElement(By.xpath("//*[@id='btnRight']")).click();
        	    		break;
        	    		}
        	    	}
        	    }
        	    Thread.sleep(3000);
        	    WebElement ele =driver.findElement(By.xpath("//*[@id='bootstrap-wizard-1']/div[1]/div/fieldset/div[5]/div[1]/div[2]/div/select"));
        	    String inventory="Allocation Inventory";
        	    if(inventory.equalsIgnoreCase("Allocation Inventory"))
        	    {
        	    	Select inv=new Select(ele);
        	    	inv.selectByVisibleText("Allocation Inventory");
        	    	Thread.sleep(2000);
        	    	WebElement allocation=driver.findElement(By.xpath("//*[@id='bootstrap-wizard-1']/div[1]/div/fieldset/div[5]/div[2]/div/div/div[2]/div/input"));
        	        allocation.sendKeys("10");
        	        Thread.sleep(2000);
        	    	JavascriptExecutor js = ((JavascriptExecutor) driver);
                    js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        	        driver.findElement(By.xpath("//*[@id='bootstrap-wizard-1']/div[3]/div/div/a[2]")).click();
        	        Thread.sleep(5000);
        	        WebElement msgbox=driver.findElement(By.xpath("//*[@id='Msg1']"));
        	        WebElement msg=msgbox.findElement(By.tagName("p"));
        	        Assert.assertEquals("Confirm your save will modify the rates if the rates have been alloted earlier or New rates will alloted for the Channel", msg.getText());
        	        msgbox.findElement(By.xpath("//*[@id='bot2-Msg1']")).click();
        	        wait=new WebDriverWait(driver,10000);
        	        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("bot1-Msg1")));
        	        WebElement SuccessMsgBox=driver.findElement(By.xpath("//*[@id='Msg1']"));
        	        SuccessMsgBox.findElement(By.id("bot1-Msg1")).click();
        	       
        	       
        	    }  
          }
		
		@AfterTest
		public static void sendMessage()
		{
			ibv4_1 mail=new ibv4_1();
			//mail.mailSend(RateCode);
		}
		
		
	
}

