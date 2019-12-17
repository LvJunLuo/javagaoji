import java.util.concurrent.CountDownLatch;


/*
     ConutDownLatch
     倒计数锁
 */
public class ConutDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 1; i <=6; i++) {
            final int tempI = i;
                    new Thread(()->{
                        System.out.println(Thread.currentThread().getName()+"，离开教室.--"+tempI);
                        countDownLatch.countDown();
                    },String.valueOf(i)).start();
                }

            countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"，班长关门走人！");

    }

}
