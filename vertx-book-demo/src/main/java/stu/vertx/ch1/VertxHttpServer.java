package stu.vertx.ch1;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;

/**
 * 使用Vert.x实现一个简单的HTTP服务，当浏览器访问8080端口时，给浏览器返回Hello World！
 *
 * @author 孔冠华
 */
public class VertxHttpServer extends AbstractVerticle {

    @Override
    public void start() throws Exception {
        // 创建一个HttpServer
        HttpServer server = vertx.createHttpServer();

        // 异步接受客户端请求
        server.requestHandler(handle -> {

            // 当请求到来，给客户端相应HelloWorld
            handle.response().end("Hello World!");
        });

        // 监听8080端口
        server.listen(8080);
    }

    public static void main(String[] args) {
        // 创建Vertx实例，并部署Verticle
        Vertx.vertx().deployVerticle(new VertxHttpServer());
    }
}
