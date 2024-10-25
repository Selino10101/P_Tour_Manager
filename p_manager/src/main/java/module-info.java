module manager {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    opens manager to javafx.fxml;
    exports manager;
}
