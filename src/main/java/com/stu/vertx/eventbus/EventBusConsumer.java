package com.stu.vertx.eventbus;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;

public class EventBusConsumer extends AbstractVerticle {
	
	@Override
	public void start() throws Exception {
		EventBus eb = vertx.eventBus();
		eb.consumer("hello", handle->{
			String msg = (String) handle.body();
			System.out.println("接收到的消息为：" + msg);
		});
	}

}
