package com.kingh.vertx.plugin.db;

import com.kingh.vertx.plugin.db.utils.JdbcUtils;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.jdbc.JDBCClient;
import io.vertx.ext.sql.SQLConnection;
import org.apache.commons.lang3.StringUtils;
import rx.Single;

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
        this.jdbcClient = new JdbcUtils(vertx).getDbClient();
    }

    @Override
    public void query(String sql, JsonArray params, Handler<AsyncResult<JsonObject>> resultHandler) {
        if (StringUtils.isBlank(sql)) {
            resultHandler.handle(Future.failedFuture("参数错误，要执行的SQL语句为空！"));
            return;
        }

        if (params == null) {
            params = new JsonArray();
        }

        jdbcClient.queryWithParams(sql, params, rx -> {
            if (rx.succeeded()) {
                JsonObject res = new JsonObject();
                res.put("data", rx.result().getRows());
                resultHandler.handle(Future.succeededFuture(res));
            } else {
                resultHandler.handle(Future.failedFuture(rx.cause()));
            }
        });
    }

    @Override
    public void update(String sql, JsonArray params, Handler<AsyncResult<JsonObject>> resultHandler) {
        if (StringUtils.isBlank(sql)) {
            resultHandler.handle(Future.failedFuture("参数错误，要执行的SQL语句为空！"));
            return;
        }

        if (params == null) {
            params = new JsonArray();
        }

        jdbcClient.updateWithParams(sql, params, rx -> {
            if (rx.succeeded()) {
                resultHandler.handle(Future.succeededFuture(rx.result().toJson()));
            } else {
                resultHandler.handle(Future.failedFuture(rx.cause()));
            }
        });
    }

    @Override
    public void update(JsonArray sql, Handler<AsyncResult<JsonObject>> resultHandler) {
        if (sql == null || sql.size() == 0) {
            resultHandler.handle(Future.failedFuture("参数错误，要执行的SQL语句为空！"));
            return;
        }

        getConnection(jdbcClient, con -> {
            if (con.succeeded()) {
                // 获取到与数据库的连接
                SQLConnection connection = con.result();

                // 开启事务
                Single<SQLConnection> s1 = rxOpenTx(connection);

                for (int i = 0; i < sql.size(); i++) {
                    JsonObject sqlmap = sql.getJsonObject(i);
                    String exeSql = sqlmap.getString("sql");
                    JsonArray params = sqlmap.getJsonArray("params");
                    s1 = s1.flatMap(c -> rxExecuteUpdate(exeSql, params, c));
                }

                s1.subscribe(ok -> {
                    // 提交事务
                    ok.commit(v -> {
                    });
                }, err -> {
                    // 回滚事务
                    connection.rollback(v -> {
                    });
                });
            }
        });

    }

    private void getConnection(JDBCClient jdbcClient, Handler<AsyncResult<SQLConnection>> resultHandler) {
        jdbcClient.getConnection(con -> {
            if (con.succeeded()) {
                resultHandler.handle(Future.succeededFuture(con.result()));
            } else {
                resultHandler.handle(Future.failedFuture(con.cause()));
            }
        });
    }

    private void openTx(SQLConnection connection, Handler<AsyncResult<SQLConnection>> resultHandler) {
        connection.setAutoCommit(false, o -> {
            if (o.succeeded()) {
                resultHandler.handle(Future.succeededFuture(connection));
            } else {
                resultHandler.handle(Future.failedFuture(o.cause()));
            }
        });
    }

    public void update(String sql, JsonArray params, SQLConnection connection, Handler<AsyncResult<SQLConnection>> resultHandler) {
        connection.updateWithParams(sql, params, in -> {
            if (in.succeeded()) {
                resultHandler.handle(Future.succeededFuture(connection));
            } else {
                resultHandler.handle(Future.failedFuture(in.cause()));
            }
        });
    }

    public Single<SQLConnection> rxOpenTx(SQLConnection connection) {
        return Single.create(new io.vertx.rx.java.SingleOnSubscribeAdapter<>(fut -> openTx(connection, fut)));
    }

    public Single<SQLConnection> rxExecuteUpdate(String sql, JsonArray params, SQLConnection connection) {
        return Single.create(new io.vertx.rx.java.SingleOnSubscribeAdapter<>(fut -> update(sql, params, connection, fut)));
    }
}
