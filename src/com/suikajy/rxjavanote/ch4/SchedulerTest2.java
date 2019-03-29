package com.suikajy.rxjavanote.ch4;

import com.suikajy.rxjavanote.util.LogUtils;
import com.suikajy.rxjavanote.util.Sleeper;
import rx.Scheduler;
import rx.schedulers.Schedulers;

@SuppressWarnings("Duplicates")
public class SchedulerTest2 {

    public static void main(String[] args) {
        trampoline();
    }

    private static void immediate() {
        Scheduler scheduler = Schedulers.immediate();
        Scheduler.Worker worker = scheduler.createWorker();
        LogUtils.log("Main start");
        worker.schedule(() -> {
            LogUtils.log(" Outer start");
            Sleeper.sleep();
            worker.schedule(() -> {
                LogUtils.log("  Middle start");
                Sleeper.sleep();
                worker.schedule(() -> {
                    LogUtils.log("   Inner start");
                    Sleeper.sleep();
                    LogUtils.log("   Inner end");
                });
                LogUtils.log("  Middle end");
            });
            LogUtils.log(" Outer end");
        });
        LogUtils.log("Main end");
        worker.unsubscribe();
    }

    // 和immediate相同, 它们都是直接继续使用上游线程，
    // 而trampoline的作用是让它规划的任务排成队列依次执行，而不是直接执行
    private static void trampoline() {
        Scheduler scheduler = Schedulers.trampoline();
        Scheduler.Worker worker = scheduler.createWorker();
        LogUtils.log("Main start");
        worker.schedule(() -> {
            LogUtils.log(" Outer start");
            Sleeper.sleep();
            worker.schedule(() -> {
                LogUtils.log("  Middle start");
                Sleeper.sleep();
                worker.schedule(() -> {
                    LogUtils.log("   Inner start");
                    Sleeper.sleep();
                    LogUtils.log("   Inner end");
                });
                LogUtils.log("  Middle end");
            });
            LogUtils.log(" Outer end");
        });
        LogUtils.log("Main end");
        worker.unsubscribe();
    }
}
