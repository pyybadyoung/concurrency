package com.badyoung.concurent2.chapet1;

public class SingletonObject2 {

    private static SingletonObject2 instance;

    private SingletonObject2(){

    }

    public static SingletonObject2 getInstance(){
        //在多线程情况下可能产生的对象不是单例的
        if (instance==null){
            instance = new SingletonObject2();
        }
        return SingletonObject2.instance;
    }
}
