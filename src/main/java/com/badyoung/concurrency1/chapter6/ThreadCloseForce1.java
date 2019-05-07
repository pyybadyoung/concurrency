package com.badyoung.concurrency1.chapter6;

/**
 * 让线程强制停止
 * 如何暴力的结束一个线程
 */
public class ThreadCloseForce1 {

        public static void main(String[] args) {

            long start = System.currentTimeMillis();
            ThreadService service = new ThreadService();
            service.excute(() ->{
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            /**
             * 还没有到指定的时间任务就完成了
             * 但不会傻傻的等到指定时间才会将任务结束
             */
            service.shutdown(10000L);
            long end = System.currentTimeMillis();
            System.out.println(end-start);


        }

}
