package com.stu.vertx.route;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;

public class MyHttpServerWithRoute {
	

	
	public static void main(String[] args) {
		
		Vertx vertx = Vertx.vertx();
		HttpServer server = vertx.createHttpServer();
		
		Router router = Router.router(vertx); // 创建路由对象
		
		router.route("/index").handler(request -> {
			request.response().end("Index");
		});
		
		router.route("/hello").handler(request -> {
			request.response().end("Hello");
		});
		
		server.requestHandler(router::accept);
		server.listen(8888);
	}
	

}
