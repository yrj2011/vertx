package com.kingh.vertx.plugin.listener.chain;

import com.kingh.vertx.common.anno.Chain;
import com.kingh.vertx.common.anno.ChainConfiguration;
import com.kingh.vertx.common.bean.ChainBean;
import io.vertx.core.Vertx;
import io.vertx.ext.web.handler.CookieHandler;
import io.vertx.ext.web.handler.SessionHandler;
import io.vertx.ext.web.sstore.LocalSessionStore;

/**
 * session的处理器链
 *
 * @author <a href="https://blog.csdn.net/king_kgh>Kingh</a>
 * @version 1.0
 * @date 2019/4/11 9:56
 */
@ChainConfiguration
public class SessionHandlerChain {


    @Chain
    public ChainBean sessionHandlerChain(Vertx vertx) {
        return new ChainBean()
                .setAvaiable(true)
                .setName("SessionHandler")
                .setGeneral(false)
                .setHandler(SessionHandler.create(LocalSessionStore.create(vertx)))
                .setPos(3);
    }
}
