package com.kingh.stu.result;

import io.vertx.ext.web.RoutingContext;

/**
 * @author <a href="https://blog.csdn.net/king_kgh>Kingh</a>
 * @version 1.0
 * @date 2019/3/5 15:37
 */
public class ForwardResponser implements ResponseHandler {

    @Override
    public void handle(Result result, RoutingContext context) {
        if(Result.FORWARD.equalsIgnoreCase(result.getType())) {
            context.reroute(result.getView());
        } else {
            context.request().response().setStatusCode(302);
            context.request().response().putHeader("Location", result.getView());
            context.request().response().end();
        }
    }
}
