package com.kingh.vertx.common.anno;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.VertxOptions;

import java.lang.annotation.*;

/**
 * 在Verticle上标注此注解，标识为一个Verticle组件，
 * 可以被自动化控制启动与停止
 *
 * @author <a href="https://blog.csdn.net/king_kgh>Kingh</a>
 * @version 1.0
 * @date 2019/3/11 9:28
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Vert {

    /**
     * Verticle名称，便于识别
     *
     * @return
     */
    String name() default "";

    /**
     * 服务发布地址
     *
     * @return
     */
    String address();

    /**
     * Verticle 描述
     *
     * @return
     */
    String description() default "";

    /**
     * 是否是自动部署
     *
     * @return
     */
    boolean autoDeploy() default false;

    boolean worker() default DeploymentOptions.DEFAULT_WORKER;

    boolean multiThreaded() default DeploymentOptions.DEFAULT_MULTI_THREADED;

    String isolationGroup() default "";

    int workerPoolSize() default VertxOptions.DEFAULT_WORKER_POOL_SIZE;

    String workerPoolName() default "";

    long maxWorkerExecuteTime() default VertxOptions.DEFAULT_MAX_WORKER_EXECUTE_TIME;

    boolean ha() default DeploymentOptions.DEFAULT_HA;

    int instances() default DeploymentOptions.DEFAULT_INSTANCES;
}
