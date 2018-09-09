参考 [BlockingQueue](https://www.cnblogs.com/zaizhoumo/p/7786793.html)
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