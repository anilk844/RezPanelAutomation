package DataSync;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		data= new ArrayList();
		FileInputStream file= new FileInputStream("D://IBE TestCase//DataSyn.xlsx");
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
		int rw=data.size()/2;
		Object a[][]=new Object[rw][2];
		int j=0;
		for(int i=0;i<data.size();i=i+2)
		{
			
		    double a1=(double)data.get(i);
		    int CustCode=(int) Math.round(a1);
		    a[j][0]=CustCode;
		    String Channels=(String) data.get(i+1);
		    a[j][1]=Channels;
			j=j+1;
			
		}
		return a;
	}
	
@Test(dataProvider="getData")	
public static void datasyn(int Cuscode,String Channel) throws IOException, InterruptedException
{
	
	if(topflag)
	{
	FileInputStream status = new FileInputStream("C:/Users/anil.kumar/git/RezPanelAutomation/PanelAnutomationSuit/src/LiveRepository/SwitchQAandLIVE.properties");
	stat=new Properties();
	stat.load(status);
	if(stat.getProperty("Status").equalsIgnoreCase("QA"))
	{
		QALIVE=stat.getProperty("Status");
		FileInputStream pageObjectGen = new FileInputStream("C:/Users/anil.kumar/git/RezPanelAutomation/PanelAnutomationSuit/src/Repository/Generic.properties");
		gen=new Properties();
		gen.load(pageObjectGen);
	}
	else if(stat.getProperty("Status").equalsIgnoreCase("LIVE"))
	{
		QALIVE=stat.getProperty("Status");
		FileInputStream pageObjectGen = new FileInputStream("C:/Users/anil.kumar/git/RezPanelAutomation/PanelAnutomationSuit/src/LiveRepository/Generic.properties");
		gen=new Properties();
		gen.load(pageObjectGen);
	}
	
	
	System.setProperty("webdriver.chrome.driver", "D://chrome//chromedriver.exe");
	 
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
    capabilities.setCapability("chrome.binary","D://chrome//chromedriver.exe");
    capabilities.setCapability(ChromeOptions.CAPABILITY, options);
    
   
   
    driver = new ChromeDriver(capabilities);
    //WebDriverWait wait=new WebDriverWait(driver,40);
    driver.get(gen.getProperty("Url"));
   
	wait=new WebDriverWait(driver,40);
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
	
	
    driver.get("https://redapptest.azurewebsites.net/index.html#/channelcontrol/channelcontrol");
    String channellist[]=Channel.split("\\+");
    System.out.println(channellist.length);
    
    for(String c:channellist)
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
			Reporter.log("<font font-family='Times New Roman'>Calendar Page--</font><font color='blue'>"+"(Custcode:-"+Cuscode+" Channel Name:-"+c+" Status:-"+txt+"-:PASS</font></a>", true);
			System.out.println(txt);
		}else
		{
			Reporter.log("<font font-family='Times New Roman'>Calendar Page--</font><font color='#990000'>"+"(Custcode:-"+Cuscode+" Channel Name:-"+c+" Status:-"+text+"-:Fail</font></a>", true);
		}
    	
   /* System.out.println(c);
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
    		String text=msgbox.findElement(By.cssSelector("*[class^='pText']")).getText();
    		if(text.equalsIgnoreCase("Please provide Min/Max Weightage for All Mapped Rooms"))
    		{
    		    System.out.println("Please provide Min/Max Weightage for All Mapped Rooms");
    		    Thread.sleep(2000);
    		    msgbox.findElement(By.id("bot1-Msg1")).click();
    		}
    		if(text.equalsIgnoreCase("Save Failed"))
    		{

    		    System.out.println("Save Failed");
    		    Thread.sleep(2000);
    		    msgbox.findElement(By.id("bot1-Msg1")).click();
    			
    		}
    		if(text.equalsIgnoreCase("Saved successfully"))
    		{
    			//System.out.println(c);
    			System.out.println("Saved successfully");
    			msgbox.findElement(By.id("bot1-Msg1")).click();
    			wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("*[class^='table table-bordered channel_control_body table-responsive']")));
    			System.out.println("Saved successfully11");
    			System.out.println("-----"+td.get(1).getText());
    			td.get(8).click();
    			System.out.println("Saved successfully111");
    			wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("*[class^='MessageBoxContainer animated fadeIn fast']")));
        		WebElement msgbox1=driver.findElement(By.cssSelector("*[class^='MessageBoxContainer animated fadeIn fast']"));
        		Thread.sleep(2000);
        		String text1=msgbox1.findElement(By.cssSelector("*[class^='pText']")).getText();
        		if(text1.equalsIgnoreCase("Please provide Min/Max Weightage for All Mapped Rooms"))
        		{
        		    System.out.println("Please provide Min/Max Weightage for All Mapped Rooms");
        		    msgbox1.findElement(By.id("bot1-Msg1")).click();
        		}
        		if(text1.equalsIgnoreCase("Saved successfully"))
        		{
        			System.out.println("Saved successfully");
        			msgbox1.findElement(By.id("bot1-Msg1")).click();
        		}
    		}
    		
    	}
    	}
    }*/
    }
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
