package com.kingh.vertx.plugin.listener.chain;

import com.kingh.vertx.common.anno.Chain;
import com.kingh.vertx.common.anno.ChainConfiguration;
import com.kingh.vertx.common.bean.ChainBean;
import com.kingh.vertx.core.config.Value;
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

    @Value(key = "chain.BodyHandler.avaiable")
    private Boolean avaiable = true;

    @Chain
    public ChainBean bodyHanlderChain() {
        System.out.println(avaiable);
        return new ChainBean()
                .setAvaiable(avaiable)
                .setName("BodyHandler")
                .setGeneral(false)
                .setHandler(BodyHandler.create())
                .setPos(0);
    }

}
