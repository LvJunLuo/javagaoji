import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareResource {
    private int flag = 1; //1:A ,2:B ,3:C
    private Lock lock = new ReentrantLock();

    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

/*
    public void print(String threadName){

            lock.lock();
            try {
                //1.判断
                while ( flag!=1 ) {
                    condition1.await();
                }

                //2.干活
                for (int i = 1; i <= 5; i++) {
                    System.out.println(Thread.currentThread().getName() + "打印了:" + i + "次");
                }
                //3.通知
               // flag =;
                condition2.signal();

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
    }
        */

    public void print5() {
        lock.lock();
        try {
            //1.判断
            System.out.println("method: print5();Therad:"+Thread.currentThread().getName() +";flag:"+flag);
            while (flag != 1) {
                condition1.await();
            }
            //1.干活
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + "打印了:" + i + "次");
            }
            //3.通知
            flag =2;
            condition2.signal();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print10() {
        lock.lock();
        try {
            //1.判断
            System.out.println("method: print10();Therad:"+Thread.currentThread().getName() +";flag:"+flag);
            while (flag != 2) {
                condition2.await();
            }
            //1.干活
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName() + "打印了:" + i + "次");
            }
            //3.通知
            flag=3;
            condition3.signal();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print15() {
        lock.lock();
        try {
            //1.判断
            System.out.println("method: print15();Therad:"+Thread.currentThread().getName() +";flag:"+flag);
            while (flag != 3) {
                condition2.await();
            }
            //1.干活
            for (int i = 1; i <= 15; i++) {
                System.out.println(Thread.currentThread().getName() + "打印了:" + i + "次");
            }
            //3.通知
            flag=1;
            condition1.signal();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


}

/*
多线程之间按顺序调用，实现A先F->B次之->C最后三个线程启动,要求如下
 AA5次BB打10次，CC打15按者顺序AA15次, BB打10, cc打15次…
 .....轮10次
 1.高聚低合前提下，线程操作资源类
 2判断/干活/通知
 3多线程交互中，必须要防止多线程的虚假唤醒,也即（判断只/while,不能用if)
 4.判断变量标识位,一定要注意标识位的修改.
 */
public class ThreadOrderAccess {
    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                shareResource.print5();
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                shareResource.print10();
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                shareResource.print15();
            }
        }, "C").start();


    }
}
