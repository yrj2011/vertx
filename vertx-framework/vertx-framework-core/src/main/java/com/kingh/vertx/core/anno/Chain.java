package com.kingh.vertx.core.anno;

import java.lang.annotation.*;

/**
 * 标注此注解的方法，必须返回一个StaticChain 可以构建链
 *
 * @author <a href="https://blog.csdn.net/king_kgh>Kingh</a>
 * @version 1.0
 * @date 2019/3/11 16:43
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Chain {
}
