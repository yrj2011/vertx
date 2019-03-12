package com.kingh.vertx.core.node;

import java.util.Set;

/**
 * @author <a href="https://blog.csdn.net/king_kgh>Kingh</a>
 * @version 1.0
 * @date 2019/3/12 9:32
 */
public class StaticService {

    private String name;

    private String description;

    private Set<StaticNode> actions;

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

    public Set<StaticNode> getActions() {
        return actions;
    }

    public void setActions(Set<StaticNode> actions) {
        this.actions = actions;
    }
}
