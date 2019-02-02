package stu.vertx.ch1;

import java.io.Serializable;

/**
 * 使用方法引用重写 CreateThreadNoWithMethodRef
 *
 * @author 孔冠华
 */
public class CreateThreadWithMethodRef implements Serializable {

    /**
     * 线程任务执行的方法
     */
    public void start() {
        System.out.println("线程任务真正执行的方法！");
    }

    /**
     * 创建线程并调用线程任务方法
     */
    public void go() {
        new Thread(this::start).start();
    }

    public static void main(String[] args) {
        new CreateThreadWithMethodRef().go();
    }
}
