package com.kingh.vertx.core.service.factory;

import com.kingh.vertx.core.service.CoreService;
import com.kingh.vertx.core.service.impl.CoreServiceImpl;
import io.vertx.core.Vertx;

/**
 * @author <a href="https://blog.csdn.net/king_kgh>Kingh</a>
 * @version 1.0
 * @date 2019/3/11 19:48
 */
public class CoreServiceFactory {

    private volatile CoreService instance;

    public CoreService getInstacne(Vertx vertx) {
        if(instance == null) {
            synchronized (CoreServiceFactory.class) {
                if(instance == null) {
                    instance = new CoreServiceImpl(vertx);
                }
            }
        }
        return instance;
    }

}
