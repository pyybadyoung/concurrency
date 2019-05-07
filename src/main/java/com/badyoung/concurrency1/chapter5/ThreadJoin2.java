package com.badyoung.concurrency1.chapter5;

import java.util.Optional;
import java.util.stream.IntStream;

public class ThreadJoin2 {

	public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(()->{
			try {
				System.out.println("t1 is running");
				Thread.sleep(10000);
				System.out.println("t1 is done");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		t1.start();
		//待他t1线程执行完毕以后再执行其他的线程
		t1.join(100,10);

		Optional.of("All of finish done").ifPresent(System.out::println);

		IntStream.range(1,1000)
				.forEach(i-> System.out.println(Thread.currentThread().getName()+"=" +i));
	}

}
