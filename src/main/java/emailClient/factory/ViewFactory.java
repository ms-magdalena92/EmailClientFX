package emailClient.factory;

import emailClient.App;
import emailClient.controller.*;
import emailClient.enums.FontSize;
import emailClient.enums.ThemeColor;
import emailClient.service.EmailManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class ViewFactory {

    private final EmailManager emailManager;

    private boolean mainViewInitialized = false;

    private ThemeColor themeColor;

    private final ArrayList<Stage> activeStages;

    private FontSize fontSize;

    public ViewFactory(EmailManager emailManager) {
        this.emailManager = emailManager;
        this.themeColor = ThemeColor.DEFAULT;
        activeStages = new ArrayList<>();
        this.fontSize = FontSize.MEDIUM;
    }

    public ThemeColor getThemeColor() {
        return themeColor;
    }

    public void setThemeColor(ThemeColor themeColor) {
        this.themeColor = themeColor;
    }

    public FontSize getFontSize() {
        return fontSize;
    }

    public void setFontSize(FontSize fontSize) {
        this.fontSize = fontSize;
    }

    public boolean isMainViewInitialized() {
        return mainViewInitialized;
    }

    public void showLoginWindow(boolean disableRememberMeBox) {
        BaseController controller = new LoginWindowController(this, emailManager, disableRememberMeBox);
        initializeStage(controller, false);
    }

    public void showMainWindow() {
        BaseController controller = new MainWindowController(this, emailManager);
        initializeStage(controller, true);
        mainViewInitialized = true;
    }

    public void showNewMessageWindow() {
        BaseController controller = new NewMessageController(this, emailManager);
        initializeStage(controller, false);
    }

    public void showMessageDetailsWindow() {
        BaseController controller = new MessageDetailsController(this, emailManager);
        initializeStage(controller, false);
    }

    public void showLayoutSettingsWindow() {
        BaseController controller = new LayoutSettingsWindowController(this, emailManager);
        initializeStage(controller, false);
    }

    private void initializeStage(BaseController baseController, boolean resizable) {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(baseController.getViewFxmlFileName()));
        fxmlLoader.setController(baseController);
        Parent parent;

        try {
            parent = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Scene scene = new Scene(parent);
        updateLayout(scene);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(resizable);
        stage.show();
        activeStages.add(stage);
    }

    private void updateLayout(Scene scene) {
        scene.getStylesheets().clear();
        scene.getStylesheets().add(Objects.requireNonNull(App.class.getResource(themeColor.getThemeCssPath())).toExternalForm());
        scene.getStylesheets().add(Objects.requireNonNull(App.class.getResource(fontSize.getFontSizePath())).toExternalForm());
    }

    public void updateActiveStagesLayout() {
        for (Stage stage : activeStages) {
            Scene scene = stage.getScene();
            updateLayout(scene);
        }
    }

    public void closeStage(Stage stage) {
        stage.close();
    }
}
