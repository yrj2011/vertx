package com.kingh.vertx.plugin.listener;

import com.kingh.vertx.common.anno.Verticle;
import com.kingh.vertx.common.bean.ChainBean;
import com.kingh.vertx.core.context.ApplicationContext;
import com.kingh.vertx.core.context.ApplicationContextHolder;
import com.kingh.vertx.core.exector.ChainExector;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 监听组件，用于接收HTTP客户端请求，并给客户端响应
 * <p>
 * 1. 根据请求地址，调起流程，拿到流程的执行结果给客户端响应
 * 2. 支持给Router注入Handler
 * 3. 响应结果支持JSON、XML
 * 4. 放弃给Router注入Handler，改为使用链处理通用Handler，让应用更加统一
 *
 * @author <a href="https://blog.csdn.net/king_kgh>Kingh</a>
 * @version 1.0
 * @date 2019/3/20 13:25
 */
@Verticle(name = "HttpListenerVerticle", description = "监听客户端请求", autoDeploy = true)
public class HttpListenerVerticle extends AbstractVerticle {

    private ApplicationContext applicationContext = ApplicationContextHolder.getApplicationContext();

    private Logger logger = LoggerFactory.getLogger(HttpListenerVerticle.class);

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
        chains.stream().filter(ChainBean::isAvaiable).sorted(Comparator.comparing(ChainBean::getPos)).forEach(c -> {
            logger.debug("绑定链给路由: " + c);
            Route routeTemp;
            if (c.getPath() == null || "".equals(c.getPath())) {
                routeTemp = router.route();
            } else {
                routeTemp = router.route(c.getPath());
            }
            Route route = routeTemp;

            // 指定method
            HttpMethod[] methods = c.getMethods();
            if (methods != null && methods.length > 0) {
                Arrays.stream(methods).forEach(m -> route.method(m));
            }

            // 判断是否为特殊链
            boolean gen = c.isGeneral();
            if (!gen) {
                // 特殊链
                route.handler(c.getHandler());
            } else {
                // 普通链，则启动链，并执行链
                route.handler(con -> {
                    // 调用链,处理结果
                    ChainExector.execute(c, con, vertx, re -> {
                        if (re.succeeded()) {
                            // 响应只支持JsonObject类型，具体内容通过JsonObject类型指定，页面渲染交给框架来做
                            JsonObject result = re.result();
                            con.request().response().putHeader("Content-type", "application/json;charset=utf-8");
                            con.request().response().end(result.toString());
                        } else {
                            con.request().response().end("ERROR");
                        }
                    });
                });
            }
        });

        // 处理请求
        server.requestHandler(router);

        // 监听端口
        server.listen(port);
    }
}
