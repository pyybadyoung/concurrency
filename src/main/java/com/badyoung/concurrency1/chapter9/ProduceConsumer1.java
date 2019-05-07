package com.badyoung.concurrency1.chapter9;

/**
 * 线程之间的通讯
 */
public class ProduceConsumer1 {

    private int i = 1;

    private Object LOCK = new Object();

    public void produce() {
        synchronized (LOCK) {
            System.out.println("P-->" + (i++));
        }
    }

    public void consumer() {
        synchronized (LOCK) {
            System.out.println("C-->" + i);
        }
    }

    public static void main(String[] args) {

        ProduceConsumer1 produceConsumer1 = new ProduceConsumer1();

        new Thread() {
            @Override
            public void run() {
                while (true)
                produceConsumer1.produce();
            }
        }.start();


        new Thread() {
            @Override
            public void run() {
                while (true)
                produceConsumer1.consumer();
            }
        }.start();
    }
}



