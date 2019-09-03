package infraestructure.batchs;


import java.util.*;


import javax.mail.*;
import javax.mail.internet.*;


public class SendEmailSSL {

     private Session session;
     public void send(String to,String sub,String msg){
              //Get properties object

              try {
               MimeMessage message = new MimeMessage(session);
               message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
               message.setSubject(sub);
               message.setText(msg);
               //send message
               Transport.send(message);
               System.out.println("message sent successfully");
              } catch (MessagingException e) {throw new RuntimeException(e);}

        }

     public void connect(String from, String password){
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.socketFactory.port", "587");
            props.put("mail.smtp.starttls.enable", "false");
            props.put("mail.smtp.socketFactory.class",
                    "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "587");
            //get Session
            session = Session.getDefaultInstance(props,
                    new javax.mail.Authenticator() {
                        protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                            return new javax.mail.PasswordAuthentication(from,password);
                        }
                    });
            //compose message
     }
}