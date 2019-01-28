package stu.vertx.core.udpserver;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.datagram.DatagramSocket;
import io.vertx.core.datagram.DatagramSocketOptions;

/**
 * 发送数据
 * 
 * @author lenovo
 *
 */
public class UdpNode2 extends AbstractVerticle {

	@Override
	public void start() throws Exception {
		DatagramSocket socket = vertx.createDatagramSocket(new DatagramSocketOptions());

		Buffer buffer = Buffer.buffer("content");
		// Send a Buffer
		socket.send(buffer, 1234, "localhost", asyncResult -> {
			System.out.println("Send succeeded? " + asyncResult.succeeded());
		});
		// Send a String
//		socket.send("A string used as content", 1234, "10.0.0.1", asyncResult -> {
//			System.out.println("Send succeeded? " + asyncResult.succeeded());
//		});
	}

	public static void main(String[] args) {
		Vertx.vertx().deployVerticle(new UdpNode2());
	}
}
