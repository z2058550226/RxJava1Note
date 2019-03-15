package com.suikajy.rxjavanote.ch2;

import com.suikajy.rxjavanote.util.Sleeper;
import rx.subjects.*;

public class SubjectTest {
    public static void main(String[] args) {
//        replaySubjectTest();
//        behaviorSubjectTest();
//        publishSubjectTest();
        asyncSubjectTest();
    }

    // 缓存之前的发送历史，并在订阅之后先发射全部历史，然后继续发送后续事件
    private static void replaySubjectTest() {
        ReplaySubject<String> replaySubject = ReplaySubject.create();
        replaySubject.onNext("s");
        replaySubject.onNext("u");
        replaySubject.subscribe(System.out::println);
        replaySubject.onNext("i");
        replaySubject.subscribe(System.out::print);
        replaySubject.onNext("k");
        replaySubject.onNext("a");
        replaySubject.onCompleted();
    }

    // 发送上一个缓存的历史，然后继续发送后续事件。
    private static void behaviorSubjectTest() {
        BehaviorSubject<String> behaviorSubject = BehaviorSubject.create("default");
        behaviorSubject.subscribe(System.out::println);
        behaviorSubject.onNext("s");
        behaviorSubject.onNext("u");
        behaviorSubject.onNext("i");
        behaviorSubject.onNext("k");
        behaviorSubject.subscribe(System.out::print);
        behaviorSubject.onNext("a");
        behaviorSubject.onCompleted();
    }

    // 只发送后续的事件
    private static void publishSubjectTest() {
        PublishSubject<String> publishSubject = PublishSubject.create();
        publishSubject.onNext("s");
        publishSubject.subscribe(System.out::println);
        publishSubject.onNext("u");
        publishSubject.onNext("i");
        publishSubject.subscribe(s -> System.out.println("-- " + s + " --"));
        publishSubject.onNext("k");
        publishSubject.onNext("a");
        publishSubject.onCompleted();
    }

    // 只在onCompleted()的时候发射值
    private static void asyncSubjectTest() {
        AsyncSubject<String> asyncSubject = AsyncSubject.create();
        asyncSubject.onNext("s");
        asyncSubject.onNext("u");
        asyncSubject.subscribe(System.out::println);
        asyncSubject.onNext("i");
        asyncSubject.onNext("k");
        asyncSubject.subscribe(s -> System.out.println("-- " + s + " --"));
        asyncSubject.onNext("a");
        Sleeper.sleep(3);
        asyncSubject.onCompleted();
    }
}
