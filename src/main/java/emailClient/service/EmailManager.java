package emailClient.service;

import emailClient.model.EmailAccount;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EmailManager {

    private final ObservableList<EmailAccount> emailAccounts = FXCollections.observableArrayList();

    public void addEmailAccount(EmailAccount emailAccount) {
        emailAccounts.add(emailAccount);
    }

    public ObservableList<EmailAccount> getEmailAccounts() {
        return emailAccounts;
    }
}
