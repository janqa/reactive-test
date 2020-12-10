package com.janqa.reactive;

import java.util.concurrent.*;

public class Device implements Flow.Publisher<Integer> {
    private ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

    public Device() {
        executor.schedule(Device::task, 10, TimeUnit.MILLISECONDS);
    }

    @Override
    public void subscribe(Flow.Subscriber<? super Integer> subscriber) {
        subscriber.onSubscribe(null);
    }

    private static Future<Integer> task() {
        return null;
    }
}
