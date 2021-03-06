package com.badyoung.concurrency1.chapter6;

public class ThreadInterrupt {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(){
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        System.out.println("收到打断信号");
                        e.printStackTrace();
                    }

                }
            }
        };
        t.start();
        Thread.sleep(10);
        System.out.println(t.isInterrupted());
        t.interrupt();
        System.out.println(t.isInterrupted());
    }
}
