package stu.vertx.ch1;

/**
 * 使用lambda表达式的形式创建线程
 *
 * @author 孔冠华
 */
public class CreateThreadWithLambda {

    public static void main(String[] args) {
        new Thread(() ->
                System.out.println("任务执行完毕！")
        ).start();
    }
}
