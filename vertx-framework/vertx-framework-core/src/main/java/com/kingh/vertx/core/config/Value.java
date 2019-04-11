package com.kingh.vertx.core.config;

import java.lang.annotation.*;

/**
 * 自动注入配置文件中配置的key
 *
 * @author <a href="https://blog.csdn.net/king_kgh>Kingh</a>
 * @version 1.0
 * @date 2019/4/11 10:29
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Value {

    String key();
}
