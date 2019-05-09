package com.kingh.vertx.plugin.db.utils;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.jdbc.JDBCClient;

public class JdbcUtils {

	private JDBCClient dbClient;

	public JdbcUtils(Vertx vertx) {
		JsonObject dbConfig = new JsonObject();
		dbConfig.put("url", "jdbc:mysql://localhost:3306/vertx?serverTimezone=UTC&characterEncoding=utf8");
		dbConfig.put("driver_class", "com.mysql.cj.jdbc.Driver");
		dbConfig.put("user", "my");
		dbConfig.put("password", "m123456.");

		dbClient = JDBCClient.createShared(vertx, dbConfig);
	}

	public JDBCClient getDbClient() {
		return dbClient;
	}

}
