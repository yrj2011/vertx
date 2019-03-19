package com.kingh.vertx.plugin.db;

import com.kingh.vertx.common.bean.runnable.RService;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.jdbc.JDBCClient;
import io.vertx.ext.sql.ResultSet;
import io.vertx.ext.sql.UpdateResult;
import org.apache.commons.lang3.StringUtils;

/**
 * @author <a href="https://blog.csdn.net/king_kgh>Kingh</a>
 * @version 1.0
 * @date 2019/3/11 11:27
 */
public class DbServiceImpl implements DbService {

    private Vertx vertx;
    private JDBCClient jdbcClient;

    public DbServiceImpl(Vertx vertx) {
        this.vertx = vertx;
        this.jdbcClient = JDBCClient.createShared(vertx, new JsonObject());
    }

    @Override
    public void query(String sql, JsonArray params, Handler<AsyncResult<JsonObject>> resultHandler) {
//        RunnableNode node = new RunnableNode(vertx);
//
//        if (StringUtils.isBlank(sql)) {
//            node.setStatus("99");
//            resultHandler.handle(Future.succeededFuture(node));
//            return;
//        }
//
//        Handler<AsyncResult<ResultSet>> handler = rs -> {
//            if (rs.succeeded()) {
//                node.setStatus("00")
//                        .setResult(new JsonObject().put("result", rs.result().getRows()));
//                resultHandler.handle(Future.succeededFuture(node));
//            } else {
//                node.setStatus("99").setT(rs.cause());
//                resultHandler.handle(Future.succeededFuture(node));
//            }
//        };
//
//        if (params == null || params.size() == 0) {
//            jdbcClient.query(sql, handler);
//        } else {
//            jdbcClient.queryWithParams(sql, params, handler);
//        }
    }


    @Override
    public void update(String sql, JsonArray params, Handler<AsyncResult<JsonObject>> resultHandler) {
//        RunnableNode node = new RunnableNode(vertx);
//
//        if (StringUtils.isBlank(sql)) {
//            node.setStatus("99");
//            resultHandler.handle(Future.succeededFuture(node));
//            return;
//        }
//
//        Handler<AsyncResult<UpdateResult>> handler = rs -> {
//            if (rs.succeeded()) {
//                node.setStatus("00")
//                        .setResult(new JsonObject().put("result", rs.result().getUpdated()));
//                resultHandler.handle(Future.succeededFuture(node));
//            } else {
//                node.setStatus("99").setT(rs.cause());
//                resultHandler.handle(Future.succeededFuture(node));
//            }
//        };
//
//        if (params == null || params.size() == 0) {
//            jdbcClient.update(sql, handler);
//        } else {
//            jdbcClient.updateWithParams(sql, params, handler);
//        }
    }
}
