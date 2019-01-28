package stu.vertx.web.route;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

/**
 * Vertx的路由，需要引入Vertx-Web模块
 * 
 * @author lenovo
 *
 */
public class HttpServerWithRoute extends AbstractVerticle {

	@Override
	public void start() throws Exception {

		// 创建一个HttpServer
		HttpServer server = vertx.createHttpServer();

		// 创建路由对象
		Router router = Router.router(vertx); 

		// 这里可以处理post请求的body体数据
		router.route().handler(BodyHandler.create());

		// 创建路由规则，当在浏览器中输入 localhost:8888/index时匹配
		router.route("/index/*").order(2).handler(request -> {
			// request.response().end("Index");
			System.out.println(1);
			request.next(); // 调下一个匹配规则
		});

		router.route("/index/main").order(-1).handler(request -> {
			// request.response().end("IndexMain");
			System.out.println("2");
			request.next(); // 调下一个匹配规则
		});
		

		// 通过route的参数限定请求的方法
		router.route(HttpMethod.GET, "/method").handler(request -> {
			String param = request.request().getParam("param");
			System.out.println("接收到用户传递的参数为：" + param);
			request.response().end("method");
		});

		// 获取参数
		router.route(HttpMethod.GET, "/method/:user/:pass").handler(request -> {
			String user = request.request().getParam("user");
			String pass = request.request().getParam("pass");

			request.response().putHeader("Content-type", "text/html;charset=utf-8")
					.end("接收到的用户名为：" + user + " 接收到的密码为：" + pass);
		});

		// 限制使用post方法
		router.post("/post").handler(request -> {
			String res = request.getBodyAsString();
			System.out.println(res);
			request.response().end("post");
		});

		// 使用正则表达式匹配
		router.routeWithRegex("/index/aaa/*").handler(request -> {
			request.response().end("Regex");
		});

		// 创建路由规则，当在浏览器中输入 localhost:8888/hello时匹配
		router.route("/hello").handler(request -> {
			request.response().end("Hello");
		});

		// 把请求交给路由处理
		// server.requestHandler(router::accept);
		server.requestHandler(new Handler<HttpServerRequest>() {

			@Override
			public void handle(HttpServerRequest event) {
				router.accept(event);
			}
		});
		server.listen(8881);
	}

	public static void main(String[] args) {
		Vertx vertx = Vertx.vertx();
		vertx.deployVerticle(new HttpServerWithRoute());
	}

}
