package com.badyoung.concurrency1.chapter9;

import java.util.stream.Stream;

/**
 * 多生产者和多消费者
 *
 * 出现的各个线程都放弃了CPU的执行权
 */
public class ProduceConsumer3 {

    private int i = 1;
    private final Object LOCK = new Object();
    private boolean isProduced = false;

    public void produce() {
        synchronized (LOCK) {
            if (isProduced) {
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                i++;
                System.out.println("P-->" + i);
                LOCK.notify();
                isProduced = true;
            }
        }

    }


    public void consumer() {
        synchronized (LOCK) {
            if (isProduced) {
                System.out.println("C-->" + i);
                LOCK.notify();
                isProduced = false;
            } else {
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {

        ProduceConsumer3 pc = new ProduceConsumer3();
        Stream.of("P1", "P2").forEach(n ->
                new Thread() {
                    @Override
                    public void run() {
                        while (true) {
                            pc.produce();
                        }
                    }
                }.start()
        );

        Stream.of("C1", "C2").forEach(c ->
                new Thread() {
                    @Override
                    public void run() {
                        while (true) {
                            pc.consumer();
                        }
                    }
                }.start()
        );
    }

}
