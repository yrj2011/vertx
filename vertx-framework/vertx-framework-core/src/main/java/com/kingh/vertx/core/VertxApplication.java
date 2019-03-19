package com.kingh.vertx.core;

import com.kingh.vertx.core.context.AnnotationApplicationContext;
import com.kingh.vertx.core.context.ApplicationContext;

/**
 * 核心启动类
 *
 * @author <a href="https://blog.csdn.net/king_kgh>Kingh</a>
 * @version 1.0
 * @date 2019/3/11 16:44
 */
public class VertxApplication {

    public void start() {
        new AnnotationApplicationContext().run();
    }

    public static void main(String[] args) {
        new VertxApplication().start();
    }
}
