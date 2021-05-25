package emailClient.controller;

import emailClient.factory.ViewFactory;
import emailClient.model.EmailFolder;
import emailClient.model.EmailMessage;
import emailClient.model.EmailMessageSize;
import emailClient.service.EmailManager;
import emailClient.service.MessageRendererService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class MainWindowController extends BaseController implements Initializable {

    private static final String MAIN_VIEW_FILE_NAME = "mainWindow.fxml";

    private MessageRendererService messageRendererService;

    @FXML
    private TreeView<String> emailsTreeView;

    @FXML
    private TableView<EmailMessage> emailsTableView;

    @FXML
    private TableColumn<EmailMessage, String> sendersColumn;

    @FXML
    private TableColumn<EmailMessage, String> subjectColumn;

    @FXML
    private TableColumn<EmailMessage, String> recipientsColumn;

    @FXML
    private TableColumn<EmailMessage, EmailMessageSize> sizeColumn;

    @FXML
    private TableColumn<EmailMessage, Date> dateColumn;

    @FXML
    private WebView emailWebView;

    public MainWindowController(ViewFactory viewFactory, EmailManager emailManager) {
        super(viewFactory, emailManager, MAIN_VIEW_FILE_NAME);
    }

    @FXML
    void composeMessage() {
        viewFactory.showNewMessageWindow();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setUpEmailsTree();
        setUpEmailsTable();
        setUpSelectedFolder();
        setUpMessageSelection();
    }

    private void setUpEmailsTree() {
        emailsTreeView.setRoot(emailManager.getFoldersRoot());
        emailsTreeView.setShowRoot(false);
    }

    private void setUpEmailsTable() {
        subjectColumn.setCellValueFactory(new PropertyValueFactory<>("subject"));
        sendersColumn.setCellValueFactory((new PropertyValueFactory<>("senders")));
        recipientsColumn.setCellValueFactory(new PropertyValueFactory<>("recipients"));
        sizeColumn.setCellValueFactory(new PropertyValueFactory<>("size"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
    }

    private void setUpSelectedFolder() {
        emailsTreeView.setOnMouseClicked(e -> {
            EmailFolder folder = (EmailFolder) emailsTreeView.getSelectionModel().getSelectedItem();
            if (folder != null) {
                emailsTableView.setItems(folder.getMessages());
            }
        });
    }

    private void setUpMessageSelection() {
        messageRendererService = new MessageRendererService(emailWebView.getEngine());

        emailsTableView.setOnMouseClicked(event -> {
            EmailMessage emailMessage = emailsTableView.getSelectionModel().getSelectedItem();
            if (emailMessage != null) {
                messageRendererService.setEmailMessage(emailMessage);
                messageRendererService.restart();
            }
        });
    }
}
