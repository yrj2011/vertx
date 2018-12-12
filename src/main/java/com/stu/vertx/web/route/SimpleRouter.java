package com.stu.vertx.web.route;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;

/**
 * 简单的路由使用
 * 
 * @author lenovo
 *
 */
public class SimpleRouter extends AbstractVerticle {

	@Override
	public void start() throws Exception {

		// 创建HttpServer
		HttpServer server = vertx.createHttpServer();

		// 创建路由对象
		Router router = Router.router(vertx);

		// 监听/index地址
		router.route("/index").handler(request -> {
			request.response().write(Buffer.buffer("INDEX SUCCESS")).end();
		});

		// 把请求交给路由处理
		server.requestHandler(router::accept);
		server.listen(8888);
	}

	public static void main(String[] args) {
		Vertx.vertx().deployVerticle(new SimpleRouter());
	}

}
