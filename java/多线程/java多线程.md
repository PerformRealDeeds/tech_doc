# [java 并发](https://docs.oracle.com/javase/tutorial/essential/concurrency/index.html)

## java进程与线程的区别
1. 进程有完整的,私有的基础运行资源,比如每个进程有自己的内存区域. 线程有时被称为`轻量级进程(lightweight processes)`, 创建线程需要的资源更少.
2. 进程使用 `Inter Process Communication (IPC)` 通信,像管道或者套接字.  线程间使用共享
3. 线程存在于进程中.每个进程至少有一个线程.  java创建进程使用ProcessBuilder.
[java 进程与线程区别 博客](https://www.cnblogs.com/lgk8023/p/6430592.html)

## 定义和启动线程
1. 实现Runnable接口, 传入Thread的构造函数,调回start()
2. 继承Thread类,重写run方法,调用start()

## 暂停线程执行  sleep 和 wait 的区别
sleep不释放锁,wait释放锁.  都释放cpu资源给其它线程.

## synchronized 
* 如果一个对象对于多个线程可见,这个对象中的所有读写函数都要加上synchronized 
* 构造函数不能加synchronized. 因为加上后说不通,在创建对象的过程中,只有创建对象的线程才能访问它.
    * 如果构造函数中有其它对象的操作,注意这个对象是否对其它线程可见, 如在构造函数中对列表a添加元素, 要检查在构造过程中,列表a对于其它线程是否可见.
* 不可变对象不能被修改,可以用来并发读
* synchronized可以重入


## Lock Objects
lock和synchronized最大的不同是lock可以回退获取锁的尝试:
* `tryLock()` : 锁失效或者超时会回退
* `lockInterruptibly()`:  如果另一个线程打断了当前线程, 当前线程会退出获取锁的尝试.

## [Atomic Access](https://docs.oracle.com/javase/tutorial/essential/concurrency/atomic.html)
原子操作:动作要么全部发生,要么全部不发生.只有操作完成后才能看到效果.
volatile变量的修改对其它线程可见.


### [死锁,活锁,饥饿](http://www.codejava.net/java-core/concurrency/understanding-deadlock-livelock-and-starvation-with-code-examples-in-java)


## [wait,notify,notify](https://docs.oracle.com/javase/tutorial/essential/concurrency/guardmeth.html)
* 调用wait,notify时要加锁,否则报java.lang.IllegalMonitorStateException
* `wait`调用后会释放内部锁
* `notify`适合唤醒大规模执行相同任务线程中的随机一个线程, `notifyAll()`会唤醒所有的线程.


## [定义不可变对象](https://docs.oracle.com/javase/tutorial/essential/concurrency/imstrat.html)

1. Don't provide "setter" methods — methods that modify fields or objects referred to by fields.
2. Make all fields final and private.
3. Don't allow subclasses to override methods. The simplest way to do this is to declare the class as final. A more sophisticated approach is to make the constructor private and construct instances in factory methods.
4. If the instance fields include references to mutable objects, don't allow those objects to be changed:
    1. Don't provide methods that modify the mutable objects.
    2. Don't share references to the mutable objects. Never store references to external, mutable objects passed to the constructor; if necessary, create copies, and store references to the copies. Similarly, create copies of your internal mutable objects when necessary to avoid returning the originals in your methods.

## Executors

java.util.concurrent 定义了三个executor interfaces.
* `Executor`, a simple interface that supports launching new tasks.
* `ExecutorService`, a subinterface of Executor, which adds features that help manage the lifecycle, both of the individual tasks and of the executor itself.
* `ScheduledExecutorService`, a subinterface of ExecutorService, supports future and/or periodic execution of tasks.
* 