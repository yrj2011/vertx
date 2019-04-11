package com.kingh.vertx.plugin.mgr;

import com.kingh.vertx.common.anno.Verticle;
import io.vertx.core.AbstractVerticle;
import io.vertx.serviceproxy.ServiceBinder;

/**
 * @author <a href="https://blog.csdn.net/king_kgh>Kingh</a>
 * @version 1.0
 * @date 2019/4/11 11:20
 */
@Verticle(name = "MgrVerticle", address = MgrService.address, autoDeploy = true, service = MgrService.class)
public class MgrVerticle extends AbstractVerticle {

    @Override
    public void start() throws Exception {
        MgrService s = MgrService.create(vertx);
        new ServiceBinder(vertx).setAddress(MgrService.address).register(MgrService.class, s);
    }
}
