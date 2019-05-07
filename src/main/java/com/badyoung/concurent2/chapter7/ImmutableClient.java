package com.badyoung.concurent2.chapter7;

import java.util.stream.IntStream;

public class ImmutableClient {
    public static void main(String[] args) {
        Person person = new Person("young","AnHui");
        IntStream.rangeClosed(0,5).forEach(i->{
            new UsePersonThread(person).start();
        });
    }
}
