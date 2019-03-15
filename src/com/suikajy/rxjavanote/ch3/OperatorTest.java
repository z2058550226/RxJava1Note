package com.suikajy.rxjavanote.ch3;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;

import java.util.Arrays;

public class OperatorTest {
    public static void main(String[] args) {
//        test1();
        test2();
    }

    private static void test1() {
        Observable.just(8, 9, 10)
                .doOnNext(i -> System.out.println("A: " + i))
                .filter(i -> i % 3 > 0)
                .doOnNext(i -> System.out.println("B: " + i))
                .map(i -> "#" + i * 10)
                .doOnNext(i -> System.out.println("C: " + i))
                .filter(s -> s.length() < 4)
                .subscribe(s -> System.out.println("D: " + s));
    }

    private static void test2() {
        // 一般用在操作符之间的组合中，比如flatMap
        Observable.<String>error(new IllegalArgumentException("ss")).subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                System.out.println("error onCompleted");
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println(throwable.toString());
            }

            @Override
            public void onNext(String o) {
                System.out.println(o);
            }
        });
        Observable.empty().subscribe(new Subscriber<Object>() {
            @Override
            public void onCompleted() {
                System.out.println("empty onCompleted");
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("onError" + throwable.toString());
            }

            @Override
            public void onNext(Object o) {
                System.out.println("onNext " + o);
            }
        });

        Observable.just(7, 8, 9,12)
                .map(integer -> {
                    if (integer % 3 == 0) {
                        throw new IllegalArgumentException("i%3 == 0");
                    } else {
                        return integer;
                    }
                }).flatMap(Observable::just, Observable::error, Observable::empty)
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("OperatorTest.onCompleted");
                    }

                    @Override
                    public void onError(Throwable throwable) {
//                        System.out.println("onError: " + Arrays.toString(new String[throwable]));
                        throwable.printStackTrace();
                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("onNext: " + integer);
                    }
                });
    }
}
