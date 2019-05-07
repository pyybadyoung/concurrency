package com.badyoung.concurrency1.chapter10;

import java.util.Collection;

public interface Lock {

    class  TimeOutException extends Exception{
        public TimeOutException(String message){
            super(message);
        }
    }

    //允许中断
    void lock() throws InterruptedException;

    //隔多长时间中断
    void lock(long time) throws InterruptedException,TimeOutException;

    void unLuck();

    Collection<Thread> getBlockedThread();

    int getBlockedSize();
}
