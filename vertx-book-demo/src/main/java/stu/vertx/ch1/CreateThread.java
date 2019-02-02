package stu.vertx.ch1;

/**
 * 不使用JDK8的lambda方式创建线程
 *
 * @author 孔冠华
 */
public class CreateThread {

    public static void main(String[] args) {

        // 创建线程，并传递一个匿名的线程任务对象
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("任务执行完毕！");
            }
        }).start();
    }
}
