package com.badyoung.concurrency1.chapter2;


/**
 * 策略模式
 * @author Administrator
 *
 */
@FunctionalInterface
public interface CalculatorStrategy {

	double calculate(double salary,double bonus);
}
