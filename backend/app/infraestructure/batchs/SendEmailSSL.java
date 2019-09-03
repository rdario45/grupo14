package infraestructure.batchs;

import java.util.*;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;

public class SendEmailSSL {

    Session session;


    public void connect(String from,String password){
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        session = Session.getInstance(prop,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(from, password);
                    }
                });
    }



    public String  send(String to,String sub,String msg){
        try {
            MimeMessage message = new MimeMessage(session);
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            message.setSubject(sub);
            message.setText(msg);
            //send message
            Transport.send(message);
            return "Message sent successfully";
        } catch (MessagingException e) {return "Error al enviar el mensaje";}
    }

 
  public static void main(String[] args) {
     //from,password,to,subject,message
      SendEmailSSL send = new SendEmailSSL();
      send.connect("designMatch14@gmail.com","rvfkzapfyonjrvbz");
      send.send("da.torres58@uniandes.edu.co","test nuevo","Cuerpo");

     //change from, password and to
 }
}