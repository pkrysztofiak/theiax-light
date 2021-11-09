package com.theiax.reactor;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import reactor.core.publisher.Flux;

public class Additions {

    public static <T> Flux<T> of(ObservableList<T> observableList) {
        return Flux.create(sink -> {
            ListChangeListener<T> listener = change -> {
                while (change.next()) {
                    if (change.wasAdded()) {
                        change.getAddedSubList().forEach(sink::next);
                    }
                }
            };
            observableList.addListener(listener);
            sink.onDispose(() -> observableList.removeListener(listener));
        });
    }
}
