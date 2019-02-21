package stu.vertx.web.client;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.ext.web.client.WebClient;

/**
 * 以get方式发送一个简单的HTTP请求
 *
 * @author kingh
 */
public class WebClientHttpGet extends AbstractVerticle {

    @Override
    public void start() throws Exception {
        // 创建WebClient，用于发送HTTP或者HTTPS请求
        WebClient webClient = WebClient.create(vertx);
        // 以get方式请求远程地址
        webClient.getAbs("http://localhost/get").send(handle -> {
            // 处理响应的结果
            if (handle.succeeded()) {
                // 这里拿到的结果就是一个HTML文本，直接打印出来
                System.out.println(handle.result().bodyAsString());
            }
        });
    }

    public static void main(String[] args) {
        Vertx.vertx().deployVerticle(new WebClientHttpGet());
    }
}
