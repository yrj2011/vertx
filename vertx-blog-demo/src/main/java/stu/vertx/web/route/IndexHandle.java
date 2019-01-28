package stu.vertx.web.route;

import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;

public class IndexHandle implements Handler<RoutingContext> {

	@Override
	public void handle(RoutingContext event) {
		event.response().end("IndexController");
	}

}
