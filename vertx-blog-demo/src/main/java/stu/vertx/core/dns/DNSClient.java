package stu.vertx.core.dns;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.dns.DnsClient;

public class DNSClient extends AbstractVerticle {

	@Override
	public void start() throws Exception {

		DnsClient client = vertx.createDnsClient(53, "202.102.128.68");
		client.lookup4("baidu.com", ar -> {
			if (ar.succeeded()) {
				System.out.println(ar.result());
			} else {
				System.out.println("Failed to resolve entry" + ar.cause());
			}
		});

	}
	
	public static void main(String[] args) {
		Vertx.vertx().deployVerticle(new DNSClient());
	}

}
