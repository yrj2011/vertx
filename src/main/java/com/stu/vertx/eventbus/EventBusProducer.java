package com.stu.vertx.eventbus;

import java.util.UUID;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;

public class EventBusProducer extends AbstractVerticle {

	@Override
	public void start() throws Exception {
		
		vertx.setPeriodic(1000, handler->{
			EventBus eb = vertx.eventBus();
			eb.publish("hello", "world " + UUID.randomUUID().toString());
		});
	}

}
