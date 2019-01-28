package stu.vertx.core.tcpserver;

import java.text.SimpleDateFormat;
import java.util.Date;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetClient;
import io.vertx.core.net.NetSocket;

/**
 * TCP时间客户端模拟
 * 
 * @author lenovo
 *
 */
public class TimeClient extends AbstractVerticle {

	@Override
	public void start() throws Exception {
		NetClient client = vertx.createNetClient();

		client.connect(123, "localhost", socket -> {
			if (socket.succeeded()) {
				NetSocket s = socket.result();
				s.write(Buffer.buffer("QUERY TIME ORDER"));
				s.handler(buffer -> {
					System.out.println("接收到的时间为：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
							.format(new Date(Long.parseLong(buffer.toString()))));
				});
			} else {
				System.out.println("连接服务器失败");
			}
		});
	}

	public static void main(String[] args) {
		Vertx.vertx().deployVerticle(new TimeClient());
	}
}
