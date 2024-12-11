module br.com.vitor.dukemarket.javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    
    requires java.sql;

    opens br.com.vitor.dukemarket.javafx to javafx.fxml;
    opens br.com.vitor.dukemarket.javafx.controller to javafx.fxml;
    
    exports br.com.vitor.dukemarket.javafx;
    exports br.com.vitor.dukemarket.javafx.controller;
    exports br.com.vitor.dukemarket.javafx.DAO;
    exports br.com.vitor.dukemarket.javafx.model;
    exports connection;
    
    requires mysql.connector.java;
}
