package com.badyoung.concurent2.chapter4_obsever;

public class ObserverClient {

    public static void main(String[] args) {
        final Subject subject = new Subject();
        BinaryObserver binaryObserver = new BinaryObserver(subject);
        OctalObserver octalObserver = new OctalObserver(subject);
        System.out.println("======================================");
        subject.setState(10);
        System.out.println("======================================");
        subject.setState(10);
        System.out.println("======================================");
        subject.setState(15);
    }
}
