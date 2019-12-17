import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyCache{

    private volatile Map<String,String> map = new HashMap<>();

    ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

    public void put(String key,String value){
        rwl.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t 写入开始");
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"\t 写入结束");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rwl.writeLock().unlock();
        }

    }

    public void get(String key){
        rwl.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t 读取开始");
            String result = map.get(key);
            System.out.println(Thread.currentThread().getName()+"\t 读取结束"+result);
            //return result;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rwl.readLock().unlock();
        }

    }


    /*
     private Lock lock = new ReentrantLock();
    public void put(String key,String value){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t 写入开始");
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"\t 写入结束");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public void get(String key){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t 读取开始");
            String result = map.get(key);
            System.out.println(Thread.currentThread().getName()+"\t 读取结束"+result);
            //return result;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }*/

}

/*
    6.读写锁
    多个线程同时读一个资源类没有任何问题，所以为了满足并发量,读取共享资源应该可以同时进行。
    但是
    如果有一个线程想去写共享资源来，就不应该再有其它线程可以对该资源进行读或写
    小总结：读--读能共存
            读--写不能共存
            写--写不能共存
     演示:
            1不加锁,乱写error,并发读可以
            2.加锁lock, 写ok, 但并发读下降
            3.加readwritelock,  写唯一, 读并发高性能

 */
public class ReadWriteDemo {

    public static void main(String[] args) {
        MyCache myCache = new MyCache();

        for (int i = 1; i <=10; i++) {
            final int tempI = i;
                    new Thread(()->{
                        myCache.put(tempI+"",tempI+"");
                    },String.valueOf(i)).start();
                }
        for (int i = 1; i <=10; i++) {
            final int tempI = i;
                    new Thread(()->{
                    myCache.get(tempI + "");

                    },String.valueOf(i)).start();
                }
    }

}
