package stu.vertx.ch2;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.json.JsonArray;
import io.vertx.ext.auth.PRNG;
import io.vertx.ext.jdbc.JDBCClient;

import java.util.UUID;

public class StressVertx extends AbstractVerticle {

    private JDBCClient jdbcClient;

    private String sql ="insert into test(id) values (?)";

    @Override
    public void start() throws Exception {
        jdbcClient = new JdbcUtils(vertx).getDbClient();

        HttpServer server = vertx.createHttpServer();

        server.requestHandler(request->{
            jdbcClient.updateWithParams(sql, new JsonArray().add(UUID.randomUUID().toString()), res->{
                if(res.succeeded()) {
                    request.response().end("success");
                } else {
                    request.response().end("error");
                }
            });
        });

        server.listen(8080);


    }

    private static String generateId(PRNG rng, int length) {
        final byte[] bytes = new byte[length];
        rng.nextBytes(bytes);

        final char[] hex = new char[length * 2];
        for (int j = 0; j < length; j++) {
            int v = bytes[j] & 0xFF;
            hex[j * 2] = HEX[v >>> 4];
            hex[j * 2 + 1] = HEX[v & 0x0F];
        }

        return new String(hex);
    }
    private static final char[] HEX = "0123456789abcdef".toCharArray();

    public static void main(String[] args) {

        Vertx.vertx().deployVerticle(new StressVertx());
    }
}
