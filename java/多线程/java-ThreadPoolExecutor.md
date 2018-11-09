参考 [BlockingQueue](https://www.cnblogs.com/zaizhoumo/p/7786793.html)
## threadPoolExecutor 例子
    threadPoolExecutor.shutdown();
    while(!threadPoolExecutor.isTerminated()){ // 注意用while不能用if
        sleep(60s);
    }

## ArrayBlockingQueue 
会阻塞的方法：put(E e); take();
没有停止ArrayBlockingQueue的方法，要put进入一些标记对象，take到这些对象时退出线程。
## [stackoverflow threadpoolexecutor](https://stackoverflow.com/questions/3929361/how-to-wait-for-all-tasks-in-an-threadpoolexecutor-to-finish-without-shutting-do)


    Collection<Future<?>> futures = new LinkedList<Future<?>>();
    futures.add(executorService.submit(myRunnable));
    for (Future<?> future:futures) {
        future.get();
    }


 ## [线程池例子](https://www.journaldev.com/1069/threadpoolexecutor-java-thread-pool-example-executorservice)   

     ExecutorService executor = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            Runnable worker = new WorkerThread("" + i);
            executor.execute(worker);
          }
        executor.shutdown();
        while (!executor.isTerminated()) {
        }
        System.out.println("Finished all threads");



用`ThreadPoolExecutor.submit()`,`ThreadPoolExecutor. shutdown()`组合

for(int i=0;i<10;i++){
    Future<?> f = threadPoolExecutor.submit(new Runnable(){public void run()});
    futures.add(f);
}
threadPoolExecutor.shutdown();
  for (Future<?> future:futures) {
        future.get();
    }
