package com.badyoung.concurrency1.chapter7;

public class TicketWindowRunable implements Runnable{
	
	private static final int MAX = 500;
	private  int index = 1 ;

	private final Object MONITOR = new Object();

	public void run() {
		// TODO Auto-generated method stub
		synchronized (MONITOR){
			while (true) {
				if (index>MAX){
					break;
				}
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread()+"当前的号码是："+index++);

			}


		}
	}

}
