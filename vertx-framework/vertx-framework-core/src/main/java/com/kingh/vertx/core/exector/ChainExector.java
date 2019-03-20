package com.kingh.vertx.core.exector;

import com.kingh.vertx.common.bean.ChainBean;
import com.kingh.vertx.common.bean.ServiceBean;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import rx.Single;

import java.util.LinkedList;
import java.util.Map;


/**
 * 执行器
 *
 * @author <a href="https://blog.csdn.net/king_kgh>Kingh</a>
 * @version 1.0
 * @date 2019/3/20 16:03
 */
public class ChainExector {

    public static void execute(ChainBean chain, RoutingContext context, Vertx vertx, Handler<AsyncResult<JsonObject>> resultHandler) {
        LinkedList<ServiceBean> services = chain.getServices();

        Single<JsonObject> r = null;

        JsonObject data = new JsonObject();
        data.put("name", "小明");

        int index = 0;
        while (services.size() > 0) {
            ServiceBean service = services.removeFirst();
            if (index++ == 0) {
                r = rxExecutor(service, data, vertx);
            } else {
                r = r.flatMap(ss -> {
                    data.mergeIn(ss);
                    return rxExecutor(service, data, vertx);
                });
            }
        }

        r.subscribe(ok -> {
            resultHandler.handle(Future.succeededFuture(ok));
        }, err -> {
            resultHandler.handle(Future.failedFuture(err));
        });
    }

    public static Single<JsonObject> rxExecutor(ServiceBean serviceBean, JsonObject data, Vertx vertx) {
        return Single.create(new io.vertx.rx.java.SingleOnSubscribeAdapter<>(fut -> execute(serviceBean, data, vertx, fut)));
    }

    private static void execute(ServiceBean service, JsonObject data, Vertx vertx, Handler<AsyncResult<JsonObject>> resultHandler) {
        // 通过配置action参数，指定要走哪一个方法
        DeliveryOptions options = new DeliveryOptions();
        options.addHeader("action", service.getId());

        // 这个是给方法传入的参数
        JsonObject config = new JsonObject();
        Map<String, ServiceBean.Param> params = service.getParams();

        params.values().stream().forEach(p -> {
            config.put(p.getName(), data.getValue(p.getName()));
        });

        // 通过eventBus调用方法
        vertx.eventBus().<JsonObject>send(service.getVerticle().getAddress(), config, options, res -> {
            // 响应数据
            Object obj = res.result().body();
            System.out.println(obj);
            resultHandler.handle(Future.succeededFuture(res.result().body()));
        });
    }


}
