package stu.vertx.web.client;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.MultiMap;
import io.vertx.core.Vertx;
import io.vertx.ext.web.client.WebClient;

/**
 * 以get方式发送一个简单的HTTP请求,并且附带数据
 * <p>
 * get方式的请求附带数据有两种方式，第一种方式就是拼接在URL后面，第二种方式是本案例演示的
 *
 * @author kingh
 */
public class WebClientHttpGetWithData extends AbstractVerticle {

    @Override
    public void start() throws Exception {
        // 创建WebClient，用于发送HTTP或者HTTPS请求
        WebClient webClient = WebClient.create(vertx);
        // 以get方式请求远程地址
        webClient
                .getAbs("http://localhost/get")
                // 通过这种方式发送数据
                .addQueryParam("username", "admin")
                .addQueryParam("password", "admin123")
                .send(handle -> {
                    // 处理响应的结果
                    if (handle.succeeded()) {
                        // 这里拿到的结果就是一个HTML文本，直接打印出来
                        System.out.println(handle.result().bodyAsString());
                    }
                });
    }

    public static void main(String[] args) {
        Vertx.vertx().deployVerticle(new WebClientHttpGetWithData());
    }
}
