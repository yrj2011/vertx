package stu.vertx.core.fs;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.file.FileSystem;

/**
 * 写数据到文件
 * 
 * @author lenovo
 *
 */
public class WriteFile extends AbstractVerticle {

	@Override
	public void start() throws Exception {
		FileSystem fs = vertx.fileSystem();
		fs.writeFile("hello.txt", Buffer.buffer("Hello"), wf -> {
			if(wf.succeeded()) {
				System.out.println("文件写入成功");
			}
		});
	}

	public static void main(String[] args) {
		Vertx.vertx().deployVerticle(new WriteFile());
	}

}
