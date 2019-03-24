package com.kingh.vertx.core.exector.param;

import io.vertx.core.json.JsonObject;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author <a href="https://blog.csdn.net/king_kgh>Kingh</a>
 * @version 1.0
 * @date 2019/3/22 16:33
 */
public class ParameterConstructorFactory {

    /**
     * 存储映射关系
     */
    private static ConcurrentHashMap<String, ParameterConstructor> parameterConstructorConcurrentHashMap = new ConcurrentHashMap<>();

    /**
     * 实例
     */
    private static volatile ParameterConstructorFactory factory;

    static {
        // 注册默认的类型处理器
        parameterConstructorConcurrentHashMap.put(JsonObject.class.getName(), new JsonParamInject());
        parameterConstructorConcurrentHashMap.put(String.class.getName(), new StringParamInject());
    }

    private ParameterConstructorFactory() {
    }


    /**
     * 根据类型获取参数构造器
     *
     * @param typeName 类型名
     * @return
     */
    public ParameterConstructor getParameterConstructor(String typeName) {
        return parameterConstructorConcurrentHashMap.get(typeName);
    }


    /**
     * 获取工厂实例
     *
     * @return
     */
    public static ParameterConstructorFactory getInstance() {
        if (factory == null) {
            synchronized (ParameterConstructorFactory.class) {
                if (factory == null) {
                    factory = new ParameterConstructorFactory();
                }
            }
        }
        return factory;
    }

    /**
     * 注册参数构造器
     *
     * @param type
     * @param constructor
     */
    public void register(String type, ParameterConstructor constructor) {
        parameterConstructorConcurrentHashMap.put(type, constructor);
    }

    /**
     * 设置参数构造器映射表
     *
     * @param map
     */
    public void setParameterConstructor(Map<String, ParameterConstructor> map) {
        if(map != null && map.size() > 0) {
            parameterConstructorConcurrentHashMap.putAll(map);
        } else {
            throw new RuntimeException("接收到的参数构造器映射表为空");
        }
    }
}
