package com.badyoung.concurent2.chapter5;

public class User extends Thread {
    private String myName;
    private String myAddress;
    private Gate gate;

    public User(String myName,String myAddress,Gate gate){
        this.myAddress=myAddress;
        this.myName=myName;
        this.gate=gate;
    }

    @Override
    public void run() {
        System.out.println(myName+ " Begin");
        while (true){
            this.gate.pass(myName,myAddress);
        }
    }
}
