package com.badyoung.concurrency1.chapter6;

/**
 * 让线程强制停止
 * 如何暴力的结束一个线程
 */
public class ThreadCloseForce {

        public static void main(String[] args) {

            long start = System.currentTimeMillis();
            ThreadService service = new ThreadService();
            service.excute(() ->{
                while (true){

                }
            });
            /**
             * 在指定的时间内，任务没有执行完毕
             */
            service.shutdown((long) 1000);
            long end = System.currentTimeMillis();
            System.out.println(end-start);


        }

}
