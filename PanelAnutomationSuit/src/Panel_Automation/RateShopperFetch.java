package Panel_Automation;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
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
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class RateShopperFetch {
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
	public static String arraycust[][];
	public static boolean propavail=false;
	public static boolean setup=false;
	@BeforeSuite
	public static void setup() throws IOException
	{
        System.out.println("RateCreation");
		
		
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
		
		FileInputStream file= new FileInputStream("D://My_Work//RateShopper//Rateshopper _Paying Customers.xlsx");
		//FileInputStream file= new FileInputStream("D://Keys1.xlsx");
		
		ArrayList data= new ArrayList();
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheet("Sheet2");
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
		System.out.println(data);
		int b=data.size()/2;
		arraycust=new String[b][2];
		int val=0;
		for(int i=0;i<data.size();i=i+2)
		{
			int k=i;
			arraycust[val][0]=(String) data.get(k);
			System.out.println(arraycust[val][0]);
			arraycust[val][1]=(String) data.get(k=k+1);
			val=val+1;
			System.out.println( data.get(i)+"------"+data.get(i+1));
		}
		
		for(int i=0;i<arraycust.length;i++)
		{
			System.out.println(arraycust[i][0]+"--------"+arraycust[i][1]);
		}
		
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
        
		 wait=new WebDriverWait(driver,80);
		//driver.manage().window().maximize();
	  
		 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(gen.getProperty("username"))));
		 
		 driver.findElement(By.xpath(gen.getProperty("username"))).sendKeys(gen.getProperty("UserNameValue"));
		  
		 driver.findElement(By.xpath(gen.getProperty("password"))).sendKeys(gen.getProperty("PasswordValue"));
		 driver.findElement(By.xpath(gen.getProperty("Button"))).click();
		 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(gen.getProperty("searchField"))));
		  
		 System.out.println("1");
	}
	@Test(dataProvider="custcode")
	public static void RateFetchClick(String custcode,String Custname) throws InterruptedException
	{   
		try
		{
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("/html/body/span")));
		propavail=false;
		setup=false;
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(gen.getProperty("searchField"))));
		driver.findElement(By.xpath(gen.getProperty("searchField"))).clear();
		
		driver.findElement(By.xpath(gen.getProperty("searchField"))).sendKeys(Custname);

		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='header']/div[2]/form/div/table/tbody/tr[2]")));	
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='header']/div[2]/form/div/table/tbody/tr[2]")));
		driver.findElement(By.xpath("//*[@id='header']/div[2]/form/div/table/tbody/tr[2]")).click();
		System.out.println("2");
		propavail=true;
		}
		catch(Exception e)
		{
			
		}
		try
		{
		Screen scn= new Screen();
		String img="D://sikili//Ban.PNG";
		Pattern ptr= new Pattern(img);
		Thread.sleep(4000);
		scn.click(ptr);
		 propavail=true;
		}
		catch(Exception e)
		{
			
		}
		
		driver.get(gen.getProperty("RateShopperFetch"));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("/html/body/span")));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='header']/div[2]/label[2]")));
	
		String custname=driver.findElement(By.xpath("//*[@id='header']/div[2]/label[2]")).getText();
		System.out.println(Custname+"-----------------"+custname);
		if(!propavail)
		{
			System.out.println("Property Not found");
		}
		if(Custname.startsWith(custname.trim().replace(".","")))
		{
			System.out.println("3");
			//driver.navigate().to(gen.getProperty("Ticket"));
			//driver.navigate().refresh();
		   
		
	   try
		{
		Thread.sleep(8000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='content']/section/div[2]/div[2]/a"))).click();
		setup=true;
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		try
		{
			
			Screen scn1= new Screen();
			String img="D://sikili//RateFetchClick.PNG";
			Pattern ptr= new Pattern(img);
			Thread.sleep(4000);
			scn1.click(ptr);
			setup=true;
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		if(setup)
		{
		Thread.sleep(2000);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='Msg1']")));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='Msg1']")));
		WebElement ele=driver.findElement(By.xpath("//*[@id='Msg1']"));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='bot2-Msg1']"))).click();
		wait.until(ExpectedConditions.alertIsPresent());
	    Alert alert=driver.switchTo().alert();
	    String msg=alert.getText();
	    alert.accept();
	    if(!msg.startsWith("Your validity period expired"))
	    {
	    Reporter.log("Custcode="+custcode+" Custname="+custname+""+msg);
	    Thread.sleep(120000);
	    }
	    else
	    {
	    Reporter.log("Custcode="+custcode+" Custname="+custname+""+msg);
	    }
		}
		else
		{
			//*[@id="Msg1"]
			Thread.sleep(2000);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='Msg1']")));
			WebElement msg=driver.findElement(By.xpath("//*[@id='Msg1']"));
			msg.findElement(By.xpath("//*[@id='bot1-Msg1']")).click();
			Thread.sleep(2000);
			driver.navigate().to(gen.getProperty("Ticket"));
			driver.navigate().refresh();
			System.out.println("setup is not completed");
			 Reporter.log("custcode="+custcode+" Custname="+custname+"---RateShopper setup is not done");
		}
	    //alert.accept();
		}
		else
		{
			Reporter.log("Not fetched due to problem in selecting property");
		}
		
	}

	
	@DataProvider
	public static String[][] custcode()
	{
		/*String[][] cust={{"13","Green Park1"}
		,{"47","Green Park"},{"595","Green Park"},{"2309","Marigold by GreenPark"}
		};*/
		return arraycust;
	}
}
