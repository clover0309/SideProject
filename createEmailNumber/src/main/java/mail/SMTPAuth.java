package mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class SMTPAuth extends Authenticator {
    private PasswordAuthentication auth;

    public SMTPAuth() {
        String username = "여기에 gmail주소 입력.";
        String password = "여기에 SMTP KEY 입력.";
        auth = new PasswordAuthentication(username, password);
    }

    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return auth;
    }
}
