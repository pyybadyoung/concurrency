package com.badyoung.concurrency1.chapter10;

import java.util.Optional;
import java.util.stream.Stream;

public class LockTest2 {

    public static void main(String[] args) throws InterruptedException {

        final BooleanLock booleanLock = new BooleanLock();
        Stream.of("T1","T2","T3","T4").forEach(t->
                new Thread(()->{
                    try {
                        booleanLock.lock(100L);
                        Optional.of(Thread.currentThread().getName()+" get booleanLock")
                            .ifPresent(System.out::println);
                        work();
                    } catch (InterruptedException e) {
                            e.printStackTrace();
                    }catch (Lock.TimeOutException e){
                       Optional.of(Thread.currentThread().getName()+" time out ")
                               .ifPresent(System.out::println);
                    }  finally{
                        booleanLock.unLuck();
                    }
                },t).start());

        /**
         * 其他的线程可以释放别的线程的锁
         */
        Thread.sleep(100);
        booleanLock.unLuck();
    }

    public static void work() throws InterruptedException {
        Optional.of(Thread.currentThread().getName()+" is working ...")
                .ifPresent(System.out::println);
        Thread.sleep(1000);
    }
}
