package com.badyoung.concurent2.chapet1;

public class SingletonObject5 {

    /**
     * volatile保证内存的可见性，有序性
     *
     *
     * 单例
     * 性能好
     * 懒加载
     */
    private static volatile SingletonObject5 instance;

    private SingletonObject5(){

    }

    /**
     * 会串行化
     * @return
     */
    public  static SingletonObject5 getInstance(){
        /**
         * 多线程下实现了懒加载
         * 降低了串行化，提高了性能
         *
         *
         * 可能会引起空指针异常
         */
        if (instance==null){
            synchronized (SingletonObject5.class){
                if (null==instance){
                    instance = new SingletonObject5();
                }
            }
        }
        /**
         * 导致出现空指针异常的原因
         * 第一个线程创建实例，还没有创建好，第二个线程就跑进来拿实例，结果实例是个空的
         */
        return SingletonObject5.instance;
    }
}
