package com.badyoung.concurrency1.chapter4;

public class  DaemonThread {
	
	public static void main(String[] args) {
		
	Thread t = new Thread() {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				System.out.println(Thread.currentThread().getName()+"running");
				Thread.sleep(10000);
				System.out.println(Thread.currentThread().getName()+"done");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName());
		}
	};	
	
//	t.setDaemon(true);
	t.start();
	}

}
