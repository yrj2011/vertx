package stu.vertx.web.client;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.client.WebClient;

/**
 * 以post方式发送一个简单的HTTP请求,附带数据
 *
 * @author kingh
 */
public class WebClientHttpPostWithData extends AbstractVerticle {

    @Override
    public void start() throws Exception {
        // 创建WebClient，用于发送HTTP或者HTTPS请求
        WebClient webClient = WebClient.create(vertx);

        // 构造请求的数据
        JsonObject data = new JsonObject()
                .put("username", "admin")
                .put("password", "admin123");

        // 以get方式请求远程地址
        webClient.postAbs("http://localhost/post")
                .sendJsonObject(data, handle -> {
                    // 处理响应的结果
                    if (handle.succeeded()) {
                        // 这里拿到的结果就是一个HTML文本，直接打印出来
//                        handle.result().bodyAsJsonObject() 可以解析Json数据
                        System.out.println(handle.result().bodyAsString());
                    }
                });
    }

    public static void main(String[] args) {
        Vertx.vertx().deployVerticle(new WebClientHttpPostWithData());
    }
}
