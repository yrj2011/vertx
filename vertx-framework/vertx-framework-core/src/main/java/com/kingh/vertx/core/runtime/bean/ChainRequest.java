package com.kingh.vertx.core.runtime.bean;

import com.kingh.vertx.common.bean.ChainBean;
import io.vertx.core.json.JsonObject;


public class ChainRequest {
    private ChainBean chain;
    private JsonObject params;

    public ChainBean getChain() {
        return chain;
    }

    public JsonObject getParams() {
        return params;
    }

    public void setChain(ChainBean chain) {
        this.chain = chain;
    }

    public void setParams(JsonObject params) {
        this.params = params;
    }
}
