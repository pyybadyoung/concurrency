package com.badyoung.concurrency1.chapter7;

public class SynchornizedRunable implements Runnable{
	
	private static final int MAX = 100;
	private  int index = 1 ;

	private final Object MONITOR = new Object();

	/**
	 * 同步方法----此处有弊端
	 * 谁先抢到这个锁，执行完方法以后index就大于max了
	 * 下一个拿到此方法的因条件不满足就直接退出了
	 */
	public synchronized  void run() {
		// TODO Auto-generated method stub

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
