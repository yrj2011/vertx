package stu.vertx.ch1;

public class CreateThreadWithLambda {

    public static void main(String[] args) {
        new Thread(() ->
                System.out.println("任务执行完毕！")
        ).start();
    }
}
