package com.stu.vertx.core.tcpserver;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetServer;

/**
 * 一个简单的TCP的时间服务器，当客户端连接成功之后，接收客户端的请求，如果请求字符串为QUERY TIME
 * ORDER，则返回当前时间戳。如果收到其他请求，则返回BAD ORDER
 * 
 * @author lenovo
 *
 */
public class TimeServer extends AbstractVerticle {

	@Override
	public void start() throws Exception {
		NetServer server = vertx.createNetServer();

		server.connectHandler(socket -> {
			System.out.println("客户端连接成功，连接的IP地址为：" + socket.remoteAddress().host());
			socket.handler(buffer -> {
				System.out.println("接收到的客户端请求报文为：" + buffer.toString());
				if ("QUERY TIME ORDER".equals(buffer.toString())) {
					socket.write(System.currentTimeMillis() + "");
				} else {
					socket.write(Buffer.buffer("BAD ORDER"));
				}
			});
		});
		server.listen(123);
	}

	public static void main(String[] args) {
		Vertx.vertx().deployVerticle(new TimeServer());
	}

}
