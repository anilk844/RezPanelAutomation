package Enroll_hotel_test;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.testng.annotations.Test;
public class ibv4_1{
	  public static void sendEmailWithAttachments(String host, String port,
	            final String userName, final String password, String toAddress,
	            String subject, String message)
	            throws AddressException, MessagingException {
	        // sets SMTP server properties
	        Properties properties = new Properties();
	        properties.put("mail.smtp.host", host);
	        properties.put("mail.smtp.port", port);
	        properties.put("mail.smtp.auth", "true");
	        properties.put("mail.smtp.starttls.enable", "true");
	        properties.put("mail.user", userName);
	        properties.put("mail.password", password);
	 
	        // creates a new session with an authenticator
	        Authenticator auth = new Authenticator() {
	            public PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(userName, password);
	            }
	        };
	        Session session = Session.getInstance(properties, auth);
	 
	        // creates a new e-mail message
	        Message msg = new MimeMessage(session);
	 
	        msg.setFrom(new InternetAddress(userName));
	        InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
	        msg.setRecipients(Message.RecipientType.TO, toAddresses);
	        msg.setSubject(subject);
	        msg.setSentDate(new Date());
	 
	        // creates message part
	        MimeBodyPart messageBodyPart = new MimeBodyPart();
	        messageBodyPart.setContent(message, "text/html");
	 
	        // creates multi-part
	        Multipart multipart = new MimeMultipart();
	        multipart.addBodyPart(messageBodyPart);
	 
	        // adds attachments
	        /*if (attachFiles != null && attachFiles.length > 0) {
	            for (String filePath : attachFiles) {
	                MimeBodyPart attachPart = new MimeBodyPart();
	 
	                try {
	                    attachPart.attachFile(filePath);
	                } catch (IOException ex) {
	                    ex.printStackTrace();
	                }
	 
	                multipart.addBodyPart(attachPart);
	            }
	        }*/
	 
	        // sets the multi-part as e-mail's content
	        msg.setContent(multipart);
	 
	        // sends the e-mail
	        Transport.send(msg);
	 
	    }
	 
	    /**
	     * Test sending e-mail with attachments
	     */

	    public static void mailSend(String RatCode){
	        // SMTP info
	        String host = "smtp.gmail.com";
	        String port = "587";
	        String mailFrom = "anilk844@gmail.com";
	        String password = "nsyksywwzuvpyhfm";
	 
	       String[] username = new String[4];
	        username[0]="anil23089@gmail.com";
	        
	        username[1]="poornima.rao@reznext.com";
	        username[2]="sahana.siddaraju@reznext.com";
	        // message info
	       // String mailTo = "anil23089@gmail.com";
	        String subject = "RateCode Creation Status";
	        String message = "IRateCode Which are Below ID---"+RatCode+" are Created.Restart Script to create rate code from ID---->"+RatCode;
	 
	        // attachments
	        //String[] attachFiles = new String[2];
	        //attachFiles[0] = "D:/enrollStatus.xlsx";
	        //attachFiles[1] = "D:/rateStatus.xlsx";
	        //attachFiles[2] = "e:/Test/Video.mp4";*/
	 
	        try {
	        	for(int i = 0;i<username.length;i++)
	        	{
	        		String mailTo=username[i];
	            sendEmailWithAttachments(host, port, mailFrom, password, mailTo,
	                subject, message);
	        	}
	            System.out.println("Email sent.");
	        } catch (Exception ex) {
	            System.out.println("Could not send email.");
	            ex.printStackTrace();
	        }
	    }
}
