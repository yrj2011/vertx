package stu.vertx.jdbc;

import io.vertx.core.*;
import io.vertx.core.json.JsonArray;
import io.vertx.ext.jdbc.JDBCClient;
import io.vertx.ext.sql.SQLConnection;
import rx.Single;

import java.util.UUID;

/**
 * @author <a href="https://blog.csdn.net/king_kgh>Kingh</a>
 * @version 1.0
 * @date 2019/4/5 14:10
 */
public class GetConnectionWithRxJava extends AbstractVerticle {

    @Override
    public void start() throws Exception {

        // 获取JDBC客户端
        JDBCClient jdbcClient = new JdbcUtils(vertx).getDbClient();

        getConnection(jdbcClient, con -> {
            if (con.succeeded()) {
                // 获取到与数据库的连接
                SQLConnection connection = con.result();

                // 开启事务
                rxOpenTx(connection)
                        // 执行写操作
                        .flatMap(this::rxExecuteUpdate1)
                        // 执行写操作
                        .flatMap(this::rxExecuteUpdate2)
                        .subscribe(ok -> {
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

    public Single<SQLConnection> rxOpenTx(SQLConnection connection) {
        return Single.create(new io.vertx.rx.java.SingleOnSubscribeAdapter<>(fut -> openTx(connection, fut)));
    }

    public Single<SQLConnection> rxExecuteUpdate1(SQLConnection connection) {
        return Single.create(new io.vertx.rx.java.SingleOnSubscribeAdapter<>(fut -> update1(connection, fut)));
    }

    public Single<SQLConnection> rxExecuteUpdate2(SQLConnection connection) {
        return Single.create(new io.vertx.rx.java.SingleOnSubscribeAdapter<>(fut -> update2(connection, fut)));
    }

    public void getConnection(JDBCClient jdbcClient, Handler<AsyncResult<SQLConnection>> resultHandler) {
        jdbcClient.getConnection(con -> {
            if (con.succeeded()) {
                resultHandler.handle(Future.succeededFuture(con.result()));
            } else {
                resultHandler.handle(Future.failedFuture(con.cause()));
            }
        });
    }

    public void openTx(SQLConnection connection, Handler<AsyncResult<SQLConnection>> resultHandler) {
        connection.setAutoCommit(false, o -> {
            if (o.succeeded()) {
                resultHandler.handle(Future.succeededFuture(connection));
            } else {
                resultHandler.handle(Future.failedFuture(o.cause()));
            }
        });
    }

    public void update1(SQLConnection connection, Handler<AsyncResult<SQLConnection>> resultHandler) {
        connection.updateWithParams("insert into t1 values (?,?)", new JsonArray().add(UUID.randomUUID().toString()).add(UUID.randomUUID().toString()), in -> {
            if (in.succeeded()) {
                resultHandler.handle(Future.succeededFuture(connection));
            } else {
                resultHandler.handle(Future.failedFuture(in.cause()));
            }
        });
    }

    public void update2(SQLConnection connection, Handler<AsyncResult<SQLConnection>> resultHandler) {
        connection.update("update t1 set name = '111' where passwd = '111'", in -> {
            if (in.succeeded()) {
                resultHandler.handle(Future.succeededFuture(connection));
            } else {
                resultHandler.handle(Future.failedFuture(in.cause()));
            }
        });
    }

    public static void main(String[] args) {
        Vertx.vertx().deployVerticle(new GetConnectionWithRxJava());
    }
}
