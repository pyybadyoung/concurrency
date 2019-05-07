package com.badyoung.concurrency1.chapter6;

/**
 * 通过开关的方式将线程停止
 */
public class ThreadCloseGraceful1 {

    private static class Worker extends Thread{

        @Override
        public void run() {
         while (true){
             try {
                 Thread.sleep(1);
             } catch (InterruptedException e) {
                 e.printStackTrace();
                 break;
             }
         }
        }


        public static void main(String[] args) {
            Worker worker = new Worker();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            worker.interrupt();
        }
    }
}
