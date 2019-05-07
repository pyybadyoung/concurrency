package com.badyoung.concurent2.chapter3;

public class VolatileTest {

    private volatile static int INIT_VALUE = 0;

    private final static int MAX_LIMIT = 100;

    public static void main(String[] args) {
        //读数据的线程
        new Thread(()->{
            int localValue = INIT_VALUE;
            while (localValue<MAX_LIMIT){
                if(localValue != INIT_VALUE){
                    System.out.printf("the value update to [%d]\n",INIT_VALUE);
                    localValue = INIT_VALUE;
                }
            }

        },"READER").start();

        //读线程
        new Thread(()->{
            int localValue = INIT_VALUE;
            while (INIT_VALUE<MAX_LIMIT){
                System.out.printf("update the value to [%d]\n",++localValue);
                INIT_VALUE=localValue;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        },"UPDATE").start();
    }
}
