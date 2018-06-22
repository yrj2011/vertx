package com.stu.vertx.hello;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;

public class Hello {
	
	public static void main(String[] args) {
		
		Vertx vertx = Vertx.vertx();
		
		HttpServer server = vertx.createHttpServer();
		
		server.requestHandler(request -> {
			// 获取到response对象
			HttpServerResponse response = request.response();
			
			// 设置响应头
			response.putHeader("Content-type", "text/html;charset=utf-8");
			
			// 响应数据
			response.end("Hello World");
			
		});
		
		server.listen(8888);
	}

}
