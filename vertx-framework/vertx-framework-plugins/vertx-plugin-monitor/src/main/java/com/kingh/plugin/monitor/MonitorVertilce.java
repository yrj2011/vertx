package com.kingh.plugin.monitor;

import com.kingh.vertx.common.anno.Verticle;
import com.kingh.vertx.plugin.db.utils.JdbcUtils;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonArray;
import io.vertx.ext.jdbc.JDBCClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author <a href="https://blog.csdn.net/king_kgh>Kingh</a>
 * @version 1.0
 * @date 2019/5/9 13:08
 */
@Verticle(name = "MonitorVertilce", address = "com.kingh.plugin.monitor.MonitorVertilce.19940706", description = "用于监控运行状态的Verticle", autoDeploy = true)
public class MonitorVertilce extends AbstractVerticle {

    private Logger logger = LoggerFactory.getLogger(MonitorVertilce.class);

    private static final String sql = "insert into vertx_ms_log(fid,address,body,headers,verticle, service) values (?,?,?,?,?,?)";

    @Override
    public void start() throws Exception {

        EventBus eventBus = vertx.eventBus();
        JDBCClient jdbcClient = new JdbcUtils(vertx).getDbClient();


        eventBus.addOutboundInterceptor(i -> {
            Message message = i.message();

            String address = message.address();
            String body = message.body().toString();
            String header = message.headers().toString();
            String fid = "";
            String vertilce = "";
            String service = "";

            // 算fid
            String[] ss = address.split(":");
            if (ss.length > 1) {
                String s1 = ss[0];
                if(s1.startsWith("verticle")) {
                    fid = ss[2];
                    vertilce = ss[1];
                } else if(s1.startsWith("service")) {
                    fid = ss[2];
                    service = ss[1];
                }
            }

            jdbcClient.updateWithParams(sql, new JsonArray().add(fid).add(address).add(body).add(header).add(vertilce).add(service), r -> {
                logger.info("Address : " + message.address());
                logger.info("Header : " + message.headers());
                logger.info("Body : " + message.body());
                logger.info("---------------------------------------------------");
            });

            i.next();
        });


    }
}
