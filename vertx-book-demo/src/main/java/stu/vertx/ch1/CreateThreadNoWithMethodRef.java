package stu.vertx.ch1;

import java.io.Serializable;

/**
 * 不使用方法引用的形式创建线程
 */
public class CreateThreadNoWithMethodRef implements Serializable {

    /**
     * 被线程任务调用的方法
     */
    public void start() {
        System.out.println("线程任务真正执行的方法！");
    }

    /**
     * 创建线程并执行线程任务
     */
    public void go() {
        // 保存当前类的引用
        CreateThreadNoWithMethodRef that = this;

        // 创建线程并指定线程任务
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                // 调用当前类的start方法
                that.start();
            }
        });
        // 启动线程
        t.start();
    }

    public static void main(String[] args) {
        new CreateThreadNoWithMethodRef().go();
    }
}
