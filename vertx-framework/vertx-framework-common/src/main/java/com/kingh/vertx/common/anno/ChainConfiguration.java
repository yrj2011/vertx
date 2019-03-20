package com.kingh.vertx.common.anno;

import java.lang.annotation.*;

/**
 * 标识类为定义链的类
 *
 * @author <a href="https://blog.csdn.net/king_kgh>Kingh</a>
 * @version 1.0
 * @date 2019/3/20 14:17
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ChainConfiguration {
}
