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
 */
public class UserNameAndPasswordProvider implements AuthProvider {

    @Override
    public void authenticate(JsonObject authInfo, Handler<AsyncResult<User>> resultHandler) {

        // authInfo中存储了认证需要的相关信息，由调用者传入
        String username = authInfo.getString("username");
        String password = authInfo.getString("password");

        // 判断用户名和密码是否正确
        if ("admin".equals(username) && "admin".equals(password)) {
            // 密码验证通过，需要实例化授权对象，并在Future中响应给调用者

            // 实例化授权对象，可以将认证信息传入
            User user = new MyUser(authInfo);
            // 所有情况均成功返回，并将授权对象响应回去
            resultHandler.handle(Future.succeededFuture(user));
        } else {
            // 密码验证不通过，响应认证失败
            resultHandler.handle(Future.failedFuture("用户名或者密码错误"));
        }

    }

}
