package com.kingh.vertx.plugin.db;

import com.kingh.vertx.common.anno.Param;
import com.kingh.vertx.common.anno.Service;
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

    @Service(name = "query", description = "查询数据库操作")
    void query(@Param(name = "sql", must = true, description = "要执行的SQL") String sql, @Param(name = "params", description = "SQL中的参数") JsonArray params, Handler<AsyncResult<JsonObject>> resultHandler);

    @Service(name = "update", description = "更新数据库操作")
    void update(@Param(name = "sql",must = true, description = "要执行的SQL") String sql, @Param(name = "params",description = "SQL中的参数") JsonArray params, Handler<AsyncResult<JsonObject>> resultHandler);
}
