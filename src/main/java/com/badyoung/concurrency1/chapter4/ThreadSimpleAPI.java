package com.badyoung.concurrency1.chapter4;

public class ThreadSimpleAPI {

	public static void main(String[] args) {
		Thread t = new Thread();
		System.out.println(t.getName());
		System.out.println(t.getId());
		System.out.println(t.getPriority());
		System.out.println(t.countStackFrames());
	}
}
