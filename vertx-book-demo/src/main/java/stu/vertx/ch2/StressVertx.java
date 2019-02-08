package stu.vertx.ch2;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.JsonArray;
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

    public static void main(String[] args) {
        Vertx.vertx().deployVerticle(new StressVertx());
    }
}
