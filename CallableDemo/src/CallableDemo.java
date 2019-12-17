import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;


/*
    获得线程的方式有几种：4种
    第三种获得多线程的方式：Callable
                           与Runnable的区别 返回值/抛出异常/方法名
 */
class MyTherad2 implements  Runnable{

    @Override
    public void run() {

    }
}
class MyTherad implements Callable<String>{


    @Override
    public String call() throws Exception {
        System.out.println("come in call()");
        return 2048+"";
    }
}


public class CallableDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask futureTask = new FutureTask(new MyTherad());
            new Thread(futureTask,"ABC").start();
        System.out.println(futureTask.get());
    }
}
