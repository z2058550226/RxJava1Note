package com.suikajy.rxjavanote.ch2;

import com.suikajy.rxjavanote.util.Sleeper;
import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;

public class SubscriptionTest {
    public static void main(String[] args) {
        Subscription subscription = Observable.<String>create(e -> {
            e.onNext("suika");
            e.add(new Subscription() {
                @Override
                public void unsubscribe() {
                    System.out.println("unsubscribe");
                }

                @Override
                public boolean isUnsubscribed() {
                    System.out.println("isUnsubscribed");
                    return false;
                }
            });
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        });

        Sleeper.sleep(3);
        if (!subscription.isUnsubscribed())
            subscription.unsubscribe();
    }
}
