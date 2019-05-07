package com.badyoung.concurrency1.chapter2;

public class TicketWindowRunable implements Runnable{
	
	private static final int MAX = 50;
	private  int index = 1 ;

	public void run() {
		// TODO Auto-generated method stub
		while (index<=MAX) {
			System.out.println(Thread.currentThread()+"当前的号码是："+index++);
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
