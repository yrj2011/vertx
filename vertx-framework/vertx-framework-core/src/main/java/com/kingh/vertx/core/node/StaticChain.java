package com.kingh.vertx.core.node;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

/**
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

}
