package stu.vertx.core.eventbus;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.ext.bridge.BridgeOptions;
import io.vertx.ext.bridge.PermittedOptions;
import io.vertx.ext.eventbus.bridge.tcp.TcpEventBusBridge;

public class EventBusBridge extends AbstractVerticle {

    @Override
    public void start() throws Exception {

        TcpEventBusBridge bridge = TcpEventBusBridge.create(
                vertx,
                new BridgeOptions()
                        .addInboundPermitted(new PermittedOptions().setAddress("in"))
                        .addOutboundPermitted(new PermittedOptions().setAddress("out")));

        bridge.listen(7000, res -> {
            if (res.succeeded()) {
                System.out.println("Event Bus Listen 7000");
            } else {
                System.out.println("Event Bus Fail");
            }
        });
    }

    public static void main(String[] args) {
        Vertx.vertx().deployVerticle(new EventBusBridge());
    }
}
