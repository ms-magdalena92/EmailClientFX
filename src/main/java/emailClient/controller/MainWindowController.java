package emailClient.controller;

import emailClient.factory.ViewFactory;
import emailClient.service.EmailManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeView;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController extends BaseController implements Initializable {

    private static final String MAIN_VIEW_FILE_NAME = "mainWindow.fxml";

    @FXML
    private TreeView<String> emailsTreeView;

    @FXML
    private TableView<?> emailsTableView;

    @FXML
    private TableColumn<?, ?> senderColumn;

    @FXML
    private TableColumn<?, ?> subjectColumn;

    @FXML
    private TableColumn<?, ?> recipientColumn;

    @FXML
    private TableColumn<?, ?> sizeColumn;

    @FXML
    private TableColumn<?, ?> dateColumn;

    @FXML
    private WebView emailWebView;

    public MainWindowController(ViewFactory viewFactory, EmailManager emailManager) {
        super(viewFactory, emailManager, MAIN_VIEW_FILE_NAME);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setUpEmailsTreeView();
    }

    private void setUpEmailsTreeView() {
        emailsTreeView.setRoot(emailManager.getFoldersRoot());
        emailsTreeView.setShowRoot(false);
    }
}
