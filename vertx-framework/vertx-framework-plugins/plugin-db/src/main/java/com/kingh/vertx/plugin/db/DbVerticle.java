package com.kingh.vertx.plugin.db;

import com.kingh.vertx.core.anno.VerticleCompoment;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.serviceproxy.ServiceBinder;

/**
 * @author <a href="https://blog.csdn.net/king_kgh>Kingh</a>
 * @version 1.0
 * @date 2019/3/11 11:02
 */
@VerticleCompoment(address = DbService.address)
public class DbVerticle extends AbstractVerticle {

    @Override
    public void start(Future<Void> startFuture) throws Exception {

        DbService s = DbService.create(vertx);
        new ServiceBinder(vertx).setAddress(DbService.address).register(DbService.class, s);

        super.start(startFuture);
    }
}
