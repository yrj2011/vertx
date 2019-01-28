package stu.vertx.core.tcpserver;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetServer;

/**
 * 创建一个TCP服务
 * 
 * @author lenovo
 *
 */
public class TcpServer extends AbstractVerticle {

	@Override
	public void start() throws Exception {

		// 创建TCP服务器
		NetServer server = vertx.createNetServer();

		// 处理连接请求
		server.connectHandler(socket -> {

			socket.handler(buffer -> {
				// 在这里应该解析报文，封装为协议对象，并找到响应的处理类，得到处理结果，并响应
				System.out.println("接收到的数据为：" + buffer.toString());

				// 按照协议响应给客户端
				socket.write(Buffer.buffer("Server Received"));
			});

			// 监听客户端的退出连接
			socket.closeHandler(close -> {
				System.out.println("客户端退出连接");
			});

		});

		// 监听端口
		server.listen(5555, res -> {
			if (res.succeeded()) {
				System.out.println("服务器启动成功");
			}
		});
	}

	public static void main(String[] args) {
		Vertx.vertx().deployVerticle(new TcpServer());
	}
}
