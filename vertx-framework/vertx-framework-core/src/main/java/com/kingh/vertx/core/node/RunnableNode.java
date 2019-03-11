package com.kingh.vertx.core.node;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

import java.util.UUID;

/**
 * 运行时节点定义
 *
 * @author <a href="https://blog.csdn.net/king_kgh>Kingh</a>
 * @version 1.0
 * @date 2019/3/11 13:43
 */
public class RunnableNode {

    /**
     * 节点唯一标识（系统定义）
     */
    private String id;

    /**
     * 与静态节点相关联
     */
    private StaticNode node;

    /**
     * 当前Vert.x实例
     */
    private Vertx vertx;

    /**
     * 执行当前服务的线程
     */
    private Thread thread;

    /**
     * 当前节点的开始执行时间
     */
    private Long startTime;

    /**
     * 当前节点的执行完毕时间
     */
    private Long endTime;

    /**
     * 执行出错抛出的异常
     */
    private Throwable t;

    /**
     * 当前节点执行的状态
     */
    private String status;

    /**
     * 响应结果
     */
    private JsonObject result;

    public RunnableNode(Vertx vertx) {
        this.vertx = vertx;
        this.thread = Thread.currentThread();
        this.startTime = System.currentTimeMillis();
        this.id = UUID.randomUUID().toString();
        this.result = new JsonObject();
    }

    public String getId() {
        return id;
    }

    public RunnableNode setId(String id) {
        this.id = id;
        return this;
    }

    public Vertx getVertx() {
        return vertx;
    }

    public RunnableNode setVertx(Vertx vertx) {
        this.vertx = vertx;
        return this;
    }

    public Thread getThread() {
        return thread;
    }

    public RunnableNode setThread(Thread thread) {
        this.thread = thread;
        return this;
    }

    public Long getStartTime() {
        return startTime;
    }

    public RunnableNode setStartTime(Long startTime) {
        this.startTime = startTime;
        return this;
    }

    public Long getEndTime() {
        return endTime;
    }

    public RunnableNode setEndTime(Long endTime) {
        this.endTime = endTime;
        return this;
    }

    public Throwable getT() {
        return t;
    }

    public RunnableNode setT(Throwable t) {
        this.t = t;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public RunnableNode setStatus(String status) {
        this.status = status;
        return this;
    }

    public StaticNode getNode() {
        return node;
    }

    public RunnableNode setNode(StaticNode node) {
        this.node = node;
        return this;
    }

    public JsonObject getResult() {
        return result;
    }

    public RunnableNode setResult(JsonObject result) {
        this.result = result;
        return this;
    }
}
