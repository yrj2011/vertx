package com.stu.vertx.hello;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Verticle;
import io.vertx.core.Vertx;

public class MyHttpServer2 extends AbstractVerticle {

	
	public static void main(String[] args) {
		Verticle verticle = new MyHttpServer2();
		Vertx vertx = Vertx.vertx();
		
		vertx.deployVerticle(verticle);
		
	}
	
	@Override
	public void start() throws Exception {
		System.out.println("Verticle is running ...");
	}
	
	@Override
	public void stop() throws Exception {
		super.stop();
	}
	
}
