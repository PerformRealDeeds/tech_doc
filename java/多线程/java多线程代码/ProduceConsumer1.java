package org.test.muti.thread;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

class Consumer1 implements Runnable {
    private String threadName;
    private int intervalSecond;
    private Pool pool;

    public Consumer1(String threadName, int intervalSecond, Pool pool) {
        this.threadName = threadName;
        this.intervalSecond = intervalSecond;
        this.pool = pool;

    }

    @Override
    public void run() {
        Thread.currentThread().setName(this.threadName);

        while (true) {
            String msg = pool.take();
            try {
                TimeUnit.SECONDS.sleep(intervalSecond);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}

class Pool {
    private static int CAPACITY = 5;

    private static void threadMessage(String msg) {
        System.out.format("%s:%s %n", Thread.currentThread().getName(), msg);
    }

    private Queue<String> queue = new ArrayDeque<>(CAPACITY);

    public boolean isEmpty() {
        return queue.size() <= 0;
    }

    public boolean isFull() {
        return queue.size() >= CAPACITY;
    }

    public synchronized void put(String msg) {
        while (isFull()) {
            try {
                threadMessage("池子已满,开始等待");
                wait();// synchronized是内部对象锁, wait会阻塞当前线程并释放内部锁(监视器)
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        queue.add(msg);
        threadMessage(msg + "已加入池子" + " [" + queue.size() + "/" + "12] " + Arrays.toString(queue.toArray()));
        // 通知其它线程
        notifyAll();
    }

    public synchronized String take() {
        while (isEmpty()) {
            try {
                threadMessage("池子空了,开始等待");
                wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        String msg = queue.poll();
        threadMessage("消费" + msg + " [" + queue.size() + "/" + "12]" + Arrays.toString(queue.toArray()));
        notifyAll();
        return msg;
    }

}

public class ProduceConsumer1 {
    public static void main(String[] args) {
        Pool pool = new Pool();

        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(5, 5, 6000, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(5));
        threadPool.execute(new Producer1("生产者1号", 1, pool));
        threadPool.execute(new Consumer1("消费者1号", 2, pool));
    }
}

class Producer1 implements Runnable {

    private String threadName;
    private int intervalSecond;
    private Pool pool;

    public Producer1(String threadName, int intervalSecond, Pool pool) {
        this.threadName = threadName;
        this.intervalSecond = intervalSecond;
        this.pool = pool;

    }

    @Override
    public void run() {
        Thread.currentThread().setName(this.threadName);

        while (true) {
            pool.put(String.valueOf(new Random().nextInt(100000)));
            try {
                TimeUnit.SECONDS.sleep(intervalSecond);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}