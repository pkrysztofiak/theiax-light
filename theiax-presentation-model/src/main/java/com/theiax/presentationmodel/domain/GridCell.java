package com.theiax.presentationmodel.domain;

import com.theiax.presentationmodel.domain.impl.GridCellImpl;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public interface GridCell {

    static GridCell create(Bounds ratioBounds, ViewType... viewTypes) {
        return new GridCellImpl(ratioBounds, viewTypes);
    }

//    void updateBounds(Bounds parentBounds);

    void selectViewType(ViewType viewType);

    Flux<Bounds> ratioBoundsFlux();

    Flux<Bounds> boundsFlux();

    void setBounds(Bounds bounds);

    Flux<ViewType> viewAddedFlux();

    Flux<ViewType> selectedViewFlux();

    default Bounds calculateBounds(Bounds gridBounds, Bounds ratioCellBounds) {
        System.out.println("gridBounds=" + gridBounds);
        System.out.println("ratioBounds=" + ratioCellBounds);
        double x = gridBounds.getWidth() * ratioCellBounds.getX();
        double y = gridBounds.getHeight() * ratioCellBounds.getY();
        double width = gridBounds.getWidth() * ratioCellBounds.getWidth();
        double height = gridBounds.getHeight() * ratioCellBounds.getHeight();
        Bounds bounds = new Bounds(x, y, width, height);
        System.out.println("calculated= " + bounds);
        return bounds;
    }

    default Mono<Bounds> calculateBoundsAsync(Bounds gridBounds, Bounds ratioCellBounds) {
        //TODO fromSupplier() vs fromCallable()
        return Mono.fromSupplier(() -> calculateBounds(gridBounds, ratioCellBounds)).subscribeOn(Schedulers.boundedElastic());
    }

}