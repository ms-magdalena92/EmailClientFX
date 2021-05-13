package emailClient.factory;

import emailClient.App;
import emailClient.controller.BaseController;
import emailClient.controller.LoginWindowController;
import emailClient.controller.MainWindowController;
import emailClient.service.EmailManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewFactory {

    private final EmailManager emailManager;

    public ViewFactory(EmailManager emailManager) {
        this.emailManager = emailManager;
    }

    public void showLoginWindow() {
        BaseController controller = new LoginWindowController(this, emailManager);
        initializeStage(controller, false);
    }

    public void showMainWindow() {
        BaseController controller = new MainWindowController(this, emailManager);
        initializeStage(controller, true);
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
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(resizable);
        stage.show();
    }

    public void closeStage(Stage stage) {
        stage.close();
    }
}
