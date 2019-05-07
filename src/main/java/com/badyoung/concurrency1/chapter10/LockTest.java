package com.badyoung.concurrency1.chapter10;

import java.util.Optional;
import java.util.stream.Stream;

public class LockTest {

    public static void main(String[] args) {

        final BooleanLock booleanLock = new BooleanLock();
        Stream.of("T1","T2","T3","T4").forEach(t->
                new Thread(()->{
                    try {
                        booleanLock.lock();
                        Optional.of(Thread.currentThread().getName()+" get booleanLock")
                            .ifPresent(System.out::println);
                        work();
                    } catch (InterruptedException e) {
                        e.printStackTrace();

                    }finally {
                        booleanLock.unLuck();
                    }
                },t).start());

    }

    public static void work() throws InterruptedException {
        Optional.of(Thread.currentThread().getName()+" is working ...")
                .ifPresent(System.out::println);
        Thread.sleep(1000);
    }
}
