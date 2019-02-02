package stu.vertx.auth.shiro;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.User;
import io.vertx.ext.auth.shiro.ShiroAuth;
import io.vertx.ext.auth.shiro.ShiroAuthOptions;
import io.vertx.ext.auth.shiro.ShiroAuthRealmType;
import org.apache.shiro.realm.Realm;

/**
 * 使用shiro实现认证和授权的演示案例
 *
 * @author lenovo
 */
public class ShiroAuthVerticle extends AbstractVerticle {

    @Override
    public void start() throws Exception {
        JsonObject config = new JsonObject().put("properties_path", "classpath:auth.properties");
        ShiroAuth provider = ShiroAuth.create(vertx, new ShiroAuthOptions().setType(ShiroAuthRealmType.PROPERTIES).setConfig(config));

        JsonObject data = new JsonObject()
                .put("username", "admin")
                .put("password", "admin");
        provider.authenticate(data, auth -> {
            if (auth.succeeded()) {
                System.out.println("认证成功");
                User user = auth.result();
                user.isAuthorized("role:manager",res->{
                    if(res.result()) {
                        System.out.println("授权成功");
                    } else {
                        System.out.println("没有权限");
                    }
                });
            } else {
                System.out.println("认证失败");
            }
        });
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }

    public static void main(String[] args) {
        Vertx.vertx().deployVerticle(new ShiroAuthVerticle());
    }
}
