package com.badyoung.concurrency1.chapter5;

public class ThreadJoin1 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(){
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    System.out.println(Thread.currentThread().getName()+"="+i);
                }
            }
        };

        thread.start();
        thread.join();

        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName()+"="+i);
        }

    }
}
