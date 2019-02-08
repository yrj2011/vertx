package stu.vertx.ch2;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.jdbc.JDBCClient;

public class JdbcUtils {

	private JDBCClient dbClient;

	public JdbcUtils(Vertx vertx) {
		JsonObject dbConfig = new JsonObject();
		dbConfig.put("url", "jdbc:mysql://192.168.40.66:3306/api");
		dbConfig.put("driver_class", "com.mysql.jdbc.Driver");
		dbConfig.put("user", "zookeeper");
		dbConfig.put("password", "Zabbix@Zookeeper");

		dbClient = JDBCClient.createShared(vertx, dbConfig);
	}

	public JDBCClient getDbClient() {
		return dbClient;
	}

}
