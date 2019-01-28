package stu.vertx.core.cache;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.shareddata.LocalMap;
import io.vertx.core.shareddata.SharedData;

/**
 * Vertx使用缓存使用
 * 
 * @author lenovo
 *
 */
public class ShardVerticle extends AbstractVerticle {

	private SharedData shardData;

	/**
	 * start方法只会在部署时执行一次，因此这里为了模拟多次请求，使用了while循环
	 */
	@Override
	public void start() throws Exception {

		// 获取到shardData
		shardData = vertx.sharedData();

		while (true) {

			LocalMap<String, String> cache = shardData.getLocalMap("cache");
			if (cache != null && cache.get("hello") != null) {
				System.out.println("从缓存中取出数据：" + cache.get("hello"));
			} else {
				System.out.println("第一次请求，没有从缓存中渠道数据");
				cache.put("hello", "world");
			}

			Thread.sleep(1000);
		}
	}

	public static void main(String[] args) throws Exception {
		Vertx vertx = Vertx.vertx();
		vertx.deployVerticle(new ShardVerticle());
	}

}
