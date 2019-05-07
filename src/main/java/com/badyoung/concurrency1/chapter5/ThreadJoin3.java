package com.badyoung.concurrency1.chapter5;

public class ThreadJoin3 {

	public static void main(String[] args) throws InterruptedException {

		//等待当前线程结束
		Thread.currentThread().join();

	}
}
