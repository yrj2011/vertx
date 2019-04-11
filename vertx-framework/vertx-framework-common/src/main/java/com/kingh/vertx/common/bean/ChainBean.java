package com.kingh.vertx.common.bean;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 链
 *
 * @author <a href="https://blog.csdn.net/king_kgh>Kingh</a>
 * @version 1.0
 * @date 2019/3/19 9:00
 */
public class ChainBean {

    /**
     * 链名
     */
    private String name;

    /**
     * 链描述
     */
    private String description;

    /**
     * 处理路径
     */
    private String path; // request path

    /**
     * 支持方法
     */
    private HttpMethod[] methods; // get or post ...

    /**
     * 服务
     */
    private LinkedList<ServiceBean> services;

    /**
     * 是否启用链
     */
    private Boolean avaiable;

    /**
     * 排序位置
     */
    private Integer pos;

    /**
     * 是否为普通链
     * <p>
     * true 普通链
     * false 特殊链，特殊链需要指定Handler, 并且建议特殊链指定的pos值小于100
     */
    private Boolean general;

    /**
     * 当链为特殊链的时候，可以直接指定Handler
     */
    private Handler<RoutingContext> handler;

    public String getName() {
        return name;
    }

    public ChainBean setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ChainBean setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getPath() {
        return path;
    }

    public ChainBean setPath(String path) {
        this.path = path;
        return this;
    }

    public HttpMethod[] getMethods() {
        return methods;
    }

    public ChainBean setMethod(HttpMethod[] methods) {
        this.methods = methods;
        return this;
    }

    public LinkedList<ServiceBean> getServices() {
        return services;
    }

    public ChainBean setServices(LinkedList<ServiceBean> services) {
        this.services = services;
        return this;
    }

    public Boolean isAvaiable() {
        return avaiable;
    }

    public ChainBean setAvaiable(Boolean avaiable) {
        this.avaiable = avaiable;
        return this;
    }

    public Integer getPos() {
        return pos;
    }

    public ChainBean setPos(Integer pos) {
        this.pos = pos;
        return this;
    }

    public Boolean isGeneral() {
        return general;
    }

    public ChainBean setGeneral(Boolean general) {
        this.general = general;
        return this;
    }

    public Handler<RoutingContext> getHandler() {
        return handler;
    }

    public ChainBean setHandler(Handler<RoutingContext> handler) {
        this.handler = handler;
        return this;
    }

    @Override
    public String toString() {
        return "ChainBean{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", path='" + path + '\'' +
                ", methods=" + Arrays.toString(methods) +
                ", services=" + services +
                ", avaiable=" + avaiable +
                ", pos=" + pos +
                ", general=" + general +
                ", handler=" + handler +
                '}';
    }
}
