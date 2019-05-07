package com.badyoung.concurrency1.chapter4;

public class ThreadSimpleAPI2 {

	public static void main(String[] args) {
		Thread t1 = new Thread() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				for (int i = 0; i < 30 ; i++) {
					System.out.println(Thread.currentThread().getName()+"==>"+i);
				}
			}
		};
		//设置线程的优先级
		t1.setPriority(Thread.MAX_PRIORITY); 
		
		
		Thread t2 = new Thread() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				for (int i = 0; i < 30 ; i++) {
					System.out.println(Thread.currentThread().getName()+"==>"+i);
				}
			}
		};
		//设置线程的优先级
		t2.setPriority(Thread.NORM_PRIORITY); 
		
		Thread t3 = new Thread() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				for (int i = 0; i < 30 ; i++) {
					System.out.println(Thread.currentThread().getName()+"==>"+i);
				}
			}
		};
		//设置线程的优先级
		t3.setPriority(Thread.MIN_PRIORITY); 
		
		t1.start();
		t2.start();
		t3.start();
	}
}
