package com.kingh.vertx.core.runtime;

import com.kingh.vertx.common.bean.ChainBean;
import com.kingh.vertx.core.runtime.bean.ChainRequest;
import io.vertx.core.json.JsonObject;

/**
 * 运行时上下文
 *
 * 主要用于保存运行的实时数据，负责将数据对外提供和数据持久化操作
 *
 * @author <a href="https://blog.csdn.net/king_kgh>Kingh</a>
 * @version 1.0
 * @date 2019/4/16 20:04
 */
public interface RunContext {

    /**
     * 创建动态链
     *
     * @param chain
     * @param params
     * @return
     */
    ChainRequest createChain(ChainBean chain, JsonObject params);

}
