module theiax.view {
    requires javafx.controls;
    requires javafx.fxml;
    requires reactor.core;
    requires theiax.presentation.model;
    requires org.reactivestreams;

    opens com.theiax.view to javafx.fxml;
    opens com.theiax.view.window to javafx.fxml;

    exports com.theiax.view;
    exports com.theiax.view.window;

}