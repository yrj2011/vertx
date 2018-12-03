package com.stu.vertx.cluster.service.hello;

import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.serviceproxy.ServiceProxyBuilder;

/**
 * 对外暴露的核心接口
 * 
 * @author lenovo
 *
 */
@ProxyGen
@VertxGen
public interface Service {

	/**
	 * 这个方法是提供给自己使用，方便创建服务的实现类
	 * 
	 * @param vertx
	 * @return
	 */
	static Service create(Vertx vertx) {
		return new ServiceImpl(vertx);
	}

	/**
	 * 这个方法给消费者使用，便于消费者创建生产者的代理类，以此来消费生产者的服务
	 * 
	 * @param vertx
	 * @param address
	 * @return
	 */
	static Service createProxy(Vertx vertx, String address) {
		return new ServiceVertxEBProxy(vertx, address);
	}

	/**
	 * 第二种创建代理类的方式
	 * 
	 * @param vertx
	 * @param address
	 * @return
	 */
	static Service createProxy2(Vertx vertx, String address) {
		return new ServiceProxyBuilder(vertx).setAddress(address).build(Service.class);
	}

	/**
	 * 测试接口方法
	 * 
	 * @param name
	 */
	void sayHello(String name, JsonObject ETF, Handler<AsyncResult<JsonObject>> resultHandle);

}