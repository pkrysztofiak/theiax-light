package com.theiax.view.window;

import com.theiax.presentationmodel.PresentationModel;
import com.theiax.presentationmodel.domain.Grid;
import com.theiax.presentationmodel.domain.Window;
import com.theiax.reactor.Additions;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.net.URL;
import java.util.ResourceBundle;

public class TabPaneView implements Initializable {

    private final PresentationModel presentationModel;
    private final Window window;

    @FXML
    private HBox tabsPane;
    @FXML
    private StackPane stackPane;

    private final ObservableList<TabView> tabs = FXCollections.observableArrayList();
    private final Flux<TabView> tabAdded = Additions.of(tabs).startWith(tabs).subscribeOn(Schedulers.single());

    private final ObservableList<GridView> grids = FXCollections.observableArrayList();
    private final Flux<GridView> gridViewAdded = Additions.of(grids).startWith(grids).subscribeOn(Schedulers.single());


    public TabPaneView(PresentationModel presentationModel, Window window) {
        this.presentationModel = presentationModel;
        this.window = window;

        tabAdded.subscribe(this::onTabAdded);

        gridViewAdded.publishOn(Schedulers.fromExecutor(Platform::runLater)).subscribe(gridView -> {
            stackPane.getChildren().add(gridView.getRoot());
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        window.perspectiveAdded().subscribe(perspective -> {
            tabs.add(new TabView(presentationModel, window, perspective));

            Grid grid = perspective.getGrids().get(window.getIndex());
            grids.add(new GridView(presentationModel, window, perspective, grid));
        });
    }

    private void onTabAdded(TabView tabView) {
        tabsPane.getChildren().add(tabView.getRoot());
    }

}
