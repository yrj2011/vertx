package com.kingh.vertx.plugin.core;

import com.kingh.vertx.common.anno.Param;
import com.kingh.vertx.common.anno.Service;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

/**
 * @author <a href="https://blog.csdn.net/king_kgh>Kingh</a>
 * @version 1.0
 * @date 2019/3/22 19:29
 */
public interface CoreService {

    String address = "com.kingh.vertx.plugin.core.service.19940706";

    static CoreService create(Vertx vertx) {
        return new CoreServiceImpl(vertx);
    }

    @Service(name = "bodyHandler")
    void bodyHandler(@Param(name = "context") RoutingContext context, Handler<AsyncResult<JsonObject>> resultHandler);

}
