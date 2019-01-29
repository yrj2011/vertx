package stu.vertx.ch1;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * 使用Netty实现一个简单的HTTP服务，当浏览器访问8080端口时，给浏览器返回Hello World！
 *
 * @author 孔冠华
 */
public class NettyHttpServer {

    public static void main(String[] args) throws Exception {
        // 创建服务，并指定运行端口
        new NettyHttpServer().run(8080);
    }

    /**
     * 服务端运行的核心方法
     *
     * @param port 服务器监听的端口号
     * @throws Exception
     */
    private void run(int port) throws Exception {

        // 创建两个线程组
        EventLoopGroup boosGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap b = new ServerBootstrap();
        b.group(boosGroup, workerGroup).channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        // 指定HTTP协议的编解码器
                        ch.pipeline().addLast("http-decoder", new HttpRequestDecoder());
                        ch.pipeline().addLast("http-aggregator", new HttpObjectAggregator(999999999));
                        ch.pipeline().addLast("http-encoder", new HttpResponseEncoder());
                        ch.pipeline().addLast("http-chunked", new ChunkedWriteHandler());
                        // 指定HTTP处理器
                        ch.pipeline().addLast("My-Server", new HttpServerHandle());
                    }
                });
        // 绑定端口，并接收请求
        ChannelFuture future = b.bind(port);
        future.channel().closeFuture().sync();
    }

    /**
     * HTTP请求处理器
     *
     * @author 孔冠华
     */
    public static class HttpServerHandle extends SimpleChannelInboundHandler<FullHttpRequest> {

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {
            // 创建相应对象
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);

            // 相应数据
            response.content().writeBytes("Hello World!".getBytes());

            // 相应给客户端
            ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
        }
    }

}
