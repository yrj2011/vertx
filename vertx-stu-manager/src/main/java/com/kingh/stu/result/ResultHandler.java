package com.kingh.stu.result;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;

import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="https://blog.csdn.net/king_kgh>Kingh</a>
 * @version 1.0
 * @date 2019/3/5 9:18
 */
public class ResultHandler implements Handler<AsyncResult<Result>> {

    private RoutingContext context;

    private static Map<String, ResponseHandler> handlerMap = new HashMap<>();

    static {
        handlerMap.put("JSON", new JsonResponser());
    }

    public ResultHandler(RoutingContext context) {
        this.context = context;
    }

    @Override
    public void handle(AsyncResult<Result> event) {
        if (event.succeeded()) {
            Result result = event.result();
            String resType = result.getType().toUpperCase();
            ResponseHandler responseHandler = handlerMap.get(resType);
            responseHandler.handle(result, context);
        } else {
            // 错误响应
        }
    }
}
