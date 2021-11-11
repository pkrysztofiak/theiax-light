package com.theiax.presentationmodel.services;

import com.theiax.presentationmodel.domain.Window;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;

public interface WindowService {

    default Mono<List<Window>> getAllAsync() {
        return Mono.fromCallable(this::getAll).subscribeOn(Schedulers.boundedElastic());
    }

    List<Window> getAll();
}
