package com.badyoung.concurrency1.chapter9;

import org.junit.Test;

import java.util.stream.Stream;

public class DifferenceOfSleepAndWait {

    private final static Object LOCK = new Object();

    public static void main(String[] args) {
//        m1();
          m2();
    }

    @Test
    public void test(){
        Stream.of("T1","T2").forEach(t->
                new Thread(){
                    @Override
                    public void run() {
                        m1();
                    }
                }.start()
                );
    }

    public static void m1() {
        synchronized (LOCK) {

                 try {
                     System.out.println(Thread.currentThread().getName());
                     Thread.sleep(1000);
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
             }

        }




    public static void m2(){
        try {
            LOCK.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void m3(){
        synchronized (LOCK) {
            try {
                /**
                 * wait()方法依赖于monitor
                 */
                LOCK.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
