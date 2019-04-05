package stu.vertx.jdbc;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.ext.jdbc.JDBCClient;
import io.vertx.ext.sql.SQLConnection;

/**
 * 获得数据库连接，执行查询，开启事务，执行更新操作
 *
 * @author <a href="https://blog.csdn.net/king_kgh>Kingh</a>
 * @version 1.0
 * @date 2019/4/3 9:19
 */
public class GetConnection extends AbstractVerticle {

    @Override
    public void start() throws Exception {

        JDBCClient jdbcClient = new JdbcUtils(vertx).getDbClient();
        System.out.println("获取到数据库客户端");
        // 获取数据库连接
        jdbcClient.getConnection(con -> {
            if (con.succeeded()) {
                System.out.println("获取到数据库连接");

                // 获取到的连接对象
                SQLConnection connection = con.result();

                // 执行查询操作
                connection.query("select * from t1", rs -> {
                    // 处理查询结果
                    if (rs.succeeded()) {
                        System.out.println(rs.result().getRows());
                    }
                });

                // 开启事务
                connection.setAutoCommit(false, (v) -> {
                    if (v.succeeded()) {
                        // 事务开启成功 执行crud操作
                        connection.update("update t1 set name = '被修改了' where name = '111'", up -> {

                            if (up.succeeded()) {
                                // 再来一笔写操作
                                connection.update("insert into t1 values ('222','222222') ", up2 -> {
                                    if (up2.succeeded()) {
                                        // 提交事务
                                        connection.commit(rx -> {
                                            if (rx.succeeded()) {
                                                // 事务提交成功
                                            }
                                        });
                                    } else {
                                        connection.rollback(rb -> {
                                            if (rb.succeeded()) {
                                                // 事务回滚成功
                                            }
                                        });
                                    }
                                });
                            } else {
                                connection.rollback(rb -> {
                                    if (rb.succeeded()) {
                                        // 事务回滚成功
                                    }
                                });
                            }
                        });

                    } else {
                        System.out.println("开启事务失败");
                    }
                });
            } else {
                System.out.println("获取数据库连接失败");
            }
        });


    }

    public static void main(String[] args) {
        Vertx.vertx().deployVerticle(new GetConnection());
    }
}
