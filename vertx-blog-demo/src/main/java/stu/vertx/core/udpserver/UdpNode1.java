package stu.vertx.core.udpserver;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.datagram.DatagramSocket;
import io.vertx.core.datagram.DatagramSocketOptions;

/**
 * 接收数据
 * 
 * @author lenovo
 *
 */
public class UdpNode1 extends AbstractVerticle {

	@Override
	public void start() throws Exception {
		DatagramSocket socket = vertx.createDatagramSocket(new DatagramSocketOptions());

		socket.listen(1234, "0.0.0.0", asyncResult -> {
			if (asyncResult.succeeded()) {
				socket.handler(packet -> {
					System.out.println("接收到的数据为：" + packet.data());
				});
			} else {
				System.out.println("Listen failed" + asyncResult.cause());
			}
		});
	}

	public static void main(String[] args) {
		Vertx.vertx().deployVerticle(new UdpNode1());
	}
}
