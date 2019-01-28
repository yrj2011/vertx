package stu.vertx.core.eventbus;

import io.vertx.core.Vertx;

public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		Vertx vertx = Vertx.vertx();
		
		// 一个生产者发布消息
		vertx.deployVerticle(new EventBusProducer());
		
		// 模拟多个消费者，订阅消息
		vertx.deployVerticle(new EventBusConsumer("1"));
		vertx.deployVerticle(new EventBusConsumer("2"));
	}

}
