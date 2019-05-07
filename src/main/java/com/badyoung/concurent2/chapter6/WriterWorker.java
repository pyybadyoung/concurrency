package com.badyoung.concurent2.chapter6;

import java.util.Random;

/**
 * 写线程
 */
public class WriterWorker extends Thread {

    private final static Random random = new Random(System.currentTimeMillis());

    private final SharedData data;

    private final String filler;

    private int index;

    public WriterWorker(SharedData data, String filler) {
        this.data = data;
        this.filler = filler;
    }

    @Override
    public void run() {
        try {
            while (true) {
                char c = nextChar();
                data.write(c);
                System.out.println(Thread.currentThread().getName()+" writer "+c);
                Thread.sleep(random.nextInt(1000));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private char nextChar() {
        char c = filler.charAt(index);
        index++;
        if (index > filler.length()) {
            index = 0;
        }
        return c;
    }
}
