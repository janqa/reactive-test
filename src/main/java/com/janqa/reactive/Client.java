package com.janqa.reactive;

import java.util.concurrent.Flow;

public class Client implements Flow.Subscriber<Integer> {
    private String name;
    private Flow.Subscription subscription;

    public Client(String name) {
        this.name = name;
    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        System.out.println("Subscribed");
        subscription.request(1);
    }

    @Override
    public void onNext(Integer item) {
        System.out.println(name + ": " + item);
        subscription.request(1);

    }

    @Override
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
    }

    @Override
    public void onComplete() {

    }
}
