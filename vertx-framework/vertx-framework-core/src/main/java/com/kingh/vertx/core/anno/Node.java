package com.kingh.vertx.core.anno;

import java.lang.annotation.*;

/**
 * 标注此注解的方法，将会返回一个ChainNode，这个ChainNode的实例会被自动发现
 *
 * @author <a href="https://blog.csdn.net/king_kgh>Kingh</a>
 * @version 1.0
 * @date 2019/3/11 13:58
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Node {

}
