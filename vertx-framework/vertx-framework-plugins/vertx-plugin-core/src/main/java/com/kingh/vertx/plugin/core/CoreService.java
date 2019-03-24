package com.kingh.vertx.plugin.core;

import com.kingh.vertx.common.anno.Param;
import com.kingh.vertx.common.anno.Service;
import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

/**
 * @author <a href="https://blog.csdn.net/king_kgh>Kingh</a>
 * @version 1.0
 * @date 2019/3/22 19:29
 */
@ProxyGen
@VertxGen
public interface CoreService {

    String address = "com.kingh.vertx.plugin.core.service.19940706";

    static CoreService create(Vertx vertx) {
        return new CoreServiceImpl(vertx);
    }

    /**
     * 测试服务调用中传递String类型
     *
     * @param name
     * @param resultHandler
     */
    @Service(name = "hello", description = "测试服务")
    void hello(@Param(name = "name", description = "名字") String name, Handler<AsyncResult<JsonObject>> resultHandler);

    /**
     * 测试服务调用中传递JsonObject类型
     *
     * @param data
     * @param resultHandler
     */
    @Service(name = "world", description = "测试服务")
    void world(@Param(name = "data", description = "数据") JsonObject data, Handler<AsyncResult<JsonObject>> resultHandler);


}
