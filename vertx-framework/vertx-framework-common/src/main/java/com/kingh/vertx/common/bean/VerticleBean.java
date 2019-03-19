package com.kingh.vertx.common.bean;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Verticle;

import java.util.Set;

/**
 * @author <a href="https://blog.csdn.net/king_kgh>Kingh</a>
 * @version 1.0
 * @date 2019/3/19 9:00
 */
public class VerticleBean {

    /**
     * 组件标识
     */
    private String name;

    /**
     * 组件描述
     */
    private String description;

    /**
     * 组件部署状态
     */
    private String status;

    /**
     * 组件实例
     */
    private Verticle verticle;

    /**
     * 部署参数
     */
    private DeploymentOptions deploymentOptions;

    /**
     * 是否自动部署
     */
    private boolean autoDeploy;

    /**
     * 服务列表
     */
    private Set<ServiceBean> services;

    /**
     * 部署地址
     */
    private String address;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Verticle getVerticle() {
        return verticle;
    }

    public void setVerticle(Verticle verticle) {
        this.verticle = verticle;
    }

    public DeploymentOptions getDeploymentOptions() {
        return deploymentOptions;
    }

    public void setDeploymentOptions(DeploymentOptions deploymentOptions) {
        this.deploymentOptions = deploymentOptions;
    }

    public boolean isAutoDeploy() {
        return autoDeploy;
    }

    public void setAutoDeploy(boolean autoDeploy) {
        this.autoDeploy = autoDeploy;
    }

    public Set<ServiceBean> getServices() {
        return services;
    }

    public void setServices(Set<ServiceBean> services) {
        this.services = services;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
