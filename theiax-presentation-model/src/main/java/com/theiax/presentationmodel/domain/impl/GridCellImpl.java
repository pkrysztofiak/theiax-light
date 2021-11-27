package com.theiax.presentationmodel.domain.impl;

import com.theiax.presentationmodel.domain.Bounds;
import com.theiax.presentationmodel.domain.GridCell;
import com.theiax.presentationmodel.domain.ViewType;
import com.theiax.reactor.Additions;
import com.theiax.reactor.Values;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class GridCellImpl implements GridCell {

    private final Property<Bounds> ratioBoundsProperty = new SimpleObjectProperty<>();
    private final Flux<Bounds> ratioBoundsFlux = Values.of(ratioBoundsProperty).subscribeOn(Schedulers.single());

    private final Property<Bounds> boundsProperty = new SimpleObjectProperty<>();
    private final Flux<Bounds> boundsFlux = Values.of(boundsProperty).subscribeOn(Schedulers.single());

    private final ObservableList<ViewType> views = FXCollections.observableArrayList();
    private final Flux<ViewType> viewAdded = Additions.of(views).startWith(views).subscribeOn(Schedulers.single());

    private final Property<ViewType> selectedViewProperty = new SimpleObjectProperty<>();
    private final Flux<ViewType> selectedViewFlux = Values.of(selectedViewProperty).subscribeOn(Schedulers.single());

    public GridCellImpl(Bounds ratioBounds, ViewType... viewTypes) {
        ratioBoundsProperty.setValue(ratioBounds);
        views.setAll(viewTypes);
        if (!views.isEmpty()) {
            selectedViewProperty.setValue(views.get(0));
        }
    }

    public void updateBounds(Bounds parentBounds) {
        Bounds ratioBounds = ratioBoundsProperty.getValue();
        double x = ratioBounds.getX() * parentBounds.getWidth();
        double y = ratioBounds.getY() * parentBounds.getHeight();
        double width = ratioBounds.getWidth() * parentBounds.getWidth();
        double height = ratioBounds.getHeight() * parentBounds.getHeight();
        Bounds bounds = new Bounds(x, y, width, height);
        boundsProperty.setValue(bounds);
    }

    public Flux<Bounds> ratioBoundsFlux() {
        return ratioBoundsFlux;
    }

    public Bounds getBounds() {
        return boundsProperty.getValue();
    }

    public Flux<Bounds> boundsFlux() {
        return boundsFlux;
    }

    public Flux<ViewType> viewAddedFlux() {
        return viewAdded;
    }

    public Flux<ViewType> selectedViewFlux() {
        return selectedViewFlux;
    }

    public void selectViewType(ViewType viewType) {
        selectedViewProperty.setValue(viewType);
    }
}
