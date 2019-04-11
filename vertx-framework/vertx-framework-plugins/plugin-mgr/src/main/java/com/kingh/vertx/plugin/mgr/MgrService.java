package com.kingh.vertx.plugin.mgr;

import com.kingh.vertx.common.anno.Verticle;
import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.Vertx;

/**
 * @author <a href="https://blog.csdn.net/king_kgh>Kingh</a>
 * @version 1.0
 * @date 2019/4/11 11:21
 */
@ProxyGen
@VertxGen
public interface MgrService {

    String address = "com.kingh.vertx.plugin.mgr.20190411";

    static MgrService create(Vertx vertx) {
        return new MgrServiceImpl(vertx);
    }
}
