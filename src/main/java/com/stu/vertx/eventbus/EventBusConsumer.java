package com.stu.vertx.eventbus;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;

/**
 * 模拟发布订阅模式的消费者
 * 
 * @author lenovo
 *
 */
public class EventBusConsumer extends AbstractVerticle {

	private String name;

	public EventBusConsumer(String name) {
		this.name = name;
	}

	@Override
	public void start() throws Exception {
		EventBus eb = vertx.eventBus();

		// 消费消息
		eb.consumer("hello", handle -> {
			String msg = (String) handle.body();
			String cType = handle.headers().get("Content-Type");
			System.out.println(name + " 接收到的消息为：" + msg + " 头信息：" + cType);
		});
	}

}
