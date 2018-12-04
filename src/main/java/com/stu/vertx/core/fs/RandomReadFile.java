package com.stu.vertx.core.fs;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.file.AsyncFile;
import io.vertx.core.file.FileSystem;
import io.vertx.core.file.OpenOptions;

/**
 * 读取文件-- 类似于JDK原生的RandomAccessFile
 * 
 * @author lenovo
 *
 */
public class RandomReadFile extends AbstractVerticle {

	@Override
	public void start() throws Exception {
		FileSystem fs = vertx.fileSystem();
		fs.open("test.md", new OpenOptions(), o -> {
			if (o.succeeded()) {
				AsyncFile af = o.result();
				// 跳过20个字节写
				af.write(Buffer.buffer("hello"), 20, w -> {
					if (w.succeeded()) {
						System.out.println("写入成功");
					}
				});
			}
		});
	}

	public static void main(String[] args) {
		Vertx.vertx().deployVerticle(new RandomReadFile());
	}

}
