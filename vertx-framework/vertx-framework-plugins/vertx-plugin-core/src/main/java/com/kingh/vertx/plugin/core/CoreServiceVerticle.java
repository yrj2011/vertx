package com.kingh.vertx.plugin.core;

import com.kingh.vertx.common.anno.Verticle;
import io.vertx.core.AbstractVerticle;
import io.vertx.serviceproxy.ServiceBinder;

/**
 * @author <a href="https://blog.csdn.net/king_kgh>Kingh</a>
 * @version 1.0
 * @date 2019/3/22 19:27
 */
@Verticle(name = "CoreServiceVerticle", address = CoreService.address, autoDeploy = true)
public class CoreServiceVerticle extends AbstractVerticle {

    @Override
    public void start() throws Exception {
        CoreService s = CoreService.create(vertx);
        new ServiceBinder(vertx).setAddress(CoreService.address).register(CoreService.class, s);
    }
}
