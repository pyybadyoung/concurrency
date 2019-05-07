package com.badyoung.concurent2.chapter7;

import org.junit.Test;

public class ImmutablePerformance {
    public static void main(String[] args) {
        //4258
        long startTimestamp = System.currentTimeMillis();
        SyncObj syncObj = new SyncObj();
        syncObj.setName("young");
        for (long l = 0L; l < 1000000; l++) {
            System.out.println(l + " --> " + syncObj.toString());
        }
        long endTimestamp = System.currentTimeMillis();
        System.out.println("用时：" + (endTimestamp - startTimestamp));
    }

    /**
     * 单线程不可变的变量
     */
    @Test
    public void testSingleThread() {
        //4494
        long startTimestamp = System.currentTimeMillis();
        ImmutableObj immutableObj = new ImmutableObj("bad");

        for (long l = 0L; l < 1000000; l++) {
            System.out.println(l + " --> " + immutableObj.toString());
        }
        long endTimestamp = System.currentTimeMillis();
        System.out.println("用时：" + (endTimestamp - startTimestamp));
    }

    /**
     * 多线程不可变的变量
     * @throws InterruptedException
     * 12336
     */
    @Test
    public void testMoreThread() throws InterruptedException {

        long startTimestamp = System.currentTimeMillis();
        ImmutableObj immutableObj = new ImmutableObj("bad");

            Thread t1 = new Thread(){
                @Override
                public void run() {
                    for (long l = 0L; l < 1000000; l++) {
                        System.out.println(Thread.currentThread().getName() + " --> " + immutableObj.toString());
                    }
                }
            };

        Thread t2 = new Thread(){
            @Override
            public void run() {
                for (long l = 0L; l < 1000000; l++) {
                    System.out.println(Thread.currentThread().getName() + " --> " + immutableObj.toString());
                }
            }
        };

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        long endTimestamp = System.currentTimeMillis();
        System.out.println("用时：" + (endTimestamp - startTimestamp));
    }

    /**
     * 多线程 可变的对象
     * @throws InterruptedException
     *
     * 12219
     */
    @Test
    public void testMoreThread1() throws InterruptedException {

        long startTimestamp = System.currentTimeMillis();
        SyncObj syncObj = new SyncObj();
        syncObj.setName("bad");
        Thread t1 = new Thread(){
            @Override
            public void run() {
                for (long l = 0L; l < 1000000; l++) {
                    System.out.println(Thread.currentThread().getName() + " --> " + syncObj.toString());
                }
            }
        };

        Thread t2 = new Thread(){
            @Override
            public void run() {
                for (long l = 0L; l < 1000000; l++) {
                    System.out.println(Thread.currentThread().getName() + " --> " + syncObj.toString());
                }
            }
        };

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        long endTimestamp = System.currentTimeMillis();
        System.out.println("用时：" + (endTimestamp - startTimestamp));
    }


}

/**
 * 不可变的对象
 */
class ImmutableObj{
    private final String name;

    public ImmutableObj(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "[ "+ name + " ]";
    }
}

/**
 * 可变的对象
 */
class SyncObj{
    private String name;

    public synchronized void setName(String name){
        this.name=name;
    }

    @Override
    public synchronized String toString() {
        return "[ "+ name + " ]";
    }
}