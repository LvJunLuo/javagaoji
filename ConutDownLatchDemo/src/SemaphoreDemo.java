import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/*
    信号量（抢车位）
    多个线程访问多个资源用Semaphere控制
    不见不散 semaphore.acquire();
    过时不候 semaphore.release(int 10);

     Semaphore semaphore = new Semaphore(1);
     当为1时 就和synchronized锁一样


 */
public  class SemaphoreDemo {
    public  static void main(String[] args) {
        //模拟有三个车位
        Semaphore semaphore = new Semaphore(1);
        //模拟6辆车占车位
        for (int i = 1; i <=6; i++) {
            //final int tempI = i;
                    new Thread(()->{
                       boolean flag =false;
                        try {
                            semaphore.acquire();

                            flag =true;
                            System.out.println(Thread.currentThread().getName()+"占到车位");
                            //暂停几秒
                           // TimeUnit.SECONDS.sleep(3);
                        try{ TimeUnit.SECONDS.sleep(3);}catch(InterruptedException e){e.printStackTrace();}
                            System.out.println(Thread.currentThread().getName()+"离开车位");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }finally {
                            if (flag) {
                                semaphore.release();
                            }

                        }
                    },String.valueOf(i)).start();
                }

    }

}
