package com.theiax.presentationmodel.domain;

import com.theiax.reactor.Additions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.List;

public class Grid {

    private final ObservableList<GridCell> cells = FXCollections.observableArrayList();
    private final Flux<GridCell> cellAdded = Additions.of(cells).startWith(cells).subscribeOn(Schedulers.single());

    public Grid(List<GridCell> cells) {
        this.cells.setAll(cells);
    }

    public Flux<GridCell> cellAdded() {
        return cellAdded;
    }
}