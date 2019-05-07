package com.badyoung.concurrency1.chapter6;

public class ThreadService {

    private Thread  excuteThread;

    private boolean finshed = false;

    public void excute(Runnable task){

        excuteThread=new Thread(){
            @Override
            public void run() {
                Thread runner = new Thread(task);
                runner.setDaemon(true);
                runner.start();
                try {
                    runner.join();
                    finshed = true ;
                } catch (InterruptedException e) {
//                    e.printStackTrace();
                }
            }
        };
        excuteThread.start();
    }

    public void shutdown(Long mills){

        long currentTime = System.currentTimeMillis();
        while (!finshed){
            if (System.currentTimeMillis()-currentTime>=mills){
                System.out.println("任务超时，请结束它");
                excuteThread.interrupt();
                break;
            }
            /**
             * 如果没有超时，然后任务又没有结束
             */
            try {
                excuteThread.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("执行线程被打断");
                break;
            }
        }
        finshed = false;
    }
}
