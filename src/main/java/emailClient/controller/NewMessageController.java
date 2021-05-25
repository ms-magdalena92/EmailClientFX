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
import javafx.scene.web.HTMLEditor;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class NewMessageController extends BaseController implements Initializable {

    private static final String NEW_MESSAGE_VIEW_FILE_NAME = "newMessageWindow.fxml";

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

    public NewMessageController(ViewFactory viewFactory, EmailManager emailManager) {
        super(viewFactory, emailManager, NEW_MESSAGE_VIEW_FILE_NAME);
    }

    @FXML
    void addAttachment() {

    }

    @FXML
    void sendMessage() {
        MessageSenderService messageSender = new MessageSenderService(
                emailAccountInput.getValue(),
                subjectInput.getText(),
                recipientsInput.getText(),
                htmlEditor.getHtmlText()
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
