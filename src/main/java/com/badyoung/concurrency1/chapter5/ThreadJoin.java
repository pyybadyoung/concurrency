package com.badyoung.concurrency1.chapter5;

import java.util.stream.IntStream;

public class ThreadJoin {

	public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(()->{
            IntStream.range(1,1000).forEach(i-> System.out.println(Thread.currentThread().getName()+"=" +i));
        });

		t1.start();
		//待他t1线程执行完毕以后再执行其他的线程
		t1.join();

		IntStream.range(1,1000)
				.forEach(i-> System.out.println(Thread.currentThread().getName()+"=" +i));
	}
}
