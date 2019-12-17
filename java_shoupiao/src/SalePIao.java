
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


class piao{
    private  int num = 30;
    private Lock lock = new ReentrantLock();
    public void sale(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"卖了第"+num+"张，还剩"+( --num)+"张");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

}
public class SalePIao {
    public static void main(String[] args) {

        piao piao = new piao();
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        try {
            for (int i = 0; i <30 ; i++) {

                executorService.execute(()->{piao.sale();});
            }

        }finally {
            executorService.shutdown();
        }







    }
}
