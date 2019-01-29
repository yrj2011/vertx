package stu.vertx.ch1;

import java.io.Serializable;

public class CreateThreadNoWithMethodRef implements Serializable {

    public void start() {
        System.out.println("线程任务真正执行的方法！");
    }

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
