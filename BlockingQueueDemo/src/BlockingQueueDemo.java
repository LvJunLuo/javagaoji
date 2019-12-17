import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.SynchronousQueue;

/*
*
*
* 阻塞队列是一个队列，先进先出，线程1往阻塞队列里添加元素，线程2从阻塞队列里移除元素
* 必须阻塞要（生产者模式）/不得不阻塞（多线程）
* 当队列是空的，从队列中获取元素的操作将会被阻塞
  当队列是满的，从队列中添加元素的操作将会被阻塞
  试图从空的队列中获取元素的线程将会被阻塞，直到其他线程往空的队列插入新的元素
  试图向已满的队列中添加新元素的线程将会被阻塞，直到其他线程从队列中移除一个或多个元素或者完全清空，
  使队列变得空闲起来并后续新增
 阻塞对列的作用
  在多线程领域：所谓阻塞，在某些情况下会挂起线程（即阻塞），一旦条件满足，被挂起的线程又会自动被唤起
  好处是我们不需要关心什么时候需要阻塞线程，什么时候需要唤醒线程，因为这一切BlockingQueue都给你一手包办了

* */
public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        //有数据结构组成的有界阻塞对列
        BlockingQueue<String> arrayblockingQueuem = new ArrayBlockingQueue(3);
        //链表结构组成的有界阻塞对列（但是大小默认是integer.MAX_VALUE）
        BlockingQueue blockingQueue1 = new LinkedBlockingDeque(3);
        //单个元素队列(不存元素队列)
        BlockingQueue blockingQueue = new SynchronousQueue();

        //阻塞对列的方法类型 插入，移除，检查
        // 对方法的分类 抛出异常，特殊值，阻塞
        // 抛出异常的方法
/*
        arrayblockingQueuem.add("a");
        arrayblockingQueuem.add("b");
        arrayblockingQueuem.add("c");
       // arrayblockingQueuem.add("a");

        arrayblockingQueuem.remove();
       // arrayblockingQueuem.remove();
        //arrayblockingQueuem.remove();
        //arrayblockingQueuem.remove();

        Object element = arrayblockingQueuem.element();
        System.out.println("****************"+element);*/

        /*
            2.特殊值的方法

         */
       /* boolean a = arrayblockingQueuem.offer("shijunwei");
        boolean b = arrayblockingQueuem.offer("b");
        boolean c = arrayblockingQueuem.offer("c");
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println("****************");
        boolean d = arrayblockingQueuem.offer("d");
        System.out.println(d);
        System.out.println("***************************************************************************");
       *//* Object poll = arrayblockingQueuem.poll();
        Object poll1 = arrayblockingQueuem.poll();
        Object poll2 = arrayblockingQueuem.poll();
        Object poll3 = arrayblockingQueuem.poll();
        System.out.println(poll);
        System.out.println(poll1);
        System.out.println(poll2);*//*
        System.out.println("****************");
        //System.out.println(poll3);
        System.out.println(arrayblockingQueuem.peek());
*/
        /*
            3.阻塞
         */

        arrayblockingQueuem.put("shijunwei");
        arrayblockingQueuem.put("shijunwei");
        arrayblockingQueuem.put("shijunwei");

        arrayblockingQueuem.take();
        arrayblockingQueuem.take();
        arrayblockingQueuem.take();
        System.out.println("****************");
        //arrayblockingQueuem.put("shijunwei");
        //arrayblockingQueuem.take();
        System.out.println("12121121212121221112");


    }
}
