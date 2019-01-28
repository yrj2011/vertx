package stu.vertx.jdbc;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.ext.jdbc.JDBCClient;

/**
 * 测试数据库
 * 
 * @author lenovo
 *
 */
public class JdbcTestVerticle extends AbstractVerticle {

	@Override
	public void start() throws Exception {

		JDBCClient jdbcClient = new JdbcUtils(vertx).getDbClient();
		String sql = "select * from t_user";
		jdbcClient.query(sql, qryRes->{
			System.out.println(qryRes.result().getRows());
		});
	
	}
	
	public static void main(String[] args) {
		Vertx vertx = Vertx.vertx();
		vertx.deployVerticle(new JdbcTestVerticle());
	}
	
}
