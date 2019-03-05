package com.kingh.stu.module.example;

import com.kingh.stu.anno.RequestMapping;
import com.kingh.stu.result.Result;
import com.kingh.stu.utils.JdbcUtils;
import com.sun.org.apache.xpath.internal.functions.FuncTranslate;
import io.vertx.core.*;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.jdbc.JDBCClient;
import io.vertx.ext.web.FileUpload;
import io.vertx.ext.web.RoutingContext;

import java.util.UUID;

/**
 * @author <a href="https://blog.csdn.net/king_kgh>Kingh</a>
 * @version 1.0
 * @date 2019/3/4 20:54
 */
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/index")
    public void index(RoutingContext context, JDBCClient jdbcClient, Handler<AsyncResult<Result>> resultHandler) {
        String sql = "select * from t_student";
        jdbcClient.query(sql, qryRes->{
            if(qryRes.succeeded()) {
                Result result = Result.HTML("/index.ftl");
                context.put("userList", qryRes.result().getRows());
                resultHandler.handle(Future.succeededFuture(result));
            } else {
                resultHandler.handle(Future.failedFuture("数据库异常"));
            }
        });
    }

    @RequestMapping("/save")
    public void save(RoutingContext context, JDBCClient jdbcClient, Handler<AsyncResult<Result>> resultHandler) {
        MultiMap data = context.request().formAttributes();
        String sql = "insert into t_student (id, stu_name, tel) values (?,?,?)";
        JsonArray parasm = new JsonArray()
                .add(UUID.randomUUID().toString())
                .add(data.get("username"))
                .add(data.get("tel"));

        jdbcClient.updateWithParams(sql, parasm, upRes->{
            if(upRes.succeeded()) {
                Result result = Result.REDIRECT("/user/index");
                resultHandler.handle(Future.succeededFuture(result));
            } else {
                Result result = Result.STATIC("/500.html");
                resultHandler.handle(Future.succeededFuture(result));
            }
        });
    }

    @RequestMapping("/delete")
    public void delete(RoutingContext context, JDBCClient jdbcClient, Handler<AsyncResult<Result>> resultHandler) {
        String id = context.request().getParam("id");
        String sql = "delete from t_student where id = ?";

        jdbcClient.updateWithParams(sql, new JsonArray().add(id), upRes->{
            resultHandler.handle(Future.succeededFuture(Result.REDIRECT("/user/index")));
        });
    }

}
