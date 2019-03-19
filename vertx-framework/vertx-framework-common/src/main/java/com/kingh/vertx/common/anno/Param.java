package com.kingh.vertx.common.anno;

/**
 * 服务参数标注
 *
 * @author <a href="https://blog.csdn.net/king_kgh>Kingh</a>
 * @version 1.0
 * @date 2019/3/19 9:21
 */
public @interface Param {

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
