import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;


/*
    CyclicBarrier
    集齐七颗龙珠召唤神龙
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {

        CyclicBarrier barrier = new CyclicBarrier(7, () -> {
            System.out.println("集齐七颗龙珠召唤神龙");
        });
        for (int i = 1; i <=7; i++) {
            //final int tempI = i;
                    new Thread(()->{

                        try {
                            System.out.println(Thread.currentThread().getName()+",我来了");
                            barrier.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (BrokenBarrierException e) {
                            e.printStackTrace();
                        }
                    },String.valueOf(i)).start();
                }

    }

}
