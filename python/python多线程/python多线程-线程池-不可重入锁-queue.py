from concurrent.futures import * #线程池
import  threading   #锁



threadPool=ThreadPoolExecutor(max_workers=2)
lock = threading.Lock()
num=0
print("执行前num:%s",num)

def add(i):
    for index in range(1000000):
         lock.acquire()
         try:
            num = num + i  #无局部变量num,会找全局变量num
            num = num + i
         finally:
             lock.release()

def sub(i):
    for index in range(1000000):
        lock.acquire()
        try:
            num = num - i
            num = num - i
        finally:
             lock.release()


threadPool.submit(add,1)
threadPool.submit(sub,1)
threadPool.shutdown()

print("执行后num:%s",num)













