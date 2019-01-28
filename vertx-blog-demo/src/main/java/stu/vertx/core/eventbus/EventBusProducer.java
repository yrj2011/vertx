package stu.vertx.core.eventbus;

import java.util.UUID;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.EventBus;

/**
 * 默认发布订阅的 生产者
 * 
 * @author lenovo
 *
 */
public class EventBusProducer extends AbstractVerticle {

	@Override
	public void start() throws Exception {

		// 每隔一秒发布一条消息
		vertx.setPeriodic(1000, handler -> {

			// 获取到事件总线
			EventBus eb = vertx.eventBus();

			// 创建消息的参数
			DeliveryOptions options = new DeliveryOptions();
			options.addHeader("Content-Type", "text/html;charset=UTF-8");

			// 发布消息
			eb.publish("hello", "world " + UUID.randomUUID().toString(), options);
		});
	}

}
