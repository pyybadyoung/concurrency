package com.badyoung.concurrency1.chapter9;
/**
 * 功能类似于线程池
 */

import java.util.*;

public class CaptureService {

    private static final LinkedList<Control> CONTROLS = new LinkedList<>();

    private static final int MAX_WORKER = 5;

    public static void main(String[] args) {

        List<Thread> worker = new ArrayList<>();
        Arrays.asList("M1","M2","M3","M4","M5","M6","M7","M8","M9","M10").stream()
            .map(CaptureService::createCaptureThread)
            .forEach(thread -> {
                thread.start();
                worker.add(thread);
                    }
            );
        worker.stream().forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Optional.of("All of capture work finshed").ifPresent(System.out::println);
    }

    private static Thread createCaptureThread(String name){
        return new Thread(()->{
            Optional.of("The work ["+Thread.currentThread().getName()+"] begin capture data")
                    .ifPresent(System.out::println);
            synchronized (CONTROLS){
                while (CONTROLS.size()>MAX_WORKER){
                    try {
                        CONTROLS.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                CONTROLS.addLast(new Control());
            }

            Optional.of("The work ["+Thread.currentThread().getName()+"] is working")
                    .ifPresent(System.out::println);
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (CONTROLS){
                Optional.of("The work ["+Thread.currentThread().getName()+"] end capture data")
                        .ifPresent(System.out::println);
                CONTROLS.removeFirst();
                CONTROLS.notifyAll();
            }
        },name);
    }

    private static class Control{

    }
}
