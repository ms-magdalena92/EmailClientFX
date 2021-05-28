package emailClient.service;

import emailClient.model.EmailFolder;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Store;
import javax.mail.event.MessageCountEvent;
import javax.mail.event.MessageCountListener;
import java.util.List;

public class FetchFolderService extends Service<Void> {

    private final Store store;

    private final EmailFolder foldersRoot;

    private final List<Folder> folderList;

    private final IconsHandler iconsHandler;

    public FetchFolderService(Store store, EmailFolder foldersRoot, List<Folder> folderList) {
        this.store = store;
        this.foldersRoot = foldersRoot;
        this.folderList = folderList;
        this.iconsHandler = new IconsHandler();
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
        handleFolders(folders, foldersRoot);
    }

    private void handleFolders(Folder[] folders, EmailFolder foldersRoot) throws MessagingException {
        for (Folder folder : folders) {
            folderList.add(folder);
            EmailFolder emailFolder = new EmailFolder(folder.getName());
            emailFolder.setGraphic(iconsHandler.getIconForFolder(folder.getName()));
            foldersRoot.getChildren().add(emailFolder);
            foldersRoot.setExpanded(true);

            fetchFoldersMessages(folder, emailFolder);
            addMessageListenerToFolder(folder, emailFolder);

            if (folder.getType() == Folder.HOLDS_FOLDERS) {
                Folder[] subFolders = folder.list();
                handleFolders(subFolders, emailFolder);
            }
        }
    }

    private void addMessageListenerToFolder(Folder folder, EmailFolder emailFolder) {
        folder.addMessageCountListener(new MessageCountListener() {
            @Override
            public void messagesAdded(MessageCountEvent e) {
                for (int i = 0; i < e.getMessages().length; i++) {
                    try {
                        Message message = folder.getMessage(folder.getMessageCount() - i);
                        emailFolder.addMessageToTheTop(message);
                    } catch (MessagingException messagingException) {
                        messagingException.printStackTrace();
                    }
                }
            }

            @Override
            public void messagesRemoved(MessageCountEvent e) {
            }
        });
    }

    private void fetchFoldersMessages(Folder folder, EmailFolder emailFolder) {
        FetchMessageService fetchMessageService = new FetchMessageService(folder, emailFolder);
        fetchMessageService.start();
    }
}
