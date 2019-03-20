package com.kingh.vertx.plugin.listener;

import com.kingh.vertx.common.anno.Verticle;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServer;

/**
 * @author <a href="https://blog.csdn.net/king_kgh>Kingh</a>
 * @version 1.0
 * @date 2019/3/20 13:25
 */
@Verticle(name = "ListenerVerticle", description = "监听客户端请求", autoDeploy = true)
public class ListenerVerticle extends AbstractVerticle {

    @Override
    public void start() throws Exception {

        HttpServer server = vertx.createHttpServer();

        server.requestHandler(req -> {
            req.response().end("Hello");
        });

        server.listen(8080);
    }
}
