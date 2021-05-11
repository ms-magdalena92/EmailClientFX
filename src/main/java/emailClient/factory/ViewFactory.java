package emailClient.factory;

import emailClient.App;
import emailClient.controller.BaseController;
import emailClient.controller.LoginWindowController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewFactory {

    public void showLoginWindow() {
        BaseController controller = new LoginWindowController(this);
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
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
