package com.badyoung.concurent2.chapter2;

/**
 * 线程在wait的时候是如何被唤醒的，再次启动运行
 */
public class WaitSet1 {

    private static final Object LOCK = new Object();

    public static void main(String[] args) throws InterruptedException {

        /**
         * 线程被唤醒后要重新取锁
         */
        new Thread(){
            @Override
            public void run() {
                synchronized (LOCK){
                    System.out.println("Begin...................");
                    try {
                        System.out.println("Thread will coming");
                        LOCK.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Thread will out");
                }
            }
        }.start();


        Thread.sleep(1000);
        synchronized (LOCK){
            LOCK.notify();
        }

    }
}
