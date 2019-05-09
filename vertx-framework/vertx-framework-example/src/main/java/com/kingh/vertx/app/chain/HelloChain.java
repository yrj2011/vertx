package com.kingh.vertx.app.chain;

import com.kingh.vertx.common.anno.Chain;
import com.kingh.vertx.common.anno.ChainConfiguration;
import com.kingh.vertx.common.bean.ChainBean;
import com.kingh.vertx.common.bean.ServiceBean;
import com.kingh.vertx.core.config.Value;
import com.kingh.vertx.core.context.ApplicationContext;
import com.kingh.vertx.core.context.ApplicationContextHolder;

import java.util.LinkedList;

/**
 * @author <a href="https://blog.csdn.net/king_kgh>Kingh</a>
 * @version 1.0
 * @date 2019/5/9 11:18
 */
@ChainConfiguration
public class HelloChain {

    @Chain
    public ChainBean hello() {
        ChainBean chain = new ChainBean();
        chain.setName("hello");
        chain.setPath("/hello");

        LinkedList<ServiceBean> services = new LinkedList<>();
        services.addLast(ApplicationContextHolder.getApplicationContext().service("CoreServiceVerticle:hello"));
        services.addLast(ApplicationContextHolder.getApplicationContext().service("CoreServiceVerticle:world"));

        chain.setServices(services);
        return chain;
    }

}
