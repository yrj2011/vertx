package com.kingh.vertx.core.context;

import com.kingh.vertx.common.bean.ChainBean;
import com.kingh.vertx.common.bean.ServiceBean;
import io.vertx.core.Vertx;

import java.util.List;

/**
 * 运行时上下文
 *
 * @author <a href="https://blog.csdn.net/king_kgh>Kingh</a>
 * @version 1.0
 * @date 2019/3/19 9:42
 */
public interface ApplicationContext {

    /**
     * 指定Vert.x实例
     *
     * @param vertx
     */
    void setVertx(Vertx vertx);

    /**
     * 获取Vert.x实例
     *
     * @return
     */
    Vertx vertx();

    /**
     * 获取所有的链
     *
     * @return
     */
    List<ChainBean> chains();

    /**
     * 获取服务
     *
     * @param tag
     * @return
     */
    ServiceBean service(String tag);

    /**
     * 启动
     */
    void run();
}
