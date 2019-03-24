package com.kingh.vertx.core.exector.param;

import com.kingh.vertx.common.bean.ServiceBean;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

import java.util.Map;

/**
 * 给方法注入JsonObject类型
 *
 * @author <a href="https://blog.csdn.net/king_kgh>Kingh</a>
 * @version 1.0
 * @date 2019/3/22 16:53
 */
public class JsonParamInject implements ParameterConstructor {

    @Override
    public Map.Entry<String, Object> constructor(ServiceBean.Param param, RoutingContext context, JsonObject data, Vertx vertx) {
        return null;
    }
}
