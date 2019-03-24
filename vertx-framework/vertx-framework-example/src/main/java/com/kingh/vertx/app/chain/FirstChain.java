package com.kingh.vertx.app.chain;

import com.kingh.vertx.common.anno.Chain;
import com.kingh.vertx.common.anno.ChainConfiguration;
import com.kingh.vertx.common.bean.ChainBean;
import com.kingh.vertx.common.bean.ServiceBean;
import com.kingh.vertx.core.context.ApplicationContext;
import com.kingh.vertx.core.context.ApplicationContextHolder;
import io.vertx.core.http.HttpMethod;

import java.util.LinkedList;

/**
 * @author <a href="https://blog.csdn.net/king_kgh>Kingh</a>
 * @version 1.0
 * @date 2019/3/20 16:11
 */
@ChainConfiguration
public class FirstChain {

    private ApplicationContext ac = ApplicationContextHolder
            .getApplicationContext();

    @Chain(path = "/world", name = "测试链", methods = {HttpMethod.GET})
    public ChainBean chain() {

        ChainBean bean = new ChainBean();

        LinkedList<ServiceBean> service = new LinkedList<>();

        service.addLast(ac.service("CoreServiceVerticle:hello"));
        service.addLast(ac.service("CoreServiceVerticle:world"));

        bean.setServices(service);

        return bean;
    }


    @Chain(path = "/hello", name = "测试链", methods = {HttpMethod.GET}, general = false)
    public ChainBean hello() {

        ChainBean bean = new ChainBean();

        bean.setHandler(rc -> {
            rc.request().response().end("Hello");
        });

        return bean;
    }

}
