module com.rhythm {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires javafx.mediaEmpty;
    requires javafx.graphics;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires java.desktop;
    requires mysql.connector.java;

    opens com.rhythm to javafx.fxml;
    exports com.rhythm;
}