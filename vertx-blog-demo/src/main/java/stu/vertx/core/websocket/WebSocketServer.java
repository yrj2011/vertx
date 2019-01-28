package stu.vertx.core.websocket;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServer;

/**
 * WebSocket的服务端，使用Vertx来实现WebSocket非常简单。
 * 
 * @author lenovo
 *
 */
public class WebSocketServer extends AbstractVerticle {

	@Override
	public void start() throws Exception {

		HttpServer server = vertx.createHttpServer();

		server.websocketHandler(webSocket -> {
			String path = webSocket.path();
			System.out.println("监听到请求的地址：" + path);

			webSocket.handler(buffer -> {
				System.out.println("接收到客户端的数据为：" + buffer.toString());
				webSocket.write(Buffer.buffer("ServerAccept"));
			});

		});

		server.listen(1258);
	}

	public static void main(String[] args) {
		Vertx.vertx().deployVerticle(new WebSocketServer());
	}
}
