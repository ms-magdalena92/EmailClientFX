module emailClient {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.web;

    opens emailClient to javafx.fxml;
    exports emailClient;
    exports emailClient.controller;
    exports emailClient.factory;
    opens emailClient.controller to javafx.fxml;
}