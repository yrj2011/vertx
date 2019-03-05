package com.kingh.stu.module.example;

import com.kingh.stu.anno.RequestMapping;
import com.kingh.stu.result.Result;
import com.kingh.stu.utils.JdbcUtils;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.jdbc.JDBCClient;
import io.vertx.ext.web.RoutingContext;

/**
 * @author <a href="https://blog.csdn.net/king_kgh>Kingh</a>
 * @version 1.0
 * @date 2019/3/4 20:54
 */
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/index")
    public void index(JDBCClient jdbcClient, Handler<AsyncResult<Result>> resultHandler) {
        String sql = "select * from t_student";
        jdbcClient.query(sql, qryRes->{
            if(qryRes.succeeded()) {
                Result result = Result.HTML("/index.ftl", new JsonObject().put("name","小明"));
                resultHandler.handle(Future.succeededFuture(result));
            } else {
                resultHandler.handle(Future.failedFuture("数据库异常"));
            }
        });
    }

}
