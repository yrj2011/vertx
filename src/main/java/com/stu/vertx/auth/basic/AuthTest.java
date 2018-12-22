package com.stu.vertx.auth.basic;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.AuthProvider;
import io.vertx.ext.auth.User;

/**
 * 认证与授权测试
 * 
 * @author lenovo
 *
 */
public class AuthTest extends AbstractVerticle {

	@Override
	public void start() throws Exception {

	    // 创建认证的Provider
		AuthProvider provider = new UserNameAndPasswordProvider();
		JsonObject authInfo = new JsonObject().put("username", "admin").put("password", "admin");

		// 调用认证方法，将认证的数据传入
		provider.authenticate(authInfo, res -> {
			if (res.succeeded()) {
				// 认证通过，可以获取到User对象，通过User对象可以进行权限校验
				User user = res.result();

				// 授权
				user.isAuthorized("save:user", auth -> {
					if (auth.succeeded()) {
						System.out.println("授权成功");
					} else {
						System.out.println("授权失败");
					}
				});
			} else {
				System.out.println("认证失败！");
			}
		});

	}

	public static void main(String[] args) {
		Vertx.vertx().deployVerticle(new AuthTest());
	}

}
