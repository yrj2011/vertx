package com.kingh.vertx.common.anno;

import java.lang.annotation.*;

/**
 * 服务参数标注
 *
 * @author <a href="https://blog.csdn.net/king_kgh>Kingh</a>
 * @version 1.0
 * @date 2019/3/19 9:21
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Param {

    /**
     * 参数名
     *
     * @return
     */
    String name();

    /**
     * 参数描述
     */
    String description() default "";

    /**
     * 是否必填
     */
    boolean must() default false;

    /**
     * 长度
     */
    int length() default -1;

}
