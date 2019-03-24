package com.kingh.vertx.plugin.listener.chain;

import com.kingh.vertx.common.anno.Chain;
import com.kingh.vertx.common.anno.ChainConfiguration;
import com.kingh.vertx.common.bean.ChainBean;
import io.vertx.ext.web.handler.BodyHandler;

/**
 * BodyHandler
 *
 * @author <a href="https://blog.csdn.net/king_kgh>Kingh</a>
 * @version 1.0
 * @date 2019/3/24 14:39
 */
@ChainConfiguration
public class BodyHandlerChain {

    @Chain
    public ChainBean bodyHanlderChain() {
        return new ChainBean()
                .setAvaiable(true)
                .setName("BodyHandler")
                .setGeneral(false)
                .setHandler(BodyHandler.create())
                .setPos(0);
    }

}
