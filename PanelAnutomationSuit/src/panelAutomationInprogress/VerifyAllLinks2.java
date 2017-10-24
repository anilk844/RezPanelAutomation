package panelAutomationInprogress;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class VerifyAllLinks2 {
	
	public static WebDriver driver;
	public static int invalidLinksCount;
    public static  List<String> lst;
    public static int invalidlink=0;
    public static int validlink=0;
    static int count=0;
	public static void main(String args[]) throws InterruptedException, IOException {

	
			System.setProperty("webdriver.chrome.driver", "D://chromedriver.exe");
			driver=new ChromeDriver();
			driver.manage().window().maximize();
			driver.get("https://red.reznext.com/login.html");
			Thread.sleep(6000);
			driver.findElement(By.xpath(".//*[@id='login-form']/fieldset/section[1]/label[2]/input")).sendKeys("superuser@reznext.com");
			driver.findElement(By.xpath(".//*[@id='password']")).sendKeys("Tiger@321");
			driver.findElement(By.xpath(".//*[@id='login-form']/footer/div/button")).click();
			Thread.sleep(20000);	    
	 
			invalidLinksCount = 0;
			
			List<WebElement> no = driver.findElements(By.tagName("a"));
			int nooflinks = no.size();
			System.out.println("Total hyperlinks:"+ nooflinks);
			
			for (WebElement pagelink : no)
			{ 	String linktext = pagelink.getText();
			String link = pagelink.getAttribute("href");
				try
				{
				
		
		
			URL u = new URL(link);
			HttpURLConnection h = (HttpURLConnection) u.openConnection();
			h.setRequestMethod("GET");
			h.connect();
			int responsecode = h.getResponseCode();
			if(responsecode!=200)
			{
			System.out.println(linktext+"t" + link + "t" + "Response Code: " + responsecode);
			invalidlink=invalidlink+1;
			}
			{
				System.out.println(linktext+"t" + link + "t" + "Response Code: " + responsecode);
				validlink=validlink+1;
			}
				}
				catch(Exception e)
				{
					count=count+1;
					System.out.println(count+""+link);
				}
			}
			System.out.println("invalid links "+invalidlink);
			System.out.println("valid links"+validlink);
			System.out.println("— End of Test —");

			driver.close();
			driver.quit();
			}
		
}



