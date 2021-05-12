package emailClient.controller;

import emailClient.factory.ViewFactory;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeView;
import javafx.scene.web.WebView;

public class MainWindowController extends BaseController {

    private static final String MAIN_VIEW_FILE_NAME = "mainWindow.fxml";

    @FXML
    private TreeView<?> emailsTreeView;

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

    public MainWindowController(ViewFactory viewFactory) {
        super(viewFactory, MAIN_VIEW_FILE_NAME);
    }
}
