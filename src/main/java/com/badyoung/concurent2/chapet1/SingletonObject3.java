package com.badyoung.concurent2.chapet1;

public class SingletonObject3 {

    private static SingletonObject3 instance;

    private SingletonObject3(){

    }

    /**
     * 会串行化
     * 性能下降
     * @return
     */
    public synchronized static SingletonObject3 getInstance(){
        //在多线程情况下可能产生的对象不是单例的
        if (instance==null){
            instance = new SingletonObject3();
        }
        return SingletonObject3.instance;
    }
}
