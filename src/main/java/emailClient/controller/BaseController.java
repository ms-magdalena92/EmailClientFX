package emailClient.controller;

import emailClient.factory.ViewFactory;

public abstract class BaseController {

    protected ViewFactory viewFactory;

    private final String viewFxmlFileName;

    public BaseController(ViewFactory viewFactory, String viewFxmlFileName) {
        this.viewFactory = viewFactory;
        this.viewFxmlFileName = viewFxmlFileName;
    }

    public String getViewFxmlFileName() {
        return viewFxmlFileName;
    }
}
