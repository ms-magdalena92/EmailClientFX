package emailClient;

import emailClient.factory.ViewFactory;
import emailClient.service.EmailManager;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    private final EmailManager emailManager = new EmailManager();

    @Override
    public void start(Stage stage) {
        ViewFactory viewFactory = new ViewFactory(emailManager);
        viewFactory.showLoginWindow();
    }

    public static void main(String[] args) {
        launch();
    }
}
