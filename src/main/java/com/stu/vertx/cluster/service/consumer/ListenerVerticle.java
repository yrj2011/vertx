package com.stu.vertx.cluster.service.consumer;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;

/**
 * 监听http请求，并调用verticle来处理请求
 * 
 * @author lenovo
 *
 */
public class ListenerVerticle extends AbstractVerticle {

	@Override
	public void start() throws Exception {
		HttpServer httpServer = vertx.createHttpServer();

		httpServer.requestHandler(request -> {
			// 获取到response对象
			HttpServerResponse response = request.response();

			// 设置响应头
			response.putHeader("Content-type", "text/html;charset=utf-8");

			DeliveryOptions options = new DeliveryOptions();
			options.addHeader("action", "sayHello");
			
			JsonObject config = new JsonObject();
			config.put("ETF", new JsonObject().put("name", "xiaoming"));
			config.put("name", "xiaozhang");

			vertx.eventBus().<JsonObject>send("service", config, options, res -> {
				// 响应数据
				response.end(res.result().body().getString("msg"));
			});

		});

		httpServer.listen(1234);
	}

	public static void main(String[] args) {
		VertxOptions options = new VertxOptions();
		options.setClustered(true);
		options.setClusterHost("192.168.101.123");
		Vertx.clusteredVertx(options, resultHandler -> {
			Vertx vertx = resultHandler.result();
			vertx.deployVerticle(new ListenerVerticle());
		});
	}

}
