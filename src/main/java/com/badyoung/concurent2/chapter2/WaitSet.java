package com.badyoung.concurent2.chapter2;

import java.util.Optional;
import java.util.stream.IntStream;

/**
 * 线程在wait的时候是如何被唤醒的，再次启动运行
 */
public class WaitSet {

    private static final Object LOCK = new Object();

    public static void main(String[] args) throws InterruptedException {
        IntStream.rangeClosed(1,10).forEach(i->{
                    new Thread(String.valueOf(i)){
                        @Override
                        public void run() {
                            synchronized (LOCK){
                                try {
                                    Optional.of(Thread.currentThread().getName()+" will come to set ")
                                    .ifPresent(System.out::println);
                                    LOCK.wait();
                                    Optional.of(Thread.currentThread().getName()+" will leave to set ")
                                            .ifPresent(System.out::println);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }.start();
                }
        );

        Thread.sleep(3000);

        IntStream.rangeClosed(1,10).forEach(i->{
            synchronized (LOCK){
                LOCK.notify();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
