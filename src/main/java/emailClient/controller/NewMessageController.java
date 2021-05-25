package emailClient.controller;

import emailClient.factory.ViewFactory;
import emailClient.service.EmailManager;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.web.HTMLEditor;

public class NewMessageController extends BaseController {

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
    private ChoiceBox<?> emailAccountInput;

    public NewMessageController(ViewFactory viewFactory, EmailManager emailManager) {
        super(viewFactory, emailManager, NEW_MESSAGE_VIEW_FILE_NAME);
    }

    @FXML
    void addAttachment() {

    }

    @FXML
    void sendMessage() {

    }
}
