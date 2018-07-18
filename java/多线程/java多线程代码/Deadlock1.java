package org.test.muti.thread;

/**
 * Business.java This class is used to illustrate a deadlock situtation.
 * 
 * @author www.codejava.net
 */
class Business {

    private Object lock1 = new Object();
    private Object lock2 = new Object();

    public void bar() {
        synchronized (lock2) {
            synchronized (lock1) {
                System.out.println("bar");
            }
        }
    }

    public void foo() {
        synchronized (lock1) {
            synchronized (lock2) {
                System.out.println("foo");
            }
        }
    }
}

public class Deadlock1 {
    public static void main(String[] args) {
        Business business = new Business();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                business.foo();
            }
        });

        t1.start();

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                business.bar();
            }
        });

        t2.start();
    }
}