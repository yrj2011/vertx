package com.kingh.vertx.plugin.listener.chain;

import com.kingh.vertx.common.anno.Chain;
import com.kingh.vertx.common.anno.ChainConfiguration;
import com.kingh.vertx.common.bean.ChainBean;
import io.vertx.ext.web.handler.FaviconHandler;

/**
 * 浏览器图标的处理器链
 *
 * @author <a href="https://blog.csdn.net/king_kgh>Kingh</a>
 * @version 1.0
 * @date 2019/4/11 9:56
 */
@ChainConfiguration
public class FaviconHandlerChain {


    @Chain
    public ChainBean faviconHandlerChain() {
        return new ChainBean()
                .setAvaiable(true)
                .setName("FaviconHandler")
                .setGeneral(false)
                .setHandler(FaviconHandler.create())
                .setPos(1);
    }
}
