package com.kingh.vertx.plugin.core;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

/**
 * @author <a href="https://blog.csdn.net/king_kgh>Kingh</a>
 * @version 1.0
 * @date 2019/3/22 19:29
 */
public class CoreServiceImpl implements CoreService {

    private Vertx vertx;

    public CoreServiceImpl(Vertx vertx) {
        this.vertx = vertx;
    }

    @Override
    public void bodyHandler(RoutingContext context, Handler<AsyncResult<JsonObject>> resultHandler) {
        BodyHandler handler = BodyHandler.create();
        handler.handle(context);
    }
}
