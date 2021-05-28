package emailClient.controller;

import emailClient.enums.ThemeColor;
import emailClient.factory.ViewFactory;
import emailClient.service.EmailManager;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LayoutSettingsWindowController extends BaseController implements Initializable {

    private static final String OPTIONS_VIEW_FILE_NAME = "layoutSettingsWindow.fxml";

    @FXML
    private Slider fontSizePicker;

    @FXML
    private ChoiceBox<ThemeColor> themePicker;

    public LayoutSettingsWindowController(ViewFactory viewFactory, EmailManager emailManager) {
        super(viewFactory, emailManager, OPTIONS_VIEW_FILE_NAME);
    }

    @FXML
    void applyLayoutSettings() {
        viewFactory.setThemeColor(themePicker.getValue());
        viewFactory.updateActiveStagesLayout();
    }

    @FXML
    void cancelLayoutSettings() {
        Stage stage = (Stage) themePicker.getScene().getWindow();
        viewFactory.closeStage(stage);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setUpThemePicker();
    }

    private void setUpThemePicker() {
        themePicker.setItems(FXCollections.observableArrayList(ThemeColor.values()));
        themePicker.setValue(viewFactory.getThemeColor());
    }
}
