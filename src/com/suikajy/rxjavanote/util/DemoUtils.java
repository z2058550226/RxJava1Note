package com.suikajy.rxjavanote.util;

import rx.Observable;
import rx.schedulers.Schedulers;

import java.text.SimpleDateFormat;
import java.util.Date;

public interface DemoUtils {

    static void logDate(long millisecond) {
        Observable.create(emitter -> {
            final long startTime = System.currentTimeMillis();
            long currentTime = System.currentTimeMillis();
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            while (currentTime - startTime < millisecond) {
                Sleeper.sleep(1);
                currentTime = System.currentTimeMillis();
                String formatTime = sf.format(new Date(currentTime));
                emitter.onNext(formatTime);
            }
            emitter.onCompleted();
        }).subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(System.out::println);
    }

    static void logTime(long second) {
        Observable.create(emitter -> {
            long counter = 1;
            while (counter < second) {
                Sleeper.sleep();
                emitter.onNext("已过"+counter+"秒");
                counter += 1;
            }
            emitter.onCompleted();
        }).subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(System.out::println);
    }

}
