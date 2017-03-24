package Dev_progress;

import java.awt.AWTException;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.testng.annotations.Test;

import com.thoughtworks.selenium.webdriven.commands.Click;

public class PromocodeCreation {
	public static Properties prop;
	public static WebDriver driver;
	public static WebDriverWait wait;
	public static Properties gen;
	public static Properties stat;
	public static String[] monthformate={"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
	@Test
	public static void promo() throws InterruptedException, FindFailed, AWTException, IOException
	{
		
		
		FileInputStream status = new FileInputStream("D:/WorkSpace/ibv4_code/PanelAnutomationSuit/src/LiveRepository/SwitchQAandLIVE.properties");
		stat=new Properties();
		stat.load(status);
		if(stat.getProperty("Status").equalsIgnoreCase("QA"))
		{
			FileInputStream pageObjectGen = new FileInputStream("D:/WorkSpace/ibv4_code/PanelAnutomationSuit/src/Repository/Generic.properties");
			gen=new Properties();
			gen.load(pageObjectGen);
		}
		else if(stat.getProperty("Status").equalsIgnoreCase("LIVE"))
		{
			FileInputStream pageObjectGen = new FileInputStream("D:/WorkSpace/ibv4_code/PanelAnutomationSuit/src/LiveRepository/Generic.properties");
			gen=new Properties();
			gen.load(pageObjectGen);
		}
		FileInputStream pageObject = new FileInputStream("D:/WorkSpace/ibv4_code/PanelAnutomationSuit/src/Repository/Promocode.properties");
		prop = new Properties();
	    prop.load(pageObject);
	    driver=RateCreation.driver;
	    
		wait=new WebDriverWait(driver,40);
		
		
		/*System.setProperty("webdriver.chrome.driver", "D://chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-extensions");
		
		driver=new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get(gen.getProperty("Url"));
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(gen.getProperty("username"))));
		
		driver.findElement(By.xpath(gen.getProperty("username"))).sendKeys(gen.getProperty("UserNameValue"));
		driver.findElement(By.xpath(gen.getProperty("password"))).sendKeys(gen.getProperty("PasswordValue"));
		driver.findElement(By.xpath(gen.getProperty("Button"))).click();
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(gen.getProperty("searchField"))));
		driver.findElement(By.xpath(gen.getProperty("searchField"))).sendKeys(gen.getProperty("hotelcode"));
		driver.findElement(By.xpath("//*[@id='header']/div[2]/form/div/table/tbody/tr[2]")).click();
		/*Screen scn= new Screen();
		String img="D://sikili//Ban.PNG";
		Pattern ptr= new Pattern(img);
		Thread.sleep(4000);
		scn.click(ptr);*/
		
		
		
		driver.get(gen.getProperty("PromocodeUrl"));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(prop.getProperty("newbutton"))));
		Thread.sleep(3000);
		driver.findElement(By.xpath(prop.getProperty("newbutton"))).click();
		WebElement ratecodetype=driver.findElement(By.xpath(prop.getProperty("RateCodeType")));
		Select sel=new Select(ratecodetype);
		if(gen.getProperty("Chain").equalsIgnoreCase("1"))
		{
			sel.selectByVisibleText(RateCreation.text);
		}
		else
		{
			sel.selectByVisibleText(gen.getProperty("RateCodeValue"));                                                               //--Changed--
		}
		
		
		Thread.sleep(2000);
		WebElement roomtype=driver.findElement(By.xpath(prop.getProperty("RoomType")));
		Select sel1=new Select(roomtype);
		if(gen.getProperty("Chain").equalsIgnoreCase("1"))
		{
			sel1.selectByVisibleText(RateCreation.room);
		}
		else
		{
			sel1.selectByVisibleText(gen.getProperty("PromocodeRoomTypeValue"));                                                   //--Changed--
		}
		
		WebElement mealtype=driver.findElement(By.xpath(prop.getProperty("MealPlanType")));
		Select sel2=new Select(mealtype);
		if(gen.getProperty("Chain").equalsIgnoreCase("1"))
		{
			sel2.selectByVisibleText(RateCreation.mealplan2);                                                                     //--Changed--
		}
		else
		{
			sel2.selectByVisibleText(gen.getProperty("PromocodeMealTypeValue"));
		}
		
		
		
		SimpleDateFormat DF = new SimpleDateFormat("dd/MM/yyyy");
		Calendar RC = Calendar.getInstance();
		String start=DF.format(RC.getTime());
		int inputyear=Integer.parseInt(start.substring(6,10));
		int month=Integer.parseInt(start.substring(3,5));
		int daycal=Integer.parseInt(start.substring(0,2));
		
		WebElement ele=driver.findElement(By.xpath("//*[@id='wid-promoCodeContent']/div/div[2]/div[2]/div[3]/label"));
		ele.findElement(By.tagName("input")).click();
	    // driver.findElement(By.xpath(prop.getProperty("StartDate"))).click();
		//*[@id="ui-datepicker-div"]
		WebElement calStart=driver.findElement(By.xpath("//*[@id='ui-datepicker-div']"));
        WebElement startYear=calStart.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/div/select[2]"));
        Select startYear1=new Select(startYear);
        startYear1.selectByVisibleText(String.valueOf(inputyear));
        //startYear1.selectByVisibleText(prop.getProperty("ProStartYear"));
		
        WebElement startMonth=calStart.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/div/select[1]"));
        Select startMonth1=new Select(startMonth);
        startMonth1.selectByVisibleText(monthformate[month-1]);
        //startMonth1.selectByVisibleText(prop.getProperty("ProStartMonth"));
      
        int flag1=0;
		WebElement ele4=calStart.findElement(By.xpath("//*[@id='ui-datepicker-div']/table"));
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
				//if(prop.getProperty("ProStartDay").equalsIgnoreCase(day))
				if(String.valueOf(daycal).equalsIgnoreCase(day))
				{
					e1.click();
					flag1=1;
					break;
					
				}
			}
			}
		}
		
		//end date select 
		
		RC.add(Calendar.DATE, 200);
		String end=DF.format(RC.getTime());
		int calendyear=Integer.parseInt(end.substring(6,10));
		int calendmonth=Integer.parseInt(end.substring(3,5));
		int calendday=Integer.parseInt(end.substring(0,2));
		
		
		
		WebElement ele1=driver.findElement(By.xpath("//*[@id='wid-promoCodeContent']/div/div[2]/div[2]/div[4]/label"));
		ele1.findElement(By.tagName("input")).click();
		WebElement calend=driver.findElement(By.xpath("//*[@id='ui-datepicker-div']"));
        WebElement EndYear=calend.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/div/select[2]"));
        Select endYear1=new Select(EndYear);
        endYear1.selectByVisibleText(String.valueOf(calendyear));
        //endYear1.selectByVisibleText(prop.getProperty("ProEndYear"));
		
        WebElement endMonth=calStart.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/div/select[1]"));
        Select endMonth1=new Select(endMonth);
        endMonth1.selectByVisibleText(monthformate[calendmonth-1]);
        //endMonth1.selectByVisibleText(prop.getProperty("ProEndMonth"));
      
        int flag2=0;
		WebElement ele5=calStart.findElement(By.xpath("//*[@id='ui-datepicker-div']/table"));
		List<WebElement> tr1=ele5.findElements(By.tagName("tr"));
		for(WebElement e:tr1)
		{
			if(flag2==0)
			{
			List<WebElement> td=e.findElements(By.tagName("td"));
			for(WebElement e1:td)
			{
				String day=e1.getText();
				System.out.println(day);
				//if(prop.getProperty("ProEndDay").equalsIgnoreCase(day))
				if(String.valueOf(calendday).equalsIgnoreCase(day))
				{
					e1.click();
					flag2=1;
					break;
					
				}
			}
			}
		}
		
        driver.findElement(By.xpath(prop.getProperty("Promocode"))).sendKeys(gen.getProperty("PromocodeValue"));
		
		driver.findElement(By.xpath(prop.getProperty("PromocodeName"))).sendKeys(gen.getProperty("PromocodeNameValue"));
		
		driver.findElement(By.xpath(prop.getProperty("NoOfUses"))).sendKeys(gen.getProperty("NoOfUsesValue"));
		
		driver.findElement(By.xpath(prop.getProperty("NoOfpplFrom"))).sendKeys(gen.getProperty("NoOfpplFromValue"));
		
		driver.findElement(By.xpath(prop.getProperty("NoOfpplTo"))).sendKeys(gen.getProperty("NoOfpplToValue"));
		
		driver.findElement(By.xpath(prop.getProperty("MinNoOfRoom"))).sendKeys(gen.getProperty("MinNoOfRoomValue"));
		
		driver.findElement(By.xpath(prop.getProperty("PromocodeDiscount"))).sendKeys(gen.getProperty("PromocodeDiscountValue"));
		
		driver.findElement(By.xpath(prop.getProperty("PerSwitch"))).click();
		
		driver.findElement(By.xpath(prop.getProperty("DiscountedRatePlan"))).click();
		
		driver.findElement(By.xpath(prop.getProperty("SaveButton"))).click();
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[@id='Msg1']")));
		WebElement msgbox=driver.findElement(By.xpath("//*[@id='Msg1']"));
		msgbox.findElement(By.id("bot1-Msg1")).click();
		 Thread.sleep(10000);
		
		
}
}