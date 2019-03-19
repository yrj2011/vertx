package com.kingh.vertx.core.service.impl;

import com.kingh.vertx.common.bean.ChainBean;
import com.kingh.vertx.common.bean.ServiceBean;
import com.kingh.vertx.common.bean.VerticleBean;
import com.kingh.vertx.core.service.CoreService;
import io.vertx.core.*;
import io.vertx.core.json.JsonObject;

import java.util.List;

/**
 * 核心服务实现类
 *
 * @author <a href="https://blog.csdn.net/king_kgh>Kingh</a>
 * @version 1.0
 * @date 2019/3/11 17:40
 */
public class CoreServiceImpl implements CoreService {

    private Vertx vertx;

    // 组件 （包含服务）
    private List<VerticleBean> compoments;

    // 服务 （最小执行单元）
    private List<ServiceBean> services;

    // 功能 （多个组件构成一个功能）
    private List<ChainBean> chains;

    private Context context;

    public CoreServiceImpl(Vertx vertx) {
        this.vertx = vertx;
        this.context = vertx.getOrCreateContext();
        this.compoments = context.get("compoments");
        this.chains = context.get("staticChains");
        this.services = context.get("staticNodes");
    }

    @Override
    public List<VerticleBean> services() {
        return null;
    }

    @Override
    public void deployService(String service, Handler<AsyncResult<VerticleBean>> resultHandler) {
        compoments.stream().filter(r -> r.getName().equals(service)).forEach(r -> {
            vertx.deployVerticle(r.getVerticle());
            resultHandler.handle(Future.succeededFuture(r));
        });
    }

    @Override
    public void stopService(String service, Handler<AsyncResult<VerticleBean>> resultHandler) {
        compoments.stream().filter(r -> r.getName().equals(service)).forEach(r -> {
            resultHandler.handle(Future.succeededFuture(r));
        });
    }

    @Override
    public String serviceStatus(String service) {
        return null;
    }

    @Override
    public List<ChainBean> chains() {
        return null;
    }

    @Override
    public void startChain(String chain, Handler<AsyncResult<Boolean>> resultHandler) {

    }

    @Override
    public void executeChain(String name, Handler<AsyncResult<JsonObject>> resultHandler) {
        chains.stream().filter(c->c.getName().equals(name)).forEach(chain->{

        });
    }

    @Override
    public void stopChain(String chain, Handler<AsyncResult<Boolean>> resultHandler) {

    }

    @Override
    public String chainStatus(String chain) {
        return null;
    }
}
