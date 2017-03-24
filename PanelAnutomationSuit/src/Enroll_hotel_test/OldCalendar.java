package Enroll_hotel_test;


import java.awt.AWTException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.testng.annotations.Test;

import com.thoughtworks.selenium.webdriven.commands.Click;

public class OldCalendar {
	public static Properties prop;
	public static WebDriver driver;
	public static WebDriverWait wait;
	public static Properties gen;
	public static Properties stat;
	public static void main(String args[]) throws InterruptedException, FindFailed, AWTException, IOException
	{
		
		FileInputStream status = new FileInputStream("D:/WorkSpace/ibv4_code/Enroll_Hotel_Test/src/LiveRepository/SwitchQAandLIVE.properties");
		stat=new Properties();
		stat.load(status);
		if(stat.getProperty("Status").equalsIgnoreCase("QA"))
		{
			FileInputStream pageObjectGen = new FileInputStream("D:/WorkSpace/ibv4_code/Enroll_Hotel_Test/src/Repository/Generic.properties");
			gen=new Properties();
			gen.load(pageObjectGen);
		}
		else if(stat.getProperty("status").equalsIgnoreCase("LIVE"))
		{
			FileInputStream pageObjectGen = new FileInputStream("D:/WorkSpace/ibv4_code/Enroll_Hotel_Test/src/LiveRepository/Generic.properties");
			gen=new Properties();
			gen.load(pageObjectGen);
		}
		FileInputStream pageObject = new FileInputStream("D:/WorkSpace/ibv4_code/Enroll_Hotel_Test/src/Repository/OldCalendar.properties");
		prop = new Properties();
	    prop.load(pageObject);
		System.setProperty("webdriver.chrome.driver", "D://chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-extensions");
		driver=new ChromeDriver();
	    //driver=new FirefoxDriver();
		wait=new WebDriverWait(driver,40);
		
		//driver.manage().window().maximize();
		driver.get(gen.getProperty("Url"));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(gen.getProperty("username"))));
		driver.findElement(By.xpath(gen.getProperty("username"))).sendKeys(gen.getProperty("UserNameValue"));
		driver.findElement(By.xpath(gen.getProperty("password"))).sendKeys(gen.getProperty("PasswordValue"));
		driver.findElement(By.xpath(gen.getProperty("Button"))).click();
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(gen.getProperty("searchField"))));
		driver.findElement(By.xpath(gen.getProperty("searchField"))).sendKeys(gen.getProperty("hotelcode"));
		Screen scn= new Screen();
		String img="D://sikili//Ban.PNG";
		Pattern ptr= new Pattern(img);
		Thread.sleep(4000);
		scn.click(ptr);
		
		driver.get(gen.getProperty("OldCalendarUrl"));
	     Thread.sleep(4000);
		//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/*[@id='Calendar']/div/div/div/form/fieldset/div[1]/div[1]/select")));
		//Select Rate Code 
		WebElement ratecodeselect=driver.findElement(By.xpath("//*[@id='Calendar']/div/div/div/form/fieldset/div[1]/div[1]/select"));
		Select SelRateCode= new Select(ratecodeselect);
		SelRateCode.selectByVisibleText(gen.getProperty("RateCodeValue"));
		
		//Select Room Type
		WebElement roomtypeselect=driver.findElement(By.xpath("//*[@id='roomtyp']"));
		Select SelectRoomType=new Select(roomtypeselect);
		SelectRoomType.selectByVisibleText(gen.getProperty("RoomTypeValue"));
		
		//select Meal Plan 
		WebElement mealplanselect=driver.findElement(By.xpath("//*[@id='Calendar']/div/div/div/form/fieldset/div[1]/div[4]/select"));
		Select SelectMealPlan=new Select(mealplanselect);
		SelectMealPlan.selectByVisibleText(gen.getProperty("MealPlanGen"));
		
		//select channel
		WebElement channelselect=driver.findElement(By.xpath("//*[@id='Calendar']/div/div/div/form/fieldset/div[1]/div[5]/select"));
		Select SelectChannel=new Select(channelselect);
		SelectChannel.selectByVisibleText(gen.getProperty("channelTypeValue"));
		singleRate();
		//select inventory/rate
		
		
		
	}
	public static void singleRate() throws InterruptedException
	{
		//Click Go Button
		System.out.println("inside Single RAte");
		WebElement InvRateSelect =driver.findElement(By.xpath("//*[@id='Calendar']/div/div/div/rate-calendar/div[1]/div[2]/div/div[1]/select"));
		Select SelectRateInv=new Select(InvRateSelect);
		SelectRateInv.selectByVisibleText("Rate");
	    Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='Calendar']/div/div/div/form/fieldset/div[1]/div[6]/a")).click();
		System.out.println("1");
		//driver.findElement(By.cssSelector("*[class^='fa  fa-plus cursor-pointer ng-scope']"))
		WebElement cal=driver.findElement(By.id("ratecalendar"));
		
		List<WebElement>AllDay=cal.findElements(By.cssSelector("*[class^='glyphicon glyphicon-ban-circle ban-size animated flash ng-hide']"));
		System.out.println(AllDay.size());
		List<WebElement>AllDays=cal.findElements(By.cssSelector("*[class^='col-md-1 border  cal-firstcell ng-isolate-scope caldate']"));
		System.out.println(AllDays.size());
		/*JavascriptExecutor js = ((JavascriptExecutor) driver);

        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");*/
		for(WebElement e:AllDays)
		{
			System.out.println(2);
		//	wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("*[class^='rateappldate ng-binding']")));
			try
			{
				System.out.println(e.getText());
			/*WebElement dateNo=e.findElement(By.cssSelector("*[class^='rateappldate ng-binding']"));
			String dateValue=dateNo.getText();
			System.out.println(dateValue);
			if(dateValue.equals("20"))
			{
				System.out.println("click");
				e.click();
				
			}*/
			}
			catch(Exception exe)
			{
				System.out.println(exe);
			}
			
		}
	}
	
}