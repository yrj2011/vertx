package stu.vertx.web.client;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;

public class WebServer extends AbstractVerticle {

    @Override
    public void start() throws Exception {

        HttpServer server = vertx.createHttpServer();
        Router router = Router.router(vertx);

        router.route("/get").handler(request -> {
            String username = request.request().getParam("username");
            String password = request.request().getParam("password");

            System.out.println(username + " " + password);

            request.response().end("get request success");
        });

        router.post("/post").handler(request -> {
            request.request().bodyHandler(body->{
                System.out.println(body.toJsonObject().toString());
                JsonObject responseData = new JsonObject()
                        .put("msg","success");
                request.response().end(responseData.toString());
            });
        });

        server.requestHandler(router::accept);
        server.listen(80);

    }

    public static void main(String[] args) {
        Vertx.vertx().deployVerticle(new WebServer());
    }
}
