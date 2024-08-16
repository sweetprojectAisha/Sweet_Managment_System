package Admin_helperClass;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.*;
import java.util.Properties;

public class EmailSessionManager {Session

    private Session session;

    public EmailSessionManager(String username, String password) {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        try {
            session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
}
