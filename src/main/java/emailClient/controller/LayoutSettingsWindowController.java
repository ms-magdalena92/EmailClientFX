package emailClient.controller;

import emailClient.enums.FontSize;
import emailClient.enums.ThemeColor;
import emailClient.factory.ViewFactory;
import emailClient.service.EmailManager;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.stage.Stage;
import javafx.util.StringConverter;

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
        setUpSizePicker();
    }

    private void setUpThemePicker() {
        themePicker.setItems(FXCollections.observableArrayList(ThemeColor.values()));
        themePicker.setValue(viewFactory.getThemeColor());
    }

    private void setUpSizePicker() {
        fontSizePicker.setMin(0);
        fontSizePicker.setMax(FontSize.values().length - 1);
        fontSizePicker.setValue(viewFactory.getFontSize().ordinal());
        fontSizePicker.setMajorTickUnit(1);

        fontSizePicker.setMinorTickCount(0);
        fontSizePicker.setBlockIncrement(1);
        fontSizePicker.setSnapToTicks(true);
        fontSizePicker.setShowTickMarks(true);

        fontSizePicker.setShowTickLabels(true);
        fontSizePicker.setLabelFormatter(new StringConverter<>() {
            @Override
            public String toString(Double object) {
                int i = object.intValue();
                return FontSize.values()[i].toString();
            }

            @Override
            public Double fromString(String string) {
                return null;
            }
        });
        fontSizePicker.valueProperty().addListener((obs, oldVal, newVal) -> fontSizePicker.setValue(newVal.intValue()));
    }
}
