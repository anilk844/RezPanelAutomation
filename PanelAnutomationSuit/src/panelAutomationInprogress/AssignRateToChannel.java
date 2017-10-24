package panelAutomationInprogress;

import java.awt.AWTException;
import java.awt.List;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.testng.Assert;

public class AssignRateToChannel {
public static WebDriver driver;
public static int flag=0;
public static void main(String args[]) throws InterruptedException, FindFailed, AWTException, IOException
{
	//String [] Rooms={"Kings Room","Superior","VILLA"};
	//String [] Meal={"Only Room","Only Wifi","Only Lunch","Bed and Breakfast"};
	String [] Rooms={"Suite","Green ST","Pasific ST"};
    String [] Meal={"Continental Plan","European Plan"};
	FileInputStream file= new FileInputStream("D://RateShopper//RateCode.xlsx");
	
	
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
		
	System.setProperty("webdriver.chrome.driver", "D://chromedriver.exe");
	ChromeOptions options = new ChromeOptions();
	options.addArguments("--disable-extensions");
	driver=new ChromeDriver(options);
	driver.manage().window().maximize();
	driver.get("https://redapptest.azurewebsites.net/");
	Thread.sleep(20000);
	driver.findElement(By.xpath(".//*[@id='login-form']/fieldset/section[1]/label[2]/input")).sendKeys("superuser@reznext.com");
	driver.findElement(By.xpath(".//*[@id='password']")).sendKeys("Test@321");
	driver.findElement(By.xpath(".//*[@id='login-form']/footer/div/button")).click();
	Thread.sleep(20000);
	driver.findElement(By.xpath("//*[@id='search-fld']")).sendKeys("2058");
	Screen scn= new Screen();
	String img="D://sikili//Ban.PNG";
	Pattern ptr= new Pattern(img);
	Thread.sleep(4000);
	scn.click(ptr);
	driver.get("https://redapptest.azurewebsites.net/index.html#/createrate/ratecreation");
	
	
	//click on assign rate to channel tab
	Thread.sleep(5000);
	//*[@id="ratechannelstab"]/a
	driver.findElement(By.xpath("//*[@id='ratechannelstab']/a")).click();
	Thread.sleep(4000);
	for(int j=0;j<data.size();j++)
	{
	
	driver.findElement(By.xpath("//*[@id='RateAssignTab']/header/div/button")).click();
	Thread.sleep(1000);
	for(int r=0;r<Rooms.length;r++)
	{
		for(int m=0;m<Meal.length;m++)
		{
	try
	{
		flag=0;
		System.out.println("room-"+Rooms[r]);
		System.out.println("Meal-"+Meal[m]);
		WebElement ratecode=driver.findElement(By.xpath("//*[@id='bootstrap-wizard-1']/div[1]/div/fieldset/div[1]/div[1]/div/select"));
	    Select selectratcode=new Select(ratecode);
	    selectratcode.selectByVisibleText(data.get(j).toString());
	    WebElement leftelement=driver.findElement(By.xpath("//*[@id='leftValues']"));
	    ((JavascriptExecutor) driver).executeScript(
	            "arguments[0].scrollIntoView();", leftelement);
	    WebDriverWait wait=new WebDriverWait(driver,20);
	    Thread.sleep(2000);
	    WebElement roomtype=driver.findElement(By.xpath("//*[@id='bootstrap-wizard-1']/div[1]/div/fieldset/div[2]/div[1]/div/select"));
	    Select selectroomtype=new Select(roomtype);
	    selectroomtype.selectByVisibleText(Rooms[r]);
	    Thread.sleep(3000);
	    //wait.until(ExpectedConditions.elementToBeSelected(By.xpath("//*[@id='bootstrap-wizard-1']/div[1]/div/fieldset/div[2]/div[2]/div/select")));
	    WebElement mealplan=driver.findElement(By.xpath("//*[@id='bootstrap-wizard-1']/div[1]/div/fieldset/div[2]/div[2]/div/select"));
	    Select selectmealplan=new Select(mealplan);
	    selectmealplan.selectByVisibleText(Meal[m]);
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
	    		if(optname.equals("CRS"))
	    		{
	    			
	    			action.moveToElement(opt).click(opt).build().perform();
	    			Thread.sleep(3000);
	    			driver.findElement(By.xpath("//*[@id='btnRight']")).click();
	    		
	    			break;
	    		}
	    	}
	    }
	    Thread.sleep(3000);
	    WebElement ele =driver.findElement(By.xpath("//*[@id='bootstrap-wizard-1']/div[1]/div/fieldset/div[5]/div[1]/div[2]/div/select"));
	    String inventory="Real Inventory";
	    Select inv=new Select(ele);
	    	inv.selectByVisibleText(inventory);
	    	Thread.sleep(2000);
	    	if(inventory.equalsIgnoreCase("Allocation Inventory"))
	    	{
	    		
	    	    WebElement allocation=driver.findElement(By.xpath("//*[@id='bootstrap-wizard-1']/div[1]/div/fieldset/div[5]/div[2]/div/div/div[2]/div/input"));
	            allocation.sendKeys("10");
	    	
	    	}
	        Thread.sleep(2000);
	    	JavascriptExecutor js = ((JavascriptExecutor) driver);
            js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	        driver.findElement(By.xpath("//*[@id='bootstrap-wizard-1']/div[3]/div/div/a[2]")).click();
	        Thread.sleep(2000);
	        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[@id='Msg1']")));
	        WebElement msgbox=driver.findElement(By.xpath("//*[@id='Msg1']"));
	        WebElement msg=msgbox.findElement(By.tagName("p"));
	        Assert.assertEquals("Confirm your save will modify the rates if the rates have been alloted earlier or New rates will alloted for the Channel", msg.getText());
	        msgbox.findElement(By.xpath("//*[@id='bot2-Msg1']")).click();
	        Thread.sleep(3000);
	        wait=new WebDriverWait(driver,1000);
	        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("bot1-Msg1")));
	        WebElement SuccessMsgBox=driver.findElement(By.xpath("//*[@id='Msg1']"));
	        SuccessMsgBox.findElement(By.id("bot1-Msg1")).click();
	        flag=1;
	       
	    
    if(flag==1)
    {
    	Thread.sleep(6000);
    }
    driver.findElement(By.xpath("//*[@id='RateAssignTab']/header/div/button")).click();
	}
	catch(Exception e)
	{
		
	}
    }
		
	}
}
}
}