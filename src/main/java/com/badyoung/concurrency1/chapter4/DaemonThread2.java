package com.badyoung.concurrency1.chapter4;

/**
 * 守护线程
 * @author Administrator
 *
 */

public class DaemonThread2 {
	
	public static void main(String[] args) {
		Thread t = new Thread(() -> {
			Thread thread = new Thread(() -> { 
				try {
					while(true) {
						int i = 0;
						System.out.println("thread  "+ ++i);
						Thread.sleep(5000);
					}
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
				}
			});
			//守护线程
			//如果不把子线程设置成守护线程，那么子线程在父线程死掉以后还是会执行
//			thread.setDaemon(true);
			thread.start();
			
			try {
				Thread.sleep(1000);
				System.out.println("t线程死掉，thread线程也跟着死掉？？？？");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}) {
			
		};
		t.start();
	}
}
