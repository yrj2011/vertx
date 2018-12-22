package com.stu.vertx.auth.utils;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.ext.auth.VertxContextPRNG;

/**
 * 生成伪随机数
 * 
 * @author lenovo
 *
 */
public class Random extends AbstractVerticle {

	@Override
	public void start() throws Exception {

		String token = VertxContextPRNG.current(vertx).nextString(32);
		int randomInt = VertxContextPRNG.current(vertx).nextInt();

		System.out.println(token);
		System.out.println(randomInt);
	}

	public static void main(String[] args) {
		Vertx.vertx().deployVerticle(new Random());
	}
}
