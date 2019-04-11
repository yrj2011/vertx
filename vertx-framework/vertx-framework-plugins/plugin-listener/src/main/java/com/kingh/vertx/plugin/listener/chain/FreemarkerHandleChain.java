package com.kingh.vertx.plugin.listener.chain;

import com.kingh.vertx.common.anno.Chain;
import com.kingh.vertx.common.anno.ChainConfiguration;
import com.kingh.vertx.common.bean.ChainBean;
import com.kingh.vertx.core.config.Value;
import io.vertx.core.Vertx;
import io.vertx.ext.web.common.template.TemplateEngine;
import io.vertx.ext.web.handler.TemplateHandler;
import io.vertx.ext.web.templ.freemarker.FreeMarkerTemplateEngine;

/**
 * @author <a href="https://blog.csdn.net/king_kgh>Kingh</a>
 * @version 1.0
 * @date 2019/4/11 11:12
 */
@ChainConfiguration
public class FreemarkerHandleChain {

    @Value(key = "chain.FreemarkerHandler.avaiable")
    private Boolean avaiable = false;

    @Chain
    public ChainBean createChain(Vertx vertx) {
        TemplateEngine freeMarkerTemplateEngine = FreeMarkerTemplateEngine.create(vertx);
        TemplateHandler templateHandler = TemplateHandler.create(freeMarkerTemplateEngine);
        return new ChainBean().setHandler(templateHandler)
                .setPath("/page/*")
                .setAvaiable(avaiable)
                .setName("FreemarkerHandler")
                .setGeneral(false)
                .setPos(10);
    }


}
