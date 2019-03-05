package com.kingh.stu.utils;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.jdbc.JDBCClient;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 数据库工具类
 *
 * @author 孔冠华
 */
public class JdbcUtils {

    private static final Logger logger = LoggerFactory.getLogger(JdbcUtils.class);

    private JDBCClient dbClient;
    private static JsonObject config;

    static {
        try {
            // 读取配置文件
            InputStream ins = new FileInputStream("conf/db.json");
            BufferedReader reader = new BufferedReader(new InputStreamReader(ins));
            String line = null;
            StringBuffer content = new StringBuffer();
            while((line = reader.readLine()) != null) {
                content.append(line);
            }
            config = new JsonObject(content.toString());
        } catch (Exception e) {
            logger.error("读取配置文件失败", e);
        }
    }

    public JdbcUtils(Vertx vertx, String dsName) {
        JsonObject dbConfig = config.getJsonObject(dsName);
        if (dbConfig == null) {
            throw new RuntimeException("没有找到指定的数据源");
        }
        dbClient = JDBCClient.createShared(vertx, dbConfig);
    }

    public JdbcUtils(Vertx vertx) {
        this(vertx, "default");
    }

    public JDBCClient getDbClient() {
        return dbClient;
    }


    public static void main(String[] args) {
        JDBCClient jdbcClient = new JdbcUtils(Vertx.vertx()).getDbClient();

        String sql = "show tables";

        jdbcClient.query(sql, res->{
            System.out.println(res.result().getRows());
        });
    }

}