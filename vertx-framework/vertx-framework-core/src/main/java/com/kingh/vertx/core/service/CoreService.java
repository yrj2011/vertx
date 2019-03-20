package com.kingh.vertx.core.service;

import com.kingh.vertx.common.bean.ChainBean;
import com.kingh.vertx.common.bean.VerticleBean;
import com.kingh.vertx.core.service.factory.CoreServiceFactory;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

import java.util.List;

/**
 * 核心服务
 *
 * @author <a href="https://blog.csdn.net/king_kgh>Kingh</a>
 * @version 1.0
 * @date 2019/3/11 17:38
 */
public interface CoreService {

    CoreServiceFactory factory = new CoreServiceFactory();

    static CoreService create(Vertx vertx) {
        return factory.getInstacne(vertx);
    }

    /**
     * 所有服务列表
     *
     * @return
     */
    List<VerticleBean> services();

    /**
     * 部署服务
     *
     * @param verticle 服务名
     */
    void deployVerticle(VerticleBean verticle, Handler<AsyncResult<JsonObject>> resultHandler);

    /**
     * 停止服务
     *
     * @param service 服务名
     */
    void stopService(String service, Handler<AsyncResult<JsonObject>> resultHandler);

    /**
     * 服务状态查询
     *
     * @param service 服务名
     * @return
     */
    String serviceStatus(String service);

    /**
     * 链列表
     *
     * @return
     */
    List<ChainBean> chains();

    /**
     * 部署链
     *
     * @param chain 链名
     */
    void startChain(String chain, Handler<AsyncResult<JsonObject>> resultHandler);

    /**
     * 执行链
     *
     * @param name
     * @param resultHandler
     */
    void executeChain(String name, Handler<AsyncResult<JsonObject>> resultHandler);

    /**
     * 停止链
     *
     * @param chain 链名
     */
    void stopChain(String chain, Handler<AsyncResult<JsonObject>> resultHandler);

    /**
     * 链状态查询
     *
     * @param chain 链名
     * @return
     */
    String chainStatus(String chain);


}
