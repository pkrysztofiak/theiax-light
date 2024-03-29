package com.theiax.view.search;

import com.theiax.presentationmodel.domain.GridCell;
import com.theiax.presentationmodel.domain.ViewType;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SearchView implements Initializable {

    private ScrollPane root;

    private final GridCell gridCell;

    public SearchView(GridCell gridCell) {
        this.gridCell = gridCell;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SearchView.fxml"));
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
            boolean selected = ViewType.SEARCH.equals(viewType);
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

    public Parent getRoot() {
        return root;
    }
}
