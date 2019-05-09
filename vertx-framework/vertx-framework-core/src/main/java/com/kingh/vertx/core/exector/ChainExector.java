package com.kingh.vertx.core.exector;

import com.kingh.vertx.common.bean.ChainBean;
import com.kingh.vertx.common.bean.ServiceBean;
import com.kingh.vertx.core.exector.param.ParameterConstructor;
import com.kingh.vertx.core.runtime.RunContext;
import com.kingh.vertx.core.runtime.bean.ChainRequest;
import io.vertx.core.*;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.RoutingContext;
import rx.Single;

import java.util.LinkedList;
import java.util.Map;
import java.util.UUID;


/**
 * 执行器
 * <p>
 * 0. 完成无参的链式调用  √
 * 1. 指定参数完成链的调用，数据格式统一使用String √
 * 2. 构造查询参数完成链的调用,数据格式统一使用String √
 * 3. 构造表单参数完成链的调用，数据格式统一使用String √
 * 4. 构造Json数据完成链的调用，数据格式统一使用String √
 * 5. 组合构造参数 √
 * 6. 参数注入支持JsonObject 类型和 String类型
 * 7. 数据格式可以使用HttpServerRequest,HttpServerResponse,RoutingContext 等
 *
 * @author <a href="https://blog.csdn.net/king_kgh>Kingh</a>
 * @version 1.0
 * @date 2019/3/20 16:03
 */
public class ChainExector {

    private static final Logger logger = LoggerFactory.getLogger(ChainExector.class);

    /**
     * 执行链
     *
     * @param chain         链定义
     * @param context       请求上下文
     * @param vertx         vertx实例
     * @param resultHandler 异步响应
     */
    public static void execute(ChainBean chain, RoutingContext context, Vertx vertx, Handler<AsyncResult<JsonObject>> resultHandler) {

        // 参数校验
        if (chain == null) {
            throw new RuntimeException("链定义为空，不能执行链");
        }

        if (vertx == null) {
            throw new RuntimeException("Vertx 实例为空，不能执行链");
        }

        LinkedList<ServiceBean> servicess = chain.getServices();
        if (servicess == null || servicess.size() <= 0) {
            throw new RuntimeException("链的实例数为0，没有要执行的方法");
        }
        LinkedList<ServiceBean> services = new LinkedList<>();
        services.addAll(servicess);

        EventBus bus = vertx.eventBus();

        // 生成链执行的唯一标识
        String id = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
        logger.info("开始执行链：" + chain.getName() + " ID为：" + id);

        JsonObject msg = new JsonObject().put("path", chain.getPath()).put("name", chain.getName());
        String address = "verticle:" + chain.getName() + ":" + id;
        bus.publish(address, msg);

        // 链中的实时数据
        JsonObject data = new JsonObject();
        // 处理请求数据
        buildRequestData(context, reqData -> {
            if (reqData.succeeded()) {
                data.mergeIn(reqData.result());

                // 循环执行链
                Single<JsonObject> r = null;
                int index = 0;
                while (services.size() > 0) {
                    ServiceBean service = services.removeFirst();

                    // 第一次执行
                    if (index++ == 0) {
                        r = rxExecutor(id, service, context, data, vertx);
                    } else {
                        r = r.flatMap(ss -> {
                            // 构造数据
                            data.mergeIn(ss);
                            return rxExecutor(id, service, context, data, vertx);
                        });
                    }
                }

                r.subscribe(ok -> {
                    resultHandler.handle(Future.succeededFuture(ok));
                }, err -> {
                    resultHandler.handle(Future.failedFuture(err));
                });
            }
        });
    }

    public static Single<JsonObject> rxExecutor(String id, ServiceBean serviceBean, RoutingContext context, JsonObject data, Vertx vertx) {
        return Single.create(new io.vertx.rx.java.SingleOnSubscribeAdapter<>(fut -> execute(id, serviceBean, context, data, vertx, fut)));
    }

    /**
     * 真正调用方法
     *
     * @param id            执行链的ID
     * @param service       要调用的服务
     * @param context       请求上下文
     * @param data          封装的数据
     * @param vertx         vertx实例
     * @param resultHandler
     */
    private static void execute(String id, ServiceBean service, RoutingContext context, JsonObject data, Vertx vertx, Handler<AsyncResult<JsonObject>> resultHandler) {
        // 通过配置action参数，指定要走哪一个方法
        DeliveryOptions options = new DeliveryOptions();
        options.addHeader("action", service.getId());

        // 这个是给方法传入的参数
        JsonObject config = new JsonObject();
        Map<String, ServiceBean.Param> params = service.getParams();

        // 构造参数
        params.values().stream().forEach(p -> {
            // p - Param
            ParameterConstructor constructor = ParameterConstructor.getParameterConstructor(p.getTypeName());
            Map.Entry<String, Object> entry = constructor.constructor(p, context, data, vertx);
            config.put(entry.getKey(), entry.getValue());
        });

        EventBus bus = vertx.eventBus();

        // 通过eventBus调用方法
        bus.<JsonObject>send(service.getVerticle().getAddress(), config, options, res -> {
            if (res.succeeded()) {
                // 响应数据
                JsonObject obj = res.result().body();

                String address = "service:" + service.getId() + ":" + id;
                bus.publish(address, new JsonObject()
                        .put("service", service.getId())
                        .put("address", service.getVerticle().getAddress())
                        .put("req", config)
                        .put("resp", obj));


                resultHandler.handle(Future.succeededFuture(res.result().body()));
            } else {
                res.cause().printStackTrace();
                resultHandler.handle(Future.failedFuture(res.cause()));
            }
        });
    }

    /**
     * 组装各种请求类型传递的参数，转成JsonObject
     *
     * @param context
     * @param resultHandler
     */
    private static void buildRequestData(RoutingContext context, Handler<AsyncResult<JsonObject>> resultHandler) {
        JsonObject data = new JsonObject();
        HttpServerRequest request = context.request();

        MultiMap queryParams = context.queryParams();
        if (queryParams != null && queryParams.size() > 0) {
            queryParams.entries().stream().forEach(en -> data.put(en.getKey(), en.getValue()));
        }

        // 处理form表单中提交上来的数据
        MultiMap formAttributes = request.formAttributes();
        if (formAttributes != null && formAttributes.size() > 0) {
            formAttributes.entries().stream().forEach(en -> data.put(en.getKey(), en.getValue()));
        }

        // 处理post请求，请求体中的数据
        String text = context.getBodyAsString();

        if (text != null && "".equals(text)) {
            try {
                data.mergeIn(new JsonObject(text));
            } catch (Exception e) {
                logger.warn("不是规范的JSON格式 " + text);
            }
        }

        logger.debug("获取到的参数为：" + data);
        resultHandler.handle(Future.succeededFuture(data));
    }
}
