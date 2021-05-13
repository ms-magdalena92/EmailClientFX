package emailClient.service;

import emailClient.model.EmailAccount;
import emailClient.model.EmailFolder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EmailManager {

    private final ObservableList<EmailAccount> emailAccounts = FXCollections.observableArrayList();

    private final EmailFolder foldersRoot = new EmailFolder("");

    public void addEmailAccount(EmailAccount emailAccount) {
        emailAccounts.add(emailAccount);
        EmailFolder emailFolder = new EmailFolder(emailAccount.getEmailAddress());
        FetchFolderService fetchFolderService = new FetchFolderService(emailAccount.getStore(), emailFolder);
        fetchFolderService.start();
        foldersRoot.getChildren().add(emailFolder);
    }

    public ObservableList<EmailAccount> getEmailAccounts() {
        return emailAccounts;
    }

    public EmailFolder getFoldersRoot() {
        return foldersRoot;
    }
}
