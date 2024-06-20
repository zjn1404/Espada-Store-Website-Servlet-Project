package util;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Properties;

import javax.mail.PasswordAuthentication;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {

    private final static String EMAIL = "zjn0907@gmail.com";
    private final static String PASSWORD = "ylch xzfy wqup pthp";
    private static Authenticator auth;
    private static Properties props;
    private static Session session;
    private static Email instance;

    private Email() {
        session = createSession();
    }

    public static Email getInstance() {

        if (instance == null) {
            instance = new Email();
        }

        return instance;
    }

    public boolean sendMail(String to, String subject, String content) {

        try {
            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(EMAIL));

            message.setRecipients(
                Message.RecipientType.TO, InternetAddress.parse(to, false));

            message.addHeader("content-type", "text/html; charset=utf-8");

            message.setSubject(subject);

            message.setSentDate(Date.valueOf(LocalDate.now()));

//          message.setReplyTo(InternetAddress.parse(EMAIL, false));

            message.setContent(content, "text/html; charset=utf-8");

            Transport.send(message);

            return true;
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return false;
    }

    private Session createSession() {
        auth = signIn();

        props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.smtp.ssl.protocols", "TLSv1.2"); // Specify TLS version

        return Session.getInstance(props, auth);
    }

    private Authenticator signIn() {
        return new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EMAIL, PASSWORD);
            }
        };
    }
}
