1. 现在有T1、T2、T3三个线程，你怎样保证T2在T1执行完后执行，T3在T2执行完后执行？
     
     main(){
        T1.start(); //T1线程启动
        T1.join();  //main线程等待T1线程执行完成
        T2.start();
        T2.join();
        T3.start();
     }
       
        T3.join();

2. 