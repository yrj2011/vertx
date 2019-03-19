package com.kingh.vertx.plugin.db;

import com.kingh.vertx.common.bean.runnable.RService;
import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

/**
 * @author <a href="https://blog.csdn.net/king_kgh>Kingh</a>
 * @version 1.0
 * @date 2019/3/11 11:27
 */
@ProxyGen
@VertxGen
public interface DbService {

    String address = "com.kingh.vertx.plugin.db";

    static DbService create(Vertx vertx) {
        return new DbServiceImpl(vertx);
    }

    void query(String sql, JsonArray params, Handler<AsyncResult<JsonObject>> resultHandler);

    void update(String sql, JsonArray params, Handler<AsyncResult<JsonObject>> resultHandler);
}
