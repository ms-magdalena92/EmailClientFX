package emailClient.model;

import javafx.scene.control.MenuButton;

import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;

public class MessageAttachmentButton extends MenuButton {

    private final MimeBodyPart mimeBodyPart;

    public MessageAttachmentButton(MimeBodyPart mimeBodyPart) throws MessagingException {
        this.mimeBodyPart = mimeBodyPart;
        createMenuButton();
    }

    private void createMenuButton() throws MessagingException {
        this.setText(mimeBodyPart.getFileName());
    }
}
