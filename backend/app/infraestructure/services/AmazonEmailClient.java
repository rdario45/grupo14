package infraestructure.services;

import com.typesafe.config.Config;
import io.vavr.control.Try;
import play.Logger;

import javax.inject.Inject;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class AmazonEmailClient {

    private final Config config;
    private final boolean enable;
    private final String from;
    private final String fromName;
    private final String smtpUsername;
    private final String smtpPassword;
    private final String host;
    private final int port;
    private final String SUBJECT = "Amazon SES test (SMTP interface accessed using Java)";
    private final String BODY = String.join(
      System.getProperty("line.separator"),
      "<h1>Amazon SES SMTP Email Test</h1>",
      "<p>This email was sent with Amazon SES using the ",
      "<a href='https://github.com/javaee/javamail'>Javamail Package</a>",
      " for <a href='https://www.java.com'>Java</a>. " +
        "{{url}}"
    );
    private final Properties props;

    @Inject
    public AmazonEmailClient(Config config) {
        this.config = config;
        this.enable = config.getBoolean("email.default.enable");
        this.from = config.getString("email.default.sender.email");
        this.fromName = config.getString("email.default.sender.name");
        this.smtpUsername = config.getString("email.default.smtp.username");
        this.smtpPassword = config.getString("email.default.smtp.password");
        this.host = config.getString("email.default.host");
        this.port = config.getInt("email.default.port");
        this.props = getProperties();
    }

    public Try<Boolean> send(String to, String url) {
        Session session = Session.getDefaultInstance(this.props);
        Try<MimeMessage> mimeMessage = createMessage(to, session);
        Try<Transport> transport = createTransport(session);

        return mimeMessage.flatMap(msg ->
            transport.flatMap(trans ->
              sendMessage(msg, trans)
            )
        );
    }

    private Try<Transport> createTransport(Session session) {
        return Try.of(session::getTransport);
    }

    private Try<Boolean> sendMessage(MimeMessage msg, Transport transport) {
        return Try.of(() -> {
            Logger.info("Sending...");
            transport.connect(this.host, this.smtpUsername, this.smtpPassword);
            transport.sendMessage(msg, msg.getAllRecipients());
            Logger.info("Email sent!");
            return true;
        }).onFailure(throwable -> Logger.error("", throwable))
          .recover(throwable -> false)
          .andFinally(() -> {
              try { transport.close(); } catch(Exception e){}
          });
    }

    private Try<MimeMessage> createMessage(String to, Session session) {
        return Try.of(() -> {
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(this.from, this.fromName));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            msg.setSubject(SUBJECT);
            msg.setContent(BODY,"text/html");
            return msg;
        });
    }

    private Properties getProperties() {
        Properties props = System.getProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.port", this.port);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        return props;
    }
}