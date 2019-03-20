package com.kingh.vertx.app;

import com.kingh.vertx.core.context.AnnotationApplicationContext;

/**
 * @author <a href="https://blog.csdn.net/king_kgh>Kingh</a>
 * @version 1.0
 * @date 2019/3/20 13:30
 */
public class MyApp {

    public void start() {
        new AnnotationApplicationContext().run();
    }

    public static void main(String[] args) {
        new MyApp().start();
    }


}
