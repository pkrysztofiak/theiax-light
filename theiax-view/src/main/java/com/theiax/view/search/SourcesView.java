package com.theiax.view.search;

import com.theiax.presentationmodel.domain.GridCell;
import com.theiax.presentationmodel.domain.ViewType;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.Region;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SourcesView implements Initializable {

    private final GridCell gridCell;

    private Region root;

    public SourcesView(GridCell gridCell) {
        this.gridCell = gridCell;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SourcesView.fxml"));
        fxmlLoader.setControllerFactory(clazz -> {
            return this;
        });
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gridCell.selectedViewFlux().subscribe(viewType -> {
            boolean selected = ViewType.SOURCES.equals(viewType);
            if (selected) {
                Platform.runLater(() -> {
                    root.setVisible(true);
                    root.toFront();
                });
            } else {
                Platform.runLater(() -> {
                    root.setVisible(false);
                });
            }
        });
    }

    public Region getRoot() {
        return root;
    }
}
