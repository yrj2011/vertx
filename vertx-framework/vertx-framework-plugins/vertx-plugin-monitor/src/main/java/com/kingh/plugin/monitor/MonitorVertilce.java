package com.kingh.plugin.monitor;

import com.kingh.vertx.common.anno.Verticle;
import com.kingh.vertx.plugin.db.utils.JdbcUtils;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.Message;
import io.vertx.ext.jdbc.JDBCClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author <a href="https://blog.csdn.net/king_kgh>Kingh</a>
 * @version 1.0
 * @date 2019/5/9 13:08
 */
@Verticle(name = "MonitorVertilce", address = "com.kingh.plugin.monitor.MonitorVertilce.19940706", description="用于监控运行状态的Verticle", autoDeploy = true)
public class MonitorVertilce extends AbstractVerticle {

    private Logger logger = LoggerFactory.getLogger(MonitorVertilce.class);

    @Override
    public void start() throws Exception {

        EventBus eventBus = vertx.eventBus();
        JDBCClient jdbcClient = new JdbcUtils(vertx).getDbClient();

        eventBus.addOutboundInterceptor(i -> {
            Message message = i.message();
            logger.info("Address : " + message.address());
            logger.info("Header : " + message.headers());
            logger.info("Body : " + message.body());
            logger.info("---------------------------------------------------");
            i.next();
        });


    }
}
