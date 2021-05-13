package emailClient.service;

import emailClient.model.EmailFolder;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

import javax.mail.Folder;
import javax.mail.MessagingException;
import javax.mail.Store;

public class FetchFolderService extends Service<Void> {

    private final Store store;

    private final EmailFolder foldersRoot;

    public FetchFolderService(Store store, EmailFolder foldersRoot) {
        this.store = store;
        this.foldersRoot = foldersRoot;
    }

    @Override
    protected Task<Void> createTask() {
        return new Task<>() {
            @Override
            protected Void call() throws Exception {
                fetchFolders();
                return null;
            }
        };
    }

    private void fetchFolders() throws MessagingException {
        Folder[] folders = store.getDefaultFolder().list();
        handleFolders(folders);
    }

    private void handleFolders(Folder[] folders) {
        for (Folder folder : folders) {
            EmailFolder emailTreeItem = new EmailFolder(folder.getName());
            foldersRoot.getChildren().add((emailTreeItem));
        }
    }
}
