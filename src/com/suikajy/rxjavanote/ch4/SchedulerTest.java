package com.suikajy.rxjavanote.ch4;

import com.suikajy.rxjavanote.util.Sleeper;
import rx.Observable;
import rx.Scheduler;
import rx.schedulers.Schedulers;

public class SchedulerTest {

    public static void main(String[] args) {
        Scheduler immediate = Schedulers.immediate();
        Observable.create(emitter -> {
            while (!emitter.isUnsubscribed()) {
                Sleeper.sleep();
                System.out.println("   "+Thread.currentThread().getName());
                emitter.onNext(1L);
            }
            emitter.onCompleted();
        })
                .subscribeOn(Schedulers.io())
                .subscribeOn(immediate)
                .buffer(2)
                .take(3)
                .subscribe(aLong -> {
                    System.out.println(System.currentTimeMillis());
                    System.out.println(Thread.currentThread().getName());
                });
        System.out.println(Thread.currentThread());
        Sleeper.sleep(7);
    }
}
