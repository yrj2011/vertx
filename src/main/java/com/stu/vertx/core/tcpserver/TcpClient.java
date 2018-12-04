package com.stu.vertx.core.tcpserver;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetClient;
import io.vertx.core.net.NetSocket;

/**
 * TCP的客户端
 * 
 * @author lenovo
 *
 */
public class TcpClient extends AbstractVerticle {

	@Override
	public void start() throws Exception {

		// 创建一个TCP客户端
		NetClient client = vertx.createNetClient();

		// 连接服务器
		client.connect(5555, "localhost", conn -> {
			if (conn.succeeded()) {
				NetSocket s = conn.result();
				// 向服务器写数据
				s.write(Buffer.buffer("hello"));
				
				// 读取服务器的响应数据
				s.handler(buffer -> {
					System.out.println(buffer.toString());
				});
			} else {
				System.out.println("连接服务器异常");
			}
		});

	}

	public static void main(String[] args) {
		Vertx.vertx().deployVerticle(new TcpClient());
	}
}
