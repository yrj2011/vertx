package com.kingh.stu.anno;

import java.lang.annotation.*;

/**
 * 请求映射，支持映射为多个路径
 *
 * @author 孔冠华
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestMapping {

    /**
     * 映射的路径
     *
     * @return
     */
    String[] value();
}
