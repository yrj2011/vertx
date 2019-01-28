package stu.vertx.cluster.service.consumer;

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
 * 调用其他的微服务组件，有两种方式，第一种是通过EventBus的形式来实现，本例中就是如此。
 * 
 * 这种调用方式不依赖任何的接口，可以实现跨编程语言调用
 * 
 * 还有一种调用方式，通过ListenerVerticle2演示
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

			// 通过配置action参数，指定要走哪一个方法
			DeliveryOptions options = new DeliveryOptions();
			options.addHeader("action", "sayHello");

			// 这个是给方法传入的参数
			JsonObject config = new JsonObject();
			config.put("ETF", new JsonObject().put("name", "xiaoming"));
			config.put("name", "xiaozhang");

			// 通过eventBus调用方法
			vertx.eventBus().<JsonObject>send("service.demo.firstverticle", config, options, res -> {
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
