module emailClient {
    requires javafx.controls;
    requires javafx.fxml;

    opens emailClient to javafx.fxml;
    exports emailClient;
    exports emailClient.controller;
    exports emailClient.factory;
    opens emailClient.controller to javafx.fxml;
}