package com.kingh.vertx.core.node;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

import java.util.UUID;

/**
 * 运行链路定义
 *
 * @author <a href="https://blog.csdn.net/king_kgh>Kingh</a>
 * @version 1.0
 * @date 2019/3/11 13:43
 */
public class RunnableChain {

    /**
     * 链唯一标识（系统定义）
     */
    private String id;

    /**
     * 与静态节点相关联
     */
    private RunnableNode node;

    /**
     * 当前Vert.x实例
     */
    private Vertx vertx;

    /**
     * 当前链的开始执行时间
     */
    private Long startTime;

    /**
     * 当前链的执行完毕时间
     */
    private Long endTime;

    /**
     * 执行出错抛出的异常
     */
    private Throwable t;

    /**
     * 响应结果
     */
    private JsonObject result;

}
