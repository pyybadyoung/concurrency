package com.badyoung.concurent2.chapter4_obsever;

import java.util.ArrayList;
import java.util.List;

/**
 * 观察者设计模式
 */

public class Subject {

    private List<Observer> observers = new ArrayList<>();

    private int state;
    public int getState(){
        return this.state;
    }

    public void setState(int state){
        if (state==this.state){
            return;
        }
        this.state=state;
        notifyAllObserver();
    }

    public void attach(Observer observer){
        observers.add(observer);
    }

    private void notifyAllObserver(){
        observers.stream().forEach(Observer::update);
    }
}
