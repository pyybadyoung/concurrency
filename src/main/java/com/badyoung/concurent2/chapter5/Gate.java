package com.badyoung.concurent2.chapter5;

/***
 * single Thread execution design pattern
 *
 * 共享资源
 *
 */
public class Gate {
     private int counter = 0;
     private String name = "Nobody";
     private String address="Nowhere";

    /**
     * 在多个线程在进行读和读的操作的时候会造成并行修改的错误
     *
     * 如果加了锁可以避免并行修改，但是串行化以后性能降低
     *
     * 临界值
     * @param name
     * @param address
     */
    public synchronized void pass(String name, String address) {
        this.counter++;
        //竞争
        this.name = name;
        this.address = address;
        verify();
    }

    /**
     * 一个线程进来，另一个线程也可能会同时进来
     */
    private void verify() {
        if (this.name.charAt(0)!=this.address.charAt(0)){
            System.out.println("***********BROKEN************"+toString());
        }
    }

    public synchronized String toString(){
        return "No. " +counter+":"+name+","+address;
    }
}
