package emailClient.service;

import emailClient.model.EmailFolder;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

import javax.mail.Folder;
import javax.mail.MessagingException;

public class FetchMessageService extends Service<Void> {

    private final Folder folder;

    private final EmailFolder emailFolder;

    public FetchMessageService(Folder folder, EmailFolder emailFolder) {
        this.folder = folder;
        this.emailFolder = emailFolder;
    }

    @Override
    protected Task<Void> createTask() {
        return new Task<>() {
            @Override
            protected Void call() throws Exception {
                fetchMessagesFromSingeFolder();
                return null;
            }
        };
    }

    private void fetchMessagesFromSingeFolder() throws MessagingException {
        if (folder.getType() != Folder.HOLDS_FOLDERS) {
            folder.open(Folder.READ_WRITE);
            for (int i = folder.getMessageCount(); i > 0; i--) {
                emailFolder.addMessage(folder.getMessage(i));
            }
        }
    }
}
