package com.kingh.vertx.core.context;

import com.kingh.vertx.common.bean.ChainBean;
import com.kingh.vertx.common.bean.ServiceBean;
import com.kingh.vertx.core.runtime.RunContext;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

import java.util.List;

/**
 * 运行时上下文
 *
 * @author <a href="https://blog.csdn.net/king_kgh>Kingh</a>
 * @version 1.0
 * @date 2019/3/19 9:42
 */
public interface ApplicationContext {

    /**
     * 指定Vert.x实例
     *
     * @param vertx
     */
    void setVertx(Vertx vertx);

    /**
     * 获取Vert.x实例
     *
     * @return
     */
    Vertx vertx();

    /**
     * 执行链，静态链-->动态链
     *
     * @param chain         链的定义
     * @param context       路由上下文
     * @param resultHandler 执行结果处理器
     */
    void execChain(ChainBean chain, RoutingContext context, Handler<AsyncResult<JsonObject>> resultHandler);

    /**
     * 获取所有的链
     *
     * @return
     */
    List<ChainBean> chains();

    /**
     * 获取服务
     *
     * @param tag 服务标识 verticle:service
     * @return
     */
    ServiceBean service(String tag);

    /**
     * 启动
     */
    void run();

    /**
     * 获取运行时上下文对象
     *
     * @return
     */
    RunContext runContxt();
}
