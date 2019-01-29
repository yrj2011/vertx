package stu.vertx.ch1;

public class CreateThread {

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("任务执行完毕！");
            }
        }).start();
    }
}
