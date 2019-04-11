package com.kingh.vertx.plugin.mgr;

import io.vertx.core.Vertx;

/**
 * @author <a href="https://blog.csdn.net/king_kgh>Kingh</a>
 * @version 1.0
 * @date 2019/4/11 11:22
 */
public class MgrServiceImpl implements MgrService {

    private Vertx vertx;

    public MgrServiceImpl(Vertx vertx) {
        this.vertx = vertx;
    }

}
