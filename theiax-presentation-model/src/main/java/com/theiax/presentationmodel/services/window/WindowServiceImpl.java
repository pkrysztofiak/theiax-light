package com.theiax.presentationmodel.services.window;

import com.theiax.presentationmodel.domain.Bounds;
import com.theiax.presentationmodel.domain.Window;
import com.theiax.presentationmodel.services.WindowService;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;
import java.util.List;

public class WindowServiceImpl implements WindowService {

    @Override
    public List<Window> getAll() {
        Window window1 = new Window(0, new Bounds(0, 0, 200, 200));
        Window window2 = new Window(1, new Bounds(200, 0, 200, 200));

        List<Window> windows = new ArrayList<>();
        windows.add(window1);
        windows.add(window2);
        return windows;
    }
}
