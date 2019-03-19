package com.kingh.vertx.common.anno;

import java.lang.annotation.*;

/**
 * 在Verticle组件中，对外发布的服务接口中标注此注解，
 * 可以自动进行服务发现
 *
 * @author <a href="https://blog.csdn.net/king_kgh>Kingh</a>
 * @version 1.0
 * @date 2019/3/11 9:46
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Serv {

    /**
     * 服务名字
     *
     * @return
     */
    String name();

    /**
     * 服务说明
     * @return
     */
    String description() default "";

}
