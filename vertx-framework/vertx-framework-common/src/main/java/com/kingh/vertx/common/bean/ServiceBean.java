package com.kingh.vertx.common.bean;

import java.util.Map;

/**
 * 服务
 *
 * @author <a href="https://blog.csdn.net/king_kgh>Kingh</a>
 * @version 1.0
 * @date 2019/3/19 9:00
 */
public class ServiceBean {

    /**
     * 服务的方法名
     */
    private String id;

    /**
     * 服务名称
     */
    private String name;

    /**
     * 服务描述
     */
    private String description;

    /**
     * 调用方法名
     */
    private String service;

    /**
     * 服务参数列表
     */
    private Map<String, Param> params;

    /**
     * 服务响应结果
     */
    private Map<String, Param> result;

    /**
     * 引用Verticle
     */
    private VerticleBean verticle;

    public static class Param {
        /**
         * 参数名
         */
        private String name;
        /**
         * 参数描述
         */
        private String description;
        /**
         * 是否必填
         */
        private boolean must = false;
        /**
         * 长度
         */
        private int length = -1;
        /**
         * 类型
         */
        private String typeName;

        public String getName() {
            return name;
        }

        public Param setName(String name) {
            this.name = name;
            return this;
        }

        public String getDescription() {
            return description;
        }

        public Param setDescription(String description) {
            this.description = description;
            return this;
        }

        public boolean isMust() {
            return must;
        }

        public Param setMust(boolean must) {
            this.must = must;
            return this;
        }

        public int getLength() {
            return length;
        }

        public Param setLength(int length) {
            this.length = length;
            return this;
        }

        public String getTypeName() {
            return typeName;
        }

        public Param setTypeName(String typeName) {
            this.typeName = typeName;
            return this;
        }
    }

    public String getName() {
        return name;
    }

    public ServiceBean setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ServiceBean setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getService() {
        return service;
    }

    public ServiceBean setService(String service) {
        this.service = service;
        return this;
    }

    public Map<String, Param> getParams() {
        return params;
    }

    public ServiceBean setParams(Map<String, Param> params) {
        this.params = params;
        return this;
    }

    public Map<String, Param> getResult() {
        return result;
    }

    public ServiceBean setResult(Map<String, Param> result) {
        this.result = result;
        return this;
    }

    public String getId() {
        return id;
    }

    public ServiceBean setId(String id) {
        this.id = id;
        return this;
    }

    public VerticleBean getVerticle() {
        return verticle;
    }

    public ServiceBean setVerticle(VerticleBean verticle) {
        this.verticle = verticle;
        return this;
    }

    @Override
    public String toString() {
        return "ServiceBean{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", service='" + service + '\'' +
                ", params=" + params +
                ", result=" + result +
                ", verticle=" + verticle +
                '}';
    }
}
