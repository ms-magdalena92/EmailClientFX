package emailClient.controller;

import emailClient.factory.ViewFactory;

public class LoginWindowController extends BaseController {

    private static final String LOGIN_VIEW_FILE_NAME = "loginWindow.fxml";

    public LoginWindowController(ViewFactory viewFactory) {
        super(viewFactory, LOGIN_VIEW_FILE_NAME);
    }
}
