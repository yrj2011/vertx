package com.kingh.stu.module.example;

import com.kingh.stu.anno.RequestMapping;
import com.kingh.stu.result.Result;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

/**
 * @author <a href="https://blog.csdn.net/king_kgh>Kingh</a>
 * @version 1.0
 * @date 2019/3/4 20:54
 */
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/save")
    public void add(RoutingContext context, Vertx vertx, Handler<AsyncResult<Result>> resultHandler) {
        System.out.println("方法执行了");
        Result result = new Result();
        result.setData(new JsonObject().put("hello","world"));
        resultHandler.handle(Future.succeededFuture(result));
    }

}
