package com.badyoung.concurent2.chapet1;

public class SingletonObject1 {

    /**
     * 不能够进行懒加载，在不需要的时候也会简历对象，占用资源
     */
    private static final SingletonObject1 instance = new SingletonObject1();

    private SingletonObject1(){

    }

    public static SingletonObject1 getInstance(){
        return instance;
    }
}
