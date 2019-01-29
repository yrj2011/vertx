package stu.vertx.ch1;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.Arrays;

public class CreateThreadWithMethodRef implements Serializable {

    public void start() {
        System.out.println("线程任务真正执行的方法！");
    }

    public void go() {
        new Thread(this::start).start();
    }

    public static void main(String[] args) {
        new CreateThreadWithMethodRef().go();
    }
}
