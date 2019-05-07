package com.badyoung.concurent2.chapter6;

public class ReadWriteLockClient {

    public static void main(String[] args) {
        final SharedData sharedData = new SharedData(10);
        new ReadWorker(sharedData).start();
        new ReadWorker(sharedData).start();
        new ReadWorker(sharedData).start();
        new ReadWorker(sharedData).start();
        new ReadWorker(sharedData).start();


        new WriterWorker(sharedData, "qwertyuiopasdfghjkl").start();
        new WriterWorker(sharedData, "QWERTYUIOPASDFGHJKL").start();
    }
}
