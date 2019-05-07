package com.badyoung.concurent2.chapter6;

public class SharedData {

    //共享的资源数据
    private final char[] buffer;
    //锁
    private final ReadWriteLock lock = new ReadWriteLock();

    //共享的资源对象
    public SharedData(int size) {
        this.buffer = new char[size];
        for (int i = 0; i < buffer.length; i++) {
          buffer[i]='*';

        }
    }

    //读操作
    public char[] read() throws InterruptedException {
        try {
            lock.readLock();
            return this.doRead();
        }finally {
            lock.readUnLock();
        }
    }

    //写操作
    public void write(char c) throws InterruptedException {
        try {
            lock.writeLock();
            this.doWrite(c);
        }finally {
            lock.writerUnLock();
        }
    }

    //写的方法
    private void doWrite(char c) {

        for (int i = 0; i < buffer.length; i++) {
             buffer[i]=c;
            slowly(10);
        }
    }

    //读的方法
    private char[] doRead() {
        char[] newBuf = new char[buffer.length];
        for (int i = 0; i < buffer.length; i++) {
            newBuf[i]=buffer[i];
        }
        slowly(50);
        return newBuf;
    }

    //休眠操作
    private void slowly(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
