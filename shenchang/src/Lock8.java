import java.util.concurrent.TimeUnit;

class Phone{
    public static synchronized void sendEmail() throws InterruptedException {
         // 2.sendEmail方法暂停4秒
       TimeUnit.SECONDS.sleep(4);
        System.out.println(Thread.currentThread().getName()+" sendEmail");

    }
    public static   synchronized void sendSMS(){
        System.out.println(Thread.currentThread().getName()+" sendSMS");
    }

    //3.新增普通的hello的方法
    public void getHello(){
        System.out.println("getHello");
    }

}

/**
 * 1.标准访问
 * 2.sendEmail方法暂停4秒
 * 3.新增普通的hello的方法
 * 4.两部手机，先打印？
 * 5.修改为两个静态同步方法，一部手机
 * 6.修改为两个静态同步方法，两部手机
 * 7.修改为一个静态同步方法，一个普通同步方法，一部手机
 * 8.修改为一个静态同步方法，一个普通同步方法，两部手机
 */
public class Lock8 {
    public static void main(String[] args) throws InterruptedException {

        Phone phone = new Phone();
        //4.两部手机
        Phone phone2 = new Phone();


        new Thread(()->{
            try {
                phone.sendEmail();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"A").start();
        //将主线程运行到这时，停0.1秒，保证A线程在B的线程前面

        Thread.sleep(100);
        new Thread(()->{
            //phone.sendSMS();

            //3.新增普通的hello的方法
           // phone.getHello();

            //4.两部手机
           phone2.sendSMS();
        },"B").start();
    }
}
