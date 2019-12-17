import java.util.concurrent.*;

/**
 * ThreadPool线程池
 * 是啥么：
 * 线程池做的工作只要是控制运行的线程数量,处理过程中将任务放入队列
 * 然后在线程创建后启动这些任务，如果线程数量超过了最大数量，超出数量的线程排队等候，
 * 等其他线程执行完毕，再从队列中取出任务来执行
 * <p>
 * 优点：
 * 主要特点为：线程复用;控制最大并发数;管理线程。
 * <p>
 * 作用：
 * 降低资源消耗。
 * 提高响应速度
 * 提高线程的可管理性
 */
public class MyThreadPoolDemo {
    public static void main(String[] args) {
         //一池固定线程
        //ExecutorService threadPool = Executors.newFixedThreadPool(5);//一池固定5线程
         //一池1线程
     //  ExecutorService threadPool2 = Executors.newSingleThreadExecutor();//一池1线程
         //一池N线程 可扩容，遇强则强
        //ExecutorService threadPool3 = Executors.newCachedThreadPool();//一池N线程

        //自定义线程(手动创建线程)
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy());

        //一个银行已经new 好了5个受理窗口，有5个工作人员
        //只要有资源类的东西，最后必须要有释放
        try {
            for (int i = 1; i <= 12; i++) {
                final int trmpI = i;
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "办理业务，客户号：" + trmpI);
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //释放
            threadPool.shutdown();
        }

    }
}
