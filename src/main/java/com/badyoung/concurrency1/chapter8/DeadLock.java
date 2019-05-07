package com.badyoung.concurrency1.chapter8;

public class DeadLock {

    private OtherService otherService;

    public DeadLock(OtherService otherService) {
        this.otherService = otherService;
    }

    private final  Object LOCK = new Object();

    public void m1(){
        synchronized (LOCK){
            System.out.println("m1");
            otherService.s1();
        }
    }

    public void m2(){
        synchronized (LOCK){
            System.out.println("m2");
        }
    }


}
