package stu.vertx.core.eventbus;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;

/**
 * 模拟发布订阅模式的消费者
 * 
 * @author lenovo
 *
 */
public class EventBusConsumer extends AbstractVerticle {

	// 使用名字可以标识多个不同的消费者
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
		
		// 第二种写法
//		MessageConsumer<String> consumer = eb.consumer("hello");
//		consumer.handler(handler->{
//			
//		});
	}

}
