package stu.vertx.auth.jdbc;

import com.stu.vertx.jdbc.JdbcUtils;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.User;
import io.vertx.ext.auth.jdbc.JDBCAuth;
import io.vertx.ext.jdbc.JDBCClient;





public class JDBCAuthTest extends AbstractVerticle {

    private JDBCClient jdbcClient;

    @Override
    public void start() throws Exception {
        // 获取到数据库的客户端
        jdbcClient = new JdbcUtils(vertx).getDbClient();

        // 这个就是实现了AuthProvider接口的认证的类
        JDBCAuth auth = JDBCAuth.create(vertx, jdbcClient);

        // 指定SQL
        auth.setAuthenticationQuery(""); // 指定认证的SQL
        auth.setPermissionsQuery(""); // 指定获取用户权限的SQL
        auth.setRolesQuery(""); // 指定获取用户角色的SQL

        // 创建用于认证的参数
        JsonObject authInfo = new JsonObject();
        auth.authenticate(authInfo, res -> {
            if (res.succeeded()) {
                // 获取到授权接口
                User user = res.result();
                System.out.println("认证成功");
            } else {
                // 认证失败
                System.out.println("认证失败");
            }
        });

    }

    public static void main(String[] args) {
        Vertx.vertx().deployVerticle(new JDBCAuthTest());
    }
}
