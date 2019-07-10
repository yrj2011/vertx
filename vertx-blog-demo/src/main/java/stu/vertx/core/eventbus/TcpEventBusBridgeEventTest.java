
package stu.vertx.core.eventbus;

import io.vertx.core.Vertx;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;
import io.vertx.core.net.*;
import io.vertx.ext.bridge.BridgeOptions;
import io.vertx.ext.bridge.PermittedOptions;
import io.vertx.ext.eventbus.bridge.tcp.TcpEventBusBridge;
import io.vertx.ext.eventbus.bridge.tcp.impl.protocol.FrameHelper;

import javax.net.ssl.SSLPeerUnverifiedException;
import javax.security.cert.X509Certificate;

public class TcpEventBusBridgeEventTest {

    private Vertx vertx;

    public static void main(String args[]) throws InterruptedException {
        Thread.sleep(1000 * 30);
        TcpEventBusBridgeEventTest test = new TcpEventBusBridgeEventTest();
        test.before();
        Thread.sleep(1000 * 30);
        test.testSendVoidMessage();
        Thread.sleep(1000 * 30);
        test.after();
    }

    public void before() {
        vertx = Vertx.vertx();

        vertx.eventBus().consumer("hello", (Message<JsonObject> msg) ->
                msg.reply(new JsonObject().put("value", "Hello " + msg.body().getString("value"))));

        vertx.eventBus().consumer("echo", (Message<JsonObject> msg) -> msg.reply(msg.body()));

        vertx.setPeriodic(1000, __ -> vertx.eventBus().send("ping", new JsonObject().put("value", "hi")));

        TcpEventBusBridge bridge = TcpEventBusBridge.create(
                vertx,
                new BridgeOptions()
                        .addInboundPermitted(new PermittedOptions().setAddress("hello"))
                        .addInboundPermitted(new PermittedOptions().setAddress("echo"))
                        .addInboundPermitted(new PermittedOptions().setAddress("test"))
                        .addOutboundPermitted(new PermittedOptions().setAddress("echo"))
                        .addOutboundPermitted(new PermittedOptions().setAddress("ping")),
                new NetServerOptions()
                        .setSsl(false).setTrustStoreOptions(new JksOptions().setPath("server.truststore").setPassword("wibble"))
                        .setKeyStoreOptions(new JksOptions().setPath("server.keystore").setPassword("wibble")),
                be -> {

                    System.out.println("Handled a bridge event " + be.getRawMessage());
                    if (be.socket().isSsl()) {
                        try {
                            for (X509Certificate c : be.socket().peerCertificateChain()) {
                                System.out.println(c.getSubjectDN().toString());
                            }

                        } catch (SSLPeerUnverifiedException e) {
                            System.out.println("Caught SSLPeerUnverifiedException when processing peerCertificateChain ");
                            //@fixme should have a test truststore/keystore that validates, the ones i made still throw this
                        }
                    }

                    be.complete(true);

                });

        bridge.listen(7000, res -> {
            if (res.succeeded()) {
                System.out.println("Event Bus Listen 7000");
            } else {
                System.out.println("Event Bus Fail");
            }
        });
    }

    public void after() {
        vertx.close();
    }

    public void testSendVoidMessage() {
        // Send a request and get a response
        NetClient client = vertx.createNetClient(new NetClientOptions().setSsl(false).setTrustAll(true)
                .setKeyStoreOptions(new JksOptions().setPath("client.keystore").setPassword("wibble")));

        vertx.eventBus().consumer("test", (Message<JsonObject> msg) -> {
            client.close();
        });

        client.connect(7000, "localhost", conn -> {

            NetSocket socket = conn.result();
            FrameHelper.sendFrame("send", "test", new JsonObject().put("value", "vert.x"), socket);
        });
    }

}
