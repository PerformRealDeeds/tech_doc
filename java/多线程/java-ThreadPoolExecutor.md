参考 [BlockingQueue](https://www.cnblogs.com/zaizhoumo/p/7786793.html)
[stackoverflow threadpoolexecutor](https://stackoverflow.com/questions/3929361/how-to-wait-for-all-tasks-in-an-threadpoolexecutor-to-finish-without-shutting-do)


    Collection<Future<?>> futures = new LinkedList<Future<?>>();
    futures.add(executorService.submit(myRunnable));
    for (Future<?> future:futures) {
        future.get();
    }





用`ThreadPoolExecutor.submit()`,`ThreadPoolExecutor. shutdown()`组合

for(int i=0;i<10;i++){
    Future<?> f = threadPoolExecutor.submit(new Runnable(){public void run()});
    futures.add(f);
}
threadPoolExecutor.shutdown();
  for (Future<?> future:futures) {
        future.get();
    }
