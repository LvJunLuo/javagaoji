import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Resou{
    private int num=0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    public  void add() throws InterruptedException {
        lock.lock();
        try {
            //1.判断
            while (num !=0) {
                condition.await();
            }
            //干活
            ++num;
            System.out.println(Thread.currentThread().getName()+":"+num);
            //通知
            condition.signalAll();

        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }

    }

    public  void sub() throws InterruptedException{
          lock.lock();
                  try {
                      //判断
                      while (num ==0) {
                          condition.await();
                      }
                      //干活
                      --num;
                      System.out.println(Thread.currentThread().getName()+":"+num);
                      //通知
                      condition.signalAll();

                  }catch (Exception e){
                      e.printStackTrace();
                  }
                  finally {
                      lock.unlock();
                  }
    }




    /*public synchronized void add() throws InterruptedException {
       //1.判断
        while (num !=0) {
           this.wait();
        }
        //干活
        ++num;
        System.out.println(Thread.currentThread().getName()+":"+num);
        this.notifyAll();
    }

    public synchronized void sub() throws InterruptedException{
        while (num ==0) {
            this.wait();
        }
        --num;
        System.out.println(Thread.currentThread().getName()+":"+num);
        this.notifyAll();
    }*/

}

public class shengchanxiao {

    public static void main(String[] args) {

        Resou resou = new Resou();

        new Thread(()->{
            for (int i = 1; i <=10 ; i++) {
                try {
                    Thread.sleep(200);
                    resou.add();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();

        new Thread(()->{
            for (int i = 1; i <=10 ; i++) {
                try {
                    Thread.sleep(300);
                    resou.sub();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();

        new Thread(()->{
            for (int i = 1; i <=10 ; i++) {
                try {
                    Thread.sleep(400);
                    resou.add();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"C").start();

        new Thread(()->{
            for (int i = 1; i <=10 ; i++) {
                try {
                    Thread.sleep(500);
                    resou.sub();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"D").start();

    }
}
