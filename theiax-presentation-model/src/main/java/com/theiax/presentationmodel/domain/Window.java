package com.theiax.presentationmodel.domain;

import com.theiax.reactor.Values;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import reactor.core.publisher.Flux;

public class Window {

    private final Property<Bounds> boundsProperty = new SimpleObjectProperty<>();
    public final Flux<Bounds> boundsChanged = Values.of(boundsProperty);

    private final Property<Perspective> selectedPerspectiveProperty = new SimpleObjectProperty<>();

    public Window(Bounds bounds) {
        boundsProperty.setValue(bounds);
    }
}
