package com.kingh.vertx.common.bean;

import java.util.Map;

/**
 * @author <a href="https://blog.csdn.net/king_kgh>Kingh</a>
 * @version 1.0
 * @date 2019/3/19 9:00
 */
public class ServiceBean {

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
}
