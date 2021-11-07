module theiax.view {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.theiax.view to javafx.fxml;
    exports com.theiax.view;
}