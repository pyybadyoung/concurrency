package com.badyoung.concurent2.chapter8;

public interface Future<T> {
    T get() throws InterruptedException;
}
