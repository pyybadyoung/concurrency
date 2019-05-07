package com.badyoung.concurrency1.chapter2;

public class TicketWindow<st> extends Thread{

	//static  只实例化一次
	private static final int MAX = 50;
	private  static int index = 1 ;
	
	private String name;
	
	

	public TicketWindow(String name) {
		this.name = name;
	}



	@Override
	public void run() {
		
		while (index<=MAX) {
			System.out.println("柜台的名字："+name+" ;  当前的号码是："+index++);
		}
	}
}
