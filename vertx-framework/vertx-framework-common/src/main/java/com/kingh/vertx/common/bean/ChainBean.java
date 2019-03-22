package com.kingh.vertx.common.bean;

import io.vertx.core.http.HttpMethod;

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
    private HttpMethod method; // get or post ...

    /**
     * 服务
     */
    private LinkedList<ServiceBean> services;

    /**
     * 是否启用链
     */
    private boolean avaiable;

    /**
     * 排序位置
     */
    private Integer pos = 100;

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

    public HttpMethod getMethod() {
        return method;
    }

    public ChainBean setMethod(HttpMethod method) {
        this.method = method;
        return this;
    }

    public LinkedList<ServiceBean> getServices() {
        return services;
    }

    public ChainBean setServices(LinkedList<ServiceBean> services) {
        this.services = services;
        return this;
    }

    public boolean isAvaiable() {
        return avaiable;
    }

    public ChainBean setAvaiable(boolean avaiable) {
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
}
