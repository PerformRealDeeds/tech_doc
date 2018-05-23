from concurrent.futures import *
import  threading



threadPool=ThreadPoolExecutor(max_workers=2)
lock = threading.Lock()
num=0
print("执行前num:%s",num)

def add(i):
    global num
    for index in range(1000000):
         lock.acquire()
         try:
            num = num + i
            num = num + i
         finally:
             lock.release()

def sub(i):
    global num
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













