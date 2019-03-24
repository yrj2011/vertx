package com.kingh.vertx.common.anno;

import io.vertx.core.http.HttpMethod;

import java.lang.annotation.*;

/**
 * 标注此注解的方法，必须返回一个ChainBean 用于构建链
 *
 * @author <a href="https://blog.csdn.net/king_kgh>Kingh</a>
 * @version 1.0
 * @date 2019/3/11 16:43
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Chain {

    /**
     * 链的名字
     *
     * @return
     */
    String name() default "";

    /**
     * 链的描述
     *
     * @return
     */
    String description() default "";

    /**
     * 处理的请求路径
     *
     * @return
     */
    String path() default "";

    /**
     * 链是否可用
     *
     * @return
     */
    boolean avaiable() default false;

    /**
     * 是否是普通链，特殊链不调用服务
     *
     * @return
     */
    boolean general() default false;

    /**
     * 支持的HTTP请求方法，默认为都支持
     *
     * @return
     */
    HttpMethod[] methods() default {};

}
