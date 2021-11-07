module theiax.view {
    requires javafx.controls;
    requires javafx.fxml;
    requires reactor.core;

    opens com.theiax.view to javafx.fxml;
    exports com.theiax.view;
}