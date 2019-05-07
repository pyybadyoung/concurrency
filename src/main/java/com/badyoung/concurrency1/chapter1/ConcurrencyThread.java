package com.badyoung.concurrency1.chapter1;

import java.util.concurrent.TimeUnit;

public class ConcurrencyThread {

	public static void main(String[] args) {
		new Thread("bad-young") {
			@Override
			public void run() {
				browseNews();
			}
		}.start();
		listenMusic();
	}
	
	
	
	private static void browseNews() {
		for (int i = 0; i < 30; i++) {
			System.out.println(i+"::我在看新闻");
			sleep(1);
		}
	}
	
	private static void listenMusic() {
		for (int i = 0; i < 30; i++) {
			System.out.println(i+"::我在听音乐");
			sleep(1);
		}
	}
	
	
	public static void sleep(int seconds) {
		try {
			TimeUnit.SECONDS.sleep(seconds);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}



