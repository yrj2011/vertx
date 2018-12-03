package com.stu.vertx.core.timer;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;

/**
 * 定时功能
 * 
 * @author lenovo
 *
 */
public class VertxTimer extends AbstractVerticle {

	@Override
	public void start() throws Exception {

		// 计时执行一次
		long id = vertx.setTimer(3000, d -> {
			System.out.println("时间到");
		});

		// 设置间隔时间，每隔一段时间执行一次
		long id2 = vertx.setPeriodic(1000, aaa -> {
			System.out.println("时间又到了");
		});
		
		// 取消定时
		vertx.cancelTimer(id);
		vertx.cancelTimer(id2);
		
	}

	public static void main(String[] args) {
		Vertx vertx = Vertx.vertx();
		vertx.deployVerticle(new VertxTimer());
	}

}
