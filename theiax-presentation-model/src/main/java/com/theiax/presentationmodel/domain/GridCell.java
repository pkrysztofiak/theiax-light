package com.theiax.presentationmodel.domain;

import com.theiax.reactor.Values;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class GridCell {

    private final Property<Bounds> ratioBoundsProperty = new SimpleObjectProperty<>();
    private final Flux<Bounds> ratioBoundsFlux = Values.of(ratioBoundsProperty).subscribeOn(Schedulers.single());

    private final Property<Bounds> boundsProperty = new SimpleObjectProperty<>();
    private final Flux<Bounds> boundsFlux = Values.of(boundsProperty).subscribeOn(Schedulers.single());

    public GridCell(Bounds ratioBounds, ViewType... viewTypes) {
        ratioBoundsProperty.setValue(ratioBounds);
    }

    public void updateBounds(javafx.geometry.Bounds parentBounds) {
        System.out.println("parentBounds=" + parentBounds);
        Bounds ratioBounds = ratioBoundsProperty.getValue();
        double x = ratioBounds.getX() * parentBounds.getWidth();
        double y = ratioBounds.getY() * parentBounds.getHeight();
        double width = ratioBounds.getWidth() * parentBounds.getWidth();
        double height = ratioBounds.getHeight() * parentBounds.getHeight();
        Bounds bounds = new Bounds(x, y, width, height);
        System.out.println("bounds=" + bounds);
        boundsProperty.setValue(bounds);
    }

    public Flux<Bounds> ratioBoundsFlux() {
        return ratioBoundsFlux;
    }

    public Flux<Bounds> boundsFlux() {
        return boundsFlux;
    }
}