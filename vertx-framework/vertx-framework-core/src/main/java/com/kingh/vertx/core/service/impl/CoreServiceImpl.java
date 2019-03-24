package com.kingh.vertx.core.service.impl;

import com.kingh.vertx.common.bean.ChainBean;
import com.kingh.vertx.common.bean.ServiceBean;
import com.kingh.vertx.common.bean.VerticleBean;
import com.kingh.vertx.common.constant.Status;
import com.kingh.vertx.core.service.CoreService;
import io.vertx.core.*;
import io.vertx.core.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 核心服务实现类
 *
 * @author <a href="https://blog.csdn.net/king_kgh>Kingh</a>
 * @version 1.0
 * @date 2019/3/11 17:40
 */
public class CoreServiceImpl implements CoreService {

    private Logger logger = LoggerFactory.getLogger(CoreService.class);

    private Vertx vertx;

    private Context context;

    public CoreServiceImpl(Vertx vertx) {
        this.vertx = vertx;
        this.context = vertx.getOrCreateContext();
    }

    @Override
    public List<VerticleBean> services() {
        return null;
    }

    @Override
    public void deployVerticle(VerticleBean verticle, Handler<AsyncResult<JsonObject>> resultHandler) {
        Verticle vert = verticle.getVerticle();
        if (vert == null) {
            logger.error("要部署的Verticle实例为空，不执行部署操作！");
            resultHandler.handle(Future.failedFuture("Verticle 实例为空"));
            return;
        }

        // 部署Verticle到Vert.x实例中
        vertx.deployVerticle(verticle.getVerticle(), res -> {
            if (res.succeeded()) {
                logger.info(verticle.getName() + " 部署成功！");
                verticle.setStatus(Status.available);
                resultHandler.handle(Future.succeededFuture(new JsonObject()));
            } else {
                logger.error(verticle.getName() + " 部署失败！", res.cause());
                resultHandler.handle(Future.failedFuture(res.cause()));
            }
        });

    }

    @Override
    public void stopService(String service, Handler<AsyncResult<JsonObject>> resultHandler) {
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
    public void startChain(String chain, Handler<AsyncResult<JsonObject>> resultHandler) {

    }

    @Override
    public void executeChain(String name, Handler<AsyncResult<JsonObject>> resultHandler) {
    }

    @Override
    public void stopChain(String chain, Handler<AsyncResult<JsonObject>> resultHandler) {

    }

    @Override
    public String chainStatus(String chain) {
        return null;
    }
}
