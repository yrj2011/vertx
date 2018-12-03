package com.stu.vertx.cluster.service.hello;

import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

/**
 * 对外暴露的核心接口
 * 
 * @author lenovo
 *
 */
@ProxyGen
@VertxGen
public interface Service {

	static Service create(Vertx vertx) {
		return new ServiceImpl(vertx);
	}

	static Service createProxy(Vertx vertx, String address) {
		return new ServiceVertxEBProxy(vertx, address);
	}

	/**
	 * 测试接口方法
	 * 
	 * @param name
	 */
	void sayHello(String name, JsonObject ETF, Handler<AsyncResult<JsonObject>> resultHandle);

}