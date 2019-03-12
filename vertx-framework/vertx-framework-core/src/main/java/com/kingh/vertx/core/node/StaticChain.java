package com.kingh.vertx.core.node;


/**
 * 链路
 *
 * @author <a href="https://blog.csdn.net/king_kgh>Kingh</a>
 * @version 1.0
 * @date 2019/3/11 14:52
 */
public class StaticChain {

    private String name;

    private String description;

    private String path; // request path

    private String method; // get or post ...

    private StaticNode headNode;

    private String status;

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

    public StaticNode getHeadNode() {
        return headNode;
    }

    public void setHeadNode(StaticNode headNode) {
        this.headNode = headNode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
