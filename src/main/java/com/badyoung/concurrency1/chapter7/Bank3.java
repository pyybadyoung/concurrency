package com.badyoung.concurrency1.chapter7;

public class Bank3 {

	public static void main(String[] args) {

		/**
		 * 把要操作的资源与线程分隔开 可以对统一资源进行处理
		 */
		final SynchornizedRunable synchornizedRunable = new SynchornizedRunable();

		Thread thread1 = new Thread(synchornizedRunable, "一号窗口");
		Thread thread2 = new Thread(synchornizedRunable, "二号窗口");
		Thread thread3 = new Thread(synchornizedRunable, "三号窗口");
		thread1.start();
		thread2.start();
		thread3.start();
	}
}
