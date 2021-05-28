package emailClient.controller;

import emailClient.factory.ViewFactory;
import emailClient.service.EmailManager;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;

public class LayoutSettingsWindowController extends BaseController {

    private static final String OPTIONS_VIEW_FILE_NAME = "layoutSettingsWindow.fxml";

    @FXML
    private Slider fontSizePicker;

    @FXML
    private ChoiceBox<?> themePicker;

    public LayoutSettingsWindowController(ViewFactory viewFactory, EmailManager emailManager) {
        super(viewFactory, emailManager, OPTIONS_VIEW_FILE_NAME);
    }

    @FXML
    void applyLayoutSettings() {

    }

    @FXML
    void cancelLayoutSettings() {

    }
}
