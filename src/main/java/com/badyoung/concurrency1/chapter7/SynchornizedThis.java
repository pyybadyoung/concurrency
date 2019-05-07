package com.badyoung.concurrency1.chapter7;

public class SynchornizedThis {
    public static void main(String[] args) {
        ThisLock thisLock = new ThisLock();
        new Thread("T1"){
            @Override
            public void run() {
                thisLock.m1();
            }
        }.start();

        new Thread("T2"){
            @Override
            public void run() {
                thisLock.m2();
            }
        }.start();
    }
}

class ThisLock{
    /**
     * this锁和自定义的锁
     * 同一把锁，有执行顺序
     * 不是同一把锁，在CPU中几乎是同时执行的
     */
    public synchronized void m1() {

        try {
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void m2() {
        try {
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
