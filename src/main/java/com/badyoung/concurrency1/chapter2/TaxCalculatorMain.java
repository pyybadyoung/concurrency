package com.badyoung.concurrency1.chapter2;

public class TaxCalculatorMain {

	public static void main(String[] args) {
		/*TaxCalaculator taxCalaculator = new TaxCalaculator(10000d, 2000d) {
			
			@Override
			public double calcTax() {
			
				return getSalary()*0.1+getBonus()*0.15;
				
			}
		};
		double calcuate = taxCalaculator.calcuate();
		System.out.println(calcuate);*/
		
		//##################   策略模式     ############################
		TaxCalaculator taxCalaculator = new TaxCalaculator(10000d, 2000d);
		CalculatorStrategyImpl calculatorStrategyImpl = new CalculatorStrategyImpl();
		taxCalaculator.setCalculatorStrategy(calculatorStrategyImpl);
		
		double calcuate = taxCalaculator.calcuate();
		System.out.println(calcuate);

		//##################   策略模式    LAMBDA表达式 ############################
		TaxCalaculator taxCalaculator1 = new TaxCalaculator(10000d, 2000d);
		taxCalaculator.setCalculatorStrategy((s,b)->s*0.1+b*0.2);
		System.out.println(calcuate);


		//##################   策略模式    LAMBDA表达式 ############################
//		TaxCalaculator taxCalaculator2 = new TaxCalculatorMain(10000d,2000d,(s, b)-> s*0.1+b*0.2);
	}
}
