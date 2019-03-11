package com.kingh.vertx.plugin.db;

import com.kingh.vertx.core.anno.Method;
import com.kingh.vertx.core.anno.Service;
import com.kingh.vertx.core.node.RunnableNode;
import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;

/**
 * @author <a href="https://blog.csdn.net/king_kgh>Kingh</a>
 * @version 1.0
 * @date 2019/3/11 11:27
 */
@ProxyGen
@VertxGen
@Service
public interface DbService {

    String address = "com.kingh.vertx.plugin.db";

    static DbService create(Vertx vertx) {
        return new DbServiceImpl(vertx);
    }

    @Method
    void query(String sql, JsonArray params, Handler<AsyncResult<RunnableNode>> resultHandler);

    @Method
    void update(String sql, JsonArray params, Handler<AsyncResult<RunnableNode>> resultHandler);
}
