package emailClient.controller;

import emailClient.enums.LoginResult;
import emailClient.factory.ViewFactory;
import emailClient.model.EmailAccount;
import emailClient.service.LoginService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginWindowController extends BaseController {

    private static final String LOGIN_VIEW_FILE_NAME = "loginWindow.fxml";

    @FXML
    private TextField emailAddressInput;

    @FXML
    private TextField passwordInput;

    @FXML
    private Label errorTextField;

    public LoginWindowController(ViewFactory viewFactory) {
        super(viewFactory, LOGIN_VIEW_FILE_NAME);
    }

    @FXML
    void login() {
        EmailAccount emailAccount = new EmailAccount(emailAddressInput.getText(), passwordInput.getText());
        LoginService loginService = new LoginService(emailAccount);
        LoginResult loginResult = loginService.login();

        switch (loginResult) {
            case SUCCESS:
                viewFactory.showMainWindow();
                break;
            case FAILED_BY_CREDENTIALS:
                errorTextField.setText("Incorrect email address or password!");
                break;
            case FAILED_BY_UNEXPECTED_ERROR:
                errorTextField.setText("Unexpected error! Please try again later.");
                break;
        }
    }
}
