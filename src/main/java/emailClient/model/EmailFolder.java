package emailClient.model;

import emailClient.factory.EmailMessageFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;

import javax.mail.*;

public class EmailFolder extends TreeItem<String> {

    private String name;

    private ObservableList<EmailMessage> messages;

    private int unreadMessagesCount = 0;

    public EmailFolder(String name) {
        super(name);
        this.name = name;
        this.messages = FXCollections.observableArrayList();
    }

    public ObservableList<EmailMessage> getMessages() {
        return messages;
    }

    public void addMessage(Message message) throws MessagingException {
        messages.add(transformToEmailMessage(message));
    }

    private EmailMessage transformToEmailMessage(Message message) throws MessagingException {
        EmailMessageFactory messageFactory = new EmailMessageFactory();
        EmailMessage emailMessage = messageFactory.createMessage(message);

        if (!emailMessage.isRead()) {
            incrementUnreadMessagesCount();
        }

        return emailMessage;
    }

    public void incrementUnreadMessagesCount() {
        unreadMessagesCount++;
        updateFolderName();
    }

    public void decrementUnreadMessagesCount() {
        unreadMessagesCount--;
        updateFolderName();
    }

    public void updateFolderName() {
        if (this.name.equals("INBOX")) {
            this.setValue((unreadMessagesCount > 0) ? this.name + " (" + unreadMessagesCount + ")" : this.name);
        }
    }

    public void addMessageToTheTop(Message message) throws MessagingException {
        EmailMessage emailMessage = transformToEmailMessage(message);
        messages.add(0, emailMessage);
    }
}
