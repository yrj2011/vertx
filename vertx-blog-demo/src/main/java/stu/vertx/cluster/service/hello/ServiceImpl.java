package stu.vertx.cluster.service.hello;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

/**
 * 核心接口的实现类
 * 
 * @author lenovo
 *
 */
public class ServiceImpl implements Service {
	
	public ServiceImpl(Vertx vertx) {
		
	}
	
	@Override
	public void sayHello(String name, JsonObject ETF, Handler<AsyncResult<JsonObject>> resultHandle) {
		resultHandle.handle(Future.succeededFuture(ETF.put("msg", "SUCCESS")));
	}
}
