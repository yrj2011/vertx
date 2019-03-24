package com.kingh.vertx.core.exector.param;

import com.kingh.vertx.common.bean.ServiceBean;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

import java.util.Map;

/**
 * 给方法注入字符串类型数据
 *
 * @author <a href="https://blog.csdn.net/king_kgh>Kingh</a>
 * @version 1.0
 * @date 2019/3/22 16:55
 */
public class StringParamInject implements ParameterConstructor {

    @Override
    public Map.Entry<String, Object> constructor(ServiceBean.Param param, RoutingContext context, JsonObject data, Vertx vertx) {

        return new Map.Entry<String, Object>() {
            @Override
            public String getKey() {
                return param.getName();
            }

            @Override
            public Object getValue() {
                return data.getValue(param.getName());
            }

            @Override
            public Object setValue(Object value) {
                return null;
            }
        };
    }
}
