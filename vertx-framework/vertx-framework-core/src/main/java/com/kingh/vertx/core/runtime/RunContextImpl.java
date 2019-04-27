package com.kingh.vertx.core.runtime;

import com.kingh.vertx.common.bean.ChainBean;
import com.kingh.vertx.core.runtime.bean.ChainRequest;
import io.vertx.core.json.JsonObject;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author <a href="https://blog.csdn.net/king_kgh>Kingh</a>
 * @version 1.0
 * @date 2019/4/16 20:22
 */
public class RunContextImpl implements RunContext {

    /**
     * 核心运行时对象
     */
    private List<ChainRequest> chainRequests;

    public RunContextImpl() {
        this.chainRequests = new CopyOnWriteArrayList<>();
    }


    @Override
    public ChainRequest createChain(ChainBean chain, JsonObject params) {
        ChainRequest chainRequest = new ChainRequest();
        chainRequest.setChain(chain);
        chainRequest.setParams(params);
        chainRequests.add(chainRequest);
        return chainRequest;
    }
}
