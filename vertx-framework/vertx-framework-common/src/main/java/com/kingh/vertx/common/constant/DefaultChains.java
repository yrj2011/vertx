package com.kingh.vertx.common.constant;

import com.kingh.vertx.common.bean.ChainBean;
import io.vertx.ext.web.handler.BodyHandler;

/**
 * 系统默认的链，可以便于创建ChainBean
 *
 * @author <a href="https://blog.csdn.net/king_kgh>Kingh</a>
 * @version 1.0
 * @date 2019/3/24 17:05
 */
public class DefaultChains {

    /**
     * Body Handler Chain
     */
    public static final ChainBean BODY_HANDLE_CHAIN = new ChainBean()
            .setAvaiable(true)
            .setGeneral(false)
            .setName("BodyHandler")
            .setHandler(BodyHandler.create())
            .setPos(0);


}
