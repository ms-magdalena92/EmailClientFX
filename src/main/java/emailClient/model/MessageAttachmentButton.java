package emailClient.model;

import emailClient.service.AttachmentHandler;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;

import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import java.io.IOException;

public class MessageAttachmentButton extends MenuButton {

    private final MimeBodyPart mimeBodyPart;

    private final MenuItem saveAttachment = new MenuItem("save");

    private final MenuItem openAttachment = new MenuItem("open");

    public MessageAttachmentButton(MimeBodyPart mimeBodyPart) throws MessagingException {
        this.mimeBodyPart = mimeBodyPart;
        createMenuButton();
        setUpAttachmentMenu();
    }

    private void createMenuButton() throws MessagingException {
        this.setText(mimeBodyPart.getFileName());
        this.getItems().addAll(saveAttachment, openAttachment);
    }

    private void setUpAttachmentMenu() {
        try {
            AttachmentHandler attachmentHandler = new AttachmentHandler(mimeBodyPart);
            saveAttachment.setOnAction(event -> {
                try {
                    attachmentHandler.saveAttachmentToDisc();
                } catch (MessagingException | IOException e) {
                    e.printStackTrace();
                }
            });
            openAttachment.setOnAction(event -> {
                try {
                    attachmentHandler.openAttachment();
                } catch (MessagingException | IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
