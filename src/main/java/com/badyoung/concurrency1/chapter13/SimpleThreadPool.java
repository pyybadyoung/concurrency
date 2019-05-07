package com.badyoung.concurrency1.chapter13;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 * 自定义简单的线程池
 */
public class SimpleThreadPool extends Thread{

    //线程池的大小
    private int size;

    private final int queueSize;

    //默认的任务数量
    private final static int DEFAULT_TASK_QUEUE_SIZE = 2000;
    //线程自增
    private static volatile int seq = 0;

    //线程名字的前缀
    private final static String THREAD_PERFIX = "THREAD_SIMPLE_POOL-";

    private final static ThreadGroup GROUP = new ThreadGroup("Pool-Group");

    //任务队列
    private final static LinkedList<Runnable> TASK_QUEUE = new LinkedList<>();

    //存放任务的集合
    private final static List<WorkTask> THREAD_QUEUE = new ArrayList<>();
    //默认的拒绝策略
    private final DiscardPolicy discardPolicy;

    public final static DiscardPolicy DEFAULT_DISCARD_POLICY = ()->{
        throw new DiscardException("Discard This Task");
    };

    private volatile boolean destroy = false;

    private int min;
    private int max;
    private int active;

    /**
     * 按照默认的线程数量创建线程池
     */
    public SimpleThreadPool() {
        this(4,8,12,DEFAULT_TASK_QUEUE_SIZE,DEFAULT_DISCARD_POLICY);
    }

    /**
     * 指定线程的数量创建线程池
     *
     */
    public SimpleThreadPool(int min,int active,int max,int queueSize,DiscardPolicy discardPolicy) {
        this.min = min;
        this.active=active;
        this.max=max;
        this.queueSize=queueSize;
        this.discardPolicy=discardPolicy;
        init();
    }

    /**
     * 初始化线程池
     */
    private void init() {
        for (int i = 0; i < this.min; i++) {
            createWorkTask();
        }
        this.size=min;
        this.start();
    }

    public void submit(Runnable runnable) {
        if (destroy){
            throw new IllegalStateException("the thread pool already destroy and not allow submit task");
        }
        synchronized (TASK_QUEUE) {
            if (TASK_QUEUE.size()>queueSize){
                discardPolicy.discard();
            }
            TASK_QUEUE.addLast(runnable);
            TASK_QUEUE.notifyAll();
        }
    }

    @Override
    public void run() {
        while (!destroy){
            System.out.printf("Pool#Min:%d,Active:%d,Max:%d,Current:%d,QueueSize:%d\n",
                    this.min,this.active,this.max,this.size,TASK_QUEUE.size());
            try {
                Thread.sleep(5000);
                if (TASK_QUEUE.size()>active&&size<active){
                    for (int i = size;i<active;i++){
                        createWorkTask();
                    }
                    System.out.println("The pool incremented to active");
                    size=active;
                }else if (TASK_QUEUE.size()>max&&size<max){
                    for (int i = size;i<max;i++){
                        createWorkTask();
                    }
                    System.out.println("The pool incremented to max");
                    size=max;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 提交任务
     */
    private void createWorkTask() {
        WorkTask task = new WorkTask(GROUP, THREAD_PERFIX + (seq++));
        task.start();
        THREAD_QUEUE.add(task);
    }

    /**
     * 关闭线程池
     */
    public void shutDown() throws InterruptedException {
        //如果任务列表不是空的就继续让线程执行任务，不进行线程打断
        while (!TASK_QUEUE.isEmpty()){
            Thread.sleep(50);
        }
        //如果任务队列不是空的，就看有多少个线程活着
        int initVal = THREAD_QUEUE.size();
        while (initVal>0){
            for (WorkTask task : THREAD_QUEUE) {
                //如果线程是阻塞的，就打断这个线程
                if (task.getTaskState()==TaskState.BLOCKED){
                    //线程打断以后就让该线程挂掉
                    task.interrupt();
                    task.close();
                    initVal--;
                }else {
                    Thread.sleep(50);
                }
            }
        }
        this.destroy=true;
        System.out.println(" the thread is disposed");
    }

    public int getSize() {
        return size;
    }

    public int getQueueSize() {
        return queueSize;
    }

    public boolean isDestroy(){
        return this.destroy;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public int getActive() {
        return active;
    }

    //定义线程的状态
    private enum TaskState {
        FREE, RUNNING, BLOCKED, DEAD
    }

    //定义拒绝时的异常信息
    public static class DiscardException extends RuntimeException{
        public DiscardException(String message){
            super(message);
        }
    }

    //定义拒绝策略
    public interface DiscardPolicy{
        void discard() throws DiscardException;
    }


    //自定义自己所需要的线程
    private static class WorkTask extends Thread {

        private volatile TaskState taskState = TaskState.FREE;

        public WorkTask(ThreadGroup group, String name) {
            super(group, name);
        }

        public TaskState getTaskState() {
            return this.taskState;
        }

        /**
         * 关闭线程池
         */
        public void close() {
            this.taskState = TaskState.DEAD;
        }

        @Override
        public void run() {
            OUTER:
            while (this.taskState != TaskState.DEAD) {
                Runnable runnable;
                synchronized (TASK_QUEUE) {
                    while (TASK_QUEUE.isEmpty()) {
                        try {
                            taskState = TaskState.BLOCKED;
                            TASK_QUEUE.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            break OUTER;
                        }
                    }
                    runnable = TASK_QUEUE.removeFirst();

                }
                if (runnable != null) {
                    taskState = TaskState.RUNNING;
                    runnable.run();
                    taskState = TaskState.FREE;
                }
            }
        }

        public static void main(String[] args) {

            SimpleThreadPool simpleThreadPool1 = new SimpleThreadPool();

            for (int i = 0; i < 40; i++) {
                simpleThreadPool1.submit(() -> {
                    System.out.println("the runable   be serviced by  " + Thread.currentThread().getName() + " start");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("the runable   be serviced by  " + Thread.currentThread().getName() + " end");
                });
                System.out.println("======================================");
            }

        }
    }
}
