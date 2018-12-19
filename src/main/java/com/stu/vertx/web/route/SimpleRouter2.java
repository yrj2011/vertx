package com.stu.vertx.web.route;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.CookieHandler;
import io.vertx.ext.web.handler.SessionHandler;
import io.vertx.ext.web.handler.StaticHandler;
import io.vertx.ext.web.sstore.LocalSessionStore;

/**
 * 简单的路由使用
 * 
 * @author lenovo
 *
 */
public class SimpleRouter2 extends AbstractVerticle {

	@Override
	public void start() throws Exception {

		// 创建HttpServer
		HttpServer server = vertx.createHttpServer();

		// 创建路由对象
		Router router = Router.router(vertx);

		// post请求的数据处理
		router.route().handler(BodyHandler.create());
		// 静态资源的处理
		router.route().handler(StaticHandler.create());
		// cookie 的处理
		router.route().handler(CookieHandler.create());
		// session的处理
	    router.route().handler(SessionHandler.create(LocalSessionStore.create(vertx)));
		
	    // 监听/index地址
		router.route("/index").handler(new IndexHandle());

		// 把请求交给路由处理
		server.requestHandler(router::accept);
		server.listen(8880);
	}
	
	public static void main(String[] args) {
		Vertx.vertx().deployVerticle(new SimpleRouter2());
	}

}
