module HW4.VF {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.desktop;
    requires javafx.media;

    exports view;
    opens view to javafx.fxml;
}