package com.stu.vertx.core.fs;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.file.FileSystem;

/**
 * 拷贝文件
 * 
 * @author lenovo
 *
 */
public class CopyFile extends AbstractVerticle {

	@Override
	public void start() throws Exception {
		FileSystem fs = vertx.fileSystem();
		fs.copy("README.md", "text.txt", cf -> {
			if(cf.succeeded()) {
				System.out.println("文件拷贝成功");
			}
		});
	}

	public static void main(String[] args) {
		Vertx.vertx().deployVerticle(new CopyFile());
	}

}
