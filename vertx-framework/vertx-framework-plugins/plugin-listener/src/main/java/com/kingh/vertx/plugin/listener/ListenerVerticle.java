package com.kingh.vertx.plugin.listener;

import com.kingh.vertx.common.anno.Verticle;
import com.kingh.vertx.common.bean.ChainBean;
import com.kingh.vertx.core.context.ApplicationContext;
import com.kingh.vertx.core.context.ApplicationContextHolder;
import com.kingh.vertx.core.exector.ChainExector;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="https://blog.csdn.net/king_kgh>Kingh</a>
 * @version 1.0
 * @date 2019/3/20 13:25
 */
@Verticle(name = "ListenerVerticle", description = "监听客户端请求", autoDeploy = true)
public class ListenerVerticle extends AbstractVerticle {

    private ApplicationContext applicationContext = ApplicationContextHolder.getApplicationContext();

    @Override
    public void start() throws Exception {

        Integer port = config().getInteger("port");
        if (port == null) {
            port = 8080;
        }

        // 创建Http服务
        HttpServer server = vertx.createHttpServer();
        Router router = Router.router(vertx);

        // 交给链处理
        List<ChainBean> chains = applicationContext.chains();
        chains.stream().filter(ChainBean::isAvaiable).forEach(c -> {
            router.route(c.getPath()).method(c.getMethod()).handler(con -> {
                // 调用链,处理结果
                ChainExector.execute(c, con, vertx, re -> {
                    if (re.succeeded()) {
                        JsonObject result = re.result();
                        con.request().response().putHeader("Content-type","application/json;charset=utf-8");
                        con.request().response().end(result.toString());
                    } else {
                        con.request().response().end("ERROR");
                    }
                });
            });
        });

        // 处理请求
        server.requestHandler(router);

        // 监听端口
        server.listen(port);
    }
}
