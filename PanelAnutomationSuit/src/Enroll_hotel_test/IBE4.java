package Enroll_hotel_test;



import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IBE4
{
public static WebDriver driver;
static boolean flag=true;
public static WebDriverWait wait;
public static void main(String args[]) throws InterruptedException
{

    DesiredCapabilities capabilities =DesiredCapabilities.chrome();
    capabilities.setJavascriptEnabled(true);
    System.setProperty("webdriver.chrome.driver", "D://chromedriver.exe");
    driver=new ChromeDriver();
	//driver.get("https://redapptest.azurewebsites.net/login.html");
    driver.get("https://rezev4qa.azurewebsites.net/hotel-oct");
    driver.manage().window().maximize();
    String Startdate="27-07-2016";
    driver.findElement(By.xpath("//*[@id='checkin']")).sendKeys(Startdate);
    String Enddate="28-07-2016";
    driver.findElement(By.xpath("//*[@id='checkout']")).sendKeys(Enddate);
    driver.findElement(By.xpath("//*[@id='guestdetailsbtn']")).click();
    //adult count selection
    wait=new WebDriverWait(driver, 100);
    int adult=3;
    for(int i=adult;i>1;i--)
    {
    	driver.findElement(By.xpath("//*[@id='GuestDetails']/div/div[1]/div[1]/div[2]/span[2]/i")).click();
    }
   //child count selection
    int child=2;
    for(int i=child;i>0;i--)
    {
    	driver.findElement(By.xpath("//*[@id='GuestDetails']/div/div[1]/div[2]/div[2]/span[2]/i")).click();
    }
    //Infant count selection
    int infant=2;
    for(int i=infant;i>0;i--)
    {
    	driver.findElement(By.xpath("//*[@id='GuestDetails']/div/div[1]/div[3]/div[2]/span[2]/i")).click();
    }
    //click on done buttn
    driver.findElement(By.xpath("//*[@id='GuestDetails']/div/div[2]/div/button")).click();
    //search button
    driver.findElement(By.xpath("//*[@id='form1']/div[4]/button")).click();
    String[]roomMeal={"Standard Deluxe","Only Room","room","Deluxe","European Plan","room","General package","Only Room","General Package" };
   
    int roomno=2;
    int count=2;
    for(int i=0;i<roomMeal.length;i=i+3)
    {
    String roomtype=roomMeal[i];
    String meal=roomMeal[i+1];
    String room=roomMeal[i+2];
    if(room.equalsIgnoreCase("room"))
    {
    	Thread.sleep(15000);
    bookroom(roomtype,meal);
    }
    if(room.equalsIgnoreCase("package"))
    {
    	Thread.sleep(15000);
    	 packageBook(room,roomtype,meal);
    }
    flag=true;
    Thread.sleep(3000);
    if(count-1>0)
    {
    	
    	JavascriptExecutor js = ((JavascriptExecutor) driver);

		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    	driver.findElement(By.xpath("//*[@id='Scroll']/div[1]/div/div[3]/div[2]/div[2]/div/a")).click();
    	count--;
    }
  }
    guestDetails();
    String totalAmt=driver.findElement(By.xpath("//*[@id='sidebar']/div[5]/div/table/thead/tr/td[2]/label")).getText();
    System.out.println(totalAmt);
    driver.findElement(By.xpath("//*[@id='User_TermsAndConditions']")).click();
    Thread.sleep(2000);
    driver.findElement(By.xpath("//*[@id='PaymentSubmit']/div[2]/div[2]/div/button")).click();
    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='amount1']")));
    String amt=driver.findElement(By.xpath("//*[@id='amount1']")).getText();
    if(amt.equalsIgnoreCase(totalAmt))
    {
    	System.out.println("pass");
    }
    else{
    	System.out.println("amount mismatch");
    }
    
   
    
}
    
   public static void bookroom(String roomtype1,String meal1) throws InterruptedException
   {
    	//driver.findElement(By.xpath("//*[@id='ScrollRoom']/div[1]/div/div[1]/div[1]/div[1]/div/a")).click();

   	JavascriptExecutor js = ((JavascriptExecutor) driver);

		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    	List<WebElement> roomtype=driver.findElements(By.cssSelector("*[class^='col-sm-12 pad-zero rate-inventory rooms-info']"));
    	Boolean flag=true;
    	for(WebElement web:roomtype)
    	{
    		if(flag)
    		{
    		WebElement roomName=web.findElement(By.cssSelector("*[class^='label-Blue col-sm-8 pad-zero']"));
    		String roomname1=roomName.getText();
    		System.out.println("1");
    		System.out.println(roomname1);
    		if(roomname1.equalsIgnoreCase(roomtype1))
    		{
    			System.out.println("2");
    			Thread.sleep(5000);
    			List<WebElement>mealPlan=web.findElements(By.cssSelector("*[class^='col-xs-12 pad-zero margin-align mealplan-rate-info']"));
    			System.out.println("meal Plan"+mealPlan.size());
    			for(WebElement meal:mealPlan)
    			{
        			System.out.println("3");
    				String mealPlan1=meal.findElement(By.cssSelector("*[class^='day-roomlabel']")).getText();
    				if(mealPlan1.equalsIgnoreCase(meal1))
    				{
    					System.out.println("4");
    					meal.findElement(By.tagName("button")).click();
    					Thread.sleep(3000);
    					try
    					{
    						System.out.println("5");
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
    	System.out.println(roomtype.size());
    }
   
   
   public static void packageBook(String PackageType,String PackageName,String MealType)
   {
	 //*[@id="ScrollRoom"]/div[1]/div/div[1]/div[1]/div[2]/a
	   driver.findElement(By.xpath("//*[@id='ScrollRoom']/div[1]/div/div[1]/div[1]/div[2]/a")).click();
	   List<WebElement>packcat=driver.findElements(By.cssSelector("*[class^='col-sm-4']"));
	   for(WebElement e:packcat)
	   {
		   String packName=e.findElement(By.tagName("p")).getText();
		   if(packName.equalsIgnoreCase(PackageType))
		   {
			   
		   }
		   
	   }
	   
   }
   
   public  static void guestDetails() throws InterruptedException
   {
	   Thread.sleep(6000);
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


}
