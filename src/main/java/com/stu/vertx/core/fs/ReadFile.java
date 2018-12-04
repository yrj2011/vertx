package com.stu.vertx.core.fs;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.file.FileSystem;

/**
 * 读取文件
 * 
 * Vertx提供了读写文件的api，这里不推荐使用jdk原生的api来读写文件的原因是Vertx是异步的，如果使用原生的APi来读写文件,会导致超时，会导致Vertx阻塞，严重影响性能
 * 
 * @author lenovo
 *
 */
public class ReadFile extends AbstractVerticle {

	@Override
	public void start() throws Exception {
		FileSystem fs = vertx.fileSystem();
		fs.readFile("README.md", rf -> {
			String f = rf.result().toString();
			System.out.println("文件的内容为：" + f);
		});
	}

	public static void main(String[] args) {
		Vertx.vertx().deployVerticle(new ReadFile());
	}

}
