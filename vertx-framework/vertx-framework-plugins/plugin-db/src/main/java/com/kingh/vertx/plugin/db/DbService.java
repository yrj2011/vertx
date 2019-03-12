package com.kingh.vertx.plugin.db;

import com.kingh.vertx.core.anno.Action;
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
@Service(name = "数据库服务", address = DbService.address)
public interface DbService {

    String address = "com.kingh.vertx.plugin.db";

    static DbService create(Vertx vertx) {
        return new DbServiceImpl(vertx);
    }

    @Action(name = "执行查询操作")
    void query(String sql, JsonArray params, Handler<AsyncResult<RunnableNode>> resultHandler);

    @Action(name = "执行更新操作")
    void update(String sql, JsonArray params, Handler<AsyncResult<RunnableNode>> resultHandler);
}
