package com.kingh.vertx.plugin.core;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

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
    public void hello(String name, Handler<AsyncResult<JsonObject>> resultHandler) {
        resultHandler.handle(Future.succeededFuture(new JsonObject().put("name", "Hello " + name)));
    }

    @Override
    public void world(JsonObject data, Handler<AsyncResult<JsonObject>> resultHandler) {
        resultHandler.handle(Future.succeededFuture(new JsonObject().put("msg", data)));
    }
}
