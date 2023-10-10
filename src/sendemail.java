
import java.io.Console;
import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class sendemail 
{

    String host = "smtp.gmail.com";
    String port = "587";
    String mailFrom = "tania.vmmeducation@gmail.com";
    String password = "App Password";
    String subject, message, emailAddress;

    public sendemail(String emailAddress, String subject, String message) 
    {
        this.subject = subject;
        this.message = message;
        this.emailAddress = emailAddress;
        sendemail();
    }
    public void sendemail() 
    {
        try 
        {
            Properties properties = new Properties();
            properties.put("mail.smtp.host", host);
            properties.put("mail.smtp.port", port);
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");

            // creates a new session with an authenticator
            Authenticator auth = new Authenticator() 
            {
                public PasswordAuthentication getPasswordAuthentication() 
                {
                    return new PasswordAuthentication(mailFrom, password);
                }
            };

            Session session = Session.getInstance(properties, auth);

            // creates a new e-mail message
            Message msg = new MimeMessage(session);

            msg.setFrom(new InternetAddress(mailFrom));

            InternetAddress[] toAddresses = {new InternetAddress(emailAddress)};
            msg.setRecipients(Message.RecipientType.TO, toAddresses);
            msg.setSubject(subject);
            msg.setSentDate(new Date());
            // set plain text message
//            msg.setText(message);
            msg.setContent(message,
             "text/html");

            // sends the e-mail
            Transport.send(msg);
            
            System.out.println("email sent to " + emailAddress);

        } 
        catch (Exception ex) 
        {
            ex.printStackTrace();
            
            
        }
    }
//    public static void main(String[] args) 
//    {
//      String otp =  (int) (1000+(9999-1000)*Math.random())+"";
//            sendemail obj = new sendemail("nirbhaysharma256@gmail.com","hello","Your Otp is: "+otp);
//    }
}
