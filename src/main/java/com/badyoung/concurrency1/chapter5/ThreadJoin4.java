package com.badyoung.concurrency1.chapter5;

/**\
 * 模拟数据采集----------thread.join
 */
public class ThreadJoin4 {
    public static void main(String[] args) throws InterruptedException {
        long beginTime = System.currentTimeMillis();
        Thread t1 = new Thread(new CaptureRunable("M1",10000L));
        Thread t2 = new Thread(new CaptureRunable("M2",20000L));
        Thread t3 = new Thread(new CaptureRunable("M3",15000L));
        t1.start();
        t2.start();
        t3.start();
        //等待线程执行结束后再自行其他的线程
        t1.join();
        t2.join();
        t3.join();
        long endTime = System.currentTimeMillis();
        System.out.println("采集开始时间："+beginTime+"   采集结束时间："+endTime);
        Long time = endTime - beginTime;
        System.out.println("数据采集需要的时间："+time);
    }
}


class CaptureRunable implements Runnable{

    private String machineName;
    private Long spendTime;

    public CaptureRunable(String machineName, Long spendTime) {
        this.machineName = machineName;
        this.spendTime = spendTime;
    }

    private  String getResult(){
        return machineName + " finsh.";
   }

    @Override
    public void run() {

        try {
            Thread.sleep(spendTime);
            System.out.println(machineName+"数据采集完成");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
