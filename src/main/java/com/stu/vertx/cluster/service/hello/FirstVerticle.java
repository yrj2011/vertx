package com.stu.vertx.cluster.service.hello;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.serviceproxy.ServiceBinder;

/**
 * 第一个可对外提供服务的Verticle， 提供一个sayHello方法
 * 
 * @author lenovo
 *
 */
public class FirstVerticle extends AbstractVerticle {
	
	public static final String address = "service.demo.firstverticle";

	public static void main(String[] args) {

		VertxOptions options = new VertxOptions();
		options.setClustered(true);
		options.setClusterHost("192.168.101.123");
		Vertx.clusteredVertx(options, resultHandler -> {
			Vertx vertx = resultHandler.result();
			vertx.deployVerticle(new FirstVerticle());
		});
	}

	@Override
	public void start() throws Exception {
		Service s = Service.create(vertx);
		new ServiceBinder(vertx).setAddress(address).register(Service.class, s);
		// ProxyHelper.registerService(Service.class, vertx, s, "");
	}

}
