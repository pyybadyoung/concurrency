package com.badyoung.concurrency1.chapter10;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;


public class BooleanLock implements Lock {

    private boolean initValue;

    public BooleanLock() {
    }

    public BooleanLock(boolean initValue) {
        this.initValue = false;
    }

    private Collection<Thread> blockedThreadCollection = new ArrayList<>();

    private Thread currentThread;


    @Override
    public synchronized void lock() throws InterruptedException {
        while (initValue){
                this.wait();
        }
        blockedThreadCollection.remove(Thread.currentThread());
        this.initValue=true;
        //在得到锁的时候记住拿到锁的线程
        this.currentThread=Thread.currentThread();
    }

    @Override
    public synchronized void lock(long time) throws InterruptedException, TimeOutException {

        if (time<=0){
            lock();
        }
        long hasRemaining = time;
        long endTime = System.currentTimeMillis() + hasRemaining;
        while (initValue){
            if (time<=0){
                throw  new TimeOutException("time out");
            }
            blockedThreadCollection.remove(Thread.currentThread());
            this.wait(time);
            hasRemaining =  endTime - System.currentTimeMillis();
            System.out.println(hasRemaining);
        }

        this.initValue=true;
        //在得到锁的时候记住拿到锁的线程
        this.currentThread=Thread.currentThread();
    }

    @Override
    public synchronized void unLuck() {
        //只有拿到锁的线程才可以释放锁
        if (Thread.currentThread()==currentThread){
            this.initValue=false;
            System.out.println(Thread.currentThread() +" release the lock monitor");
            this.notifyAll();
        }
    }

    @Override
    public Collection<Thread> getBlockedThread() {
        return Collections.unmodifiableCollection(blockedThreadCollection);
    }

    @Override
    public int getBlockedSize() {
        return blockedThreadCollection.size();
    }
}
