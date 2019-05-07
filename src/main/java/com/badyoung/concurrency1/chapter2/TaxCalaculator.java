package com.badyoung.concurrency1.chapter2;


/**
 * Thread类和Runable接口所引起的java中的设计模式问题
 *策略接口
 * @author Administrator
 */

public class TaxCalaculator {

    private final double salary;

    private final double bonus;


    private CalculatorStrategy calculatorStrategy;


    public TaxCalaculator(double salary, double bonus, CalculatorStrategy calculatorStrategy) {
        this.salary = salary;
        this.bonus = bonus;
        this.calculatorStrategy = calculatorStrategy;
    }

    public CalculatorStrategy getCalculatorStrategy() {
        return calculatorStrategy;
    }


    public void setCalculatorStrategy(CalculatorStrategy calculatorStrategy) {
        this.calculatorStrategy = calculatorStrategy;
    }


    public double getSalary() {
        return salary;
    }


    public double getBonus() {
        return bonus;
    }


    public TaxCalaculator(double salary, double bonus) {
        this.salary = salary;
        this.bonus = bonus;
    }


    protected double calcTax() {

        return calculatorStrategy.calculate(salary, bonus);
    }

    public double calcuate() {
        return this.calcTax();

    }
}
