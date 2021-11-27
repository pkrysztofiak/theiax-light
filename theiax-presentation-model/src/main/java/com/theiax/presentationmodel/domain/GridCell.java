package com.theiax.presentationmodel.domain;

import com.theiax.presentationmodel.domain.impl.GridCellImpl;
import reactor.core.publisher.Flux;

public interface GridCell {

    static GridCell create(Bounds ratioBounds, ViewType... viewTypes) {
        return new GridCellImpl(ratioBounds, viewTypes);
    }

    void updateBounds(Bounds parentBounds);

    void selectViewType(ViewType viewType);

    Flux<Bounds> ratioBoundsFlux();

    Flux<Bounds> boundsFlux();

    Flux<ViewType> viewAddedFlux();

    Flux<ViewType> selectedViewFlux();

}