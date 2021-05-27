module emailClient {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.web;
    requires activation;
    requires java.mail;
    requires java.desktop;

    opens emailClient to javafx.fxml;
    exports emailClient;
    exports emailClient.controller;
    exports emailClient.factory;
    opens emailClient.controller to javafx.fxml;
    exports emailClient.enums;
    opens emailClient.enums to javafx.fxml;
    exports emailClient.service;
    opens emailClient.service to javafx.fxml;
    exports emailClient.model;
    opens emailClient.model to javafx.fxml;
}
