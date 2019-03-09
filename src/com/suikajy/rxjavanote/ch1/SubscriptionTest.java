package com.suikajy.rxjavanote.ch1;

import com.suikajy.rxjavanote.util.DemoUtils;
import com.suikajy.rxjavanote.util.Sleeper;
import rx.Observable;
import rx.Subscription;
import rx.subscriptions.Subscriptions;

public class SubscriptionTest {

    public static void main(String[] args) {
        DemoUtils.logTime(10);
        Observable<String> observable = Observable.create(subscriber -> {
            Thread thread = new Thread(() -> {
                Sleeper.sleep(10);
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onNext("suika");
                    subscriber.onCompleted();
                }
            });
            thread.start();

            subscriber.add(Subscriptions.create(thread::interrupt));
        });

        Subscription subscription = observable.subscribe(s -> {
            System.out.println(s);
        });
        Sleeper.sleep(5);
        System.out.println(5);
        subscription.unsubscribe();
    }
}
