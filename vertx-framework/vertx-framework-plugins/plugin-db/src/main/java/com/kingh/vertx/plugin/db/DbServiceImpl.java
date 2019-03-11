package com.kingh.vertx.plugin.db;

import com.kingh.vertx.core.node.StaticNode;
import com.kingh.vertx.core.node.RunnableNode;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;

/**
 * @author <a href="https://blog.csdn.net/king_kgh>Kingh</a>
 * @version 1.0
 * @date 2019/3/11 11:27
 */
public class DbServiceImpl implements DbService {

    public DbServiceImpl(Vertx vertx) {

    }

    @Override
    public void query(String sql, JsonArray params, Handler<AsyncResult<RunnableNode>> resultHandler) {

    }

    @Override
    public void update(String sql, JsonArray params, Handler<AsyncResult<RunnableNode>> resultHandler) {
        
    }
}
