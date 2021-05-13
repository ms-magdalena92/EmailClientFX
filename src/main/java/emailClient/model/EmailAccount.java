package emailClient.model;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.Store;
import java.security.GeneralSecurityException;
import java.util.Properties;

public class EmailAccount {

    private final String emailAddress;
    private final String password;
    private Properties properties;
    private Store store;

    public EmailAccount(String emailAddress, String password) {
        this.emailAddress = emailAddress;
        this.password = password;

        try {
            setGmailProperties();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public Properties getProperties() {
        return properties;
    }

    public Store getStore() {
        return store;
    }

    public void setGmailProperties() throws GeneralSecurityException {
        properties = new Properties();
        properties.put("incomingHost", "imap.gmail.com");
        properties.put("mail.store.protocol", "imaps");

        properties.put("mail.transport.protocol", "smtps");
        properties.put("mail.smtps.host", "smtp.gmail.com");
        properties.put("mail.smtps.auth", "true");
        properties.put("outgoingHost", "smtp.gmail.com");

        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        properties.put("mail.imaps.ssl.trust", "*");
        properties.put("mail.imaps.ssl.socketFactory", sf);
    }
}
