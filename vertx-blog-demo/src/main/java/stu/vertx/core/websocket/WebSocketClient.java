package stu.vertx.core.websocket;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpClient;

/**
 * WebSocket的客户端程序
 * 
 * 向服务端发送一次数据，数据内容为 hi
 * @author lenovo
 *
 */
public class WebSocketClient extends AbstractVerticle {

	@Override
	public void start() throws Exception {
		HttpClient client = vertx.createHttpClient();
		client.websocket(1258,"localhost","/", websocket -> {
			websocket.write(Buffer.buffer("hi"));
		});
	}

	public static void main(String[] args) {
		Vertx vertx = Vertx.vertx();
		vertx.deployVerticle(new WebSocketClient());
	}

}
