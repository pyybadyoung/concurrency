package com.badyoung.concurrency1.chapter6;

/**
 * 通过开关的方式将线程停止
 */
public class ThreadCloseGraceful {

    private static class Worker extends Thread{

        private volatile boolean start = true;

        @Override
        public void run() {
            while (start){

            }
        }

        public void shutDown(){
            this.start=false;
        }

        public static void main(String[] args) {
            Worker worker = new Worker();
            worker.start();
            try {
                Thread.sleep(100);
                worker.shutDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
