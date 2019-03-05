package com.kingh.stu.router;

import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.ext.web.RoutingContext;


/**
 * @author <a href="https://blog.csdn.net/king_kgh>Kingh</a>
 * @version 1.0
 * @date 2019/3/4 19:34
 */
public interface MainRouter extends Handler<RoutingContext> {

    String E404 = "/404.html";
    String E500 = "/500.html";

    static MainRouter create(Vertx vertx) {
        return new MainRouterImpl(vertx);
    }

    void setE404(String e404);

    void setE500(String e500);

}
