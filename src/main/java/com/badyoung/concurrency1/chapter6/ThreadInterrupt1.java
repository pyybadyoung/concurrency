package com.badyoung.concurrency1.chapter6;

public class ThreadInterrupt1 {

    private static final Object MONTTOR = new Object();
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(){
            @Override
            public void run() {
                while (true){
                synchronized (MONTTOR){
                    try {
                        MONTTOR.wait(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                }
            }
        };
        t.start();
        Thread.sleep(10);
        System.out.println(t.isInterrupted());
        t.interrupt();
        System.out.println(t.isInterrupted());
        //将线程干掉
        t.stop();
    }
}
