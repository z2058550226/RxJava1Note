package com.suikajy.rxjavanote;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;

public class Main {

    public static void main(String[] args) {
        // write your code here
        Subscription subscription = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("suika");
                subscriber.onCompleted();
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
            }
        });
    }
}
