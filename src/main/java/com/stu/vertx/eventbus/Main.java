package com.stu.vertx.eventbus;

import io.vertx.core.Vertx;

public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		Vertx vertx = Vertx.vertx();
		vertx.deployVerticle(new EventBusProducer());
		vertx.deployVerticle(new EventBusConsumer());
	}

}
