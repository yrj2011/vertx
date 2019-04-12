package com.kingh.vertx.plugin.mgr.chains;

import com.kingh.vertx.common.anno.Chain;
import com.kingh.vertx.common.anno.ChainConfiguration;
import com.kingh.vertx.common.bean.ChainBean;
import com.kingh.vertx.core.context.ApplicationContextHolder;
import io.vertx.core.json.JsonArray;

import java.util.List;

/**
 * @author <a href="https://blog.csdn.net/king_kgh>Kingh</a>
 * @version 1.0
 * @date 2019/4/11 11:28
 */
@ChainConfiguration
public class IndexHandler {

    @Chain(name = "index", description = "跳转到首页", path = "/index", general = false)
    public ChainBean index() {
        return new ChainBean().setHandler(r -> {
            r.reroute("/page/index.ftl");
        });
    }


    @Chain(name = "chainsList", description = "所有的链列表", path = "/chains", general = false)
    public ChainBean chainsList() {
        return new ChainBean().setHandler(r -> {
            List<ChainBean> chains = ApplicationContextHolder.getApplicationContext().chains();
            r.request().response().putHeader("Content-Type", "application/json;charset=utf-8");
            r.request().response().end(new JsonArray(chains).toString());
        });
    }
}
