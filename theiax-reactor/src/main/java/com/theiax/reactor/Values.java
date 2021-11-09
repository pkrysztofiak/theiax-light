package com.theiax.reactor;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import reactor.core.publisher.Flux;

import java.util.Optional;

public class Values {

    public static <T> Flux<T> of(ObservableValue<T> observableValue) {
        return Flux.create(sink -> {
            ChangeListener<T> changeListener = (observable, oldValue, newValue) -> {
                if (newValue != null) {
                    sink.next(newValue);
                }
            };
            observableValue.addListener(changeListener);
            sink.onDispose(() -> observableValue.removeListener(changeListener));
        });
    }
}
