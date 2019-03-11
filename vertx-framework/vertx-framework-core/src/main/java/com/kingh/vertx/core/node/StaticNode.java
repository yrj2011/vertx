package com.kingh.vertx.core.node;


import java.util.HashMap;
import java.util.Map;

/**
 * 链 节点
 *
 * @author <a href="https://blog.csdn.net/king_kgh>Kingh</a>
 * @version 1.0
 * @date 2019/3/11 13:24
 */
public class StaticNode {

    /**
     * 节点名称（服务提供者定义）
     */
    private String name;

    /**
     * 节点描述
     */
    private String description;

    /**
     * 服务地址
     */
    private String address;

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

    private StaticNode next;

    private StaticNode previous;

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
         * 类名
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

    public StaticNode(String name, String address, String service) {
        this.name = name;
        this.address = address;
        this.service = service;
        this.params = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public StaticNode setName(String name) {
        this.name = name;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public StaticNode setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getService() {
        return service;
    }

    public StaticNode setService(String service) {
        this.service = service;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public StaticNode setDescription(String description) {
        this.description = description;
        return this;
    }

    public Map<String, Param> getParams() {
        return params;
    }

    public StaticNode setParams(Map<String, Param> params) {
        this.params = params;
        return this;
    }

    public Map<String, Param> getResult() {
        return result;
    }

    public StaticNode setResult(Map<String, Param> result) {
        this.result = result;
        return this;
    }

    public StaticNode getNext() {
        return next;
    }

    public StaticNode setNext(StaticNode next) {
        this.next = next;
        return this;
    }

    public StaticNode getPrevious() {
        return previous;
    }

    public StaticNode setPrevious(StaticNode previous) {
        this.previous = previous;
        return this;
    }
}
