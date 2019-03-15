package com.suikajy.rxjavanote.ch2;

import com.suikajy.rxjavanote.util.DemoUtils;
import com.suikajy.rxjavanote.util.Sleeper;
import rx.Observable;

import java.util.concurrent.TimeUnit;

public class TimerAndInterval {
    public static void main(String[] args) {
//        Observable.timer(1, TimeUnit.SECONDS)
//                .subscribe(System.out::println); // 延迟一秒后输出0
        Observable.interval(1, TimeUnit.SECONDS)
                .subscribe(System.out::println); // 延迟1秒后从0开始依次发射自然数，时间间隔1秒
        DemoUtils.logTime(5);
        Sleeper.sleep(5);
    }


}
