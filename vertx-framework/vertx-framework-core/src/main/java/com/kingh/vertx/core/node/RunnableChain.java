package com.kingh.vertx.core.node;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

import java.util.List;
import java.util.UUID;

/**
 * 运行时链路
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
     * 与静态链路关联
     */
    private StaticChain staticChain;

    /**
     * 与静态节点相关联
     */
    private List<RunnableNode> nodes;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public StaticChain getStaticChain() {
        return staticChain;
    }

    public void setStaticChain(StaticChain staticChain) {
        this.staticChain = staticChain;
    }

    public List<RunnableNode> getNodes() {
        return nodes;
    }

    public void setNodes(List<RunnableNode> nodes) {
        this.nodes = nodes;
    }

    public Vertx getVertx() {
        return vertx;
    }

    public void setVertx(Vertx vertx) {
        this.vertx = vertx;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Throwable getT() {
        return t;
    }

    public void setT(Throwable t) {
        this.t = t;
    }

    public JsonObject getResult() {
        return result;
    }

    public void setResult(JsonObject result) {
        this.result = result;
    }
}
