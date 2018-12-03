package com.stu.vertx.core.httpserver;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;

/**
 * Vertx 创建一个最简单的HttpServer，当用户请求时返回Hello World
 * 
 * @author lenovo
 *
 */
public class MyHttpServer {
	
	public static void main(String[] args) {
		
		Vertx vertx = Vertx.vertx();
		
		// 创建一个HttpServer
		HttpServer server = vertx.createHttpServer();
		
		server.requestHandler(request-> {
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
