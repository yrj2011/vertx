package com.kingh.vertx.plugin.listener.chain;

import com.kingh.vertx.common.anno.Chain;
import com.kingh.vertx.common.anno.ChainConfiguration;
import com.kingh.vertx.common.bean.ChainBean;
import io.vertx.ext.web.handler.CookieHandler;
import io.vertx.ext.web.handler.StaticHandler;

/**
 * cookie的处理器链
 *
 * @author <a href="https://blog.csdn.net/king_kgh>Kingh</a>
 * @version 1.0
 * @date 2019/4/11 9:56
 */
@ChainConfiguration
public class StaticHandlerChain {

    @Chain
    public ChainBean staticHandlerChain() {
        return new ChainBean()
                .setAvaiable(true)
                .setName("StaticHandler")
                .setGeneral(false)
                .setHandler(StaticHandler.create())
                .setPos(4);
    }
}
