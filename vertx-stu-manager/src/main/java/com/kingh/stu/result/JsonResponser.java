package com.kingh.stu.result;

import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.eventbus.bridge.tcp.TcpEventBusBridge;
import io.vertx.ext.web.RoutingContext;

import java.util.Collections;
import java.util.HashMap;

/**
 * @author <a href="https://blog.csdn.net/king_kgh>Kingh</a>
 * @version 1.0
 * @date 2019/3/5 9:36
 */
public class JsonResponser implements ResponseHandler {

    @Override
    public void handle(Result result, RoutingContext context) {
        HttpServerResponse response = context.request().response();
        response.putHeader("Content-Type","application/json");
        Object data = result.getData();
        if(data == null) {
            data = Collections.EMPTY_MAP;
        }
        JsonObject resData = JsonObject.mapFrom(data);
        response.end(resData.toString());
    }
}
