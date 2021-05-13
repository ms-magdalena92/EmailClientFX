package emailClient.controller;

import emailClient.factory.ViewFactory;
import emailClient.service.EmailManager;

public abstract class BaseController {

    protected ViewFactory viewFactory;

    private final String viewFxmlFileName;

    protected EmailManager emailManager;

    public BaseController(ViewFactory viewFactory, EmailManager emailManager, String viewFxmlFileName) {
        this.viewFactory = viewFactory;
        this.emailManager = emailManager;
        this.viewFxmlFileName = viewFxmlFileName;
    }

    public String getViewFxmlFileName() {
        return viewFxmlFileName;
    }
}
