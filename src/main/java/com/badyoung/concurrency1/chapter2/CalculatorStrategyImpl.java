package com.badyoung.concurrency1.chapter2;

/**
 * 策略实现
 */
public class CalculatorStrategyImpl implements CalculatorStrategy{
	
	private final static double SALARY_RATE=0.1;
	
	private final static double BONUS_RATE=0.15;

	
	@Override
	public double calculate(double salary, double bonus) {
		// TODO Auto-generated method stub
		return salary*SALARY_RATE + bonus*BONUS_RATE;
	}

}
