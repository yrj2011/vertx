package com.kingh.vertx.common.bean;

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
    private String method; // get or post ...

    /**
     * 服务
     */
    private LinkedList<ServiceBean> services;

    /**
     * 是否启用链
     */
    private boolean avaiable;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public LinkedList<ServiceBean> getServices() {
        return services;
    }

    public void setServices(LinkedList<ServiceBean> services) {
        this.services = services;
    }

    public boolean isAvaiable() {
        return avaiable;
    }

    public void setAvaiable(boolean avaiable) {
        this.avaiable = avaiable;
    }
}
