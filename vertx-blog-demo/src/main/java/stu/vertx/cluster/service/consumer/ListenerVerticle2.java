package stu.vertx.cluster.service.consumer;

import stu.vertx.cluster.service.hello.Service;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;

/**
 * 监听http请求，并调用verticle来处理请求
 * 
 * 调用其他的微服务组件，有两种方式，第一种是通过EventBus的形式来实现。
 * 
 * 还有一种调用方式，通过Service接口中提供的静态方法来调用，这种调用使用更方便，但通用性不好，不能够跨编程语言调用
 * 
 * @author lenovo
 *
 */
public class ListenerVerticle2 extends AbstractVerticle {

	@Override
	public void start() throws Exception {
		HttpServer httpServer = vertx.createHttpServer();

		httpServer.requestHandler(request -> {

			// 获取到response对象
			HttpServerResponse response = request.response();

			// 设置响应头
			response.putHeader("Content-type", "text/html;charset=utf-8");

			// 直接创建代理类，这样调用需要使用具体的接口，而且只能是同类语言才能调用，第一种方式可以实现跨语言调用
			Service s = Service.createProxy(vertx, "");
			s.sayHello("", new JsonObject(), res -> {
				response.end(res.result().getString("msg"));
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
			vertx.deployVerticle(new ListenerVerticle2());
		});
	}

}
