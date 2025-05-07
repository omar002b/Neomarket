module com.example.neomarket {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.sql;
    requires jbcrypt;

    opens com.example.neomarket to javafx.fxml;
    opens com.example.controllers to javafx.fxml;
    opens com.example.dao to javafx.fxml;
    opens com.example.modelos to javafx.fxml;
    exports com.example.neomarket;
}