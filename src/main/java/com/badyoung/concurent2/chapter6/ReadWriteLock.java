package com.badyoung.concurent2.chapter6;

/**
 * 读写锁分离
 */
public class ReadWriteLock {

    private int readingReaders = 0;//有多少个线程正在读
    private int waitingReaders = 0;//有多少个线程等着读
    private int writingWriters = 0;//有多少个线程正在写
    private int waitingWriters = 0;//有多少个线程等着写

    //是不是更喜欢写操作
    private boolean preferWriter = true;

    public ReadWriteLock() {
        this(true);
    }

    public ReadWriteLock(boolean preferWriter) {
        this.preferWriter = preferWriter;
    }

    //拿到读锁
    public synchronized void readLock() throws InterruptedException {
        //等待读的加1
        this.waitingReaders++;
        try {
            //如果有线程正在写就不能读，当前线程要wait()
            while (writingWriters > 0 || (preferWriter && waitingWriters > 0)) {
                this.wait();
            }
            //如果没有线程正在写，那么正在读的线程要加1
            this.readingReaders++;
        } finally {
            //不管当前线程有没有进行读操作，最终要做的都是将等着读的线程减1
            this.waitingReaders--;
        }
    }

    //当前线程放弃读锁，并唤醒其他所有等着读的线程
    public synchronized void readUnLock() {
        this.readingReaders--;
        this.notifyAll();
    }

    public synchronized void writeLock() throws InterruptedException {
        this.waitingWriters++;
        try {
            while (readingReaders > 0 || writingWriters > 0) {
                this.wait();
            }
            this.writingWriters++;
        } finally {
            this.waitingWriters--;
        }
    }

    public synchronized void writerUnLock() {
        this.writingWriters--;
        this.notifyAll();
    }
}
