package com.badyoung.concurent2.chapet1;

import java.util.stream.IntStream;

public class SingletonObject7 {

    private SingletonObject7(){

    }

    private enum Singleton{
        INSTANCE;

        private final SingletonObject7 instance;

        //枚举类型是线程安全的，构造器只会被装载一次
        Singleton() {
            instance = new SingletonObject7();
        }

        public SingletonObject7 getInstance(){
            return instance;
        }
    }

    public static SingletonObject7 getInstance(){
        return Singleton.INSTANCE.getInstance();
    }

    public static void main(String[] args) {
        IntStream.rangeClosed(1,100).forEach(i->new Thread(String.valueOf(i)){
            @Override
            public void run() {
                System.out.println(SingletonObject7.getInstance());
            }
        }.start());
    }
}
