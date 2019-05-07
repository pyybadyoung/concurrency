package com.badyoung.concurent2.chapter7;

public class UsePersonThread extends Thread{

    private Person person;

    public UsePersonThread(Person person) {
        this.person = person;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" print "+person.toString());
    }
}
