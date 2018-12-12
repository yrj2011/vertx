package com.stu.vertx.core.httpserver;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Verticle;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;

/**
 * 通过继承 AbstractVerticle 的方式实现Hello World
 * 
 * 【【【【这种方式更为常用】】】】
 * 
 * 通过这两个HelloWorld，能够明显的感觉到，使用Vertx要比使用Tomcat轻量级的多，比使用Netty的开发效率要高的多
 * 
 * @author lenovo
 *
 */
public class MyHttpServer2 extends AbstractVerticle {

	public static void main(String[] args) {
		// 创建服务
		Verticle verticle = new MyHttpServer2();
		Vertx vertx = Vertx.vertx();

		// 部署服务，会调用服务的start方法
		vertx.deployVerticle(verticle);
	}

	@Override
	public void start() throws Exception {
		// 在这里可以通过this.vertx获取到当前的Vertx
		Vertx vertx = this.vertx;

		// 下面可以实现helloworld中相同的功能

		// 创建一个HttpServer
		HttpServer server = vertx.createHttpServer();

		server.requestHandler(request -> {
			// 获取到response对象
			HttpServerResponse response = request.response();

			// 设置响应头
			response.putHeader("Content-type", "text/html;charset=utf-8");

			// 响应数据
			response.end("Hello World");

		});

		server.listen(8889);

	}

	@Override
	public void stop() throws Exception {
		super.stop();
	}

}
