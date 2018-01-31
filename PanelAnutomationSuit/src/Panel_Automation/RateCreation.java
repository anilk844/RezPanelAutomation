package Panel_Automation;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.SendKeysAction;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.python.antlr.PythonParser.list_for_return;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Location;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

//import com.sun.glass.ui.Clipboard;

public class RateCreation {
	
	public static Properties prop;
	public static Properties gen;
	public static WebDriver driver;
	public static WebDriverWait wait;
	public static Properties stat;
	public static String text;
	public static String room;
	public static String mealplan2;
	public static String channelid2;
	public static String[] monthformate={"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
	public static String QALIVE;
	//public static String Rateresult;
	@BeforeSuite
	public static void login() throws IOException, InterruptedException
	{
        
		 //Status variable conatins Switch QA and Live properties file address
		 FileInputStream status = new FileInputStream("C:/Users/anil.kumar/git/Panel&IBEAutomationSuit/PanelAnutomationSuit/src/LiveRepository/SwitchQAandLIVE.properties");
		 stat=new Properties();//stat is variable of type properties and its used to store object reference
		 stat.load(status);//loading status variable into stat
		 System.out.println(stat.getProperty("Status"));
		 String s1=stat.getProperty("Status");
		 System.out.println(s1);
		 if(s1.equalsIgnoreCase("QA"))
		 {
		
			 System.out.println("inside");
			QALIVE=stat.getProperty("Status");
			FileInputStream pageObjectGen = new FileInputStream("C:/Users/anil.kumar/git/Panel&IBEAutomationSuit/PanelAnutomationSuit/src/Repository/Generic.properties");
			gen=new Properties();//test property data is accessed here from Repository--Generic.properties
			gen.load(pageObjectGen);
		}
		else if(stat.getProperty("Status").equalsIgnoreCase("LIVE"))
		{
			System.out.println("inside");
			QALIVE=stat.getProperty("Status");
			FileInputStream pageObjectGen = new FileInputStream("C:/Users/anil.kumar/git/Panel&IBEAutomationSuit/PanelAnutomationSuit/src/LiveRepository/Generic.properties");
			gen=new Properties();//test live property data is accessed here--LiveRepository--Generic.properties
			gen.load(pageObjectGen);//USING GEN.PROPERTIES we access pageobjectgen 
		}
		
		
		 room=gen.getProperty("ExistingRoomType1");
		channelid2=gen.getProperty("ExistingChannel");
		mealplan2=gen.getProperty("ExixtingMealPlan");
		
		
		//chrome drive code START (general google code)
		 System.setProperty("webdriver.chrome.driver", "D://Maxim Chrom Driver//chromedriver.exe");
		 
		 DesiredCapabilities capabilities = DesiredCapabilities.chrome();
	     ChromeOptions options = new ChromeOptions();
	     options.addArguments("test-type");
	     options.addArguments("--start-maximized");
	     options.addArguments("--disable-web-security");
	     options.addArguments("--allow-running-insecure-content");
	    
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
	    chromePrefs.put("profile.default_content_settings.popups", 0);
	    chromePrefs.put("credentials_enable_service", false);
	    chromePrefs.put("profile.password_manager_enabled", false);
	    //chromePrefs.put("download.default_directory", downloadFilepath);
	    options.setExperimentalOption("prefs", chromePrefs);
	    capabilities.setCapability("chrome.binary","D://Maxim Chrom Driver//chromedriver.exe");
	    capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        driver = new ChromeDriver(capabilities);
        
        //chrome drive code ENDS (general google code)
        driver.get(gen.getProperty("Url"));
        
		wait=new WebDriverWait(driver,40);
		//driver.manage().window().maximize();
	  
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(gen.getProperty("username"))));
		driver.findElement(By.xpath(gen.getProperty("username"))).sendKeys(gen.getProperty("UserNameValue"));  
		driver.findElement(By.xpath(gen.getProperty("password"))).sendKeys(gen.getProperty("PasswordValue"));
		driver.findElement(By.xpath(gen.getProperty("Button"))).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(gen.getProperty("searchField"))));
		driver.findElement(By.xpath(gen.getProperty("searchField"))).sendKeys(gen.getProperty("hotelcode"));
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(gen.getProperty("searchField"))));
		//driver.findElement(By.xpath(gen.getProperty("searchField"))).sendKeys(String.valueOf(Cuscode));
		
		//Property selection from serach box --code STARTS
		WebElement DropOut=driver.findElement(By.cssSelector("*[class^='search_dropdown']"));
		List<WebElement>tr=DropOut.findElements(By.cssSelector("*[class^='ng-scope']"));
		for(WebElement a : tr)//ONE PROPERTY ROW data--for each loop
		{
					List<WebElement>td=a.findElements(By.tagName("td"));//getting all column data of one property
					

			if(td.get(0).getText().equals(String.valueOf(gen.getProperty("hotelcode"))))//if sent hotel code from i/p matches searched hotel code (hotel code always in 0th position 
			{
				a.click();//a contains full row with property info
				System.out.println("Pass");
			}
		}
		//Property selection from search box --code ENDS
	}
	
	
	private static String String(String property) {
		// TODO Auto-generated method stub
		return null;
	}


	@Test
	public static void start() throws InterruptedException, FindFailed, AWTException, IOException, UnsupportedFlavorException
	{
		FileInputStream pageObject = new FileInputStream("C:/Users/anil.kumar/git/Panel&IBEAutomationSuit/PanelAnutomationSuit/src/Repository/Rate_Creation_Repository.properties");
		prop = new Properties();
		prop.load(pageObject);
		driver.get(gen.getProperty("Rate_creation_URL")); //contains either test gen or live gen file
		driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);//implicit wait waits for every element if its not present for 15seconds
		//Thread.sleep(2000);
		for(int i=0;i<1;i++)
		{
			if(gen.getProperty("createNewRate").equalsIgnoreCase("true"))
			{
				
				 CreateNewRate();
				 WebElement rat=driver.findElement(By.xpath("//*[@id='ratecode']"));
				 Actions action=new Actions(driver);
				 action.moveToElement(rat).doubleClick().build().perform();
				 //Thread.sleep(5000);
				 System.out.println("Clicked");
				 Robot robot1 =new Robot();
				 robot1.keyPress(KeyEvent.VK_CONTROL);
			     robot1.keyPress(KeyEvent.VK_C);
			     robot1.keyRelease(KeyEvent.VK_CONTROL);
			     robot1.keyRelease(KeyEvent.VK_C);
			 	 driver.findElement(By.xpath(prop.getProperty("Rate_Des"))).click();
				
				//robot1.keyPress(KeyEvent.VK_ENTER);
				 robot1.keyPress(KeyEvent.VK_CONTROL);
				 robot1.keyPress(KeyEvent.VK_V);
				 robot1.keyRelease(KeyEvent.VK_CONTROL);
				 robot1.keyRelease(KeyEvent.VK_V);
			     //java.awt.datatransfer.Clipboard.setContents(null, null);
			     java.awt.datatransfer.Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			     DataFlavor flavor = DataFlavor.stringFlavor;
			     if (clipboard.isDataFlavorAvailable(flavor)) {
			       try {
			         text = (String) clipboard.getData(flavor);
			         System.out.println(text);
			       } catch (UnsupportedFlavorException e) {
			         System.out.println(e);
			       } catch (IOException e) {
			         System.out.println(e);
			       }
			     }
				 Thread.sleep(2000);
				
				
				 
			
				
			
				if(gen.getProperty("addNewMealPlan").equalsIgnoreCase("true"))
				{
					AddNewRatePlan();
		        }
				
				if(gen.getProperty("addRates").equalsIgnoreCase("true"))
				{
					AddRate();
					Thread.sleep(3000);
					
					//waiting to see save button on create rate header
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='bootstrap-wizard-1']/div/div[1]/div/div/a[1]")));
					//click on save button --STARTS
					driver.findElement(By.xpath("//*[@id='bootstrap-wizard-1']/div/div[1]/div/div/a[1]")).click();
					
					
					WebElement msgbox=driver.findElement(By.xpath("//*[@id='Msg1']"));
					String msg=msgbox.findElement(By.tagName("p")).getText();
					
					if(msg.equalsIgnoreCase("Are you sure do you want to save"))
					{
						msgbox.findElement(By.id("bot2-Msg1")).click();
						Thread.sleep(3000);
						WebElement SuccessMsgBox=driver.findElement(By.xpath("//*[@id='Msg1']"));
						SuccessMsgBox.findElement(By.id("bot1-Msg1")).click();
						Thread.sleep(3000);
						
					}
					//click on save button --ENDS
		        }
				
			}
				//Rate modification
				if(gen.getProperty("existingRateCode").equalsIgnoreCase("true"))
				{
					//AddNewRatePlan();
					ExistingRateCode();
				}
				//AssignRateToChannel();
		
		        //String CreateNewRate ="False";
		//Create New Rate Code 
	}
	}
	
	
	
	
	
	//CreateNewRate method starts here
	
	public static void CreateNewRate() throws InterruptedException
	{
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(prop.getProperty("Add_New_button"))));//prop contains rate creation repository 
		//wait.until(ExpectedConditions.(By.xpath(prop.getProperty("Add_New_button"))));
		Thread.sleep(3000);

		driver.findElement(By.xpath(prop.getProperty("Add_New_button"))).click();
		WebElement ele=driver.findElement(By.xpath(prop.getProperty("Rate_type")));
		Select sel = new Select(ele);
		sel.selectByVisibleText(gen.getProperty("RateTypeVAlue"));
		
		WebElement ele1 =driver.findElement(By.xpath(prop.getProperty("Currency_type")));
		Select sel1= new Select(ele1);
		sel1.selectByVisibleText(gen.getProperty("CurrencyTypeValue"));
		
		driver.findElement(By.xpath(prop.getProperty("Season"))).sendKeys(gen.getProperty("SeasonValue"));
		driver.findElement(By.xpath(prop.getProperty("Start_date"))).click();
		
		//start date calendar function
		String year=driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/div/span")).getText();
		
		SimpleDateFormat DF = new SimpleDateFormat("dd/MM/yyyy");
		Calendar RC = Calendar.getInstance();
		String start=DF.format(RC.getTime());//todays date you get here in dd/mm/yyyy format
		int inputyear=Integer.parseInt(start.substring(6,10));
		int month=Integer.parseInt(start.substring(3,5));
		int daycal=Integer.parseInt(start.substring(0,2));
		int year1=Integer.parseInt(year);
		//int inputyear=Integer.parseInt(gen.getProperty("StartYear"));
		while(inputyear!=year1) //coparing system date format year with the calendar year in panel
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
			sel2.selectByVisibleText(monthformate[month-1]);//monthformat is as array used above and array starts from 0th position so month-1
			//sel2.selectByVisibleText(gen.getProperty("StartMonth"));
		}
		//Date Selection Codes starts from here
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
				System.out.println(day);
				//if(gen.getProperty("StartDay").equalsIgnoreCase(day))
				if(String.valueOf(daycal).equalsIgnoreCase(day))
				{
					e1.click();
					flag1=1;
					break;
					
				}
			}
			}
		}
		//Date Selection Codes ENDS here
		
		//Rate code end date selection starts here
		RC.add(Calendar.DATE, 200);//rate creation happens for 200 days duration
		String end=DF.format(RC.getTime());
		int endyear=Integer.parseInt(end.substring(6,10));
		int endmonth=Integer.parseInt(end.substring(3,5));
		int enddaycal=Integer.parseInt(end.substring(0,2));
		
		
		driver.findElement(By.xpath(prop.getProperty("end_date"))).click();
		String yearend=driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/div/span")).getText();
		
		int yearend1=Integer.parseInt(yearend);
		int inputyearend=endyear;
		//int inputyearend=Integer.parseInt(gen.getProperty("EndYear"));
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
			sel5.selectByVisibleText(monthformate[endmonth-1]);
		}
		WebElement ele6=driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/table"));
		List<WebElement> tr1=ele6.findElements(By.tagName("tr"));
		for(WebElement e1:tr1)
		{
			List<WebElement> td1=e1.findElements(By.tagName("td"));
			for(WebElement e2:td1)
			{
				String day=e2.getText();
				System.out.println(day);
				//if(gen.getProperty("EndDay").equalsIgnoreCase(day))
				if(String.valueOf(enddaycal).equalsIgnoreCase(day))
				{
					e1.click();
					break;
				}
			}
		}
		
		//Rate code end date selection ends here
		
		driver.findElement(By.xpath(prop.getProperty("Rate_Name"))).sendKeys(gen.getProperty("RateNameValue"));
		driver.findElement(By.xpath(prop.getProperty("Rate_Des"))).sendKeys(gen.getProperty("RateDescValue"));
		driver.findElement(By.xpath(prop.getProperty("Room_Type"))).click();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElement(By.xpath(prop.getProperty("Room_Type"))).sendKeys(gen.getProperty("ExistingRoomType1"));
        Thread.sleep(2000);
	}

		
	//CreateNewRate method ends here
	
	
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
			//---**add save button----*****
		}
		
		
		
		
		//end add new rate plan 
		//click on existing ratecode 
		
		//end on clicking existing rate code
	

		
		
		
		
          public static void AddRate() throws InterruptedException, FindFailed
          {
			 Thread.sleep(2000);
			//*[@id="accord0"]/div/div[1]/h6/a
			   driver.findElement(By.xpath("//*[@id='addrates']")).click();
			   wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[@id='remoteModal-rate']/div/div/div[2]/div")));
			   //Add rate pop up window
			   WebElement msgbox1=driver.findElement(By.xpath("//*[@id='remoteModal-rate']/div/div/div[2]/div"));
			
			List<WebElement> lst2=msgbox1.findElements(By.tagName("div"));//rate plan div storing in list variable
			
			for(int k=0;k<lst2.size();k=k+31)//there are 31 div tags in each meal plan accordian
			{
			    try
			    {
				WebElement e2=lst2.get(k);
				List<WebElement>st=e2.findElements(By.tagName("strong"));//meal plan tag name everywhere is in strong tagname
				String str1=st.get(0).getText();//CP is stored
				WebElement strong=e2.findElement(By.tagName("i"));// meal plan accordion arrow address is stored
				if(str1.equalsIgnoreCase(gen.getProperty("ExixtingMealPlan")))
				{
					strong.click();//clicking on meal plan accordion
					WebElement Tbody=e2.findElement(By.tagName("tbody"));
					List<WebElement>tr2=Tbody.findElements(By.tagName("tr"));//all rows stored in list tr2
					for(int i=0;i<tr2.size();i++) //considering each row while entering rates
					{
						WebElement tr3=tr2.get(i);
						List<WebElement>td3=tr3.findElements(By.tagName("td")); //all columns stored here in list td3
						for(int j=1;j<3;j++) //entering single and double rate for all 7 days in this loop
						{
							WebElement td4 =td3.get(j);
							WebElement input = td4.findElement(By.tagName("input"));
							input.clear();
							input.sendKeys(gen.getProperty("AddAmount"));
							//---**add save button or cancel button----*****
							
							//these 2 lines used to scroll the entire page to downwards--starts
							JavascriptExecutor js = ((JavascriptExecutor) driver);
					        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
					        
					      //these 2 lines used to scroll the entire page to downwards--ends
							driver.findElement(By.xpath("//*[@id='remoteModal-rate']/div/div/div[3]/a[1]")).click();//clicking on save button
							
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
			
		}
		
          
          
          
          
          
          public static void AssignRateToChannel() throws InterruptedException
          {
        	   //if(channelid2.equalsIgnoreCase(""))
        	   //driver.get("https://redapptest.azurewebsites.net/index.html#/taxstru/taxstru");
        	  
        	   Thread.sleep(4000);
      
        		driver.findElement(By.xpath("//*[@id='ratechannelstab']/a/i")).click();//assign rate to channel -tab click
        		Thread.sleep(3000);
        		//*[@id="ratechannelstab"]
        		driver.findElement(By.xpath("//*[@id='RateAssignTab']/header/div/button")).click();//assign rate to channel button click
        		Thread.sleep(4000);
        	    WebElement ratecode=driver.findElement(By.xpath("//*[@id='bootstrap-wizard-1']/div[1]/div/fieldset/div[1]/div[1]/div/select"));//rate code select box address
        	    Select selectratcode=new Select(ratecode);
        	    if(gen.getProperty("createNewRate").equalsIgnoreCase("false"))
        	    {
        	    	  selectratcode.selectByVisibleText(gen.getProperty("ExistingRateCode"));
        	    }
        	    else
        	    {
        	    	selectratcode.selectByVisibleText(text);//assigning selected rate code 
        	    	
        	    }
        	  
        	    WebElement leftelement=driver.findElement(By.xpath("//*[@id='leftValues']"));
        	  //these 2 lines used to scroll the entire page to downwards--starts
        	    ((JavascriptExecutor) driver).executeScript(
        	            "arguments[0].scrollIntoView();", leftelement);
        	  //these 2 lines used to scroll the entire page to downwards--ends
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
        	   
        	    Thread.sleep(3000);
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
        	    			action.moveToElement(opt).click(opt).build().perform();//Mouse action to click on matched enabled channel
        	    			Thread.sleep(3000);
        	    			 driver.findElement(By.xpath("//*[@id='btnRight']")).click();//navigation arrow xpath
        	    		
        	    			//break;
        	    		}
        	    	}
        	    }
        	    Thread.sleep(3000);
        	  
        	    WebElement ele=driver.findElement(By.xpath("//*[@id='bootstrap-wizard-1']/div[1]/div/fieldset/div[5]/div[1]/div[2]/div/select"));

        	  
        	    String inventory="Allocation Inventory";
        	    if(inventory.equalsIgnoreCase("Allocation Inventory"))
        	    {
        	    	WebElement allocation=null;
        	    
        	    	Select inv=new Select(ele);
        	    	inv.selectByVisibleText("Allocation Inventory");
        	    	Thread.sleep(2000);
        	    	try
        	    	{
        	    	allocation=driver.findElement(By.xpath("//*[@id='bootstrap-wizard-1']/div[1]/div/fieldset/div[6]/div[2]/div/div/div[2]/div/input"));//allocation inventory will be sent to allocation
        	    	}
        	    	
        	    	catch(Exception e)
        	    	{
        	    		
        	    	}
        	    	try //2 try boxes just to manage inventory which we are getting sometimes in div[5] or div[6]
        	    	{
        	    	allocation=driver.findElement(By.xpath("//*[@id='bootstrap-wizard-1']/div[1]/div/fieldset/div[5]/div[2]/div/div/div[2]/div/input"));
        	    	}
        	    	catch(Exception e)
        	    	{
        	    		
        	    	}
        	    	
        	        allocation.sendKeys("10");
        	        Thread.sleep(2000);
        	        
        	      //these 2 lines used to scroll the entire page to downwards--starts
        	    	JavascriptExecutor js = ((JavascriptExecutor) driver);

        	        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        	         
        	      //these 2 lines used to scroll the entire page to downwards--ends
        	        driver.findElement(By.xpath("//*[@id='bootstrap-wizard-1']/div[3]/div/div/a[2]")).click();//save button click
        	        Thread.sleep(3000);
        	        //For Internal Use only box address  
        	        WebElement dialogmodule=driver.findElement(By.xpath("/html/body/div[52]/div/div"));
        	        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("*[class^='btn btn-primary']")));
        	        //Internal Use proceed button address 
        	        dialogmodule.findElement(By.cssSelector("*[class^='btn btn-primary']")).click();
        	        Thread.sleep(5000);
        	        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='Msg1']")));
        	        
        	        WebElement msgbox=driver.findElement(By.xpath("//*[@id='Msg1']"));//1st masg box after clicking on proceed is stored here
        	        WebElement msg=msgbox.findElement(By.tagName("p"));
        	        Assert.assertEquals("Confirm your save will modify the rates if the rates have been alloted earlier or New rates will alloted for the Channel", msg.getText());//if assert functions returns false next entire code wont be executed
        	        msgbox.findElement(By.xpath("//*[@id='bot2-Msg1']")).click();
        	        Thread.sleep(3000);
        	        wait=new WebDriverWait(driver,10000);
        	        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("bot1-Msg1")));
        	        WebElement SuccessMsgBox=driver.findElement(By.xpath("//*[@id='Msg1']"));//2nd masg box after clicking on proceed is stored here
        	        SuccessMsgBox.findElement(By.id("bot1-Msg1")).click();
        	       
        	       
        	    }  
          }
		
		//driver.findElement(By)
		//Thread.sleep(30000);
		
		
	
}
