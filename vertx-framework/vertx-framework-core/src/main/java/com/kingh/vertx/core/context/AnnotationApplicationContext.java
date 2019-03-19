package com.kingh.vertx.core.context;

import com.kingh.vertx.common.scan.Scanner;

import java.util.Set;

/**
 * @author <a href="https://blog.csdn.net/king_kgh>Kingh</a>
 * @version 1.0
 * @date 2019/3/19 9:58
 */
public class AnnotationApplicationContext implements ApplicationContext {

    private Set<Class<?>> classes;

    @Override
    public void run() {
        // 扫描所有的类
        classes = Scanner.scanner("");
        System.out.println(classes);
        System.out.println(classes.size());

        // 分析类

        // 构建链

        // 部署自动服务
    }
}
