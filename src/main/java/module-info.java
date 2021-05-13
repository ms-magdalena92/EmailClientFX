module emailClient {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.web;
    requires activation;
    requires java.mail;

    opens emailClient to javafx.fxml;
    exports emailClient;
    exports emailClient.controller;
    exports emailClient.factory;
    opens emailClient.controller to javafx.fxml;
    exports emailClient.enums;
    opens emailClient.enums to javafx.fxml;
}
