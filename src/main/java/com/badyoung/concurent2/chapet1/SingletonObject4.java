package com.badyoung.concurent2.chapet1;

public class SingletonObject4 {

    private static SingletonObject4 instance;

    private SingletonObject4(){

    }

    /**
     * 会串行化
     * @return
     */
    public  static SingletonObject4 getInstance(){
        /**
         * 多线程下实现了懒加载
         * 降低了串行化，提高了性能
         *
         *
         * 可能会引起空指针异常
         */
        if (instance==null){
            synchronized (SingletonObject4.class){
                if (null==instance){
                    instance = new SingletonObject4();
                }
            }
        }
        /**
         * 导致出现空指针异常的原因
         * 第一个线程创建实例，还没有创建好，第二个线程就跑进来拿实例，结果实例是个空的
         */
        return SingletonObject4.instance;
    }
}
