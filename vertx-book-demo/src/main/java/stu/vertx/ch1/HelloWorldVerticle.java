package stu.vertx.ch1;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;

/**
 * 创建一个Verticle需要继承AbstractVerticle
 * Verticle是Vert.x运行的最小单元
 *
 * @author 孔冠华
 */
public class HelloWorldVerticle extends AbstractVerticle {

    /**
     * start 方法是在Verticle被部署到实例中的时候执行
     * 每个Verticle只会部署到实例中一次，因此，每个Verticle的start方法只会执行一次
     *
     * @throws Exception
     */
    @Override
    public void start() throws Exception {

        // 创建HttpServer，vertx是父类AbstractVerticle中提供的一个protected对象，在子类中可以直接使用
        HttpServer server = vertx.createHttpServer();

        // 接收用户请求，并交给请求处理器处理，这里使用了JDK8的新特性
        server.requestHandler(request -> {
            request.response().end("Hello");
        });

        // 监听8080端口
        server.listen(8080);
    }

    /**
     * 当Verticle被卸载的时候，stop方法会执行
     *
     * @throws Exception
     */
    @Override
    public void stop() throws Exception {
        super.stop();
    }

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new HelloWorldVerticle());
    }
}
