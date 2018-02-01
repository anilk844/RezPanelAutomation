package SendMail;



	import java.io.File;
	import java.io.IOException;
	import java.nio.file.Files;
	import java.text.SimpleDateFormat;
	import java.util.Calendar;
	import java.util.Properties;

	import javax.activation.DataHandler;
	import javax.activation.DataSource;
	import javax.activation.FileDataSource;
	import javax.mail.BodyPart;
	import javax.mail.Message;
	import javax.mail.MessagingException;
	import javax.mail.Multipart;
	import javax.mail.PasswordAuthentication;
	import javax.mail.Session;
	import javax.mail.Transport;
	import javax.mail.internet.InternetAddress;
	import javax.mail.internet.MimeBodyPart;
	import javax.mail.internet.MimeMessage;
	import javax.mail.internet.MimeMultipart;

	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.firefox.FirefoxDriver;
	import org.testng.annotations.Test;

	public class SendMaximaDataDumpStatus {

		public  void copyfile() throws IOException
		{
		    
			SimpleDateFormat DF = new SimpleDateFormat("dd-MM-yyyy");
		    Calendar RC = Calendar.getInstance();
		    String start=DF.format(RC.getTime());
		    File source=new File("C:/Users/anil.kumar/git/RezPanelAutomation/PanelAnutomationSuit/test-output/Maxim/Maxim.html");
		    //C:/Users/anil.kumar/git/RezPanelAutomation/PanelAnutomationSuit/test-output/Maxima/Maxima.html
			//File source = new File("D:/WorkSpace/ibv4_code/MobileTest/test-output/AndroidMobileAutomation/Mobile Android Automation.html");
			File dest = new File("D:/Maxim Daily Report/PanelARIReport-"+start+".html");
			
			try
			{
			Files.copy(source.toPath(), dest.toPath());
			}
			catch(Exception e)
			{
				
			}
			    //org.apache.commons.io.FileUtils.copyDirectory(source, dest);
			
			}

		public  void  ibv4()
		{try
		{
			SimpleDateFormat DF = new SimpleDateFormat("dd-MM-yyyy");
		    Calendar RC = Calendar.getInstance();
		    String start=DF.format(RC.getTime());
		   Properties props = new Properties();
		   props.put("mail.smtp.host", "smtp.rediffmailpro.com");
		   props.put("mail.smtp.socketFactory.port", "587");
		   props.put("mail.smtp.socketFactory.class",
		   "javax.net.ssl.SSLSocketFactory");
		   props.put("mail.smtp.auth", "true");
		   props.put("mail.smtp.port", "587");
		   Session session = Session.getDefaultInstance(props,

		   new javax.mail.Authenticator() {
		   protected PasswordAuthentication getPasswordAuthentication() {
		   return new PasswordAuthentication("systemmonitoring@reznext.net","Reznext@123");
		}
		});
		   
		       // Create a default MimeMessage object.
		       Message message = new MimeMessage(session);

		       // Set From: header field of the header.
		       message.setFrom(new InternetAddress("systemmonitoring@reznext.net"));

		       // Set To: header field of the header.
		       message.setRecipients(Message.RecipientType.TO,
		          InternetAddress.parse("poornima.rao@reznext.com"));

		       // Set Subject: header field
		       message.setSubject("Maxim Occupancy Push Status");

		       // Create the message part
		       BodyPart messageBodyPart = new MimeBodyPart();

		       // Now set the actual message
		       messageBodyPart.setText("Hi Team,\n"
		       		+ "Please find Maxim Occupancy Push Status");

		       // Create a multipar message
		       Multipart multipart = new MimeMultipart();

		       // Set text message part
		       multipart.addBodyPart(messageBodyPart);

		       // Part two is attachment
		       messageBodyPart = new MimeBodyPart();
		       
		       String filename = "C:/Users/anil.kumar/git/RezPanelAutomation/PanelAnutomationSuit/test-output/Maxim/Maxim.html";
		       DataSource source = new FileDataSource(filename);
		       messageBodyPart.setDataHandler(new DataHandler(source));
		       messageBodyPart.setFileName(filename);
		       multipart.addBodyPart(messageBodyPart);

		       // Send the complete message parts
		       message.setContent(multipart);

		       // Send message
		       Transport.send(message);

		       System.out.println("Sent message successfully....");

		    } catch (MessagingException e) {
		      System.out.println(e);
		    }


		 }
		
		@Test
		public void execute() throws IOException

		{
			copyfile();
			ibv4();
		// finalization code here

		}
	}




