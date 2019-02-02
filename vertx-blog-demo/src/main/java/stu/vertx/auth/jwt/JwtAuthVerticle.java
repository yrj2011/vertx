package stu.vertx.auth.jwt;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.PubSecKeyOptions;
import io.vertx.ext.auth.jwt.JWTAuth;
import io.vertx.ext.auth.jwt.JWTAuthOptions;
import io.vertx.ext.auth.jwt.impl.JWTUser;
import io.vertx.ext.jwt.JWTOptions;
import org.apache.commons.lang3.StringUtils;
import stu.vertx.auth.basic.UserNameAndPasswordProvider;

/**
 * java web token 认证演示
 *
 * @author lenovo
 */
public class JwtAuthVerticle extends AbstractVerticle {

    private JWTAuthOptions config = new JWTAuthOptions()
            .addPubSecKey(new PubSecKeyOptions()
                    .setAlgorithm("HS256")
                    .setPublicKey("public key")
                    .setSymmetric(true));

    private JWTAuth provider = JWTAuth.create(vertx, config);

    @Override
    public void start() throws Exception {

        HttpServer server = vertx.createHttpServer();

        // 处理客户端请求
        server.requestHandler(request -> {
            JsonObject req = JwtAuthVerticle.parseQuery(request.query());

            // 判断用户是否带token来认证，如果带token，就直接通过token来认证，否则认为是第一次认证，通过用户名和密码的方式进行认证
            String jwt = req.getString("jwt");
            if (StringUtils.isBlank(jwt)) {

                // 先使用默认的用户名密码进行认证
                UserNameAndPasswordProvider p = new UserNameAndPasswordProvider();
                p.authenticate(req, auth -> {
                    if (auth.succeeded()) {
                        // 认证通过之后，再生成token，以后就使用token进行认证
                        JsonObject data = new JsonObject()
                                .put("userId", "admin");

                        String token = provider.generateToken(data, new JWTOptions());

                        request.response().end(token);
                    } else {
                        System.out.println("认证失败");
                        request.response().end("认证失败，请输出正确的用户名和密码");
                    }
                });
            } else {

                // 使用jwt进行认证
                provider.authenticate(new JsonObject().put("jwt", jwt), auth -> {
                    if (auth.succeeded()) {
                        JWTUser user = (JWTUser) auth.result();
                        JsonObject authData = user.principal();
                        String userId = authData.getString("");
                        System.out.println(userId);
                        request.response().end("认证成功！");
                    } else {
                        System.out.println("认证失败");
                        request.response().end("token无效");
                    }
                });
            }
        });
        server.listen(8080);
    }


    /**
     * 把URL后跟的查询字符串转成json对象
     *
     * @param query
     * @return
     */
    public static JsonObject parseQuery(String query) {
        JsonObject data = new JsonObject();
        String[] params = query.split("&");
        for (String param : params) {
            String[] k = param.split("=");
            data.put(k[0], k[1]);
        }
        return data;
    }

    public static void main(String[] args) {
        Vertx.vertx().deployVerticle(new JwtAuthVerticle());
    }
}
