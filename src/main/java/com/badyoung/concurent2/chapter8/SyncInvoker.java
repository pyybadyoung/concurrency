package com.badyoung.concurent2.chapter8;
/**
 * Future----------代表的是未来的一个凭据
 * FutureTask------将你的调用逻辑进行了隔离
 * FutureService---桥接，将Future和FutureTask
 */

import org.junit.Test;

/**
 * 同步调用
 */
public class SyncInvoker {
    public static void main(String[] args) throws InterruptedException {
        String result = get();
        System.out.println(result);
    }

    @Test
    public void testFutureService() throws InterruptedException {
        FutureService futureService = new FutureService();
        Future<String> future = futureService.submit(() -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "FINISH";
        });
        System.out.println("========================");
        System.out.println("我要干其他事情");
        System.out.println("========================");
        System.out.println(future.get());
    }

    private static String get() throws InterruptedException {
        Thread.sleep(10000);
        return "FINISH";
    }
}
