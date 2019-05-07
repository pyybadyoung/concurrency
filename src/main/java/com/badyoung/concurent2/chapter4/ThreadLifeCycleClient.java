package com.badyoung.concurent2.chapter4;

import java.util.Arrays;

public class ThreadLifeCycleClient {
    public static void main(String[] args) {
        new ThreadLifeCycleObserver().concueerntQuery(Arrays.asList("1","2"));
    }
}
