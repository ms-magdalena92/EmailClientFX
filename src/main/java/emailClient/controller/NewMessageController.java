package emailClient.controller;

import emailClient.factory.ViewFactory;
import emailClient.model.EmailAccount;
import emailClient.service.EmailManager;
import emailClient.service.MessageSenderService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.web.HTMLEditor;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class NewMessageController extends BaseController implements Initializable {

    private static final String NEW_MESSAGE_VIEW_FILE_NAME = "newMessageWindow.fxml";

    private final List<File> attachments = new ArrayList<>();

    @FXML
    private TextField recipientsInput;

    @FXML
    private TextField subjectInput;

    @FXML
    private HTMLEditor htmlEditor;

    @FXML
    private Label errorTextField;

    @FXML
    private ChoiceBox<EmailAccount> emailAccountInput;

    @FXML
    private HBox attachmentList;

    public NewMessageController(ViewFactory viewFactory, EmailManager emailManager) {
        super(viewFactory, emailManager, NEW_MESSAGE_VIEW_FILE_NAME);
    }

    @FXML
    void addAttachment() {
        FileChooser fileChooser = new FileChooser();
        File selectedAttachment = fileChooser.showOpenDialog(null);
        if (selectedAttachment != null) {
            attachments.add(selectedAttachment);
            attachmentList.getChildren().add(new Label(selectedAttachment.getName()));
        }
    }

    @FXML
    void sendMessage() {
        MessageSenderService messageSender = new MessageSenderService(
                emailAccountInput.getValue(),
                subjectInput.getText(),
                recipientsInput.getText(),
                htmlEditor.getHtmlText(),
                attachments
        );

        messageSender.start();
        messageSender.setOnSucceeded(event -> {
            switch (messageSender.getValue()) {
                case SUCCESS:
                    Stage stage = (Stage) recipientsInput.getScene().getWindow();
                    viewFactory.closeStage(stage);
                    break;
                case FAILED_BY_PROVIDER:
                    errorTextField.setText("Sorry, the message couldn't be sent: provider error.");
                    break;
                case FAILED_BY_UNEXPECTED_ERROR:
                    errorTextField.setText("Sorry, the message couldn't be sent: unexpected error.");
                    break;
            }
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        emailAccountInput.setItems(emailManager.getEmailAccounts());
        emailAccountInput.setValue(emailManager.getEmailAccounts().get(0));
    }
}
