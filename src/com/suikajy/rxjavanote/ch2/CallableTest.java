package com.suikajy.rxjavanote.ch2;

import rx.Observable;
import rx.Subscriber;

import java.util.concurrent.Callable;

public class CallableTest {
    public static void main(String[] args) {
        rxLoad(1).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
            }
        });
        rxLoad2(2).subscribe(System.out::println);
    }

    // 下面两种写法相同，fromCallable是有try-catch包裹的简写形式（因为很常用）
    private static Observable<String> rxLoad(int id) {
        return Observable.fromCallable(new Callable<String>() {
            @Override
            public String call() throws Exception {
                if (id == 1) throw new IllegalArgumentException("id can not be 1");
                return "suika" + id;
            }
        });
    }

    private static Observable<String> rxLoad2(int id) {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    subscriber.onNext("suika" + id);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });
    }
}
