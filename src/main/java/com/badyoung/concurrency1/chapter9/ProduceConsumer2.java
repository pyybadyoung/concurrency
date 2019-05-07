package com.badyoung.concurrency1.chapter9;

/**
 * 单一生产者和第一消费者
 */
public class ProduceConsumer2 {

    private int i = 1;
    private final Object LOCK = new Object();
    private boolean isProduced = false;

    public void produce(){
        synchronized (LOCK){
            if (isProduced){
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else {
                i++;
                System.out.println("P-->" + i);
                LOCK.notify();
                isProduced=true;
            }
        }

    }


    public void consumer(){
        synchronized (LOCK){
            if (isProduced){
                System.out.println("C-->" + i);
                LOCK.notify();
                isProduced=false;
            }else {
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {

        ProduceConsumer2 pc = new ProduceConsumer2();

        new Thread() {
            @Override
            public void run() {
                while (true){
                    pc.produce();
                }

            }
        }.start();


        new Thread() {
            @Override
            public void run() {
                while (true){
                    pc.consumer();
                }

            }
        }.start();
    }

}
