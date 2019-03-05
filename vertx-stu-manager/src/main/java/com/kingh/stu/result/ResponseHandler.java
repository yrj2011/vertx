package com.kingh.stu.result;

import io.vertx.ext.web.RoutingContext;

/**
 * @author <a href="https://blog.csdn.net/king_kgh>Kingh</a>
 * @version 1.0
 * @date 2019/3/5 9:35
 */
public interface ResponseHandler {

    void handle(Result result, RoutingContext context);

}
