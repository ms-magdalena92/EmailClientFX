package emailClient;

import emailClient.factory.ViewFactory;
import emailClient.model.EmailAccount;
import emailClient.model.ValidAccountCredentials;
import emailClient.service.EmailManager;
import emailClient.service.AccountPersistenceHandler;
import emailClient.service.LoginService;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class App extends Application {

    private final EmailManager emailManager = new EmailManager();

    private final AccountPersistenceHandler accountPersistenceHandler = new AccountPersistenceHandler();

    List<ValidAccountCredentials> validAccountCredentials = new ArrayList<>();

    @Override
    public void start(Stage stage) {
        ViewFactory viewFactory = new ViewFactory(emailManager);

        validAccountCredentials = accountPersistenceHandler.loadFromPersistence();
        if (validAccountCredentials.size() > 0) {
            emailManager.setRememberCredentials(true);
            viewFactory.showMainWindow();
            addEmailAccountsToView();
        } else {
            viewFactory.showLoginWindow(false);
        }
    }

    private void addEmailAccountsToView() {
        for (ValidAccountCredentials validAccount : validAccountCredentials) {
            EmailAccount emailAccount = new EmailAccount(validAccount.getEmailAddress(),
                    validAccount.getPassword());
            LoginService loginService = new LoginService(emailAccount, emailManager);
            loginService.start();
        }
    }

    @Override
    public void stop() {
        if (emailManager.isRememberCredentials()) {
            List<ValidAccountCredentials> validAccountList = new ArrayList<>();
            for (EmailAccount emailAccount : emailManager.getEmailAccounts()) {
                validAccountList.add(new ValidAccountCredentials(emailAccount.getEmailAddress(), emailAccount.getPassword()));
            }
            accountPersistenceHandler.saveToPersistence(validAccountList);
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
