package com.badyoung.concurent2.chapter3;

public class VolatileTest2 {

    private  volatile static int INIT_VALUE = 0;

    private final static int MAX_LIMIT = 100;

    public static void main(String[] args) {

        new Thread(()->{
            while (INIT_VALUE<MAX_LIMIT){
                    System.out.println("T1--->> "+(++INIT_VALUE));
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"ADDER-1").start();

        new Thread(()->{
            while (INIT_VALUE<MAX_LIMIT){
                    System.out.println("T2--->> "+(++INIT_VALUE));
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"ADDER-2").start();
    }
}
