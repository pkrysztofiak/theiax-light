package com.theiax.view.window;

import com.theiax.presentationmodel.domain.GridCell;
import com.theiax.presentationmodel.domain.ViewType;
import com.theiax.reactor.Events;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import reactor.core.scheduler.Schedulers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GridCellTabView implements Initializable {

    @FXML
    private Parent root;

    @FXML
    private Label nameLabel;

    private final ViewType viewType;
    private final GridCell cell;

    public GridCellTabView(ViewType viewType, GridCell cell) {
        this.viewType = viewType;
        this.cell = cell;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GridCellTabView.fxml"));
        fxmlLoader.setControllerFactory(clazz -> {
            return this;
        });

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nameLabel.setText(viewType.toString());

        Events.of(root, MouseEvent.MOUSE_CLICKED)
                .publishOn(Schedulers.single())
                .subscribe(event -> {
                    cell.selectViewType(viewType);
                });
    }

    public Parent getRoot() {
        return root;
    }
}
