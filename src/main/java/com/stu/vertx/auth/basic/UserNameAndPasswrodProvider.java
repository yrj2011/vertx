package com.stu.vertx.auth.basic;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.AuthProvider;
import io.vertx.ext.auth.User;

/**
 * 自定义认证
 * 
 * @author lenovo
 *
 */
public class UserNameAndPasswrodProvider implements AuthProvider {

	@Override
	public void authenticate(JsonObject authInfo, Handler<AsyncResult<User>> resultHandler) {
		// 实例化授权对象，可以将认证信息传入
		User user = new MyUser(authInfo);
		// 所有情况均成功返回，并将授权对象响应回去
		resultHandler.handle(Future.succeededFuture(user));
	}

}
