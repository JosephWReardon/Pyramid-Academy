module com.example.humanvsgoblingui {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.ikonli.javafx;
    requires com.almasb.fxgl.all;

    opens com.example.humanvsgoblingui to javafx.fxml;
    exports com.example.humanvsgoblingui;
}