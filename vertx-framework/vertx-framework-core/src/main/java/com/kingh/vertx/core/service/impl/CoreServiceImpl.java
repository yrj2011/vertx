package com.kingh.vertx.core.service.impl;

import com.kingh.vertx.core.node.Compoment;
import com.kingh.vertx.core.node.StaticChain;
import com.kingh.vertx.core.node.StaticNode;
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
    private List<Compoment> compoments;

    // 服务 （最小执行单元）
    private List<StaticNode> staticNodes;

    // 功能 （多个组件构成一个功能）
    private List<StaticChain> staticChains;

    private Context context;

    public CoreServiceImpl(Vertx vertx) {
        this.vertx = vertx;
        this.context = vertx.getOrCreateContext();
        this.compoments = context.get("compoments");
        this.staticChains = context.get("staticChains");
        this.staticNodes = context.get("staticNodes");
    }

    @Override
    public List<Compoment> services() {
        return null;
    }

    @Override
    public void deployService(String service, Handler<AsyncResult<Compoment>> resultHandler) {
        compoments.stream().filter(r -> r.getName().equals(service)).forEach(r -> {
            vertx.deployVerticle(r.getVerticle());
            resultHandler.handle(Future.succeededFuture(r));
        });
    }

    @Override
    public void stopService(String service, Handler<AsyncResult<Compoment>> resultHandler) {
        compoments.stream().filter(r -> r.getName().equals(service)).forEach(r -> {
            resultHandler.handle(Future.succeededFuture(r));
        });
    }

    @Override
    public String serviceStatus(String service) {
        return null;
    }

    @Override
    public List<StaticChain> chains() {
        return null;
    }

    @Override
    public void startChain(String chain, Handler<AsyncResult<Boolean>> resultHandler) {

    }

    @Override
    public void executeChain(String name, Handler<AsyncResult<JsonObject>> resultHandler) {
        staticChains.stream().filter(c->c.getName().equals(name)).forEach(chain->{

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
