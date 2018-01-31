package DataSync;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataSyn {
	public static Properties prop;
	public static Properties gen;
	public static WebDriver driver;
	public static WebDriverWait wait;
	public static Properties stat;
	//public static String text;
	public static String room;
	public static String mealplan2;
	public static String channelid2;
	public static String[] monthformate={"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
	public static String QALIVE;
	public static ArrayList data;
	
	public static boolean topflag=true;
	//This section is to fetch data from Excel Starts here**
	@DataProvider
	public Object[][] getData() throws IOException 
	{
		data= new ArrayList();
		System.out.println("1");
		FileInputStream file= new FileInputStream("C://Users//anil.kumar//git//Panel&IBEAutomationSuit//PanelAnutomationSuit//src//DataSync//DataSyn.xlsx");
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
		/*
		 3665
		 1
		 783
		 1
		 13
		 1
		 Data would be stored in this fashion
		 */
		int rw=data.size()/2;//To calculate row. eg: 6/2 = 3
		Object a[][]=new Object[rw][2]; // creating multi-dimensional array 
		int j=0;
		for(int i=0;i<data.size();i=i+2)
		{
			
		    double a1=(double)data.get(i);
		    int CustCode=(int) Math.round(a1);//to handle excel input errors 3665.0
		    System.out.println(CustCode);
		    a[j][0]=CustCode;
		    a[j][1]="1";
		    j++;
			
		}
		return a;
	}
	//This section is to fetch data from Excel ends here**

	//Data provider(getData()) is interconnected with the dataSync() 
	@Test(dataProvider="getData")	
	public static void datasyn(int Cuscode,String n) throws IOException, InterruptedException, ClassNotFoundException, SQLException
	{
	 Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");	
	
	 //Connection conn = DriverManager.getConnection("jdbc:sqlserver://he05ik8udk.database.windows.net;user=Rezstaging;password=Staging@123;database=redcoredblive28may16_test");
	 Connection conn = DriverManager.getConnection("jdbc:sqlserver://he05ik8udk.database.windows.net;user=RED;password=TechOperation_786;database=redcoredblive");
	if(topflag)
	{
	FileInputStream status = new FileInputStream("C:/Users/anil.kumar/git/Panel&IBEAutomationSuit/PanelAnutomationSuit/src/LiveRepository/SwitchQAandLIVE.properties");
	stat=new Properties();
	stat.load(status);
	if(stat.getProperty("Status").equalsIgnoreCase("QA"))
	{
		QALIVE=stat.getProperty("Status");
		FileInputStream pageObjectGen = new FileInputStream("C:/Users/anil.kumar/git/Panel&IBEAutomationSuit/PanelAnutomationSuit/src/Repository/Generic.properties");
		gen=new Properties();
		gen.load(pageObjectGen);
	}
	else if(stat.getProperty("Status").equalsIgnoreCase("LIVE"))
	{
		QALIVE=stat.getProperty("Status");
		FileInputStream pageObjectGen = new FileInputStream("C:/Users/anil.kumar/git/Panel&IBEAutomationSuit/PanelAnutomationSuit/src/LiveRepository/Generic.properties");
		gen=new Properties();
		gen.load(pageObjectGen);
	}
	
	
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
    //WebDriverWait wait=new WebDriverWait(driver,40);
    driver.get(gen.getProperty("Url"));
   
	wait=new WebDriverWait(driver,500);
	//driver.manage().window().maximize();
 
	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(gen.getProperty("username"))));
	 
	driver.findElement(By.xpath(gen.getProperty("username"))).sendKeys(gen.getProperty("UserNameValue"));
	  
	driver.findElement(By.xpath(gen.getProperty("password"))).sendKeys(gen.getProperty("PasswordValue"));
	driver.findElement(By.xpath(gen.getProperty("Button"))).click();
	topflag=false;
	}
	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(gen.getProperty("searchField"))));
	driver.findElement(By.xpath(gen.getProperty("searchField"))).sendKeys(String.valueOf(Cuscode));
	
	WebElement DropOut=driver.findElement(By.cssSelector("*[class^='search_dropdown']"));
	List<WebElement>tr=DropOut.findElements(By.cssSelector("*[class^='ng-scope']"));
	for(WebElement a : tr)
	{
				List<WebElement>td=a.findElements(By.tagName("td"));
				System.out.println(td.get(1).getText());

		if(td.get(0).getText().equals(String.valueOf(Cuscode)))
		{
			a.click();
			System.out.println("Pass");
		}
	}
	//driver.findElement(By.xpath("//*[@id='header']/div[2]/form/div/table/tbody/tr[2]")).click();
	
	
    driver.get("https://red.reznext.com/index.html#/channelcontrol/channelcontrol");
    Statement Cust = conn.createStatement();
    //String Custstr1="select channelid from TBLRZNChannelControl where custcode="+Cuscode+" and DSwitchChnl=1 and channelid not in (8,2,45)";
    String Custstr1="select channelid from TBLRZNChannelControl where custcode="+Cuscode+" and DSwitchChnl=1 and channelid =86";
    ResultSet Custx = Cust.executeQuery(Custstr1);
    ArrayList<String> st=new ArrayList<String>();
    while (Custx.next())
    {
       System.out.println(Custx.getString(1));
       String channelid=Custx.getString(1);
       Statement Chanl = conn.createStatement();
       String ChannelName="select Channelname from TBLRZNChannels where id="+channelid+"";
       ResultSet ChannelNamex = Chanl.executeQuery(ChannelName);
       while (ChannelNamex.next())
       {
    	   System.out.println(ChannelNamex.getString(1));
    	   st.add(ChannelNamex.getString(1));
       }
    }
       
    
    //start----------------
   // String channellist[]=Channel.split("\\+");
    //System.out.println(channellist.length);
    
    for(String c:st)
    {
    	String text=click(c);
    	if(text.equalsIgnoreCase("Please provide Min/Max Weightage for All Mapped Rooms"))
		{
		    System.out.println("Please provide Min/Max Weightage for All Mapped Rooms");
		    Reporter.log("<font font-family='Times New Roman'>Calendar Page--</font><font color='#990000'>"+"(Custcode:-"+Cuscode+" Channel Name:-"+c+" Status:-"+text+"-:FAIL</font></a>", true);
		    
		}else
		if(text.equalsIgnoreCase("Save Failed"))
		{

			Reporter.log("<font font-family='Times New Roman'>Calendar Page--</font><font color='#990000'>"+"(Custcode:-"+Cuscode+" Channel Name:-"+c+" Status:-"+text+"-:FAIL</font></a>", true);
		    System.out.println("Save Failed");
		   
			
		}else
			if(text.equalsIgnoreCase("Saved successfully"))
		{
			
			System.out.println(text);
			String txt=click(c);
			if(txt.equalsIgnoreCase("Please provide Min/Max Weightage for All Mapped Rooms"))
			{
			    System.out.println("Please provide Min/Max Weightage for All Mapped Rooms");
			    Reporter.log("<font font-family='Times New Roman'>Calendar Page--</font><font color='#990000'>"+"(Custcode:-"+Cuscode+" Channel Name:-"+c+" Status:-"+txt+"-:FAIL</font></a>", true);
			    
			}else
			if(txt.equalsIgnoreCase("Save Failed"))
			{

				Reporter.log("<font font-family='Times New Roman'>Calendar Page--</font><font color='#990000'>"+"(Custcode:-"+Cuscode+" Channel Name:-"+c+" Status:-"+txt+"-:FAIL</font></a>", true);
			    System.out.println("Save Failed");
			   
			
			    
			}else
			if(text.equalsIgnoreCase("Saved successfully"))
			{
			Reporter.log("<font font-family='Times New Roman'>Calendar Page--</font><font color='blue'>"+"(Custcode:-"+Cuscode+" Channel Name:-"+c+" Status:-"+txt+"-:PASS</font></a>", true);
			}
			else
			{
				Reporter.log("<font font-family='Times New Roman'>Calendar Page--</font><font color='#990000'>"+"(Custcode:-"+Cuscode+" Channel Name:-"+c+" Status:-"+txt+"-:Fail</font></a>", true);
			}
			System.out.println(txt);
		}
    	
    	
    	
    	
    	 //end-------------------------------
    	


   }
    Thread.sleep(30000);
}



public static String click(String c) throws InterruptedException
{       String text=null;
	    System.out.println(c);
	    wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("*[class^='fixed-width desktop-detected menu-on-top ng-scope container smart-style-2']")));
	    Thread.sleep(3000);
	    //WebElement div=driver.findElement(By.cssSelector("*[class^='fixed-width desktop-detected menu-on-top ng-scope container smart-style-2']"));
	    WebElement table=driver.findElement(By.cssSelector("*[class^='table table-bordered channel_control_body table-responsive']"));
	    List<WebElement>ChannelControlList=table.findElements(By.cssSelector("*[class^='ng-scope']"));
	    //System.out.println("Hello"+ChannelControlList.size());
	    boolean flag=true;
	    for(WebElement a :ChannelControlList)
	    {
	    	
	    	if(flag)
	    	{
	    	List<WebElement>td=a.findElements(By.tagName("td"));
	    	if(td.get(1).getText().equalsIgnoreCase(c))
	    	{
	    		flag=false;
	    		//System.out.println(td.get(1).getText());
	    		td.get(8).click();
	    	    wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("*[class^='MessageBoxContainer animated fadeIn fast']")));
	    		WebElement msgbox=driver.findElement(By.cssSelector("*[class^='MessageBoxContainer animated fadeIn fast']"));
	    		Thread.sleep(2000);
	    	    text=msgbox.findElement(By.cssSelector("*[class^='pText']")).getText();
	    		
	    		msgbox.findElement(By.id("bot1-Msg1")).click();
	    		
	    	}
	    	}
	    }
	    return text;
}
}
