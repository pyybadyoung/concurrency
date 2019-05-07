package com.badyoung.concurrency1.chapter1;

public class TemplateMethod {

	/**
	 * 模板设计模式
	 * @param message
	 */
	
	public final void pring(String message) {
		System.out.println("###################################");
		wrapPrint(message);
		System.out.println("###################################");
	}
	
	protected void wrapPrint(String message) {
		
	}
	
//###########模板设计模式##############	
	
	
	
	
	public static void main(String[] args) {
		TemplateMethod t1 = new TemplateMethod() {
			
			@Override
			protected void wrapPrint(String message) {
				System.out.println("="+message+"=");
			}
		};
		
		t1.pring("young");
		
	}
}
