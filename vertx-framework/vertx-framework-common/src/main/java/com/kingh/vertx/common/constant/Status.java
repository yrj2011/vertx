package com.kingh.vertx.common.constant;

/**
 * @author <a href="https://blog.csdn.net/king_kgh>Kingh</a>
 * @version 1.0
 * @date 2019/3/19 20:16
 */
public enum Status {

    /**
     * 可用
     */
    available("00"),

    /**
     * 不可用
     */
    not_available("01");

    String status;

    Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

}
