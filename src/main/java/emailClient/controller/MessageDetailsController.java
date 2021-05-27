package emailClient.controller;

import emailClient.factory.ViewFactory;
import emailClient.model.EmailMessage;
import emailClient.model.MessageAttachmentButton;
import emailClient.service.EmailManager;
import emailClient.service.MessageRendererService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebView;

import javax.mail.internet.MimeBodyPart;
import java.net.URL;
import java.util.ResourceBundle;

public class MessageDetailsController extends BaseController implements Initializable {

    private static final String MESSAGE_DETAILS_VIEW_FILE_NAME = "messageDetailsWindow.fxml";

    @FXML
    private Label attachmentsLabel;

    @FXML
    private WebView webView;

    @FXML
    private Label subject;

    @FXML
    private Label sender;

    @FXML
    private HBox attachments;

    public MessageDetailsController(ViewFactory viewFactory, EmailManager emailManager) {
        super(viewFactory, emailManager, MESSAGE_DETAILS_VIEW_FILE_NAME);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        EmailMessage emailMessage = emailManager.getSelectedMessage();
        subject.setText(emailMessage.getSubject());
        sender.setText(emailMessage.getSenders());
        loadAttachments(emailMessage);

        MessageRendererService messageRendererService = new MessageRendererService(webView.getEngine());
        messageRendererService.setEmailMessage(emailMessage);
        messageRendererService.restart();
    }

    private void loadAttachments(EmailMessage emailMessage) {
        if (emailMessage.hasAttachment()) {
            for (MimeBodyPart mimeBodyPart : emailMessage.getAttachments()) {
                try {
                    MessageAttachmentButton attachmentButton = new MessageAttachmentButton(mimeBodyPart);
                    attachments.getChildren().add(attachmentButton);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            attachmentsLabel.setText("");
        }
    }
}
